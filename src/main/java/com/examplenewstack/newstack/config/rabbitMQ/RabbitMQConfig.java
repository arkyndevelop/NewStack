package com.examplenewstack.newstack.config.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Nome da Exchange: para onde as mensagens são enviadas.
    public static final String LOAN_EXCHANGE_NAME = "loan-events-exchange";

    // Nome da Fila: de onde o consumidor lerá as mensagens
    public static final String NOTIFICATION_QUEUE_NAME = "loan-notification-queue";

    // Routing Key: um "endereço" para a mensagem. A chave `loan.status.#`
    // significa que qualquer mensagem que comece com "loan.status." será roteada.
    public static final String LOAN_STATUS_ROUTING_KEY = "loan.status.#";


    @Bean
    public TopicExchange loanExchange(){
        return new TopicExchange(LOAN_EXCHANGE_NAME);
    }

    @Bean
    public Queue notificationQueue(){
        return new Queue(NOTIFICATION_QUEUE_NAME, true);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(LOAN_STATUS_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
