package com.padr.gys.infra.inbound.rest.attribute.usecase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.attribute.port.AttributeServicePort;
import com.padr.gys.infra.inbound.rest.attribute.model.response.AttributeResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllAttributeUseCase {

    private final AttributeServicePort attributeServicePort;

    public Page<AttributeResponse> execute(Pageable pageable) {
        Page<Attribute> attributes = attributeServicePort.find(pageable);

        List<AttributeResponse> attributeResponses = attributes.getContent().stream().map(AttributeResponse::of)
                .toList();

        return new PageImpl<>(attributeResponses, pageable, attributes.getTotalElements());
    }
}
