package manager.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataRepository {
    @JsonProperty("hashedUserPass")
    private String hashedUserPass;

    @JsonProperty("savedAccounts")
    private List<Account> savedAccounts;

    @JsonProperty("deletedAccounts")
    private List<Account> deletedAccounts;

    @JsonCreator
    public DataRepository(String hashedUserPass, List<Account> savedAccounts, List<Account> deletedAccounts) {
        this.hashedUserPass = hashedUserPass;
        this.savedAccounts = savedAccounts;
        this.deletedAccounts = deletedAccounts;
    }

    public String getHashedUserPass() {
        return hashedUserPass;
    }

    public List<Account> getSavedAccounts() {
        return savedAccounts;
    }

    public List<Account> getDeletedAccounts() {
        return deletedAccounts;
    }

    @Override
    public String toString() {
        return "DataRepository{" +
                "hashedUserPass='" + hashedUserPass + '\'' +
                ", savedAccounts count=" + savedAccounts.size() +
                ", deletedAccounts count=" + deletedAccounts.size() +
                '}';
    }
}
