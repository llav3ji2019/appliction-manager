package com.llav3ji2019.application.applicationmanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Система управление заявками",
                description = "Спроектировать и разработать систему регистрации и обработки пользовательских заявок.", version = "1.0.0",
                contact = @Contact(
                        name = "Emelyanov Pavel",
                        email = "pavel@emelyanov.dev",
                        url = "https://pavel.emelyanov.dev"
                )
        )
)
public class OpenApiConfiguration {
}
