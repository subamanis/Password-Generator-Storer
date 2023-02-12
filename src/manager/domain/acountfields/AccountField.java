package manager.domain.acountfields;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Objects;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @Type(value = SiteNameField.class, name = "siteName"),
        @Type(value = UsernameField.class, name = "username"),
        @Type(value = EmailField.class, name = "email"),
        @Type(value = PasswordField.class, name = "password"),
        @Type(value = ExtraInfoField.class, name = "extraInfo"),
        @Type(value = CardNumberField.class, name = "cardNumber"),
        @Type(value = FourDigitPinField.class, name = "fourDigitPin"),
        @Type(value = ThreeDigitPinField.class, name = "threeDigitPin"),
})
public abstract class AccountField implements FieldValidator {
    public String value;

    public AccountField(String value) {
        this.value = value;
    }

    public abstract boolean validate(String value);

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("type")
    public String getType() {
        return this.getClass().getAnnotation(JsonTypeName.class).value();
    }

    public String toString() {
        return this.getClass().getSimpleName() + ": "+ this.value;
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
