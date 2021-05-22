package com.tw.bootcamp.bookshop.book.controller;

import com.tw.bootcamp.bookshop.book.domain.Book;
import com.tw.bootcamp.bookshop.book.service.BookService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Get
    List<Book> list() {
        return bookService.fetchAll();
    }
}
