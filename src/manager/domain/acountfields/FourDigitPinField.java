package manager.domain.acountfields;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("fourDigitPin")
public class FourDigitPinField extends AccountField {
    private FourDigitPinField() {}

    public FourDigitPinField(String value) {
        super(value);
    }

    @Override
    public boolean validate(String value) {
        return false;
    }
}
