package manager.domain.acountfields;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("threeDigitPin")
public class ThreeDigitPinField extends AccountField {
    public ThreeDigitPinField(String value) {
        super(value);
    }

    @Override
    public boolean validate(String value) {
        return false;
    }
}
