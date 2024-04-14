package com.padr.gys.infra.outbound.client.tcmb.port;

import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;

public interface TcmbClientPort {

    <T, R> ResponseEntity<R> send(String tcmbEndpoint, HttpMethod httpMethod, T request,
            TypeReference<R> responsTypeReference, Map<String, String> requestParameters);
}
