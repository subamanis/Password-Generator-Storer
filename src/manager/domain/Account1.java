package manager.domain;

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

@JsonSerialize(using = Account1Serializer.class)
public final class Account1
{
    public static final String DATETIME_FORMAT_PATTERN = "yyyy/MM/dd HH:mm:ss";
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN);

    private String siteName;
    private String username;
    private String email;
    private String extraInfo;
    private String password;

    @JsonDeserialize(using = LocalDateTime1Deserializer.class)
    private LocalDateTime timestampUpdated;

//    @JsonCreator
//    private Account1(
//            @JsonProperty("siteName") String siteName,
//            @JsonProperty("username") String username,
//            @JsonProperty("email") String email,
//            @JsonProperty("password") String password,
//            @JsonProperty("extraInfo") String extraInfo,
//            @JsonProperty("timestampUpdated") LocalDateTime timestampUpdated)
//    {
//        this.siteName = siteName.trim();
//        this.username = username.trim();
//        this.email = email.trim();
//        this.extraInfo = extraInfo.trim();
//        this.password = password.trim();
//        this.timestampUpdated = timestampUpdated;
//    }

    private Account1(AccountBuilder builder) {
        this.siteName = builder.siteName;
        this.username = builder.username;
        this.email = builder.email;
        this.extraInfo = builder.extraInfo;
        this.password = builder.password;
        this.timestampUpdated = builder.timestampUpdated;
    }

    public boolean isMoreRecentlyUpdatedThan(Account1 other) {
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
        return "Account1{" +
                "siteName='" + siteName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", extraInfo='" + extraInfo + '\'' +
                ", password='" + password + '\'' +
                ", timestampUpdated=" + timestampUpdated +
                '}';
    }

    public static class AccountBuilder {
        private String siteName;
        private String username;
        private String email;
        private String extraInfo;
        private String password;
        private LocalDateTime timestampUpdated;

//        private AccountBuilder makeStandardWebsiteAccount() {
////            this.
////            return new Account1()
//        }
    }
}

class Account1Serializer extends StdSerializer<Account1> {
    public Account1Serializer() {
        this(null);
    }

    public Account1Serializer(Class<Account1> acc) {
        super(acc);
    }

    @Override
    public void serialize(Account1 account, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("siteName", account.getSiteName());
        jsonGenerator.writeStringField("username", account.getUsername());
        jsonGenerator.writeStringField("email", account.getEmail());
        jsonGenerator.writeStringField("password", account.getPassword());
        jsonGenerator.writeStringField("extraInfo", account.getExtraInfo());
        jsonGenerator.writeStringField("timestampUpdated",
                account.getTimestampUpdated().format(Account1.DATETIME_FORMATTER));
        jsonGenerator.writeEndObject();
    }
}

class LocalDateTime1Deserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalDateTime.parse(p.getValueAsString(), Account1.DATETIME_FORMATTER);
    }
}
