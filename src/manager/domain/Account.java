package manager.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import manager.Utilities;
import manager.domain.acountfields.AccountField;
import manager.domain.acountfields.SiteNameField;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account
{
    @JsonProperty("uuid")
    private final String UUID;

    @JsonProperty("dateTimeCreated")
    private final LocalDateTime dateTimeCreated;

    // Corresponds to the dateTimeLastModified of the most recently modified field
    @JsonProperty("dateTimeLastModified")
    private LocalDateTime dateTimeLastModified;

    @JsonProperty("dateTimeDeleted")
    private LocalDateTime dateTimeDeleted;

    @JsonProperty("fields")
    private List<AccountField> fields;

    @JsonProperty("deletedFields")
    private List<AccountField> deletedFields;

    private AccountField latestDeletedField;
    private LocalDateTime latestDeletedFieldLastModified;
    private int latestDeletedFieldIndex;
    private String identifier;

    public Account(List<AccountField> fields) {
        if (fields == null || fields.stream().noneMatch(f -> f.getClass() == SiteNameField.class)) {
            throw new IllegalArgumentException("Accounts must have a SiteNameField");
        }

        this.UUID = Utilities.generateUuid();
        this.fields = fields;
        this.dateTimeCreated = LocalDateTime.now();
        this.dateTimeLastModified = this.dateTimeCreated;
        this.deletedFields = new ArrayList<>();
    }

    @JsonCreator
    public Account(String uuid, List<AccountField> fields, List<AccountField> deletedFields,
                   LocalDateTime dateTimeCreated, LocalDateTime dateTimeLastModified, LocalDateTime dateTimeDeleted)
    {
        if (fields == null || fields.stream().noneMatch(f -> f.getClass() == SiteNameField.class)) {
            throw new IllegalArgumentException("Accounts must have a SiteNameField");
        }

        this.UUID = uuid;
        this.fields = fields;
        this.deletedFields = deletedFields;
        this.dateTimeCreated = dateTimeCreated;
        this.dateTimeLastModified = dateTimeLastModified;
        this.dateTimeDeleted = dateTimeDeleted;
    }

    public String getStringIdentifier() {
        if (this.identifier == null) {
            for (AccountField field : this.fields) {
                if (field.getClass() == SiteNameField.class) {
                    this.identifier = field.getValue();
                    break;
                }
            }

            if (this.identifier == null) {
                this.identifier = "--nositename--";
            }
        }

        return this.identifier;
    }

    public boolean addFieldAtTheEndIfTypeNotPresent(AccountField field) {
        var res = this.fields.stream().anyMatch(f -> f.getClass() == field.getClass());
        if (res) {
            return false;
        }

        this.fields.add(field);
        this.dateTimeLastModified = LocalDateTime.now();
        return true;
    }

    public boolean addFieldAtPositionIfTypeNotPresent(AccountField field, int position) {
        var res = this.fields.stream().anyMatch(f -> f.getClass() == field.getClass());
        if (res) {
            return false;
        }

        this.fields.add(position, field);
        this.dateTimeLastModified = LocalDateTime.now();
        return true;
    }
    public void moveFieldToPosition(AccountField field, int newPosition) {
        int indexOfField = this.fields.indexOf(field);
        if (indexOfField != -1) {
            this.fields.remove(indexOfField);
            this.fields.add(newPosition, field);
        }
    }

    public void deleteField(AccountField field) {
        int indexOfField = this.fields.indexOf(field);
        if (indexOfField != -1) {
            this.latestDeletedFieldIndex = indexOfField;
            latestDeletedFieldLastModified = field.getDateTimeLastModified();
            field.setDateTimeLastModified(LocalDateTime.now());

            this.fields.remove(field);
            this.deletedFields.add(field);
            this.latestDeletedField = field;
        }
    }

    public void undoLatestDelete() {
        if (this.latestDeletedField != null) {
            this.latestDeletedField.setDateTimeLastModified(latestDeletedFieldLastModified);
            this.fields.add(latestDeletedFieldIndex, this.latestDeletedField);
            this.deletedFields.remove(this.latestDeletedField);
            this.latestDeletedField = null;
            this.latestDeletedFieldLastModified = null;
            this.latestDeletedFieldIndex = -1;
        }
    }

    public boolean hasSameUuid(Account other)
    {
        return this.UUID.equals(other.getUuid());
    }

    public String getUuid() {
        return this.UUID;
    }

    public List<AccountField> getFields() {

        return this.fields;
    }
    public List<AccountField> getDeletedFields() {
        return this.deletedFields;
    }

    public void setFields(List<AccountField> fields) {
        this.fields = fields;
    }

    public LocalDateTime getDateTimeCreated() {
        return this.dateTimeCreated;
    }

    public LocalDateTime getDateTimeLastModified() {
        return this.dateTimeLastModified;
    }

    public LocalDateTime getDateTimeDeleted() {
        return this.dateTimeDeleted;
    }

    @Override
    public String toString() {
        return "Account2{" +
                "uuid=" + UUID +
                ", identifier=" + getStringIdentifier() +
                ", dateTimeCreated=" + dateTimeCreated.format(Utilities.DATETIME_FORMATTER) +
                ", dateTimeLastModified=" + dateTimeLastModified.format(Utilities.DATETIME_FORMATTER) +
                (dateTimeDeleted == null ? "" : ", dateTimeDeleted=" + dateTimeDeleted.format(Utilities.DATETIME_FORMATTER)) +
                ", fields=" + fields +
                '}';
    }
}
