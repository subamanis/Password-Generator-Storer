package manager.domain.acountfields;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import manager.Utilities;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @Type(value = SiteNameField.class,      name = "siteName"),
        @Type(value = UsernameField.class,      name = "username"),
        @Type(value = EmailField.class,         name = "email"),
        @Type(value = PasswordField.class,      name = "password"),
        @Type(value = ExtraInfoField.class,     name = "extraInfo"),
        @Type(value = CardNumberField.class,    name = "cardNumber"),
        @Type(value = FourDigitPinField.class,  name = "fourDigitPin"),
        @Type(value = ThreeDigitPinField.class, name = "threeDigitPin"),
})
public abstract class AccountField implements FieldValidator {
    private String value;

    private LocalDateTime dateTimeLastModified;

    public AccountField(String value) {
        this.value = value;
        this.dateTimeLastModified = LocalDateTime.now();
    }

    public AccountField(String value, LocalDateTime dateTimeLastModified) {
        this.value = value;
        this.dateTimeLastModified = dateTimeLastModified;
    }

    public abstract boolean validate(String value);

    public boolean isMoreRecentlyUpdatedThan(AccountField other) {
        return this.dateTimeLastModified.isAfter(other.dateTimeLastModified);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        this.dateTimeLastModified = LocalDateTime.now();
    }

    public LocalDateTime getDateTimeLastModified() {

        return dateTimeLastModified;
    }
    public void setDateTimeLastModified(LocalDateTime dateTime) {
        this.dateTimeLastModified = dateTime;
    }

    @JsonProperty("type")
    public String getType() {
        return this.getClass().getAnnotation(JsonTypeName.class).value();
    }

    public String toString() {
        return this.getClass().getSimpleName() + ": "+ this.value +
                "(" + this.dateTimeLastModified.format(Utilities.DATETIME_FORMATTER) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountField that = (AccountField) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
