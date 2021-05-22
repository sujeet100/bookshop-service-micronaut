package com.tw.bootcamp.bookshop.book.service;

import com.tw.bootcamp.bookshop.book.domain.Book;
import com.tw.bootcamp.bookshop.book.repository.BookRepository;
import com.tw.bootcamp.bookshop.entity.tables.records.BooksRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.tw.bootcamp.bookshop.book.domain.Book.booksRecordToBookMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @Test
    void fetchAllShouldReturnAllBooks() {
        BookService bookService = new BookService(bookRepository);

        BooksRecord eragon = new BooksRecord()
                .setName("Eragon (The Inheritance Cycle, #1)")
                .setAuthorName("Christopher Paolini")
                .setPrice(2098);

        when(bookRepository.fetchAll()).thenReturn(List.of(eragon));

        List<Book> actualBooks = bookService.fetchAll();

        assertThat(actualBooks.size()).isEqualTo(1);
        assertThat(actualBooks.get(0)).usingRecursiveComparison().isEqualTo(booksRecordToBookMapper.apply(eragon));
    }

}
