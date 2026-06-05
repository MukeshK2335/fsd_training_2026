package com.example.test.repository;

import com.example.test.model.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker,Integer> {


    @Query("""
               select j from JobSeeker j
               where j.user.username=?1
               """)
    Optional<JobSeeker> getByUsername(String userName);
}
