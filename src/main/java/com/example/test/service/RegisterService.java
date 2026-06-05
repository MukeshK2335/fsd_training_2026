package com.example.test.service;

import com.example.test.dto.RegisterReqDto;
import com.example.test.enums.RoleType;
import com.example.test.exception.RoleNotFoundException;
import com.example.test.mapper.EmployerMapper;
import com.example.test.mapper.JobSeekerMapper;
import com.example.test.mapper.UserMapper;
import com.example.test.model.Employer;
import com.example.test.model.JobSeeker;
import com.example.test.model.User;
import com.example.test.repository.EmployerRepository;
import com.example.test.repository.JobSeekerRepository;
import com.example.test.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmployerMapper employerMapper;
    private final EmployerRepository employerRepository;
    private final JobSeekerMapper jobSeekerMapper;
    private final JobSeekerRepository jobSeekerRepository;
    public void add(@Valid RegisterReqDto dto) {

        RoleType role = RoleType.valueOf(dto.role());
        User user=userMapper.mapDtoToEntity(dto);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(dto.password()));
        user=userRepository.save(user);

        if(role==RoleType.EMPLOYER){
            Employer employer=employerMapper.mapDtoToEntity(dto);
            employer.setUser(user);
            employerRepository.save(employer);
        } else if (role==RoleType.SEEKER) {
            JobSeeker jobSeeker=jobSeekerMapper.mapDtoToEntity(dto);
            jobSeeker.setUser(user);
            jobSeekerRepository.save(jobSeeker);
        }
        else {
            throw new RoleNotFoundException("Invalid Role");
        }
    }
}
