package com.example.test.repository;

import com.example.test.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query("""
                 
                 select b from Book b
                 where b.author.user.username=?1
                 """)
    Page<Book> getByAll(Pageable pageable, String userName);
}
