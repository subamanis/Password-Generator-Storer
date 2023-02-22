package manager.domain;

import manager.domain.acountfields.AccountField;

import java.util.ArrayList;
import java.util.List;

public final class AccountListMerger {
    private AccountListMerger() {
    }

    public static List<Account2> merge(List<Account2> list1, List<Account2> deletedList1,
                                       List<Account2> list2, List<Account2> deletedList2)
    {
        List<Account2> mergedList = new ArrayList<>(list1.size()+5);

        for (Account2 currAccount : list1) {
            var matchingAccount = list2.stream()
                    .filter(acc -> acc.hasSameUuid(currAccount))
                    .findFirst();
            if (matchingAccount.isPresent()) {
                mergedList.add(currAccount);
                mergeAccounts(currAccount, matchingAccount.get());
            } else {
                var matchingDeletedAccount = deletedList2.stream()
                        .filter(acc -> acc.hasSameUuid(currAccount))
                        .findFirst();
                if (matchingDeletedAccount.isPresent()){
                    if (currAccount.getDateTimeLastModified().isAfter(matchingDeletedAccount.get().getDateTimeLastModified())) {
                        mergedList.add(currAccount);
                    }
                } else {
                    mergedList.add(currAccount);
                }
            }
        }

        var remainingAccountsNotInList1 = list2.stream()
                .filter(acc -> mergedList.stream()
                        .anyMatch(accMerged -> accMerged.hasSameUuid(acc)))
                .toList();

        for (Account2 currAccount : remainingAccountsNotInList1) {
            var matchingDeletedAccount = deletedList1.stream()
                    .filter(acc -> acc.hasSameUuid(currAccount))
                    .findFirst();
            if (matchingDeletedAccount.isPresent()){
                if (currAccount.getDateTimeLastModified().isAfter(matchingDeletedAccount.get().getDateTimeLastModified())) {
                    mergedList.add(currAccount);
                }
            } else {
                mergedList.add(currAccount);
            }
        }

        return mergedList;
    }

    private static void mergeAccounts(Account2 mainAcc, Account2 otherAcc) {
        for (AccountField currField : mainAcc.getFields()) {
            var matchingField = otherAcc.getFields().stream()
                    .filter(f -> f.getClass() == currField.getClass())
                    .findFirst();
            if (matchingField.isPresent()) {
                if (currField.getDateTimeLastModified().isBefore(matchingField.get().getDateTimeLastModified())) {
                    mainAcc.getFields().remove(currField);
                    mainAcc.getFields().add(matchingField.get());
                }
            } else {
                var matchingDeletedField = otherAcc.getDeletedFields().stream()
                        .filter(f -> f.getClass() == currField.getClass())
                        .findFirst();
                if (matchingDeletedField.isPresent()){
                    if (currField.getDateTimeLastModified().isBefore(matchingDeletedField.get().getDateTimeLastModified())) {
                        mainAcc.getFields().remove(currField);
                    }
                }
            }
        }

        for (AccountField currField : otherAcc.getFields()) {
            var matchingField = mainAcc.getFields().stream()
                    .filter(f -> f.getClass() == currField.getClass())
                    .findFirst();
            if (matchingField.isEmpty()) {
                var matchingDeletedField = mainAcc.getDeletedFields().stream()
                        .filter(f -> f.getClass() == currField.getClass())
                        .findFirst();
                if (matchingDeletedField.isEmpty()){
                    mainAcc.getFields().add(currField);
                }
            }
        }
    }
}
