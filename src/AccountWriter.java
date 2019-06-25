import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;

class AccountWriter {
    AccountWriter() {
    }

    public void writeToFile(ArrayList<Account> Accounts) {
        String fileName = "AccountData.txt";
        int counter = 0;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(PasswordGen.storedHashedPass);
            writer.newLine();
            writer.newLine();
            for (int remainingAccounts = Accounts.size(); remainingAccounts > 0; --remainingAccounts) {
                Account ac = Accounts.get(counter);
                writer.write("Account");
                writer.newLine();
                writer.write("\t" + ac.getSiteName());
                writer.newLine();
                writer.write("\t" + ac.getUsername());
                writer.newLine();
                writer.write("\t" + ac.getEmail());
                writer.newLine();
                writer.write("\t" + ac.getExtraInfo());
                writer.newLine();
                writer.write("\n" + ac.getPassword());
                writer.newLine();
                writer.newLine();
                ++counter;
            }
            writer.write("#");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
    }
}