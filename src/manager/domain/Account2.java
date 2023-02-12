package manager.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import manager.domain.acountfields.AccountField;

import java.util.List;

public class Account2 {
    @JsonProperty("fields")
    private List<AccountField> fields;

    @JsonCreator
    public Account2(List<AccountField> fields) {
        this.fields = fields;
    }

    public List<AccountField> getFields() {
        return fields;
    }

    public void setFields(List<AccountField> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "Account2{" +
                "fields=" + fields +
                '}';
    }
}
