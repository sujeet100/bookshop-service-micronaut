package com.tw.bootcamp.bookshop.book.service;

import com.tw.bootcamp.bookshop.book.domain.Book;
import com.tw.bootcamp.bookshop.book.repository.BookRepository;

import javax.inject.Singleton;
import java.util.List;

import static com.tw.bootcamp.bookshop.book.domain.Book.booksRecordToBookMapper;
import static java.util.stream.Collectors.toList;

@Singleton
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> fetchAll() {
        return bookRepository.fetchAll().stream().map(booksRecordToBookMapper).collect(toList());
    }
}
