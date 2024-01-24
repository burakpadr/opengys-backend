package com.padr.gys.infra.inbound.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padr.gys.domain.common.constant.AllowedFileType;
import com.padr.gys.infra.inbound.common.response.ExceptionResponse;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MultipartFileFilter implements Filter {

    private final MessageSource messageSource;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String contentType = httpServletRequest.getContentType();

        if (Objects.nonNull(contentType) && contentType.startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
            for (Part part : httpServletRequest.getParts()) {
                if (part.getSize() == 0) {
                    prepareResponseForEmptyFile(httpServletResponse);

                    return;
                }

                boolean isAllowedType = Arrays.asList(AllowedFileType.values())
                        .stream()
                        .anyMatch(allowedFileType -> part.getContentType()
                                .contains(allowedFileType.getExtension()));

                if (!isAllowedType) {
                    prepareResponseForUnsupportedFile(httpServletResponse);

                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    public void prepareResponseForEmptyFile(HttpServletResponse httpServletResponse) {
        try {
            ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                    .code(BadRequest.class.getName())
                    .message(messageSource.getMessage("independent.file-cannot-be-empty", null,
                            LocaleContextHolder.getLocale()))
                    .build();

            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.getOutputStream()
                    .write(new ObjectMapper().writeValueAsString(exceptionResponse).getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void prepareResponseForUnsupportedFile(HttpServletResponse httpServletResponse) {
        try {
            ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                    .code(BadRequest.class.getName())
                    .message(messageSource.getMessage("independent.unsupported-file-type", null,
                            LocaleContextHolder.getLocale()))
                    .build();

            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.getOutputStream()
                    .write(new ObjectMapper().writeValueAsString(exceptionResponse).getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
