package com.padr.gys.infra.inbound.rest.advertplace.adapter;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.domain.carrier.constant.EmailTemplateCode;
import com.padr.gys.domain.carrier.entity.EmailTemplate;
import com.padr.gys.domain.carrier.port.EmailTemplateServicePort;
import com.padr.gys.infra.inbound.amqp.carrier.model.SendEmailTransactionModel;
import com.padr.gys.infra.inbound.rest.advertplace.model.AdvertPlaceRequest;
import com.padr.gys.infra.inbound.rest.advertplace.model.AdvertPlaceResponse;
import com.padr.gys.infra.inbound.rest.advertplace.usecase.CreateAdvertPlaceUseCase;
import com.padr.gys.infra.inbound.rest.advertplace.usecase.DeleteAdvertPlaceUseCase;
import com.padr.gys.infra.inbound.rest.advertplace.usecase.FindAdvertPlaceByIdUseCase;
import com.padr.gys.infra.inbound.rest.advertplace.usecase.FindAdvertPlacesAsListUseCase;
import com.padr.gys.infra.inbound.rest.advertplace.usecase.FindAdvertPlacesUseCase;
import com.padr.gys.infra.inbound.rest.advertplace.usecase.SearchAdvertPlacesUseCase;
import com.padr.gys.infra.inbound.rest.advertplace.usecase.UpdateAdvertPlaceUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/advert-places")
@RequiredArgsConstructor
public class AdvertPlaceAdapter {

    private final FindAdvertPlacesUseCase findAdvertPlacesUseCase;
    private final FindAdvertPlaceByIdUseCase findAdvertPlaceByIdUseCase;
    private final FindAdvertPlacesAsListUseCase findAdvertPlacesAsListUseCase;
    private final CreateAdvertPlaceUseCase createAdvertPlaceUseCase;
    private final UpdateAdvertPlaceUseCase updateAdvertPlaceUseCase;
    private final DeleteAdvertPlaceUseCase deleteAdvertPlaceUseCase;
    private final SearchAdvertPlacesUseCase searchAdvertPlacesUseCase;

    private final EmailTemplateServicePort emailTemplateServicePort;

    private final RabbitTemplate rabbitTemplate;

    @GetMapping
    public Page<AdvertPlaceResponse> findAll(Pageable pageable) {
        return findAdvertPlacesUseCase.execute(pageable);
    }

    @GetMapping("/search")
    public Page<AdvertPlaceResponse> search(@RequestParam("search") String searchTerm, Pageable pageable) {
        return searchAdvertPlacesUseCase.execute(searchTerm, pageable);
    }

    @GetMapping("/deneme")
    public void deneme() {
        SendEmailTransactionModel sendEmailTransactionModel = new SendEmailTransactionModel();

        EmailTemplate emailTemplate = emailTemplateServicePort.findByCode(EmailTemplateCode.PASSWORD_RESET_OTP);

        String[] to = {"burakpadr99@gmail.com"};

        sendEmailTransactionModel.setSubject(emailTemplate.getSubject());
        sendEmailTransactionModel.setTo(to);
        sendEmailTransactionModel.setContent(String.format(emailTemplate.getContent(), "-link-"));

        rabbitTemplate.convertAndSend("carrier.email.send.queue", sendEmailTransactionModel);
    }

    @GetMapping("/as-list")
    public List<AdvertPlaceResponse> findAll() {
        return findAdvertPlacesAsListUseCase.execute();
    }

    @PostMapping
    public AdvertPlaceResponse create(@Valid @RequestBody AdvertPlaceRequest request) {
        return createAdvertPlaceUseCase.execute(request);
    }

    @GetMapping("/{advertPlaceId}")
    public AdvertPlaceResponse findById(@PathVariable Long advertPlaceId) {
        return findAdvertPlaceByIdUseCase.execute(advertPlaceId);
    }

    @PutMapping("/{advertPlaceId}")
    public AdvertPlaceResponse update(@PathVariable Long advertPlaceId,
            @Valid @RequestBody AdvertPlaceRequest request) {
        return updateAdvertPlaceUseCase.execute(advertPlaceId, request);
    }

    @DeleteMapping("/{advertPlaceId}")
    public void delete(@PathVariable Long advertPlaceId) {
        deleteAdvertPlaceUseCase.execute(advertPlaceId);
    }
}
