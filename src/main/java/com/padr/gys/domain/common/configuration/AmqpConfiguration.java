package com.padr.gys.domain.common.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
    
    @Bean
    public Queue emailQueue() {
        return new Queue("carrier.email.send.queue", true);
    }
}
