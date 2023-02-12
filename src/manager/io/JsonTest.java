package manager.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import manager.domain.Account;
import manager.domain.acountfields.AccountField;
import manager.domain.acountfields.EmailField;
import manager.domain.acountfields.ExtraInfoField;
import manager.domain.acountfields.PasswordField;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class JsonTest {
//    Json

    private ObjectMapper mapper = new ObjectMapper();

    public void run() {
        testIt();
    }

    public void testIt() {
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);


//        var acc = new Account("twitter", "petros21",
//                        "petros@dd", "none", "password", LocalDateTime.now());
//        var acc1 = new Account("twitter22", "petros2122",
//                "petros@dd22", "none22", "password22", LocalDateTime.now());
//        var list = List.of(acc, acc1);
//
//        try {
//            mapper.writeValue(new File("./test.json"), list);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            List<Account> res = mapper.readValue(new File("C:\\dev\\IntellijProjects\\Password-Generator-Storer\\test.json"), new TypeReference<List<Account>>(){});
//            System.out.println(res);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try {
            List<AccountField> accountFields = List.of(new EmailField("test@yahoo.com"),
                    new ExtraInfoField("fdfdfdf"), new PasswordField("thisisapass"));

            // Serialize the list to JSON
            mapper.writeValue(new File("./test.json"), accountFields);
//            String json = mapper.writeValueAsString(accountFields);
//            System.out.println(json);

            // Deserialize the JSON back into a list of AccountField objects
            List<AccountField> deserializedAccountFields =
                    mapper.readValue(new File("./test.json"), mapper.getTypeFactory().constructCollectionType(List.class, AccountField.class));
            System.out.println("fields:" + deserializedAccountFields.size());
            System.out.println(deserializedAccountFields);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
