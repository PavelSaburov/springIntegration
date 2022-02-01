package ru.diasoft.integration.channel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;

@Configuration
public class ChannelDemocracy {
    @Bean
    public PollableChannel democracyChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public SubscribableChannel massMediaChannel() {
        SubscribableChannel channel = MessageChannels
                .publishSubscribe()
                .get();

        channel.subscribe( msg -> {
            System.out.println("Было выслано 5 тонн демократии в страну - " + msg.getPayload());
        });

        return channel;
    }

    @Bean
    public SubscribableChannel spyChannel() {
        SubscribableChannel channel = MessageChannels
                .publishSubscribe()
                .get();

        channel.subscribe( msg -> {
            System.out.println("ВНИМАНИЕ!!! Шпионы обнаружили нефть!");
            System.out.println(msg.getPayload());
        });

        return channel;
    }

    @Bean
    public SubscribableChannel oilChannel() {
        return MessageChannels
                .publishSubscribe()
                .get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 100 )
                .maxMessagesPerPoll( 2 ).get();
    }

    @Bean
    public IntegrationFlow cafeFlow() {
        return IntegrationFlows.from( "democracyChannel" )
                .split()
                .channel("massMediaChannel")
                .handle( "democracyServiceImpl", "sendDemocracyToCountry")
                .channel("spyChannel")
                .aggregate()
                .channel("oilChannel")
                .get();
    }
}
