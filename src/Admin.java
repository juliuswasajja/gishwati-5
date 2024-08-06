import java.io.IOException;
import java.util.Scanner;

public class Admin {


        public void ScreenOptions(){

            System.out.println("Admin select action you would like to perform");
            System.out.println("----------------------------");

            System.out.println("1. Admin Initiate Patient Registration");
            System.out.println("2. Generate Analytics");
            System.out.println("2. Export Data Analytics");
            System.out.println("----------------------------");
            System.out.println("Enter option 1 - 3");


        }

        public String adminGetOptionSelection () {

            Scanner scanner  = new Scanner( System.in );
            String submittedOption = scanner.next();

            return submittedOption;

        }

        public void enterPatientEmailMessage () {

            System.out.println("Initiate Patient Registration");
            System.out.println("----------------------------");
            System.out.println("Enter Patient Email");

        }

        public String InitiatePatientRegistration () {

            Scanner scanner  = new Scanner( System.in );
            String patientEmail = scanner.next();

            return patientEmail;

        }

        public void adminAddPatient (String patientEmail) {

            String addPatientCmd [] = {"/bin/bash","../scripts/add-user.sh", patientEmail};

            ProcessBuilder addUserScript = new ProcessBuilder(addPatientCmd);

            try {
            Process process = addUserScript.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("User added successfully.");
            } else {
                System.err.println("Error: Script execution failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        }
    
}
