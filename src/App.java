// package src;
/**
 * App
 */

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        boolean validSelection = false;

        while ( !validSelection ) {

            System.out.println("------------------------------------------------");
            System.out.println("Welcome to the Life Prognosis Management Tool");
            System.out.println("------------------------------------------------");

            System.out.println("Please select action you would like to perform");
            System.out.println("1. Login" + "\n" + "2. Complete Registration");
        
            System.out.println("----------------------------");
            System.out.println("Enter your selection");

            Scanner scanner  = new Scanner( System.in );
            int num = scanner.nextInt();

            if (num == 1) {
                validSelection = true;

                //instantiate user
                User user = new User();

                //Perform login here
                user.logInMessage();

                //instantiate admin
                Admin admin = new Admin();

                admin.ScreenOptions();
               //admin1.adminGetOptionSelection();
                String selectedOption = admin.adminGetOptionSelection();

                switch (selectedOption.toString()) {
                    case "1":{

                    admin.enterPatientEmailMessage();
                    String patientEmail = admin.InitiatePatientRegistration();
    
                    Patient patient = new Patient();
                    patient.setEmail(patientEmail);
        
                    String newPatientEmail = patient.getEmail();
                    admin.adminAddPatient(newPatientEmail);

                    }
                    break;

                    case "2": System.out.println("Feature coming soon");
                    break;

                    case "3": System.out.println("Feature coming soon");
                    break;
                
                    default: {
                        System.out.println("----------------------------");
                        System.out.println("Wrong selection, please select again");
                        System.out.println("----------------------------");
                    }
                        break;
                }

            } else if (num == 2){
                validSelection = true;

                Patient patient = new Patient();

                String patientUuid = patient.completeRegistrationMessage(); 

                patient.setUuid(patientUuid);
                String verifyUuid = patient.getUuid();

                String verifiedUiid = patient.verifyPatientUuid(verifyUuid);
                
                String patientPassword = patient.fetchPatientPassword();

                patient.encryptPassword(verifiedUiid, patientPassword);

                patient.completeHealthRegistration( "UUID001");

                // patient.completeHealthRegistration( "UUID001", "John", "Doe", "USA", "30", "Positive" ,"2023-08-01", "On ART" ,"2023-08-02" );

    
    
            } else {
                System.out.println("-------------------------------------");
                System.out.println("Wrong selection, please select again");
                System.out.println("-------------------------------------");

            }


        }
        
        

        
  
    
      
        



    }
}