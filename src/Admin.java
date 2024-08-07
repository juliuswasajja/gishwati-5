import java.io.IOException;
import java.util.Scanner;

public class Admin {

    public int menu() {

        System.out.println("                                                       ");
        System.out.println("--------------Welcome to Admin Dashboard---------------");
        System.out.println("                                                       ");
        System.out.println("What operation would you like to perfrom");
        System.out.println("                                                       ");

        boolean validSelection = false;

        int selectedOption = 0;

        while (!validSelection) {

            System.out.println("-------------------------------------------------------");
            System.out.println("1. Admin Initiate Patient Registration");
            System.out.println("2. Generate Analytics");
            System.out.println("3. Export Data Analytics");
            System.out.println("-------------------------------------------------------");
            System.out.println("                                                       ");
            System.out.println("Enter option 1 - 3");
            System.out.println("                                                       ");

            Scanner scanner = new Scanner(System.in);
            String userSelection = scanner.next();

            try {
                selectedOption = Integer.parseInt(userSelection);
            } catch (NumberFormatException e) {
                selectedOption = 0;
            }

            if (selectedOption == 1 ||
                    selectedOption == 2 ||
                    selectedOption == 3) {
                validSelection = true;
            } else {
                System.out.println("                                                       ");
                System.out.println("-------------------------------------------------------");
                System.out.println("Wrong selection, please select again");
                System.out.println("-------------------------------------------------------");
            }
        }

        return selectedOption;

    }

    public void selectOption(int selectedOption) {

        switch (selectedOption) {
            case 1: {
                registerPatientEmail();
            }
                break;

            case 2: {
                System.out.println("Generate Analtics feature is coming soon");
            }
                break;

            case 3: {
                System.out.println("Export Data feature is coming soon");
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

    }

    

    public void registerPatientEmail() {

        System.out.println("                                                       ");
        System.out.println("Initiating Patient Registration");
        System.out.println("                                                       ");
        System.out.println("----------------------------");
        System.out.println("Please enter Patient's Email");
        System.out.println("                                                       ");

        Scanner scanner = new Scanner(System.in);
        String patientEmail = scanner.next();

        String addPatientCmd[] = { "/bin/bash", "../scripts/add-user.sh", patientEmail };

        ProcessBuilder addUserScript = new ProcessBuilder(addPatientCmd);

        try {
            Process process = addUserScript.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("                                                       ");
                System.out.println("-------------------------------------------------------");
                System.out.println("User added successfully.");
                System.out.println("-------------------------------------------------------");
                System.out.println("                                                       ");

            } else {
                System.out.println("                                                       ");
                System.out.println("-------------------------------------------------------");
                System.err.println("Error: Script execution failed.");
                System.out.println("-------------------------------------------------------");
                System.out.println("                                                       ");

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
