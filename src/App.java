import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        System.out.println("*******************************************************");
        System.out.println("                                                       ");
        System.out.println("     Welcome to the Life Prognosis Management Tool     ");
        System.out.println("                                                       ");
        System.out.println("*******************************************************");

        boolean validSelection = false;

        while ( !validSelection ) {
            System.out.println("                                                       ");
            System.out.println("Please select action you would like to perform");
            System.out.println("                                                       ");
            System.out.println("1. Login" + 
                        "\n" + "2. Complete Registration" + 
                        "\n" + "3. Exit Application");
            System.out.println("                                                       ");
            System.out.println("-------------------------------------------------------");
            System.out.println("                                                       ");
            System.out.println("Enter your selection");
            System.out.println("                                                       ");


            Scanner scanner  = new Scanner( System.in );
            String selection = scanner.next();

            int selectionNumber;

            try {
                selectionNumber = Integer.parseInt(selection);
            } catch (NumberFormatException e) {
                selectionNumber = 0;
            }

            switch (selectionNumber) {
                case 1:{

                    validSelection = true;

                Admin admin = new Admin();

                int selectedOption = admin.mainMenu();

                    switch (selectedOption) {
                    case 1: admin.registerPatientEmail();
                    break;

                    case 2: System.out.println("Feature coming soon");
                    break;

                    case 3: System.out.println("Feature coming soon");
                    break;
                
                    default: {
                        System.out.println("                                                       ");
                        System.out.println("-------------------------------------------------------");
                        System.out.println("Wrong selection, please select again");
                        System.out.println("-------------------------------------------------------");
                    }
                        break;
                }

                
                }
                    
                    break;

                case 2: {

                    validSelection = true;

                    Patient patient = new Patient();

                    patient.completeRegistration();

                }
                    
                    break;

                case 3: {

                    System.out.println("                                                       ");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("----------Thank You For Using LPMT---------------------");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("                                                       ");

                    validSelection = true;
                    System.exit(0);

                }
                    
                    break;
            
                default: {

                    System.out.println("                                                       ");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Wrong selection, please select again");
                    System.out.println("-------------------------------------------------------");

                }
                    break;
            }

            // if (selectionNumber == 1) {

            //     validSelection = true;

            //     Admin admin = new Admin();

            //     int selectedOption = admin.mainMenu();

            //         switch (selectedOption) {
            //         case 1: admin.registerPatientEmail();
            //         break;

            //         case 2: System.out.println("Feature coming soon");
            //         break;

            //         case 3: System.out.println("Feature coming soon");
            //         break;
                
            //         default: {
            //             System.out.println("                                                       ");
            //             System.out.println("-------------------------------------------------------");
            //             System.out.println("Wrong selection, please select again");
            //             System.out.println("-------------------------------------------------------");
            //         }
            //             break;
            //     }

            // } else if (selectionNumber == 2){
                
            //     validSelection = true;

            //     Patient patient = new Patient();

            //     String patientUuid = patient.completeRegistrationMessage(); 

            //     patient.setUuid(patientUuid);

            //     String verifyUuid = patient.getUuid();

            //     String verifiedUiid = patient.verifyPatientUuid(verifyUuid);
                
            //     String patientPassword = patient.fetchPatientPassword();

            //     patient.encryptPassword(verifiedUiid, patientPassword);

            //     patient.completeHealthRegistration( "UUID001");

            //     // patient.completeHealthRegistration( "UUID001", "John", "Doe", "USA", "30", "Positive" ,"2023-08-01", "On ART" ,"2023-08-02" );

    
    
            // } 
            
            // else if (selectionNumber == 3){

            //     System.out.println("Thank You For Using LPMT");
            //     validSelection = true;
            //     System.exit(0);

            // }
            
            // else {
            //     System.out.println("                                                       ");
            //     System.out.println("-------------------------------------------------------");
            //     System.out.println("Wrong selection, please select again");
            //     System.out.println("-------------------------------------------------------");
            // }


        }
        
        

        
  
    
      
        



    }
}