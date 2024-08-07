import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Menu menu = new Menu();

        menu.welcome();

        selectOperation (menu.main());

    }

    public static void selectOperation( int userSelectedOption) {

        switch (userSelectedOption) {
            case 1: {
                // Admin admin = new Admin();
                // admin.selectOption(admin.menu());
                Patient patient = new Patient();
                patient.selectOption(patient.menu());


            }
                break;

            case 2: {
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


}