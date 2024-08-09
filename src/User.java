// package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class User {
    
    public String userData;
    public String email;

    public String logIn () {

        System.out.println("Enter your email Address");
        Scanner scanner  = new Scanner( System.in );

        String email = scanner.next();

        System.out.println("Enter your password");
        
        String password = scanner.next();

        // email = "admin@lpmt.com";
        // password = "julio";

        email = "user39595@gmail.com";
        password = "julio";

        String[] authenticateCmd = {"/bin/bash", "../scripts/authenticate-user.sh", email, password};
        ProcessBuilder authenticateUser = new ProcessBuilder(authenticateCmd);

        try {
            Process process = authenticateUser.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Authentication successful.");
                return userData = output.toString();
            } else {
                System.err.println("Authentication failed.");
                System.out.println(userData = output.toString());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String setRole ( String userData ){

        String[] fields = userData.split(",");

        String role = fields[2];

        return role;

    }

}
