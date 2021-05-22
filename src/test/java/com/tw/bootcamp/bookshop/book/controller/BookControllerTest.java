package com.tw.bootcamp.bookshop.book.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.bootcamp.bookshop.entity.tables.records.BooksRecord;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Optional;

import static com.tw.bootcamp.bookshop.entity.Tables.BOOKS;
import static io.micronaut.http.HttpRequest.GET;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class BookControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    private DSLContext ctx;

    @Test
    void shouldListAllBooksWhenPresent() throws JsonProcessingException {
        BooksRecord cityOfBones = ctx.newRecord(BOOKS)
                                     .setName("City of Bones (The Mortal Instruments, #1)")
                                     .setAuthorName("Cassandra Clare")
                                     .setPrice(1461);

        BooksRecord eclipse = ctx.newRecord(BOOKS)
                                 .setName("Eclipse (Twilight, #3)")
                                 .setAuthorName("Stephenie Meyer")
                                 .setPrice(2335);


        BooksRecord eragon = ctx.newRecord(BOOKS)
                                .setName("Eragon (The Inheritance Cycle, #1)")
                                .setAuthorName("Christopher Paolini")
                                .setPrice(2098);
        cityOfBones.store();
        eclipse.store();
        eragon.store();

        HttpResponse<String> response = client.toBlocking().exchange(GET("/books"), String.class);

        assertThat(response.getStatus().getCode()).isEqualTo(200);

        Optional<String> mayBeResponse = response.getBody();
        assertThat(mayBeResponse.isPresent()).isTrue();
        assertThat(objectMapper.readTree(mayBeResponse.get())).isEqualTo(objectMapper.readTree(
                """
                        [
                            {
                              "name": "Eclipse (Twilight, #3)",
                              "authorName": "Stephenie Meyer",
                              "price": 2335
                            },
                            {
                              "name": "Eragon (The Inheritance Cycle, #1)",
                              "authorName": "Christopher Paolini",
                              "price": 2098
                            },
                            {
                              "name": "City of Bones (The Mortal Instruments, #1)",
                              "authorName": "Cassandra Clare",
                              "price": 1461
                            }
                          ]
                             """.stripIndent()
                )
        );

    }

}
