package com.example.test.service;

import com.example.test.dto.BookResponseDto;
import com.example.test.mapper.BookMapper;
import com.example.test.model.Author;
import com.example.test.model.Book;
import com.example.test.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    public List<BookResponseDto> getAll(int page,int size,String userName) {
        Pageable pageable= PageRequest.of(page,size);
        List<Book> books=bookRepository.getByAll(pageable,userName).getContent();
        List<BookResponseDto> bookdto=books.stream().map(bookMapper::mapEntityToDto).toList();
        return bookdto;
    }
}
