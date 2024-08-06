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

    public String userLogIn ( String email, String password) {


        System.out.println("Enter your email Address");

        Scanner scanner  = new Scanner( System.in );
        email = scanner.next();

        System.out.println("Enter your password Address");
        password = scanner.next();



        return email + password;

    }
    
}
