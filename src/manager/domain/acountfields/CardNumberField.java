package manager.domain.acountfields;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("cardNumber")
public class CardNumberField extends AccountField {
    private CardNumberField() {}

    public CardNumberField(String value) {
        super(value);
    }

    @Override
    public boolean validate(String value) {
        return false;
    }
}
