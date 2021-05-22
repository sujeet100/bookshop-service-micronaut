package com.tw.bootcamp.bookshop.book.repository;

import com.tw.bootcamp.bookshop.entity.tables.records.BooksRecord;
import org.jooq.DSLContext;

import javax.inject.Singleton;
import java.util.List;

import static com.tw.bootcamp.bookshop.entity.Tables.BOOKS;

@Singleton
public class BookRepository {

    private final DSLContext ctx;

    public BookRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    public List<BooksRecord> fetchAll() {
        return ctx.selectFrom(BOOKS).orderBy(BOOKS.PRICE.desc()).fetch();
    }

}
