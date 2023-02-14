package manager.domain.jackson.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import manager.domain.Account2;
import manager.domain.Utilities;
import manager.domain.acountfields.AccountField;

import java.io.IOException;

public class AccountSerializer extends StdSerializer<Account2> {

    public AccountSerializer() {
        this(null);
    }

    public AccountSerializer(Class<Account2> t) {
        super(t);
    }

    @Override
    public void serialize(Account2 account, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("uuid", account.getUUID());
        jsonGenerator.writeFieldName("fields");
        jsonGenerator.writeStartArray();
        for (AccountField accountField : account.getFields()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", accountField.getType());
            jsonGenerator.writeStringField("value", accountField.getValue());
            jsonGenerator.writeStringField("lastModified", accountField.getLastModified().format(Utilities.DATETIME_FORMATTER));
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeFieldName("deletedFields");
        jsonGenerator.writeStartArray();
        for (AccountField accountField : account.getFields()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", accountField.getType());
            jsonGenerator.writeStringField("value", accountField.getValue());
            jsonGenerator.writeStringField("lastModified", accountField.getLastModified().format(Utilities.DATETIME_FORMATTER));
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
