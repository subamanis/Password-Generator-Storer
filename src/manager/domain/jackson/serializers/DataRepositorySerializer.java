package manager.domain.jackson.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import manager.Utilities;
import manager.domain.Account;
import manager.domain.DataRepository;
import manager.domain.acountfields.AccountField;

import java.io.IOException;

public class DataRepositorySerializer extends StdSerializer<DataRepository> {
    public DataRepositorySerializer() {
        this(null);
    }

    public DataRepositorySerializer(Class<DataRepository> t) {
        super(t);
    }

    @Override
    public void serialize(DataRepository dataRepository, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("hashedUserPass", dataRepository.getHashedUserPass());
        jsonGenerator.writeFieldName("savedAccounts");
        jsonGenerator.writeStartArray();
        for (Account account : dataRepository.getSavedAccounts()) {
            jsonGenerator.writeObject(account);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeFieldName("deletedAccounts");
        jsonGenerator.writeStartArray();
        for (Account account : dataRepository.getDeletedAccounts()) {
            jsonGenerator.writeObject(account);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

}
