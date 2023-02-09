package manager.logic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonSerialize(using = AccountSerializer.class)
public final class Account
{
    public static final String DATETIME_FORMAT_PATTERN = "yyyy/MM/dd HH:mm:ss";
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN);

    private String siteName;
    private String username;
    private String email;
    private String extraInfo;
    private String password;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timestampUpdated;

    @JsonCreator
    public Account(
            @JsonProperty("siteName") String siteName,
            @JsonProperty("username") String username,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("extraInfo") String extraInfo,
            @JsonProperty("timestampUpdated") LocalDateTime timestampUpdated)
    {
        this.siteName = siteName.trim();
        this.username = username.trim();
        this.email = email.trim();
        this.extraInfo = extraInfo.trim();
        this.password = password.trim();
        this.timestampUpdated = timestampUpdated;
    }

    public boolean isMoreRecentlyUpdatedThan(Account other) {
        return this.timestampUpdated.isAfter(other.timestampUpdated);
    }

    public String getSiteName(){
        return this.siteName;
    }

    public void changeSiteName(String siteName){
        this.siteName = siteName;
    }

    public String getUsername(){
        return this.username;
    }

    public void changeUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return this.email;
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public String getExtraInfo(){
        return this.extraInfo;
    }

    public void changeExtraInfo(String extraInfo){
        this.extraInfo = extraInfo;
    }

    public String getPassword(){
        return this.password;
    }

    public LocalDateTime getTimestampUpdated() {
        return this.timestampUpdated;
    }

    public void changePassword(String password){
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "siteName='" + siteName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", extraInfo='" + extraInfo + '\'' +
                ", password='" + password + '\'' +
                ", timestampUpdated=" + timestampUpdated +
                '}';
    }
}

class AccountSerializer extends StdSerializer<Account> {
    public AccountSerializer() {
        this(null);
    }

    public AccountSerializer(Class<Account> acc) {
        super(acc);
    }

    @Override
    public void serialize(Account account, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                          throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("siteName", account.getSiteName());
        jsonGenerator.writeStringField("username", account.getUsername());
        jsonGenerator.writeStringField("email", account.getEmail());
        jsonGenerator.writeStringField("password", account.getPassword());
        jsonGenerator.writeStringField("extraInfo", account.getExtraInfo());
        jsonGenerator.writeStringField("timestampUpdated",
                account.getTimestampUpdated().format(Account.DATETIME_FORMATTER));
        jsonGenerator.writeEndObject();
    }
}

class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalDateTime.parse(p.getValueAsString(), Account.DATETIME_FORMATTER);
    }
}
