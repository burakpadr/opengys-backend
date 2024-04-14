package com.padr.gys.infra.outbound.client.tcmb.port;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.infra.outbound.common.property.IntegrationProperty;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class TcmbClientPortImpl implements TcmbClientPort {

    private final RestTemplate restTemplate;

    private final IntegrationProperty integrationProperty;

    private final MessageSource messageSource;

    public <T, R> ResponseEntity<R> send(String tcmbEndpoint, HttpMethod httpMethod, T request,
            TypeReference<R> responsTypeReference, Map<String, String> requestParameters) {
        HttpHeaders httpHeaders = prepareHttpHeaders();

        HttpEntity<?> httpEntity = null;

        if (request == null) {
            httpEntity = new HttpEntity<>(httpHeaders);
        } else {
            httpEntity = new HttpEntity<>(request, httpHeaders);
        }

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    integrationProperty.getTcmb().getKurlarBaseUrl() + tcmbEndpoint,
                    httpMethod,
                    httpEntity,
                    String.class,
                    requestParameters == null ? Map.of() : requestParameters);

            if (httpHeaders.getAccept().contains(MediaType.APPLICATION_XML)) {
                JavaTimeModule module = new JavaTimeModule();
                module.addDeserializer(LocalDateTime.class,
                        new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));

                XmlMapper xmlMapper = new XmlMapper();

                xmlMapper.registerModule(module);
                xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

                return (ResponseEntity<R>) ResponseEntity
                        .ok(xmlMapper.readValue(response.getBody(), responsTypeReference));
            } else {
                ObjectMapper objectMapper = new ObjectMapper();

                return (ResponseEntity<R>) ResponseEntity
                        .ok(objectMapper.readValue(response.getBody(), responsTypeReference));
            }
        } catch (HttpStatusCodeException e) {
            e.printStackTrace();

            throw new BusinessException(messageSource.getMessage("outbound.integration.tcmb.something-went-wrong",
                    null, LocaleContextHolder.getLocale()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private HttpHeaders prepareHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setAccept(List.of(MediaType.APPLICATION_XML));

        return httpHeaders;
    }
}
