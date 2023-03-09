package manager.domain.jackson.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import manager.domain.Account;
import manager.domain.DataRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataRepositoryDeserializer extends JsonDeserializer<DataRepository>  {
    @Override
    public DataRepository deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String hashedUserPass = node.get("hashedUserPass").asText();
        List<Account> savedAccounts = new ArrayList<>();
        node.get("savedAccounts").forEach(fieldNode -> {
            try {
                savedAccounts.add(jsonParser.getCodec().treeToValue(fieldNode, Account.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        List<Account> deletedAccounts = new ArrayList<>();
        node.get("deletedAccounts").forEach(fieldNode -> {
            try {
                deletedAccounts.add(jsonParser.getCodec().treeToValue(fieldNode, Account.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        return new DataRepository(hashedUserPass, savedAccounts, deletedAccounts);
    }
}
