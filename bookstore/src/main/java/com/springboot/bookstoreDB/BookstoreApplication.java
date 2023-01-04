package com.springboot.bookstoreDB;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book Store API"))
public class BookstoreApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BookstoreApplication.class, args);
    }
}
