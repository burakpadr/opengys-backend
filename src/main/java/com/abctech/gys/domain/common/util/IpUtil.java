package com.abctech.gys.domain.common.util;

import java.util.Objects;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public class IpUtil {
    
    private IpUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    @SuppressWarnings({"all"})
    public static String getClientIp() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (Objects.isNull(requestAttributes))
            return "0.0.0.0";

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        for (String header: IP_HEADER_CANDIDATES) {
            String ipList = request.getHeader(header);

            if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList))
                return ipList.split(",")[0];
        }

        return request.getRemoteAddr();
    }
}
