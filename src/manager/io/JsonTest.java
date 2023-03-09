package manager.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import manager.Utilities;
import manager.domain.Account;
import manager.domain.acountfields.AccountField;
import manager.domain.acountfields.EmailField;
import manager.domain.acountfields.ExtraInfoField;
import manager.domain.acountfields.PasswordField;
import manager.domain.jackson.modules.ApplicationModule;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class JsonTest {

    public void run() {
        testIt();
    }

    public void testIt() {
        Utilities.logger.logError("test");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.registerModule(new ApplicationModule());

        try {
            List<AccountField> accountFields = List.of(new EmailField("test@yahoo.com"),
                    new ExtraInfoField("fdfdfdf"), new PasswordField("thisisapass"));
            var account = new Account(accountFields);

            mapper.writeValue(new File("./test.json"), account);
            Account accountDeserialized = mapper.readValue(new File("./test.json"), Account.class);
            System.out.println(accountDeserialized);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
