import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Patient extends User {

    public String healthData;
    private String email;

    public String uuid;
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String country;
    public String hivStatus = "0";
    public String diagnosisDate;
    public String artStatus = "0";
    public String artStartDate;

    public void viewData(String healthData) {

        String[] fields = healthData.split(",");

        if (fields.length >= 5) { // Ensure that there are enough fields
            email = fields[0];
            firstName = fields[4];
            lastName = fields[5];
            dateOfBirth = fields[6];
            country = fields[7];
            hivStatus = fields[8];
            diagnosisDate = fields[9];
            artStatus = fields[10];
            artStartDate = fields[11];

            
            

            System.out.println("#  Email          :" + email);
            System.out.println("#  First Name     :" + firstName);
            System.out.println("#  Last Name      :" + lastName);
            System.out.println("#  Date of Birth  :" + dateOfBirth);
            System.out.println("#  Country        :" + country);
            
            if (hivStatus.equals("1")) {
                System.out.println("#  HIV Status     :Positive");
            System.out.println("#  Diagnosis Date :" + diagnosisDate);
                if (artStatus.equals("1")) {
                    System.out.println("#  ART Status     :Yes");
            System.out.println("#  ART Start Date :" + artStartDate);
                }else {
                    System.out.println("#  ART Status     : No");
                }
            }else {
                System.out.println("#  HIV Status     : Negative");
            }
            System.out.println("# Life Expectancy: " + calculateLifeSpan(healthData));
            navigationOptions();
        } else {
            System.out.println("Invalid user data.");
        }

    }

    public void completeRegistration() {

        System.out.println("                                                       ");
        System.out.println("To Complete Registration, enter your UUID");
        System.out.println("                                                       ");

        Scanner scanner = new Scanner(System.in);
        String patientUuid = scanner.next();

        String verifyPatientUuidCmd[] = { "/bin/bash", "../scripts/check-patient-uuid.sh", patientUuid };

        ProcessBuilder verifyPatientUuid = new ProcessBuilder(verifyPatientUuidCmd);

        try {

            Process process = verifyPatientUuid.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();

            if (exitCode == 0) {

                System.out.println(output);

                encryptPassword(patientUuid, fetchPatientPassword());

                healthDataRegistration(patientUuid);

            }

            else {
                System.err.println("Error: Script execution failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String fetchPatientPassword() {

        String submittedPassword = "1";
        String reSubmittedPassword = "2";

        while (!(submittedPassword.equals(reSubmittedPassword))) {

            System.out.println("Enter your password");
            System.out.println("----------------------------");

            Console console = System.console();
            char [] charpass = console.readPassword();
            submittedPassword = String.valueOf(charpass);
        

            System.out.println("Re - enter your password");
            System.out.println("----------------------------");

            char [] charpass2 = console.readPassword();
            reSubmittedPassword = String.valueOf(charpass2);

            if (submittedPassword.equals(reSubmittedPassword)) {
                System.out.println("\033\143");
                System.out.println("Passwords match");

            } else {
                System.out.println("\033\143");
                System.out.println("Password does not match, please try again");
            }

        }

        return submittedPassword;

    }

    public void encryptPassword(String uuid, String password) {

        String[] encryptPasswordCmd = { "/bin/bash", "../scripts/encrypt-password.sh", uuid, password };

        ProcessBuilder encryptPatientPassword = new ProcessBuilder(encryptPasswordCmd);

        try {
            Process process = encryptPatientPassword.start();

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Password created successfully.");
            } else {
                System.err.println("Password encryption failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void healthDataRegistration(String uuid) {

        
        System.out.println("----------------------------");
        System.out.println("What is your First Name");

        Scanner scanner = new Scanner(System.in);

        firstName = scanner.next();

        
        System.out.println("----------------------------");
        System.out.println("What is your Last Name");

        lastName = scanner.next();

        System.out.println("----------------------------");
        System.out.println("What is your Date of Birth");
        System.out.println("                            ");
        System.out.println("Should follow the formatt DD-MM-YYYY");

        dateOfBirth = scanner.next();

        System.out.println("----------------------------");
        System.out.println("Which country do you reside in?");

        country = scanner.next();

        while (!(hivStatus.equals("1"))  && !(hivStatus.equals("2"))) {

            System.out.println("----------------------------");
            System.out.println("What is your HIV Status");
            System.out.println("                            ");
            System.out.println("If Positive, type 1\nIf Negative, type 2");
            System.out.println("                            ");

            hivStatus = scanner.next();

        }

        if (hivStatus.equals("1")) {

            hivStatus = "Positive";
            System.out.println("art here" + hivStatus);
            System.out.println("----------------------------");
            System.out.println("What date did you contract HIV/AIDs?");
            System.out.println("                            ");
            System.out.println("Should follow the formatt DD-MM-YYYY");
            System.out.println("                            ");

            diagnosisDate = scanner.next();

            while (!(artStatus.equals("1")) && !(artStatus.equals("2"))) {

                System.out.println("----------------------------");
                System.out.println("Are you on Antiretrovial Therapy (ARVs)?");
                System.out.println("                            ");
                System.out.println("If Yes, type 1\nIf No, type 2");

                artStatus = scanner.next();

            }

            if (artStatus.equals("1")) {

                artStatus = "Yes";
                System.out.println("----------------------------");
                System.out.println("When did you start Antiretrovial Therapy (ARVs)?");
                System.out.println("                            ");

                System.out.println("Should follow the formatt DD-MM-YYYY");
                System.out.println("                            ");
                artStartDate = scanner.next();

            }else {
                artStatus = "No";
            }

        } else {
            hivStatus = "Negative";
        }

        String[] completeRegistrationCmd = {
                "/bin/bash", "../scripts/complete-registration.sh",
                uuid, firstName, lastName, dateOfBirth, country, hivStatus, diagnosisDate, artStatus, artStartDate };

        ProcessBuilder completePatientRegistrationProcess = new ProcessBuilder(completeRegistrationCmd);

        try {
            Process process = completePatientRegistrationProcess.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorOutput = new StringBuilder();
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("\033\143");
                System.out.println("Registration complete, you have been added to the system successfully.");
                System.out.println("Redirecting you to main maenu...");
                Menu menu = new Menu();
                menu.welcome();
                App.selectOperation(menu.main());

            } else {
                System.err.println("Error Output: " + errorOutput);
                System.err.println("User information registration failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int menu() {

        System.out.println("                                                       ");
        System.out.println("--------------Welcome to Patient Dashboard---------------");
        System.out.println("                                                       ");
        System.out.println("What operation would you like to perfrom");
        System.out.println("                                                       ");

        boolean validSelection = false;

        int selectedOption = 0;

        while (!validSelection) {

            System.out.println("-------------------------------------------------------");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. Log out and exit application");
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
                    selectedOption == 2 || selectedOption == 3) {
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
        Scanner scanner = new Scanner(System.in);
        switch (selectedOption) {
            case 1: {
                System.out.println("\033\143");
                System.out.println("-------------------------------------------------------");
                System.out.println("Here is your Health Data");
                System.out.println("-------------------------------------------------------");
                calculateLifeSpan(healthData);
                viewData(healthData);
                System.out.println("-------------------------------------------------------");
            }
            break;

            case 2: {
                System.out.println("\033\143");
                System.out.println("Here is your cuurent profile");
                viewData(healthData);


                System.out.println("Select the field you want to update: ");

                int field = scanner.nextInt();
                scanner.nextLine(); // consume the newline
                // System.out.println("\033\143");
                System.out.println("Field number selected: " + field);

                String fieldName = "";
                switch (field) {
                    case 1:
                        fieldName = "Email";
                        break;
                    case 2:
                        fieldName = "First Name";
                        break;
                    case 3:
                        fieldName = "Last Name";
                        break;
                    case 4:
                        fieldName = "Date of Birth";
                        break;
                    case 5:
                        fieldName = "Country";
                        break;
                    case 6:
                        fieldName = "HIV Status";
                        break;
                    case 7:
                        fieldName = "Diagnosis Date";
                        break;
                    case 8:
                        fieldName = "ART Status";
                        break;
                    case 9:
                        fieldName = "ART Start Date";
                        break;
                    default:
                        System.out.println("Invalid field number.");
                        return;
                }


                System.out.println("Field name to update: " + fieldName); // Debugging statement

                System.out.println("Enter the new value: ");
                String newValue = scanner.nextLine();

                updateProfile(email, fieldName, newValue);

                System.out.println("this is after the update");
                //this is returning old data, why???
                viewData(healthData);

            }
            break;

            case 3: {
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
        private static final String SCRIPT_PATH = "../scripts/update-user-profile.sh"; // Ensure the correct path
        private void updateProfile (String email, String fieldName, String newValue) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder(
                        "/bin/bash",
                        SCRIPT_PATH,
                        email,
                        fieldName,
                        newValue
                );
                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    System.out.println("Error: Script exited with code " + exitCode);
                }
            } catch (IOException e) {
                System.err.println("IOException: " + e.getMessage());
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.err.println("InterruptedException: " + e.getMessage());
                e.printStackTrace();
            }
        }

    // Method to process a single line of data and calculate life span
    public int calculateLifeSpan(String healthData) {
        String[] fields = healthData.split(",");

            String diagnosisDate = fields[9];
            String artStartDate = fields[11];
            String country = fields[7];
            String dateOfBirth = fields[6];

            int yearOfDiagnosis = Integer.parseInt((diagnosisDate.split("-")[2]).trim());
            int yearTreatmentStarted = Integer.parseInt((artStartDate.split("-")[2]).trim());
            int yearOfBirth = Integer.parseInt((dateOfBirth.split("-")[2]).trim());

            
            // Get life expectancy from the CSV data
            HealthDataCompiler compiler = new HealthDataCompiler();

            String lifeExpectancyStr = compiler.getLifeExpectancy(country);

            // System.out.println(country);
            // double lifeExpectancy = Double.parseDouble(lifeExpectancyStr);

            double lifeExpectancy = 63.2;

            // patientAge
            int patientsAge = 2024 - yearOfBirth;

            int yearsDelayed = yearTreatmentStarted - yearOfDiagnosis;
            double lifeSpan = Math.round((lifeExpectancy - patientsAge) * Math.pow(0.9, yearsDelayed + 1));

            if (yearsDelayed >= 5) {
                lifeSpan = 5;
            }
            // System.out.println("This is the life span" + lifeSpan);
            return (int) lifeSpan;
        
    }

    public void navigationOptions () {
        
        boolean navMenuSelection = false;

                while (!navMenuSelection) {

                System.out.println("                                                       ");
                System.out.println("Select next action");
                System.out.println("                                                       ");
                System.out.println("00. Return to the Patient Main Menu" +
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

