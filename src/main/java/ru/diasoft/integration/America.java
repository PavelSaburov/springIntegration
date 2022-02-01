package ru.diasoft.integration;

import java.util.Collection;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.diasoft.integration.domain.Oil;

@MessagingGateway
public interface America {
    @Gateway(requestChannel = "democracyChannel", replyChannel = "oilChannel")
    Collection<Oil> sendDemocracy(Collection<String> countries);
}
