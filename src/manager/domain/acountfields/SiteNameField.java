package manager.domain.acountfields;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("siteName")
public class SiteNameField extends AccountField {
    public SiteNameField(String value) {
        super(value);
    }

    @Override
    public boolean validate(String value) {
        return false;
    }
}
