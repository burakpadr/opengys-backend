package com.padr.gys.infra.outbound.sftp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SFTPClientConfiguration {

    // storage

    @Value(value = "${sftp.storage.host}")
    private String storageHost;

    @Value(value = "${sftp.storage.port}")
    private Integer storagePort;

    @Value(value = "${sftp.storage.username}")
    private String storageUsername;

    @Value(value = "${sftp.storage.password}")
    private String storagePassword;

    @Bean
    public SFTPClientPort storageSFTPClientPort() {
        return new SFTPClientImpl(storageHost, storagePort, storageUsername, storagePassword);
    }
}
