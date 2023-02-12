package manager.domain.acountfields;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("email")
public class EmailField extends AccountField {
    public EmailField() {
        super("");
    }
    public EmailField(String value) {
        super(value);
    }

    @Override
    public boolean validate(String value) {
        return false;
    }
}
