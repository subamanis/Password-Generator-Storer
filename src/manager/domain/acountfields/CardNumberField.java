package manager.domain.acountfields;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("cardNumber")
public class CardNumberField extends AccountField {
    public CardNumberField(String value) {
        super(value);
    }

    @Override
    public boolean validate(String value) {
        return false;
    }
}
