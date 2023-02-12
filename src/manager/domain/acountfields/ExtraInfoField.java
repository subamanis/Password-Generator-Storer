package manager.domain.acountfields;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("extraInfo")
public class ExtraInfoField extends AccountField {
    public ExtraInfoField() {
        super("");
    }
    public ExtraInfoField(String value) {
        super(value);
    }

    @Override
    public boolean validate(String value) {
        return false;
    }
}
