package manager.domain;

import manager.domain.acountfields.AccountField;

public class TestUtilities {
    public static Account2 cloneAccount(Account2 account) {
        return new Account2(account.getUuid(), account.getFields(), account.getDeletedFields(),
                account.getDateTimeCreated(), account.getDateTimeDeleted(), account.getDateTimeLastModified());
    }

    public static AccountField cloneAccountField(AccountField field) {
        return new AccountField(field.getName(), field.getValue(), field.getDateTimeCreated(),
                field.getDateTimeDeleted(), field.getDateTimeLastModified());
    }
}
