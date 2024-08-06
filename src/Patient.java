import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Patient {
        
        public String email;
        public String uuid;
        public String password;
    
    
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getUuid() {
            return uuid;
        }
        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }

        public String completeRegistrationMessage () {
            System.out.println("To Complete Registration Process, enter your UUID");

            Scanner scanner  = new Scanner( System.in );
            String patientUuid = scanner.next();

            return patientUuid;

        }

        public String verifyPatientUuid ( String verifyUuid) {

            String verifyPatientUuidCmd [] = {"/bin/bash","../scripts/check-patient-uuid.sh", verifyUuid};

            ProcessBuilder checkPatientUuid = new ProcessBuilder(verifyPatientUuidCmd);

               try {
            Process process = checkPatientUuid.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println(output);
                // return output.toString(); 
            } else {
                System.err.println("Error: Script execution failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

            return verifyUuid;

        }



        public String fetchPatientPassword () {

            String submittedPassword = "1";
            String reSubmittedPassword = "2";

            while (! (submittedPassword.equals(reSubmittedPassword)) ){

                System.out.println("Enter your password");
                System.out.println("----------------------------");

                Scanner scanner  = new Scanner( System.in );
                submittedPassword = scanner.next();

                System.out.println("Re - enter your password");
                System.out.println("----------------------------");

                reSubmittedPassword = scanner.next();

                if ( submittedPassword.equals(reSubmittedPassword) ) {
                System.out.println("Password correct");

                }else {
                    System.out.println("Password incorrect try again");
                }
                
            }
    
            return submittedPassword;

        }

        public void encryptPassword ( String verifyUuid, String userPassword) {

            String [] encryptPasswordCmd = {"/bin/bash", "../scripts/encrypt-password.sh", verifyUuid, userPassword  };

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
         
            // String encryptedPassword ;
    
            // return null;
        }

        public void completeHealthRegistration (String uuid ) {

            System.out.println("Enter your First Name");
            System.out.println("----------------------------");
    
            Scanner scanner  = new Scanner( System.in );
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

            // public void submitHealthRegistration ( String uuid, String firstName, String lastName, String country, String age, String hivStatus, String disgnosisDate, String artStatus, String artStartDate ) {
                
                String[] completePatientRegistrationUCmd = {
                    "/bin/bash", "../scripts/complete-registration.sh",
                    uuid, firstName, lastName, country, age, hivStatus, disgnosisDate, artStatus, artStartDate };

                    System.out.println( uuid + firstName + lastName + country + age + hivStatus + disgnosisDate + artStatus + artStartDate );

    
                    ProcessBuilder updateUserProcess = new ProcessBuilder(completePatientRegistrationUCmd);
                    // Set the working directory to ensure correct paths
            
                    try {
                        Process process = updateUserProcess.start();
            
                        // Read the output
                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        StringBuilder output = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            output.append(line).append("\n");
                        }
            
                        // Read errors
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
    
    
                // String registeredData = firstName;
                
                //  return null;




        }
        
}
