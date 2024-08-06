// package src;

import java.util.Scanner;

public class User {
    
    public void logInMessage () {
       
        System.out.println("Enter your email Address");

        Scanner scanner  = new Scanner( System.in );
        String email = scanner.next();

        System.out.println("Enter your password Address");
        String password = scanner.next();

        System.out.println("You have successfully logged in");


    }

}
