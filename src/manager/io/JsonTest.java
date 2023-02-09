package manager.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import manager.logic.Account;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

//        try {
//            mapper.writeValue(new File("./test.json"), list);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try {
            List<Account> res = mapper.readValue(new File("C:\\dev\\IntellijProjects\\Password-Generator-Storer\\test.json"), new TypeReference<List<Account>>(){});
            System.out.println(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
