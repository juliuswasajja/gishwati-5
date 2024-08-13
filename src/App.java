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

                User user = new User();
                String userData = user.logIn();

                if ( (user.setRole(userData)).equals("patient") ) {
                    Patient patient = new Patient();
                    patient.healthData  = userData;
                    // patient.calculateLifeSpan(userData);
                    patient.selectOption(patient.menu());
                } else if ( (user.setRole(userData)).equals("admin")) {
                    Admin admin = new Admin();
                    admin.selectOption(admin.menu());
                } 
                else {
                    System.out.println("Unknow Role" + user.setRole(userData));
                    System.out.println(userData);
                    System.exit(0);
                }


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