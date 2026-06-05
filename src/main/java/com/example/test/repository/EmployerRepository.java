package com.example.test.repository;

import com.example.test.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer,Integer> {

    @Query("""
                 select e from Employer e 
                 where e.user.username=?1
                 """)
    Optional<Employer> getByUsername(String username);
}
