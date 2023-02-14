package manager.domain.acountfields;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import manager.domain.Utilities;

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
    public String value;

    public LocalDateTime lastModified;

    public AccountField(String value) {
        this.value = value;
        this.lastModified = LocalDateTime.now();
    }

    public abstract boolean validate(String value);

    public boolean isMoreRecentlyUpdatedThan(AccountField other) {
        return this.lastModified.isAfter(other.lastModified);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        this.lastModified = LocalDateTime.now();
    }

    public LocalDateTime getLastModified() {

        return lastModified;
    }
    public void setLastModified(LocalDateTime dateTime) {
        this.lastModified = dateTime;
    }

    @JsonProperty("type")
    public String getType() {
        return this.getClass().getAnnotation(JsonTypeName.class).value();
    }

    public String toString() {
        return this.getClass().getSimpleName() + ": "+ this.value +
                "(" + this.lastModified.format(Utilities.DATETIME_FORMATTER) + ")";
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
