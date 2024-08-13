import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Admin  extends User {

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
            System.out.println("4. Log out and Exit");
            System.out.println("-------------------------------------------------------");
            System.out.println("                                                       ");
            System.out.println("Enter option 1 - 4");
            System.out.println("                                                       ");

            Scanner scanner = new Scanner(System.in);
            String userSelection = scanner.next();
            System.out.println("\033\143");

            try {
                selectedOption = Integer.parseInt(userSelection);
            } catch (NumberFormatException e) {
                selectedOption = 0;
            }

            if (selectedOption == 1 ||
                    selectedOption == 2 ||
                    selectedOption == 3 ||
                    selectedOption == 4) {
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
                exportUserData ("../data-store/user-store.txt","../data-store/exported_user_data.csv");
            }
                break;

            case 4: {
                System.out.println("                                                       ");
                System.out.println("-------------------------------------------------------");
                System.out.println("----------Thank You For Using LPMT---------------------");
                System.out.println("-------------------------------------------------------");
                System.out.println("                                                       ");

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

    }

    public void registerPatientEmail() {

        System.out.println("                                                       ");
        System.out.println("Initiating Patient Registration");
        System.out.println("                                                       ");
        
        while ( email == null) {

            System.out.println("----------------------------");
            System.out.println("Please enter Patient's Email");
            System.out.println("                                                       ");

            Scanner scanner = new Scanner(System.in);
            email = scanner.next();
            System.out.println("\033\143");

            if (email == null){
                System.out.println("Please provide a user email");
            }
            
        }

        String addPatientCmd[] = { "/bin/bash","../scripts/add-user.sh", email };

        ProcessBuilder addUserScript = new ProcessBuilder(addPatientCmd);

        try {
            Process process = addUserScript.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("                                                       ");
                System.out.println("-------------------------------------------------------");
                System.out.println("User added successfully.");
                System.out.println("-------------------------------------------------------");
                System.out.println("                   "+ output +"                          ");
                System.out.println("-------------------------------------------------------");
                System.out.println("                                                       ");
                navigationOptions();

            } else {
                System.out.println("                                                       ");
                System.out.println("-------------------------------------------------------");
                System.err.println("Error: Script execution failed.");
                System.out.println("-------------------------------------------------------");
                System.out.println("                                                       ");
                navigationOptions();


            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

        public void exportUserData(String userStoreFilePath, String csvFilePath) {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            // Open the user-store.txt file for reading
            reader = new BufferedReader(new FileReader(userStoreFilePath));

            // Open the CSV file for writing
            writer = new BufferedWriter(new FileWriter(csvFilePath));

            // Read the header line and write it to the CSV file
            String line = reader.readLine();
            if (line != null) {
                writer.write(line + "\n");
            }

            // Read the rest of the file and write to CSV
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
            }

            System.out.println("Data exported to CSV successfully.");

            // Optionally, copy the CSV file to a location for download
            String downloadPath = "../data-store/exported_user_data.csv"; // Update as needed
            Files.copy(Paths.get(csvFilePath), Paths.get(downloadPath), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("CSV file is ready for download: " + downloadPath);
            navigationOptions();

        } catch (IOException e) {
            System.err.println("Error occurred while exporting data to CSV: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void navigationOptions () {
        
        boolean navMenuSelection = false;

                while (!navMenuSelection) {

                System.out.println("                                                       ");
                System.out.println("Select next action");
                System.out.println("                                                       ");
                System.out.println("00. Return to the Admin Main Menu" +
                        "\n" + "1. Exit the application ");
                System.out.println("                                                       ");
                System.out.println("-------------------------------------------------------");
                System.out.println("Enter your selection");
                System.out.println("                                                       ");

                Scanner scanner = new Scanner(System.in);
                String userNavSelection = scanner.next();

                System.out.println("\033\143");


                int selectedNavOption;
                // System.out.println("\033\143");
                try {
                    selectedNavOption = Integer.parseInt(userNavSelection);
                } catch (NumberFormatException e) {
                    selectedNavOption = 0;
                }
    
                if (selectedNavOption == 00 ||
                selectedNavOption == 1 ) {
    
                    navMenuSelection = true;

                    if (selectedNavOption == 00) { 
                        selectOption(menu());
                    }
                    else if (selectedNavOption == 1) {

                        System.out.println("                                                       ");
                        System.out.println("-------------------------------------------------------");
                        System.out.println("----------Thank You For Using LPMT---------------------");
                        System.out.println("-------------------------------------------------------");
                        System.out.println("                                                       ");

                        System.exit(0);
                    }
    
                } else {
                    System.out.println("                                                       ");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Wrong selection, please select again");
                    System.out.println("-------------------------------------------------------");
    
                }
            }
    }

}
