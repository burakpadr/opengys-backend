package com.padr.gys.infra.inbound.rest.payment.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.rest.payment.model.request.CreateInvoiceRequest;
import com.padr.gys.infra.inbound.rest.payment.model.request.FindInvoicesRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.InvoiceResponse;
import com.padr.gys.infra.inbound.rest.payment.usecase.CreateInvoiceUseCase;
import com.padr.gys.infra.inbound.rest.payment.usecase.FindInvoiceByIdUseCase;
import com.padr.gys.infra.inbound.rest.payment.usecase.FindInvoicesUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceAdapter {

    private final FindInvoicesUseCase findInvoicesUseCase;
    private final FindInvoiceByIdUseCase findInvoiceByIdUseCase;
    private final CreateInvoiceUseCase createInvoiceUseCase;

    @PostMapping
    public InvoiceResponse create(@Valid @RequestBody CreateInvoiceRequest request) {
        return createInvoiceUseCase.execute(request);
    }

    @PostMapping("/as-page")
    public Page<InvoiceResponse> findWithFilter(Pageable pageable, @Valid @RequestBody FindInvoicesRequest request) {
        return findInvoicesUseCase.execute(pageable, request);
    }

    @GetMapping("/{id}")
    public InvoiceResponse findById(@PathVariable Long id) {
        return findInvoiceByIdUseCase.execute(id);
    }
}
