package manager.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import manager.io.LocalDateTimeDeserializer;
import manager.domain.acountfields.AccountField;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@JsonSerialize(using = Account2Serializer.class)
public class Account2 {
    @JsonProperty("fields")
    private List<AccountField> fields;

    @JsonProperty("timestampUpdated")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timestampUpdated;

    @JsonCreator
    public Account2(List<AccountField> fields) {
        this.fields = fields;
        timestampUpdated = LocalDateTime.now();
    }
}

class Account2Serializer extends StdSerializer<Account2> {
    public Account2Serializer() {
        this(null);
    }

    public Account2Serializer(Class<Account2> acc) {
        super(acc);
    }

    @Override
    public void serialize(Account2 account, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
//        jsonGenerator.writeStartObject();
////        jsonGenerator.write
//        jsonGenerator.writeStringField("siteName", account.getSiteName());
//        jsonGenerator.writeStringField("username", account.getUsername());
//        jsonGenerator.writeStringField("email", account.getEmail());
//        jsonGenerator.writeStringField("password", account.getPassword());
//        jsonGenerator.writeStringField("extraInfo", account.getExtraInfo());
//        jsonGenerator.writeStringField("timestampUpdated",
//                account.getTimestampUpdated().format(Account2.DATETIME_FORMATTER));
//        jsonGenerator.writeEndObject();
    }
}

