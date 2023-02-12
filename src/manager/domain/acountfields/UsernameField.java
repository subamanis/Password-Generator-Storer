package manager.domain.acountfields;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("username")
public class UsernameField extends AccountField {
    public UsernameField(String value) {
        super(value);
    }

    @Override
    public boolean validate(String value) {
        return false;
    }
}
