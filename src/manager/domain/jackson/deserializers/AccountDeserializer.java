package manager.domain.jackson.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import manager.Utilities;
import manager.domain.Account;
import manager.domain.acountfields.AccountField;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountDeserializer extends JsonDeserializer<Account> {

    @Override
    public Account deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String uuid = node.get("uuid").asText();
        LocalDateTime dateTimeCreated = LocalDateTime.parse(node.get("dateTimeCreated").asText(), Utilities.DATETIME_FORMATTER);
        LocalDateTime dateTimeLastModified = LocalDateTime.parse(node.get("dateTimeLastModified").asText(), Utilities.DATETIME_FORMATTER);
        JsonNode dateTimeDeletedNode = node.get("dateTimeDeleted");
        LocalDateTime dateTimeDeleted = null;
        if (dateTimeDeletedNode != null) {
            dateTimeDeleted = LocalDateTime.parse(dateTimeDeletedNode.asText(), Utilities.DATETIME_FORMATTER);
        }
        List<AccountField> fields = new ArrayList<>();
        node.get("fields").forEach(fieldNode -> {
            try {
                fields.add(jsonParser.getCodec().treeToValue(fieldNode, AccountField.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        List<AccountField> deletedFields = new ArrayList<>();
        node.get("deletedFields").forEach(fieldNode -> {
            try {
                deletedFields.add(jsonParser.getCodec().treeToValue(fieldNode, AccountField.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return new Account(uuid, fields, deletedFields, dateTimeCreated, dateTimeLastModified, dateTimeDeleted);
    }

}