package manager.io;

import manager.logic.Account;
import manager.security.SecurityHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class IOHandler
{
    public static final String FILE_DIRECTORY = "../data/";
    public static final String FILE_NAME = FILE_DIRECTORY + "AccountData.txt";

    public static void validateDataFileAndDirectory()
    {
        File dir = new File(FILE_DIRECTORY);
        File file = new File(FILE_NAME);

        if(!(dir.isDirectory())){
            dir.mkdir();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Account> readFromFile()
    {
        File file = new File(FILE_NAME);
        if(file.length() == 0){
            return new ArrayList<>();
        }

        List<Account> accounts = new ArrayList<>(30);
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line = br.readLine();
            SecurityHandler.hashPassAndSetIfNotSet(line);

            int counter = 0;
            String[] accountDetails = new String[5];
            do{
                line = br.readLine().trim();
                if(!line.equals("") && !line.equals("Account")){
                    if(counter == accountDetails.length){
                        counter = 0;
                        accounts.add(new Account(accountDetails[0], accountDetails[1], accountDetails[2],
                                accountDetails[3], accountDetails[4]));
                    }
                    accountDetails[counter] = line;
                    counter++;
                }
            }while(!line.equals("#"));
        }catch(IOException e){
            System.out.println("Error reading from file '"+ FILE_NAME + "'");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Wrong account file format");
            e.printStackTrace();
        }

        return accounts;
    }

    public static void saveAccounts(final List<Account> accounts){
        if(accounts == null || accounts.size() == 0){
            return;
        }

        try(PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))){
            pr.println(SecurityHandler.getStoredHashedPass());
            pr.println();

            int counter = 0;
            while(counter < accounts.size()){
                Account ac = accounts.get(counter);
                pr.println("Account");
                pr.println("\t"+ac.getSiteName());
                pr.println("\t"+ac.getUsername());
                pr.println("\t"+ac.getEmail());
                pr.println("\t"+ac.getExtraInfo());
                pr.println("\t"+ac.getPassword());
                pr.println();
                counter++;
            }

            pr.println();
            pr.print("#");
        }catch(IOException e){
            System.out.println("Error writing to file '"+ FILE_NAME + "'");
        }

    }
}
