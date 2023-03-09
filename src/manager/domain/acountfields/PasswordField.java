package manager.domain.acountfields;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("password")
public class PasswordField extends AccountField {
    private PasswordField() {}

    public PasswordField(String value) {
        super(value);
    }

    @Override
    public boolean validate(String value) {
        return false;
    }
}
