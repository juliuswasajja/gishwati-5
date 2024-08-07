import java.util.Scanner;

public class Menu {

    public void welcome() {

        System.out.println("*******************************************************");
        System.out.println("                                                       ");
        System.out.println("     Welcome to the Life Prognosis Management Tool     ");
        System.out.println("                                                       ");
        System.out.println("*******************************************************");

    }

    public int main() {

        boolean validSelection = false;

        int selectedOption = 0;

        while (!validSelection) {

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

                // selectedOption
            } else {
                System.out.println("                                                       ");
                System.out.println("-------------------------------------------------------");
                System.out.println("Wrong selection, please select again");
                System.out.println("-------------------------------------------------------");

            }

        }

        return selectedOption;

    }

    // public void selectOption(int selectedOption) {

    //     switch (selectedOption) {
    //         case 1: {
    //             Admin admin = new Admin();
    //             admin.selectOption(admin.menu());
    //         }
    //             break;

    //         case 2: {
    //             Patient patient = new Patient();
    //             patient.completeRegistration();
    //         }
    //             break;

    //         case 3: {
    //             System.out.println("                                                       ");
    //             System.out.println("-------------------------------------------------------");
    //             System.out.println("----------Thank You For Using LPMT---------------------");
    //             System.out.println("-------------------------------------------------------");
    //             System.out.println("                                                       ");

    //             System.exit(0);
    //         }
    //             break;

    //         default: {

    //             System.out.println("                                                       ");
    //             System.out.println("-------------------------------------------------------");
    //             System.out.println("Wrong selection, please select again");
    //             System.out.println("-------------------------------------------------------");

    //         }
    //             break;
    //     }

    // }



}
