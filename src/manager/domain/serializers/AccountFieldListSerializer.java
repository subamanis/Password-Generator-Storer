package manager.domain.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import manager.domain.acountfields.AccountField;

import java.io.IOException;
import java.util.List;

public class AccountFieldListSerializer extends JsonSerializer<List<AccountField>> {
    @Override
    public void serialize(List<AccountField> fields, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Write your custom serialization logic here
        jsonGenerator.writeStartArray();
        //TODO: we need to use jackson inheritance for serialization of Account types
        for (AccountField account : fields) {
            jsonGenerator.writeObject(account);
        }
        jsonGenerator.writeEndArray();
    }
}