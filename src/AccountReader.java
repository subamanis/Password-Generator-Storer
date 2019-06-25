import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;

class AccountReader {
    AccountReader() {
    }

    public void readFromFile(ArrayList<Account> Accounts) {
        String fileName = "AccountData.txt";
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            PasswordGen.storedHashedPass = line = reader.readLine().trim();
            reader.readLine();
            line = reader.readLine().trim();
            while (!line.equals("#")) {
                if (!line.trim().equals("Account")) {
                    throw new IOException();
                }
                Account ac = new Account();
                line = reader.readLine().trim();
                ac.changeSiteName(line);
                line = reader.readLine().trim();
                ac.changeUsername(line);
                line = reader.readLine().trim();
                ac.changeEmail(line);
                line = reader.readLine().trim();
                ac.changeExtraInfo(line);
                line = reader.readLine().trim();
                line = reader.readLine().trim();
                ac.changePassword(line);
                line = reader.readLine().trim();
                line = reader.readLine().trim();
                Accounts.add(ac);
            }
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Error reading from file '" + fileName + "'");
        }
    }
}