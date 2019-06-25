import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordGen {
    static String storedHashedPass;
    private int key = 0;
    private Scanner sc = new Scanner(System.in);
    private Random rng = new Random();
    private char[] characters;
    private char[] numbers;
    private char[] specialCharacters;
    private ArrayList<Account> Accounts = new ArrayList<>();

    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
        PasswordGen pg = new PasswordGen();
        pg.run();
    }

    public void run() throws UnsupportedEncodingException, FileNotFoundException {
        String inputProgramPass;
        boolean empty;
        File ff = new File("AccountData.txt");
        boolean bl = empty = ff.length() == 0L;
        if (empty) {
            System.out.println("\nHello and welcome to the password manager!Firstly, you need to create a password.");
            System.out.println("This is the only password you need to remember and it is used to grant you access to the password manager.");
            do {
                System.out.print("\nGive me the password(recommended:at least 8 characters): ");
                inputProgramPass = this.sc.next();
                if (inputProgramPass.equals("exit")) {
                    System.exit(1);
                }
                System.out.print("Is ' " + inputProgramPass + " ' correct? ");
            } while (!this.sc.next().toLowerCase().equals("yes"));
            storedHashedPass = this.hashProgramPass(inputProgramPass);
            PrintWriter w = new PrintWriter("AccountData.txt", "UTF-8");
            System.out.println("\nIt is strongly recommended to read the useful information from the menu before using the app");
        } else {
            AccountReader reader = new AccountReader();
            reader.readFromFile(this.Accounts);
            System.out.println("Welcome to the password manager!");
            int counter = 1;
            do {
                System.out.print("Enter your password: ");
                inputProgramPass = this.sc.next();
                if (this.checkProgramPass(inputProgramPass, storedHashedPass)) break;
                System.out.println("Wrong password." + (3 - counter) + " attempts remaining.");
                if (++counter != 4) continue;
                System.exit(1);
            } while (true);
        }
        do {
            try {
                System.out.print("\nGive me the encryption key: ");
                this.key = this.sc.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println(e + ", please enter an integer NUMBER.");
                this.sc.nextLine();
                continue;
            }
            break;
        } while (true);
        this.initializeArrays();
        this.navigateManager();
    }

    private void printMenu() {
        System.out.println("\n1: Generate password");
        System.out.println("2: Retrieve password");
        System.out.println("3: Manage an account");
        System.out.println("4: Delete an account");
        System.out.println("5: Show details of all accounts");
        System.out.println("6: Change the key");
        System.out.println("7: Store an existing account");
        System.out.println("8: Open a web page");
        System.out.println("9: Show useful information");
        System.out.println("10: Save and exit");
    }

    private void navigateManager() throws InputMismatchException {
        do {
            int userAnswer;
            this.printMenu();
            block17 : do {
                try {
                    do {
                        System.out.print("\nChoose an action: ");
                        userAnswer = this.sc.nextInt();
                        if (userAnswer >= 1 && userAnswer <= 10) break block17;
                        System.out.println("\nInvalid input");
                    } while (true);
                }
                catch (InputMismatchException e) {
                    System.out.println(e + ", please enter an integer NUMBER.");
                    this.sc.nextLine();
                    continue;
                }
            } while (true);
            try {
                switch (userAnswer) {
                    case 1: {
                        this.answer1();
                        break;
                    }
                    case 2: {
                        this.answer2();
                        break;
                    }
                    case 3: {
                        this.answer3();
                        break;
                    }
                    case 4: {
                        this.answer4();
                        break;
                    }
                    case 5: {
                        this.answer5();
                        break;
                    }
                    case 6: {
                        this.answer6();
                        break;
                    }
                    case 7: {
                        this.answer7();
                        break;
                    }
                    case 8: {
                        this.answer8();
                        break;
                    }
                    case 9: {
                        this.answer9();
                        break;
                    }
                    case 10: {
                        this.answer10();
                    }
                }
                continue;
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input");
                continue;
            }
        } while (true);
    }

    private void answer1() {
        String password;
        do {
            try {
                password = this.createPassword();
            }
            catch (InputMismatchException e) {
                System.out.println(e + ", please enter an integer NUMBER.");
                this.sc.nextLine();
                continue;
            }
            break;
        } while (true);
        System.out.println("Your password is: " + password);
        password = this.encryptPassword(password);
        Account account = this.fillAccountDetails(password);
        System.out.println("\nAccount info: " + account);
        System.out.print("\nDo you want to edit something? ");
        if (this.sc.next().toLowerCase().equals("yes")) {
            int choice;
            do {
                try {
                    System.out.println("\n1)Edit website name\n2)Edit username\n3)Edit email\n4)Edit extraInfo\n0)Cancel edit");
                    choice = this.sc.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("\n" + e + ", please enter an integer NUMBER.");
                    this.sc.nextLine();
                    continue;
                }
                break;
            } while (true);
            if (choice == 1) {
                System.out.print("Give a new site name: ");
                this.sc.nextLine();
                account.changeSiteName(this.sc.nextLine());
            } else if (choice == 2) {
                System.out.print("\nGive a new username: ");
                this.sc.nextLine();
                account.changeUsername(this.sc.nextLine());
            } else if (choice == 3) {
                System.out.print("\nGive me a new email address: ");
                account.changeEmail(this.sc.next());
            } else if (choice == 4) {
                System.out.print("\nGive some new additional info:");
                this.sc.nextLine();
                account.changeExtraInfo(this.sc.nextLine());
            }
            if (choice == 1 || choice == 2 || choice == 3) {
                System.out.println("\nEdits saved.");
            }
        }
        this.Accounts.add(account);
        System.out.println("Account creation successful!");
    }

    private void answer2() {
        this.checkForStoredAccounts();
        this.printAccounts();
        System.out.print("\nChoose the number of the website that you want to retrieve the password: ");
        int siteNumber = this.sc.nextInt();
        for (int i = 0; i < this.Accounts.size(); ++i) {
            if (i != siteNumber) continue;
            System.out.print("The password is: " + this.decryptPassword(this.Accounts.get(i).getPassword()) + "\n");
            break;
        }
    }

    private void answer3() throws InputMismatchException {
        int selectedNum;
        this.checkForStoredAccounts();
        Account ac = null;
        this.printAccounts();
        do {
            System.out.print("\nChoose the number of a website: ");
            int siteNumber = this.sc.nextInt();
            for (int i = 0; i < this.Accounts.size(); ++i) {
                if (i != siteNumber) continue;
                ac = this.Accounts.get(i);
            }
            if (ac != null) continue;
            System.out.println("Error:please give an input that matches an account number.");
        } while (ac == null);
        do {
            String password;
            System.out.println("\nAccount info: " + ac);
            System.out.println("\n1)Edit site name\n2)Edit username\n3)Edit email\n4)Edit extraInfo\n5)Change password\n0)Back");
            selectedNum = this.sc.nextInt();
            if (selectedNum == 1) {
                System.out.print("Give a new site name: ");
                this.sc.nextLine();
                ac.changeSiteName(this.sc.nextLine());
                System.out.println("Site name changed successfuly");
                continue;
            }
            if (selectedNum == 2) {
                System.out.print("\nGive a new username: ");
                this.sc.nextLine();
                ac.changeUsername(this.sc.nextLine());
                System.out.println("Username changed successfuly");
                continue;
            }
            if (selectedNum == 3) {
                System.out.print("\nGive a new email: ");
                ac.changeEmail(this.sc.next());
                System.out.println("Email changed successfuly");
                continue;
            }
            if (selectedNum == 4) {
                System.out.print("\nGive some new additional info:");
                this.sc.nextLine();
                ac.changeExtraInfo(this.sc.nextLine());
                System.out.println("Additional information changed successfully");
                continue;
            }
            if (selectedNum != 5) continue;
            System.out.println("\n1)Generate a new password\n2)Add your own custom password\n0)Go back");
            int selectedNumber = this.sc.nextInt();
            if (selectedNumber == 1) {
                password = this.createPassword();
                System.out.println("Your new password is: " + password);
                password = this.encryptPassword(password);
                ac.changePassword(password);
                continue;
            }
            if (selectedNumber != 2) continue;
            System.out.print("Enter your new password: ");
            password = this.sc.next();
            System.out.println("Password changed to: " + password);
            password = this.encryptPassword(password);
            ac.changePassword(password);
        } while (selectedNum > 0);
    }

    private void answer4() {
        this.checkForStoredAccounts();
        this.printAccounts();
        System.out.print("\nChoose the number of the account you want to delete: ");
        int siteNumber = this.sc.nextInt();
        System.out.print("\nAre you sure you want to delete this account?: ");
        if (this.sc.next().toLowerCase().equals("yes")) {
            this.Accounts.remove(siteNumber);
            System.out.println("The account was successfully removed.");
            System.out.print("\nType anything to go back: ");
            this.sc.next();
        }
    }

    private void answer5() {
        this.checkForStoredAccounts();
        int counter = 1;
        for (Account acc : this.Accounts) {
            System.out.print("\n" + counter + ")");
            System.out.println(acc);
            ++counter;
        }
        System.out.print("\nType anything to go back: ");
        this.sc.next();
    }

    private void answer6() {
        System.out.print("\nGive me the new encryption key: ");
        this.key = this.sc.nextInt();
        System.out.println("The key has changed successfully!");
    }

    private void answer7() {
        System.out.print("\nEnter your account's password: ");
        String password = this.sc.next();
        password = this.encryptPassword(password);
        Account account = this.fillAccountDetails(password);
        this.Accounts.add(account);
        System.out.println("\nYour account has been successfully added.");
        System.out.println("Type anything to go back: ");
        this.sc.next();
    }

    private void answer8() {
        System.out.println("0) Origin\n1) Uplay\n2) Gmail\n3) Steam\n4) Paysafe\n5) Paypal\n6) GitHub\n7) Mi Account\n8) Facebook\n9) Instagram\n10 GOG Galaxy\n11 router(HOL_ZTE_4)\n12) LinkedIn\n13) Twitch\n14) BitBucket\n15) Mega\n16) Epic Games");
        System.out.print("\nChoose the website you want to open: ");
        int i = this.sc.nextInt();
        NetCommunicator.openWebPage(i);
    }

    private void answer9() {
        System.out.println("\nTake a look at these useful tips concerning the correct use of the app:");
        System.out.println("\n-This program will create a .txt file, in which all your date will be stored and loaded from\n Do NOT manually modify this file.\n\n-When you first launched the program you were asked to enter a password\n This password is used for authentication and is stored strongly encrypted in the txt file.\n\n-After entering your password you were asked to enter a key,this is used for the ecryption of your accounts'\n passwords and is not stored anywhere so it cannot be retrieved.The key can be changed anytime from the menu.\n\n-All passwords WILL contain at least one number.\n\n-When asked a yes/no question, typing anything other than 'yes'(not case sensitive) will be taken as a 'no' \n\n-You can enter as input multiple words for the website name and the additional information, but only 1 word\n for the email and the username.\n\n- 1 = one, O = upper o(letter), there is no zero and lower L for clear reasons.");
        System.out.print("\nType anything to go back: ");
        this.sc.next();
    }

    private void answer10() {
        AccountWriter writer = new AccountWriter();
        System.out.print("Are you sure you want to exit the program?: ");
        if (this.sc.next().toLowerCase().equals("yes")) {
            writer.writeToFile(this.Accounts);
            System.exit(0);
        }
    }

    private void printAccounts() {
        System.out.print("\nThese are the websites for which I have an account stored: \n");
        for (int i = 0; i < this.Accounts.size(); ++i) {
            System.out.println(i + ") " + this.Accounts.get(i).getSiteName());
        }
    }

    private void checkForStoredAccounts() {
        if (this.Accounts.size() == 0) {
            System.out.print("There no accounts stored at the moment.\n\nType anything to go back: ");
            this.sc.next();
            this.navigateManager();
        }
    }

    private Account fillAccountDetails(String password) {
        System.out.print("\nGive me the name of the website: ");
        this.sc.nextLine();
        String siteName = this.sc.nextLine();
        System.out.print("\nGive me the username you used: ");
        String username = this.sc.nextLine();
        System.out.print("\nGive me the email you used: ");
        String email = this.sc.next();
        System.out.print("\nAny additional info: ");
        this.sc.nextLine();
        String extraInfo = this.sc.nextLine();
        return new Account(siteName, username, email, extraInfo, password);
    }

    private boolean checkProgramPass(String inputProgramPass, String storedHash) {
        boolean passwordVerified = false;
        if (null == storedHash || !storedHash.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hash provided for comparison");
        }
        passwordVerified = BCrypt.checkpw((String)inputProgramPass, (String)storedHash);
        return passwordVerified;
    }

    private String hashProgramPass(String inputProgramPass) {
        String salt = BCrypt.gensalt((int)12);
        return BCrypt.hashpw((String)inputProgramPass, (String)salt);
    }

    private String createPassword() throws InputMismatchException {
        char currentChar;
        boolean hasSpecialChars = false;
        StringBuilder password = new StringBuilder();
        System.out.print("\nChoose a length for the password(more that 3 characters) or type 0 to cancel: ");
        int passwordLength = this.sc.nextInt();
        if (passwordLength <= 3) {
            System.out.println("\nPassword creation canceled.");
            this.navigateManager();
        }
        System.out.print("Do you need special characters?(e.g. @ # !): ");
        if (this.sc.next().toLowerCase().equals("yes")) {
            hasSpecialChars = true;
        }
        System.out.println("\nGenerating password...");
        for (int i = 0; i < passwordLength; ++i) {
            currentChar = this.characters[this.rng.nextInt(this.characters.length)];
            password.append(currentChar);
        }
        if (hasSpecialChars) {
            currentChar = this.specialCharacters[this.rng.nextInt(this.specialCharacters.length)];
            password = new StringBuilder(password.substring(0, passwordLength - 1));
            password.append(currentChar);
            return password.toString();
        }
        int randomIndex = this.rng.nextInt(passwordLength - 2) + 1;
        password = new StringBuilder(password.substring(0, randomIndex - 1) + this.numbers[this.rng.nextInt(this.numbers.length)] + password.substring(randomIndex, passwordLength));
        return password.toString();
    }

    private String encryptPassword(String password) {
        StringBuilder encryptedPassword = new StringBuilder();
        if (this.key < 0) {
            this.key *= -1;
        }
        this.key -= this.key / 126 * 126;
        for (int i = 0; i < password.length(); ++i) {
            int asciiValue;
            char currentChar = password.charAt(i);
            if (currentChar + this.key < 126) {
                asciiValue = currentChar + this.key;
            } else if (currentChar + this.key > 126) {
                int tempKey = this.key - (126 - currentChar);
                asciiValue = 32 + tempKey;
            } else {
                asciiValue = 126;
            }
            currentChar = (char)asciiValue;
            encryptedPassword.append(currentChar);
        }
        return encryptedPassword.toString();
    }

    private String decryptPassword(String encryptedPassword) {
        StringBuilder password = new StringBuilder();
        if (encryptedPassword.equals("")) {
            return "\nError:Could not find password for the requested website name";
        }
        if (this.key < 0) {
            this.key *= -1;
        }
        this.key -= this.key / 126 * 126;
        for (int i = 0; i < encryptedPassword.length(); ++i) {
            int asciiValue;
            char currentChar = encryptedPassword.charAt(i);
            if (currentChar - this.key > 32) {
                asciiValue = currentChar - this.key;
            } else {
                int tempKey = this.key - (currentChar - 32);
                asciiValue = 126 - tempKey;
            }
            currentChar = (char)asciiValue;
            password.append(currentChar);
        }
        return password.toString();
    }

    private void initializeArrays() {
        this.characters = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        this.numbers = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        this.specialCharacters = new char[]{'!', '@', '#', '$', '&'};
    }
}