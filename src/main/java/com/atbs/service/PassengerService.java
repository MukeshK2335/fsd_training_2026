package com.atbs.service;

import com.atbs.dto.*;
import com.atbs.enums.RoleType;
import com.atbs.exception.FlightNotFoundException;
import com.atbs.exception.FlightOwnerNotFoundException;
import com.atbs.exception.PassengerNotFoundException;
import com.atbs.exception.UserAlreadyExistException;
import com.atbs.mapper.PassengerAllMapper;
import com.atbs.mapper.PassengerMapper;
import com.atbs.mapper.UserMapper;
import com.atbs.model.Booking;
import com.atbs.model.Passenger;
import com.atbs.model.User;
import com.atbs.repository.BookingRepository;
import com.atbs.repository.PassengerRepository;
import com.atbs.repository.UserRepository;
import com.atbs.utility.FileUtility;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PassengerAllMapper passengerAllMapper;
    private final PassengerMapper passengerMapper;
    private final UserMapper userMapper;
    private final BookingRepository bookingRepository;
    private static final String NOT_FOUND = "Passenger Not Found";
    public Passenger getById(int passengerId) {
        return passengerRepository.findById(passengerId).orElseThrow(()->new FlightNotFoundException(NOT_FOUND));

    }

    public void add(@Valid PassengerReqDto dro) {
        Optional<?> existUser =userRepository.findByUsername(dro.username());
        if(existUser.isPresent()){
            throw new UserAlreadyExistException("Username already taken");
        }
        Passenger passenger=passengerMapper.mapDtoToEntity(dro);
        User user=userMapper.mapDtoToEntityPass(dro);
        user.setRole(RoleType.PASSENGER);
        user.setPassword(passwordEncoder.encode(dro.password()));
        user=userRepository.save(user);
        passenger.setUser(user);
        passengerRepository.save(passenger);

    }

    public PassengerPageResp getAll(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Passenger> pass=passengerRepository.findByUser_ActiveTrue(pageable);
        Page<PassengerAllDto> passengerAllDtos=pass.map(passengerAllMapper::entity2Dto);
        return passengerAllMapper.mapPageToDto(passengerAllDtos);

    }

    public PassenegrGetDto getByIdOwnerCheck(int id, String userName) {
        PassenegrGetDto passenegrGetDto=null;
        Passenger passenger=passengerRepository.findById(id).orElseThrow(()-> new PassengerNotFoundException("Invalid Passenger Id"));
        User user=(User) userRepository.findByUsername(userName).orElseThrow(()->new UsernameNotFoundException("User Not found"));
        if(user.getRole()==RoleType.ADMIN){
             passenegrGetDto=passengerAllMapper.entity2DtoById(passenger);
        }
        else if(user.getRole()==RoleType.PASSENGER)
        {
            if(!(passenger.getUser().getUsername().equals(userName))){
                throw new PassengerNotFoundException("This Passenger id is Not belongs Yours");
            }
            passenegrGetDto=passengerAllMapper.entity2DtoById(passenger);
        }
        if(passenegrGetDto==null){
            throw new NoResultException("Not Found Anything");
        }


        return passenegrGetDto;

    }

    public void delete(int id) {
        Passenger passenger=passengerRepository.findById(id).orElseThrow(()->new PassengerNotFoundException(NOT_FOUND));
        User user=passenger.getUser();
        if(user!=null){
            user.setActive(false);
            userRepository.save(user);
        }
    }

    public Passenger getByUserName(String userName) {
        return passengerRepository.findByUserUsername(userName).orElseThrow(()->new PassengerNotFoundException(NOT_FOUND));

    }

    public void upload(String userName, MultipartFile file) throws IOException {
        Passenger passenger = passengerRepository.findByUserUsername(userName)
                .orElseThrow(() -> new PassengerNotFoundException(NOT_FOUND));
        FileUtility.validFile(file);
        String uploadLoc = "C:/myTraining/Main Project-Frontend/public/images";
        Path uploadPath = Paths.get(uploadLoc).normalize().toAbsolutePath();
        // Sanitize: strip any path components from the filename
        String safeFilename = Paths.get(Objects.requireNonNull(file.getOriginalFilename())).getFileName().toString();
        Path destinationPath = uploadPath.resolve(safeFilename).normalize();
        // Validate the resolved path is still inside the upload directory
        if (!destinationPath.startsWith(uploadPath)) {
            throw new SecurityException("Access denied");
        }
        Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        passenger.setIdPath(safeFilename);
        passengerRepository.save(passenger);
    }
    public Passenger getDetail(String username) {
        return passengerRepository.findByUserUsername(username).orElseThrow(()->new PassengerNotFoundException(NOT_FOUND));

    }

    public CombineStatDto getStat(String username) {
        List<Booking> bookingList=bookingRepository.findByPassengerUserUsername(username);
        
        List<String> label=List.of("Bookings");
        List<Long> count=List.of((long) bookingList.size());

        return new CombineStatDto(
                label,
                count
        );
    }

    public void resetPassword(PasswordsReqDto passwordsReqDto, String username) {
        User user=(User)userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(NOT_FOUND));
        passengerRepository.findByUserUsername(username).orElseThrow(()->new PassengerNotFoundException(NOT_FOUND));
        user.setPassword(passwordEncoder.encode(passwordsReqDto.password()));
        userRepository.save(user);
    }
}