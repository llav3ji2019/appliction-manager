package com.llav3ji2019.application.applicationmanager.core.phone.client;

import com.kuliginstepan.dadata.client.domain.organization.Phone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;
import java.util.Optional;

@Component
public class PhoneValidationClientService {
    private static final String JWT_HEADER_PREFIX = "Token ";
    private static final String AUTH_HEADER = "Authorization";
    private static final String SECRET_HEADER = "X-Secret";

    private final WebClient webClient;

    @Value("${dadata.client.phone-end-point}")
    private String phoneEndPoint;

    @Value("${dadata.client.token}")
    private String token;

    @Value("${dadata.client.secret-key}")
    private String secret;

    public PhoneValidationClientService() {
        webClient = WebClient.create("https://cleaner.dadata.ru");
    }

    public Optional<Phone> sendValidationRequest(String phoneNumber) {
        return Objects.requireNonNull(
                webClient.post()
                        .uri(phoneEndPoint)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTH_HEADER, JWT_HEADER_PREFIX + token)
                        .header(SECRET_HEADER, secret)
                        .bodyValue(generateBody(phoneNumber))
                        .retrieve()
                        .bodyToFlux(Phone.class)
                        .collectList()
                        .block()
                )
                .stream()
                .findFirst();
    }

    private static String generateBody(String phoneNumber) {
        return "[ \"" + phoneNumber +  "\" ]";
    }
}
