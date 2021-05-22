package com.tw.bootcamp.bookshop.book.repository;

import com.tw.bootcamp.bookshop.entity.tables.records.BooksRecord;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static com.tw.bootcamp.bookshop.entity.Tables.BOOKS;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class BookRepositoryTest {

    @Inject
    BookRepository bookRepository;

    @Inject
    private DSLContext ctx;

    @Test
    void fetchAllShouldReturnBooksInDescendingOrderOfPrice() {

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

        List<BooksRecord> booksRecords = bookRepository.fetchAll();

        assertThat(booksRecords.size()).isEqualTo(3);

        assertThat(booksRecords.get(0)).usingRecursiveComparison().isEqualTo(eclipse);
        assertThat(booksRecords.get(1)).usingRecursiveComparison().isEqualTo(eragon);
        assertThat(booksRecords.get(2)).usingRecursiveComparison().isEqualTo(cityOfBones);
    }

}
