package com.example.test.controller;

import com.example.test.dto.BookResponseDto;
import com.example.test.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/author")
@AllArgsConstructor
public class AuthorController {
    private  final AuthorService authorService;
    @GetMapping("/get-all")
    public List<BookResponseDto> getAll(@RequestParam int page, @RequestParam int size, Principal principal){
        String userName= principal.getName();
        return authorService.getAll(page,size,userName);
    }
}
