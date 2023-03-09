package manager.domain.jackson.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import manager.domain.Account;
import manager.Utilities;
import manager.domain.acountfields.AccountField;

import java.io.IOException;

public class AccountSerializer extends StdSerializer<Account> {

    public AccountSerializer() {
        this(null);
    }

    public AccountSerializer(Class<Account> t) {
        super(t);
    }

    @Override
    public void serialize(Account account, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("uuid", account.getUuid());
        jsonGenerator.writeStringField("identifier", account.getStringIdentifier());
        jsonGenerator.writeFieldName("dateTimeCreated");
        jsonGenerator.writeObject(account.getDateTimeCreated());
        jsonGenerator.writeFieldName("dateTimeLastModified");
        jsonGenerator.writeObject(account.getDateTimeCreated());
        if (account.getDateTimeDeleted() != null) {
            jsonGenerator.writeFieldName("dateTimeDeleted");
            jsonGenerator.writeObject(account.getDateTimeDeleted());
        }
        jsonGenerator.writeFieldName("fields");
        jsonGenerator.writeStartArray();
        for (AccountField accountField : account.getFields()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", accountField.getType());
            jsonGenerator.writeStringField("value", accountField.getValue());
            jsonGenerator.writeStringField("lastModified", accountField.getDateTimeLastModified().format(Utilities.DATETIME_FORMATTER));
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeFieldName("deletedFields");
        jsonGenerator.writeStartArray();
        for (AccountField accountField : account.getFields()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", accountField.getType());
            jsonGenerator.writeStringField("value", accountField.getValue());
            jsonGenerator.writeStringField("lastModified", accountField.getDateTimeLastModified().format(Utilities.DATETIME_FORMATTER));
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
