package com.example.test.mapper;

import com.example.test.dto.BookResponseDto;
import com.example.test.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookResponseDto mapEntityToDto(Book book){
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getName(),
                book.getAuthor().getEmail()

        );
    }
}
