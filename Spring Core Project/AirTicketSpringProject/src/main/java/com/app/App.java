package com.app;

import com.app.Dao.PassengerDao;
import com.app.DaoImpl.PassengerDaoImpl;
import com.app.config.AppConfig;
import com.app.exception.PassengerNotFoundException;
import com.app.model.Passenger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        PassengerDao passengerDao=context.getBean(PassengerDaoImpl.class);
        while(true){
            System.out.println("=======Passenger Menu=======");
            System.out.println("1.Add Passenger");
            System.out.println("2.Delete Passenger By ID:");
            System.out.println("3.Get the Passenger BY ID:");
            System.out.println("4.Update Passenger Email:");
            System.out.println("5.Get All Passenger");
            System.out.println("0.Exit");

            int op=in.nextInt();
            if(op==0){
                break;
            }
            switch (op){
                case 1:
                    Passenger passenger=new Passenger();
                    in.nextLine();
                    System.out.println("Enter the name:");
                    passenger.setName(in.nextLine());
                    System.out.println("Enter the Gender:");
                    passenger.setGender(in.nextLine());
                    System.out.println("Enter the Contact Number:");
                    passenger.setContactNumber(in.nextLine());
                    System.out.println("Enter the address:");
                    passenger.setAddress(in.nextLine());
                    System.out.println("Enter the Email:");
                    passenger.setEmail(in.nextLine());
                    passengerDao.addPassenger(passenger);
                    System.out.println("Passenger Added Successfully");
                    break;
                case 2:
                    System.out.println("Enter the Passenger ID:");
                    int id=in.nextInt();
                    try {
                        passengerDao.deletePassengerById(id);
                        System.out.println("Passenger Deleted Successfully");
                    }
                    catch (PassengerNotFoundException p){
                        System.out.println(p.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Enter the Passenger ID:");
                    id=in.nextInt();
                    try {
                        Passenger passenger1 = passengerDao.getPassengerById(id);
                        System.out.println(passenger1);
                    }
                    catch (EmptyResultDataAccessException e){
                        System.out.println("Invalid Id");
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Enter the Passenger ID:");
                        id= in.nextInt();
                        Passenger passenger1=passengerDao.getPassengerById(id);
                        System.out.println("Enter the Email To be Updated:");
                        in.nextLine();
                        passenger1.setEmail(in.nextLine());
                        passengerDao.updatePassenger(passenger1);
                        System.out.println("Passenger Email Updated Successfully");
                    }
                    catch (PassengerNotFoundException | EmptyResultDataAccessException p){
                        System.out.println("Invalid ID");
                    }
                    break;
                case 5:
                    List<Passenger> list=passengerDao.getAllPassenger();
                    list.forEach(System.out::println);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }

        }
    }
}
