// package src;

import java.util.Scanner;

public class User {
    
    public void logInMessage () {
        System.out.println("                                                       ");
        System.out.println("Enter your email Address");
        System.out.println("                                                       ");

    

        Scanner scanner  = new Scanner( System.in );
        
        String email = scanner.next();

        System.out.println("                                                       ");
        System.out.println("Enter your password");
        System.out.println("                                                       ");

        String password = scanner.next();

        System.out.println("You have successfully logged in");


    }

}
