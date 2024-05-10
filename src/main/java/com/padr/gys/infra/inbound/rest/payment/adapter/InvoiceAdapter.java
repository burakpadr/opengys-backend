package com.padr.gys.infra.inbound.rest.payment.adapter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.rest.payment.model.request.CreateInvoiceRequest;
import com.padr.gys.infra.inbound.rest.payment.model.request.FindInvoicesByFilterRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.InvoiceResponse;
import com.padr.gys.infra.inbound.rest.payment.usecase.CreateInvoiceUseCase;
import com.padr.gys.infra.inbound.rest.payment.usecase.FindInvoiceByIdUseCase;
import com.padr.gys.infra.inbound.rest.payment.usecase.FindInvoicesByFilterAsList;
import com.padr.gys.infra.inbound.rest.payment.usecase.FindMatchableInvoicesUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceAdapter {

    private final FindInvoicesByFilterAsList findInvoicesByFilterAsList;
    private final FindInvoiceByIdUseCase findInvoiceByIdUseCase;
    private final CreateInvoiceUseCase createInvoiceUseCase;
    private final FindMatchableInvoicesUseCase findMatchableInvoicesUseCase;

    @PostMapping
    public InvoiceResponse create(@Valid @RequestBody CreateInvoiceRequest request) {
        return createInvoiceUseCase.execute(request);
    }

    @PostMapping("/find-by-filter-as-list")
    public List<InvoiceResponse> findByFilterAsList(@Valid @RequestBody FindInvoicesByFilterRequest request) {
        return findInvoicesByFilterAsList.execute(request);
    }

    @PostMapping("/find-matchable-invoices")
    public List<InvoiceResponse> findMatchableInvoices(@Valid @RequestBody FindInvoicesByFilterRequest request) {
        return findMatchableInvoicesUseCase.execute(request);
    }

    @GetMapping("/{id}")
    public InvoiceResponse findById(@PathVariable Long id) {
        return findInvoiceByIdUseCase.execute(id);
    }
}
