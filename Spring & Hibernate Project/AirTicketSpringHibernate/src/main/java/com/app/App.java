package com.app;

import com.app.config.AppConfig;
import com.app.dao.AuthDao;
import com.app.dao.FlightDao;
import com.app.dao.FlightOwnerDao;
import com.app.dao.PassengerDao;
import com.app.exception.FlightNotFoundException;
import com.app.exception.PassengerNotFoundException;
import com.app.model.Flight;
import com.app.model.Passenger;
import com.app.model.User;
import jakarta.persistence.NoResultException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        Scanner in=new Scanner(System.in);
        AuthDao authDao=context.getBean(AuthDao.class);
        PassengerDao passengerDao=context.getBean(PassengerDao.class);
        FlightDao flightDao=context.getBean(FlightDao.class);
        FlightOwnerDao flightOwnerDao=context.getBean(FlightOwnerDao.class);
        System.out.println("========Welcome to MK Air Ticket Booking System+========");
        System.out.println("Enter the Username:");
        String username=in.nextLine();
        System.out.println("Enter the Password:");
        String password=in.nextLine();
        try{
            User user= authDao.login(username,password);
            switch (user.getRole().toString()){
                case "PASSENGER":
                    while(true){
                        System.out.println("====Welcome "+username+" ======");
                        System.out.println("1.View My Profile");
                        System.out.println("2.View All Available Flights");
                        System.out.println("3.Search Flight by ID");
                        System.out.println("4.Update My Profile's Email");
                        System.out.println("5.Exit");
                        int op=in.nextInt();
                        if(op==5){
                            break;
                        }
                        switch (op){
                            case 1:
                                try {
                                    Passenger passenger = passengerDao.viewProfile(user);
                                    System.out.println(passenger);
                                }
                                catch (NoResultException | PassengerNotFoundException p)
                                {
                                    System.out.println("Passenger Profile Not Found");
                                }
                                break;
                            case 2:
                                System.out.println("======Avaliable Flights=====");
                                List<Flight> flights=flightDao.getAll();
                                flights.forEach(System.out::println);
                                break;
                            case 3:
                                System.out.println("Enter the Flight ID:");
                                String id=in.next();
                                try{
                                    Flight flight=flightDao.getById(id);
                                    System.out.println(flight);
                                }
                                catch (FlightNotFoundException | NoResultException f)
                                {
                                    System.out.println("Invalid Flight Number");
                                }
                                break;
                            case 4:
                                System.out.println("Enter the email:");
                                String email=in.next();
                                try{
                                    passengerDao.updateEmail(email,user);
                                    System.out.println("Updated Successfully");
                                }
                                catch (NoResultException n){
                                    System.out.println("Passenger Not Found");
                                }
                                break;
                            default:
                                System.out.println("Invalid Choice");
                        }
                    }
                    break;
                case "FLIGHT_OWNER":
                    while(true) {
                        System.out.println("====Welcome " + username + " ======");
                        System.out.println("1.Add New Flight");
                        System.out.println("2.View All My Flights ");
                        System.out.println("3.Search Flight by ID");
                        System.out.println("4.Delete a Flight");
                        System.out.println("5.Exit");
                        int op=in.nextInt();
                        if(op==5){
                            break;
                        }
                        switch (op){
                            case 1:
                                in.nextLine();

                                System.out.print("Enter Flight Name: ");
                                String flightName = in.nextLine();

                                System.out.print("Enter Flight Number: ");
                                String flightNumber = in.nextLine();

                                System.out.print("Enter Total Seats: ");
                                int totalSeats = in.nextInt();

                                System.out.print("Enter Check-In Baggage (kg): ");
                                double checkIn = in.nextDouble();

                                System.out.print("Enter Cabin Baggage (kg): ");
                                double cabin = in.nextDouble();

                                Flight newFlight = new Flight();
                                newFlight.setFlightName(flightName);
                                newFlight.setFlightNumber(flightNumber);
                                newFlight.setTotalSeats(totalSeats);
                                newFlight.setCheckInBaggage(checkIn);
                                newFlight.setCabinBaggage(cabin);
                                flightOwnerDao.addFlight(newFlight,user);
                                System.out.println("Flight Added Successfully!");


                                break;
                            case 2:
                                System.out.println("Alll Flights");
                                List<Flight> list=flightOwnerDao.myFlight(user);
                                list.forEach(System.out::println);

                                break;
                            case 3:
                                System.out.print("Enter Flight ID: ");
                                int searchId = in.nextInt();
                                try{
                                    Flight found = flightOwnerDao.findByIdAndOwner(searchId, user);
                                    System.out.println(found);
                                }
                                catch (FlightNotFoundException f){
                                    System.out.println(f.getMessage());
                                }

                                break;
                            case 4:
                                System.out.print("Enter Flight ID to delete: ");
                                int deleteId = in.nextInt();
                                try {
                                    Flight toDelete = flightOwnerDao.findByIdAndOwner(deleteId, user);
                                    flightOwnerDao.deleteFlight(deleteId, user);
                                    System.out.println("Flight deleted successfully.");
                                }
                                catch (FlightNotFoundException f){
                                    System.out.println(f.getMessage());
                                }
                                break;
                        }


                    }
                    break;
                default:
                    System.out.println("Invalid User");
            }
        }
        catch (NoResultException n){
            System.out.println("Invalid Credentials");
        }

        context.close();
    }
}
