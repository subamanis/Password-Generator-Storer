package manager;

//import manager.io.IOHandler;
import manager.io.JsonTest;
//import manager.logic.Account;
//import manager.logic.Utilities;
//import manager.security.SecurityHandler;

import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;

public final class Main
{
//    private List<Account> savedAccounts;
//    private final Scanner sc = new Scanner(System.in);
//    private static SecureRandom srng = new SecureRandom();
//
    public static void main(String[] args)
    {
        new Main().run();
    }

    private void run()
    {
        new JsonTest().run();
//        IOHandler.validateDataFileAndDirectory();
//        savedAccounts = IOHandler.readFromFile();
//
//        initiateLoginSequence();
//
//        while(true){
//            showMenu();
//            int menuOption = Utilities.getIntInputWithBounds("\nChoose an action: ", 1, 8);
//            if(handleMenuOptionInput(menuOption)){
//                IOHandler.saveAccounts(savedAccounts);
//            }
//        }

    }
//
//    private void initiateLoginSequence()
//    {
//        System.out.println("Hello and Welcome to the password manager!");
//        if(SecurityHandler.getStoredHashedPass() == null){
//            System.out.println("Firstly you need to create a password for the app (>7 characters).");
//            Utilities.getPasswordInputWithCheckAndSave("Enter password: ");
//            System.out.println("It is highly recommended to read the useful information from the menu before using the app.");
//        }else{
//            if(!Utilities.tryLogin()){
//                System.exit(1);
//            }
//        }
//        SecurityHandler.hashAndSetUserKey(Utilities.getPasswordInputNoCheck("Give me the key: "));
//    }
//
//    private void showMenu()
//    {
//        System.out.println("\n1: Generate alphanumeric password");
//        System.out.println("2: Retrieve password");
//        System.out.println("3: Manage an account");
//        System.out.println("4: Delete an account");
//        System.out.println("5: Show details of all accounts");
//        System.out.println("6: Store an existing account");
//        System.out.println("7: Show useful information");
//    }
//
//    private boolean handleMenuOptionInput(final int menuOption)
//    {
//        switch (menuOption){
//            case 1:
//                return generateAlphanumericPassword();
//            case 2:
//                retrievePassword();
//                return false;
//            case 3:
//                return manageAccount();
//            case 4:
//                return deleteAccount();
//            case 5:
//                showAccountDetails();
//                return false;
//            case 6:
//                storeExistingAccount();
//                return true;
//            case 7:
//                showUsefulInfo();
//                return true;
//        }
//
//        return false;
//    }
//
//    private boolean generateAlphanumericPassword()
//    {
//        String password = createAlphanumericPassword();
//        if(password == null) return false;
//        System.out.println("Your password is: "+password);
//
//        password = SecurityHandler.encrypt(password);
//        if(password == null) return false;
//
//        Account account = fillAccountDetails(password);
//
//        System.out.println("\nAccount info: "+account);
//        System.out.print("\nDo you want to edit something? ");
//        if(sc.next().toLowerCase().equals("yes")){
//            int choice = Utilities.getIntInputWithBounds(
//                    "\n1)Edit website name\n2)Edit username\n3)Edit email\n4)Edit extraInfo\n0)Cancel edit",0,4);
//
//            switch (choice){
//                case 0:
//                    return true;
//                case 1:
//                    System.out.print("Give a new site name: ");
//                    sc.nextLine();
//                    account.changeSiteName(sc.nextLine());
//                    break;
//                case 2:
//                    System.out.print("\nGive a new username: ");
//                    sc.nextLine();
//                    account.changeUsername(sc.nextLine());
//                    break;
//                case 3:
//                    System.out.print("\nGive me a new email address: ");
//                    account.changeEmail(sc.next());
//                    break;
//                case 4:
//                    System.out.print("\nGive some new additional info:");
//                    sc.nextLine();
//                    account.changeExtraInfo(sc.nextLine());
//                    break;
//            }
//            System.out.println("\nEdits saved.");
//
//        }
//        savedAccounts.add(account);
//        System.out.println("Account creation successful!");
//        return true;
//    }
//
//    private void retrievePassword()
//    {
//        if(!checkForStoredAccounts()) return;
//
//        printAccounts();
//        int accountNumber = Utilities.getIntInputWithBounds(
//                "\nChoose the number of the website that you want to retrieve the password: ", 1, savedAccounts.size());
//        System.out.print("The password is: "+SecurityHandler.decrypt(savedAccounts.get(accountNumber-1).getPassword())+"\n");
//    }
//
//    private boolean manageAccount()
//    {
//        if(!checkForStoredAccounts()) return false;
//
//        printAccounts();
//        int accountNumber = Utilities.getIntInputWithBounds("\nChoose the number of a website: ", 1, savedAccounts.size());
//        Account ac = savedAccounts.get(accountNumber - 1);
//
//        return editAccountMenu(ac);
//    }
//
//    private boolean deleteAccount()
//    {
//        if(!checkForStoredAccounts()) return false;
//
//        printAccounts();
//        int accountNumber = Utilities.getIntInputWithBounds("\nChoose the number of the account you want to delete: ",
//                1, savedAccounts.size());
//        System.out.print("\nAre you sure you want to delete this account?: ");
//        if(sc.next().toLowerCase().equals("yes")){
//            savedAccounts.remove(accountNumber - 1);
//            System.out.println("The account was successfully removed.");
//            System.out.print("\nType anything to go back: ");
//            sc.next();
//            return true;
//        }
//        return false;
//    }
//
//    private void showAccountDetails()
//    {
//        checkForStoredAccounts();
//        int counter=1;
//        for(Account ac : savedAccounts){
//            System.out.print("\n"+counter+")");
//            System.out.println(ac);
//            System.out.println("Password: "+SecurityHandler.decrypt(ac.getPassword()));
//            counter++;
//        }
//        System.out.print("\nType anything to go back: ");
//        sc.next();
//    }
//
//    private void storeExistingAccount()
//    {
//		String password = new String(Utilities.getPasswordInputNoCheck("\nEnter your account's password: "));
//        password = SecurityHandler.encrypt(password);
//        Account ac = fillAccountDetails(password);
//        savedAccounts.add(ac);
//        System.out.println("\nYour account has been successfully added.");
//        System.out.println("Type anything to go back: ");
//        sc.next();
//    }
//
//    private void showUsefulInfo()
//    {
//        System.out.println("\n-This program will create a .txt file, in which all your date will be stored and loaded "+
//                "from\n Do NOT manually modify this file.\n\n-When you first launched the program you were asked to enter a password\n"+
//                " This password is used for authentication and is stored strongly encrypted in the txt file."+
//                "\n\n-After entering your password you are asked to enter a key, this is used for the encryption of your accounts'\n"+
//                " passwords and is not stored anywhere so it cannot be retrieved. The key can be changed anytime from the menu."+
//                "\n\n-All passwords WILL contain at least one number."+
//                "\n\n-When asked a yes/no question, typing anything other than 'yes'(not case sensitive) will be taken as a 'no' "+
//                "\n\n-You can enter as input multiple words for the website name and the additional information, but only 1 word"+
//                "\n for the email and the username.\n\n-1 = one, O = upper o(letter), there is no zero and lower L for clear reasons.");
//    }
//
//
//
//    // ***************************************- HELPER FUNCTIONS -******************************************
//
//
//
//    private boolean checkForStoredAccounts(){
//        if(savedAccounts.size()==0){
//            System.out.println("There no accounts stored at the moment.");
//            return false;
//        }
//
//        return true;
//    }
//
//    private String createAlphanumericPassword() {
//        int passwordLength = Utilities.getIntInputWithBoundExcludingCharacter
//                ("\nChoose a length for the password(more that 4 characters) or type 0 to cancel: ", 4, 30, 0);
//        if(passwordLength == 0) return null;
//
//        System.out.print("Do you need special characters?(e.g. @ # !): ");
//        boolean hasSpecialChars = false;
//        if(sc.next().toLowerCase().equals("yes")){
//            hasSpecialChars=true;
//        }
//        System.out.println("\nGenerating password...");
//
//        //select random characters as password
//        char[] passwordCharacters = new char[passwordLength];
//        for(int i=0; i<passwordLength; i++){
//            passwordCharacters[i] = Utilities.characters[srng.nextInt(Utilities.characters.length)];
//        }
//
//        //(almost) ensure that the password will have at least one number
//        passwordCharacters[srng.nextInt(passwordLength)] = Utilities.numbers[srng.nextInt(Utilities.numbers.length)];
//
//        //add special character if needed
//        if(hasSpecialChars){
//            passwordCharacters[srng.nextInt(passwordLength)] = Utilities.specialCharacters[srng.nextInt(Utilities.specialCharacters.length)];
//        }
//
//        return new String(passwordCharacters);
//    }
//
//    private Account fillAccountDetails(String password){
//        String siteName,username,email,extraInfo;
//        System.out.print("\nGive me the name of the website: ");
//		sc.nextLine();
//        siteName=sc.nextLine();
//        System.out.print("\nGive me the username you used: ");
//        username=sc.nextLine();
//        System.out.print("\nGive me the email you used: ");
//        email=sc.next();
//        System.out.print("\nAny additional info: ");
//        sc.nextLine();
//        extraInfo=sc.nextLine();
//        return new Account(siteName,username,email,extraInfo,password);
//    }
//
//    private void printAccounts(){
//        System.out.print("\nThese are the websites for which I have an account stored: \n");
//        for(int i=0;i<savedAccounts.size();i++){
//            System.out.println((i+1)+") "+savedAccounts.get(i).getSiteName());
//        }
//    }
//
//    private boolean editAccountMenu(Account ac)
//    {
//        int selectedNum;
//        boolean accountEdited = false;
//        do{
//            System.out.println("\nAccount info: "+ac);
//            System.out.println("Password: "+SecurityHandler.decrypt(ac.getPassword()));
//            System.out.println();
//            selectedNum = Utilities.getIntInputWithBounds("\n0)Back\n1)Edit site name\n2)Edit username\n3)Edit email\n4)Edit extraInfo\n5)Change password",
//                    0, 5);
//            switch (selectedNum){
//                case 1:
//                    System.out.print("Give a new site name: ");
//                    sc.nextLine();
//                    ac.changeSiteName(sc.nextLine());
//                    System.out.println("Site name changed successfully");
//                    accountEdited = true;
//                    break;
//                case 2:
//                    System.out.print("\nGive a new username: ");
//                    sc.nextLine();
//                    ac.changeUsername(sc.nextLine());
//                    System.out.println("Username changed successfully");
//                    accountEdited = true;
//                    break;
//                case 3:
//                    System.out.print("\nGive a new email: ");
//                    ac.changeEmail(sc.next());
//                    System.out.println("Email changed successfully");
//                    accountEdited = true;
//                    break;
//                case 4:
//                    System.out.print("\nGive some new additional info:");
//                    sc.nextLine();
//                    ac.changeExtraInfo(sc.nextLine());
//                    System.out.println("Additional information changed successfully");
//                    accountEdited = true;
//                    break;
//                case 5:
//                    System.out.println("\n1)Generate a new password\n2)Add your own custom password\n0)Go back");
//                    int selectedNumber=sc.nextInt();
//                    if(selectedNumber==1){
//                        String password = createAlphanumericPassword();
//                        if(password == null) break;
//                        System.out.println("Your new password is: "+password);
//                        password = SecurityHandler.encrypt(password);
//                        ac.changePassword(password);
//                    }
//                    else if(selectedNumber==2){
//                        System.out.print("Enter your new password: ");
//                        String password = sc.next();
//                        System.out.println("Password changed to: "+password);
//                        password = SecurityHandler.encrypt(password);
//                        ac.changePassword(password);
//                        accountEdited = true;
//                    }
//                    break;
//            }
//        }while(selectedNum == 0);
//
//        return accountEdited;
//    }

}
