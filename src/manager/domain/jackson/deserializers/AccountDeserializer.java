package manager.domain.jackson.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import manager.domain.Account2;
import manager.domain.acountfields.AccountField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountDeserializer extends JsonDeserializer<Account2> {

    @Override
    public Account2 deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String uuid = node.get("uuid").asText();
        List<AccountField> fields = new ArrayList<>();
        node.get("fields").forEach(fieldNode -> {
            try {
                fields.add(jsonParser.getCodec().treeToValue(fieldNode, AccountField.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return new Account2(uuid, fields);
    }

}