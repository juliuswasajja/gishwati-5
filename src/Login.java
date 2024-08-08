import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Login {

    public String email;
    public String password;

    // public String getEmail() {
    //     return email;
    // }
    // public void setEmail(String email) {
    //     this.email = email;
    // }
    // public String getPassword() {
    //     return password;
    // }
    // public void setPassword(String password) {
    //     this.password = password;
    // }

    public String userLogIn () {

        System.out.println("Enter your email Address");

        Scanner scanner  = new Scanner( System.in );
        String email = scanner.next();

        System.out.println("Enter your password");
        String password = scanner.next();

        email = "julius@lpmt.com";
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
                System.out.println("User authenticated successfully.");
                return output.toString();
            } else {
                System.err.println("Authentication failed.");
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String setRole ( String userData ){
        String[] fields = userData.split(",");

        String role = fields[3];

        return role;

    }

    
}
