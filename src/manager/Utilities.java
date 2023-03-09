package manager;

//import manager.io.IOHandler;
//import manager.security.SecurityHandler;

import com.petrosp.util.PUtils;
import manager.io.IOHandler;
import manager.io.Logger;
import manager.security.SecurityHandler;

import java.io.Console;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class Utilities
{
    private static final Scanner sc = new Scanner(System.in);
    private static final Console console = System.console();

    public static final Logger logger = new Logger(Logger.LogMode.DEBUG_PC);

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    public static final char[] characters = new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
        'Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f',
        'g','h','i','j','k','m','n','o','p','q','r','s','t','u','v',
        'w','x','y','z','1','2','3','4','5','6','7','8','9'};

    public static final char[] numbers = new char[] {'1','2','3','4','5','6','7','8','9'};

    public static final char[] specialCharacters = new char[] {'!','@','#','$','&'};


    public static void getPasswordInputWithCheckAndSave(String prompt)
    {
        String input;
        do{
            char[] password = console.readPassword(prompt);
            input = new String(password);
            System.out.print("Is ' "+input+" ' correct? ");
        }while(!sc.next().equalsIgnoreCase("yes"));

//        SecurityHandler.hashPassAndSetIfNotSet(input);
//        IOHandler.saveAccounts(new ArrayList<>());
    }

    public static char[] getPasswordInputNoCheck(String prompt)
    {
        return console.readPassword(prompt);
    }

    public static boolean tryLogin()
    {

        return true;
    }

    /* Ensures an int input from the user between two bounds.
     *  All the necessary checks are made.*/
    public static int getIntInputWithBounds(String promptMessage, int lowerBound, int upperBound)
    {
        int input = -1;
        while (true) {
            System.out.print(promptMessage);
            try {
                input = sc.nextInt();
            }catch (InputMismatchException e){
                sc.nextLine();
            }
            if(input >= lowerBound && input <= upperBound){
                break;
            }else{
                System.out.println("Incorrect input.");
            }
        }
        return input;
    }

    public static int getIntInputWithBoundExcludingCharacter(String promptMessage, int lowerBound, int upperBound, int exclusion)
    {
        int input = -1;
        while (true) {
            System.out.print(promptMessage);
            try {
                input = sc.nextInt();
            }catch (InputMismatchException e){
                sc.nextLine();
            }
            if((input >= lowerBound && input <= upperBound) || input == exclusion){
                break;
            }else{
                System.out.println("Incorrect input.");
            }
        }
        return input;
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
