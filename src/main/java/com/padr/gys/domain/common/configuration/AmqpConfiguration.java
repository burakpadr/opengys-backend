package com.padr.gys.domain.common.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.padr.gys.domain.common.constant.AmqpQueueName;

@Configuration
public class AmqpConfiguration {
    
    @Bean
    public Queue emailQueue() {
        return new Queue(AmqpQueueName.EMAIL_QUEUE_NAME, true);
    }
}
