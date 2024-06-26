package com.padr.gys.infra.inbound.rest.payment.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.infra.inbound.rest.payment.model.request.CreatePaymentDeclarationRequest;
import com.padr.gys.infra.inbound.rest.payment.model.request.FindPaymentDeclarationRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.PaymentDeclarationResponse;
import com.padr.gys.infra.inbound.rest.payment.usecase.CreatePaymentDeclarationUseCase;
import com.padr.gys.infra.inbound.rest.payment.usecase.FindPaymentDeclarationByIdUseCase;
import com.padr.gys.infra.inbound.rest.payment.usecase.FindPaymentDeclarationsByFilterUseCase;
import com.padr.gys.infra.inbound.rest.payment.usecase.ApprovePaymentDeclarationUseCase;
import com.padr.gys.infra.inbound.rest.payment.usecase.RejectPaymentDeclarationUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/payment-declarations")
@RequiredArgsConstructor
public class PaymentDeclarationAdapter {

    private final CreatePaymentDeclarationUseCase createPaymentDeclarationUseCase;
    private final FindPaymentDeclarationByIdUseCase findPaymentDeclarationByIdUseCase;
    private final FindPaymentDeclarationsByFilterUseCase findPaymentDeclarationsByFilterUseCase;
    private final ApprovePaymentDeclarationUseCase approvePaymentDeclarationUseCase;
    private final RejectPaymentDeclarationUseCase rejectPaymentDeclarationUseCase;

    @PostMapping
    public PaymentDeclarationResponse create(@RequestPart MultipartFile receipt,
            @Valid CreatePaymentDeclarationRequest request) {

        return createPaymentDeclarationUseCase.execute(receipt, request);
    }

    @PostMapping("/find-by-filter")
    public Page<PaymentDeclarationResponse> findByFilter(Pageable pageable,
            @Valid @RequestBody FindPaymentDeclarationRequest request) {

        return findPaymentDeclarationsByFilterUseCase.execute(pageable, request);
    }

    @GetMapping("/{id}")
    public PaymentDeclarationResponse findById(@PathVariable Long id) {
        return findPaymentDeclarationByIdUseCase.execute(id);
    }

    @PutMapping("/{id}/approve")
    public void approve(@PathVariable Long id) {
        
        approvePaymentDeclarationUseCase.execute(id);
    }

    @PutMapping("/{id}/reject")
    public void reject(@PathVariable Long id) {
        rejectPaymentDeclarationUseCase.execute(id);
    }
}