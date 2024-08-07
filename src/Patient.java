import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Patient {

    public String email;
    public String uuid;
    public String password;

    // public String getEmail() {
    // return email;
    // }
    // public void setEmail(String email) {
    // this.email = email;
    // }
    // public String getUuid() {
    // return uuid;
    // }
    // public void setUuid(String uuid) {
    // this.uuid = uuid;
    // }
    // public String getPassword() {
    // return password;
    // }
    // public void setPassword(String password) {
    // this.password = password;
    // }

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
                // System.out.println(output);
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
                System.out.println("Password matches");

            } else {
                System.out.println("Password does not match, please try again");
            }

        }

        return submittedPassword;

    }

    public void encryptPassword(String patientUuid, String userPassword) {

        String[] encryptPasswordCmd = { "/bin/bash", "../scripts/encrypt-password.sh", patientUuid, userPassword };

        ProcessBuilder encryptPatientPassword = new ProcessBuilder(encryptPasswordCmd);

        try {
            Process process = encryptPatientPassword.start();

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Password encrypted.");
            } else {
                System.err.println("Password encryption failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void healthDataRegistration(String patientUuid) {

        System.out.println("Enter your First Name");
        System.out.println("----------------------------");

        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.next();

        System.out.println("Enter your Last Name");
        System.out.println("----------------------------");

        String lastName = scanner.next();

        System.out.println("Enter your Country");
        System.out.println("----------------------------");

        String country = scanner.next();

        System.out.println("Enter your Age");
        System.out.println("----------------------------");

        String age = scanner.next();

        System.out.println("Enter your HIV Status");
        System.out.println("----------------------------");

        String hivStatus = scanner.next();

        System.out.println("Enter your HIV Diagnosis Date");
        System.out.println("----------------------------");

        String disgnosisDate = scanner.next();

        System.out.println("Enter your ART Status");
        System.out.println("----------------------------");

        String artStatus = scanner.next();

        System.out.println("Enter your ART start Date");
        System.out.println("----------------------------");

        String artStartDate = scanner.next();

        String[] completeRegistrationCmd = {
                "/bin/bash", "../scripts/complete-registration.sh",
                patientUuid, firstName, lastName, country, age, hivStatus, disgnosisDate, artStatus, artStartDate };

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

        //calculate the life expectancy

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
                System.out.println("Here is your profile");
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
