package com.tw.bootcamp.bookshop.book.domain;

import com.tw.bootcamp.bookshop.entity.tables.records.BooksRecord;
import org.jooq.RecordMapper;

import java.util.function.Function;

public class Book {
    private String name;
    private String authorName;
    private Integer price;

    public Book(String name, String authorName, Integer price) {
        this.name = name;
        this.authorName = authorName;
        this.price = price;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public static Function<BooksRecord, Book> booksRecordToBookMapper =
            r -> new Book(r.getName(), r.getAuthorName(), r.getPrice());
}
