package manager.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import manager.domain.acountfields.AccountField;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account2
{
    @JsonProperty("uuid")
    private final String UUID;

    @JsonProperty("fields")
    private List<AccountField> fields;

    @JsonProperty("deletedFields")
    private List<AccountField> deletedFields;

    private AccountField latestDeletedField;
    private LocalDateTime latestDeletedFieldLastModified;
    private int latestDeletedFieldIndex;

    @JsonCreator
    public Account2(List<AccountField> fields) {
        this.UUID = Utilities.generateUuid();
        this.fields = fields;
        this.deletedFields = new ArrayList<>();
    }

    @JsonCreator
    public Account2(String uuid, List<AccountField> fields) {
        this.UUID = uuid;
        this.fields = fields;
        this.deletedFields = new ArrayList<>();
    }

    public String getUUID() {
        return UUID;
    }

    public List<AccountField> getFields() {
        return fields;
    }

    public void setFields(List<AccountField> fields) {
        this.fields = fields;
    }

    public void addFieldAtPosition(AccountField field, int position) {
        this.fields.add(position, field);
    }

    public void deleteField(AccountField field) {
        int indexOfField = this.fields.indexOf(field);
        if (indexOfField != -1) {
            this.latestDeletedFieldIndex = indexOfField;
            latestDeletedFieldLastModified = field.getLastModified();
            field.setLastModified(LocalDateTime.now());

            this.fields.remove(field);
            this.deletedFields.add(field);
            this.latestDeletedField = field;
        }
    }

    public void undoLatestDelete() {
        if (this.latestDeletedField != null) {
            this.latestDeletedField.setLastModified(latestDeletedFieldLastModified);
            this.fields.add(latestDeletedFieldIndex, this.latestDeletedField);
            this.deletedFields.remove(this.latestDeletedField);
            this.latestDeletedField = null;
            this.latestDeletedFieldLastModified = null;
            this.latestDeletedFieldIndex = -1;
        }
    }

    @Override
    public String toString() {
        return "Account2{" +
                "uuid=" + UUID +
                "fields=" + fields +
                '}';
    }
}
