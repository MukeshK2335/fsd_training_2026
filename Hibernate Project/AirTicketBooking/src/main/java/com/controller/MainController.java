package com.controller;

import com.config.HibernateConfig;
import com.enums.RoleType;
import com.exception.UserNotFoundException;
import com.model.Flight;
import com.model.FlightOwner;
import com.model.Passenger;
import com.model.User;
import com.service.FlightOwnerService;
import com.service.FlightService;
import com.service.PassengerService;
import com.service.UserService;
import org.hibernate.Session;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {
        Session session = HibernateConfig.getSessionFactory().openSession();

        Scanner in = new Scanner(System.in);
        UserService userService=new UserService(session);
        PassengerService passengerService=new PassengerService(session);
        FlightService flightService=new FlightService(session);
        FlightOwnerService flightOwnerService=new FlightOwnerService(session);
        User user = null;
        while(true) {



                System.out.println("========Welcome to MK Air Ticket Booking System========");
                System.out.println("1.Register as a New User");
                System.out.println("2.Login");
                System.out.println("3.Exit");
                int op = in.nextInt();
                if (op == 3) {
                    break;
                }
                switch (op) {
                    case 1:
                        try {


                            user = new User();
                            in.nextLine();
                            System.out.println("Enter the UserName:");
                            user.setUsername(in.nextLine());
                            System.out.println("Enter the Password:");
                            user.setPassword(in.nextLine());
                            userService.addUser(user);
                            Passenger passenger = new Passenger();
                            passenger.setUser(user);
                            System.out.print("Enter Name: ");
                            passenger.setName(in.nextLine());
                            System.out.print("Enter Contact Number: ");
                            passenger.setContactNumber(in.nextLine());
                            System.out.print("Enter Gender: ");
                            passenger.setGender(in.nextLine());
                            System.out.print("Enter Address: ");
                            passenger.setAddress(in.nextLine());
                            System.out.print("Enter Email:");
                            passenger.setEmail(in.nextLine());
                            passengerService.addPassenger(passenger);
                            System.out.println("Passenger Added Successfully");

                        }
                        catch (UserNotFoundException u){
                            System.out.println(u.getMessage());
                        }

                        break;
                    case 2:
                        in.nextLine();
                        System.out.println("Enter the UserName:");
                        String userName = in.nextLine();
                        System.out.println("Enter the Password:");
                        String password = in.nextLine();
                        try {
                            user = userService.authunticateUser(userName, password);
                            System.out.println("Welcome to Air Ticket Booking "+user.getUsername());
                            switch (user.getRole().toString()){
                                case "PASSENGER":
                                    passengerMenu(in, passengerService, user);
                                    break;
                               case "FLIGHT_OWNER":
                                   flightOwnerMenu(in, flightOwnerService, flightService, user);
                                 break;
      }
                        } catch (UserNotFoundException u) {
                            System.out.println(u.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }


        }

        in.close();
        session.close();
    }

    static void passengerMenu(Scanner in, PassengerService passengerService, User loggedUser) {

        while(true){
            System.out.println("=======Passenger Menu=====");
            System.out.println("1.View Profile By Id");
            System.out.println("2.Update Profile");
            System.out.println("0.Exit");
            int op=in.nextInt();
            in.nextLine();
            if(op==0){
                break;
            }
            switch (op){
                case 1:
                    try {
                        Passenger passenger = passengerService.viewPassenger(loggedUser.getId());
                        System.out.println(passenger);
                    }
                    catch (UserNotFoundException u) {
                        System.out.println(u.getMessage());
                    }
                    break;
                case 2:
                    try{
                        Passenger passenger = passengerService.viewPassenger(loggedUser.getId());
                        System.out.println("Enter the Email to be Updated");
                        passenger.setEmail(in.nextLine());
                        passengerService.updatePassenger(passenger);

                    }
                    catch (UserNotFoundException u){
                        System.out.println(u.getMessage());
                    }
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    static void flightOwnerMenu(Scanner in, FlightOwnerService flightOwnerService,
                                FlightService flightService, User loggedUser) {
        while (true) {
            System.out.println("====== Flight Owner Menu ======");
            System.out.println("--- Profile ---");
            System.out.println("1. View My Profile");
            System.out.println("2. Update My Profile");
            System.out.println("--- Flight ---");
            System.out.println("3. Add Flight");
            System.out.println("4. View My Flights");
            System.out.println("5. Delete Flight");
            System.out.println("0. Exit");
            int op = in.nextInt();

            if (op == 0) {
                System.out.println("Logged out successfully!");
                break;
            }

            switch (op) {


                case 1:
                    try {
                        FlightOwner fo = flightOwnerService.getFlightOwnerByUserId(loggedUser.getId());
                        System.out.println(fo);
                    } catch (UserNotFoundException u) {
                        System.out.println(u.getMessage());
                    }
                    break;

                case 2:
                    try {
                        FlightOwner fo = flightOwnerService.getFlightOwnerByUserId(loggedUser.getId());
                        in.nextLine();
                        System.out.print("New Name (" + fo.getName() + "): ");
                        fo.setName(in.nextLine());
                        System.out.print("New Company (" + fo.getCompanyName() + "): ");
                        fo.setCompanyName(in.nextLine());
                        System.out.print("New Contact (" + fo.getContactNumber() + "): ");
                        fo.setContactNumber(in.nextLine());
                        System.out.print("New Address (" + fo.getAddress() + "): ");
                        fo.setAddress(in.nextLine());
                        flightOwnerService.updateFlightOwner(fo);
                        System.out.println("Profile updated successfully!");
                    } catch (UserNotFoundException u) {
                        System.out.println(u.getMessage());
                    }
                    break;

                case 3:
                    try {
                        in.nextLine();
                        FlightOwner owner = flightOwnerService.getFlightOwnerByUserId(loggedUser.getId());
                        Flight flight = new Flight();
                        flight.setFlightOwner(owner);
                        System.out.print("Flight Name: ");
                        flight.setFlightName(in.nextLine());
                        System.out.print("Flight Number: ");
                        flight.setFlightNumber(in.nextLine());
                        System.out.print("Total Seats: ");
                        flight.setTotalSeats(Integer.parseInt(in.nextLine()));
                        System.out.print("Check-in Baggage (kg): ");
                        flight.setCheckInBaggage(Double.parseDouble(in.nextLine()));
                        System.out.print("Cabin Baggage (kg): ");
                        flight.setCabinBaggage(Double.parseDouble(in.nextLine()));
                        flightService.addFlight(flight);
                        System.out.println("Flight added successfully!");
                    } catch (UserNotFoundException u) {
                        System.out.println(u.getMessage());
                    }
                    break;


                case 4:
                    try {
                        FlightOwner myFo = flightOwnerService.getFlightOwnerByUserId(loggedUser.getId());
                        List<Flight> flights = flightService.getFlightsByOwner(myFo.getId());

                        flights.forEach(System.out::println);
                    } catch (UserNotFoundException u) {
                        System.out.println(u.getMessage());
                    }
                    break;

                case 5:
                    try {
                        System.out.print("Enter Flight ID to delete: ");
                        int deleteFid = in.nextInt();
                        flightService.deleteFlight(deleteFid);
                        System.out.println("Flight deleted successfully!");
                    } catch (UserNotFoundException u) {
                        System.out.println(u.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}


