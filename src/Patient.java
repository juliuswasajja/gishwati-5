import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Patient extends User {

    public String data;

    public String uuid;
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String country;
    public String hivStatus = "0";
    public String diagnosisDate;
    public String artStatus = "0";
    public String artStartDate;

    public void viewData(String data) {

        String[] fields = data.split(",");

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

            System.out.println("Email: " + email);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Date of Birth: " + dateOfBirth);
            System.out.println("Country: " + country);
            System.out.println("HIV Status: " + hivStatus);
            if (hivStatus.equals("P")) {
                System.out.println("Diagnosis Date: " + diagnosisDate);
                System.out.println("ART Status: " + artStatus);
                if (artStatus.equals("Y")) {
                    System.out.println("ART Start Date: " + artStartDate);
                }
            }
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

            Scanner scanner = new Scanner(System.in);
            submittedPassword = scanner.next();

            System.out.println("Re - enter your password");
            System.out.println("----------------------------");

            reSubmittedPassword = scanner.next();

            if (submittedPassword.equals(reSubmittedPassword)) {
                System.out.println("Passwords match");

            } else {
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

        System.out.println("Enter your First Name");
        System.out.println("----------------------------");

        Scanner scanner = new Scanner(System.in);
        firstName = scanner.next();

        System.out.println("Enter your Last Name");
        System.out.println("----------------------------");

        lastName = scanner.next();

        System.out.println("Enter your Date of Birth");
        System.out.println("----------------------------");

        dateOfBirth = scanner.next();

        System.out.println("Enter your Country");
        System.out.println("----------------------------");

        country = scanner.next();

        while (!(hivStatus.equals("P"))  && !(hivStatus.equals("N"))) {

            System.out.println("Enter your HIV Status");
            System.out.println("----------------------------");

            System.out.println("If Positive, type P\nIf Negative, type N");
            hivStatus = scanner.next();

        }

        if (hivStatus.equals("P")) {

            System.out.println("Enter your HIV Diagnosis Date");
            System.out.println("----------------------------");

            diagnosisDate = scanner.next();

            while (!(artStatus.equals("Y"))) {

                System.out.println("Enter your ART Status");
                System.out.println("----------------------------");

                System.out.println("If Yes, type Y\nIf No, type N");


                artStatus = scanner.next();

            }

            if (artStatus.equals("Y")) {

                System.out.println("Enter your ART start Date");
                System.out.println("----------------------------");

                artStartDate = scanner.next();

            }

        }

        String[] completeRegistrationCmd = {
                "/bin/bash", "../scripts/complete-registration.sh",
                uuid, firstName, lastName, dateOfBirth, country, hivStatus, diagnosisDate, artStatus, artStartDate };

        // System.out.println( patientUuid + firstName + lastName + country + age +
        // hivStatus + disgnosisDate + artStatus + artStartDate );

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
                System.out.println("Output: " + output);
                System.out.println("User information updated successfully.");
            } else {
                System.err.println("Error Output: " + errorOutput);
                System.err.println("User information update failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // calculate the life expectancy

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
            System.out.println("-------------------------------------------------------");
            System.out.println("                                                       ");
            System.out.println("Enter option 1 - 2");
            System.out.println("                                                       ");

            Scanner scanner = new Scanner(System.in);
            String userSelection = scanner.next();

            try {
                selectedOption = Integer.parseInt(userSelection);
            } catch (NumberFormatException e) {
                selectedOption = 0;
            }

            if (selectedOption == 1 ||
                    selectedOption == 2) {
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
                System.out.println("-------------------------------------------------------");
                System.out.println("Here is your Health Data");
                System.out.println("-------------------------------------------------------");
                viewData(data);
                System.out.println("-------------------------------------------------------");
            }
                break;

            case 2: {
                System.out.println(" Update Profile");
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

}
