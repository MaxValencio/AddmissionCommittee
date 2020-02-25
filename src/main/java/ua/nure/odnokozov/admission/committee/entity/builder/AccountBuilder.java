package ua.nure.odnokozov.admission.committee.entity.builder;

import java.time.LocalDateTime;

import ua.nure.odnokozov.admission.committee.entity.Account;
import ua.nure.odnokozov.admission.committee.entity.Language;
import ua.nure.odnokozov.admission.committee.entity.Profile;
import ua.nure.odnokozov.admission.committee.enums.ActivationStatus;
import ua.nure.odnokozov.admission.committee.enums.Role;

public class AccountBuilder {

    private Account account;

    private AccountBuilder() {
        this.account = new Account();
    }

    public static AccountBuilder getInstance() {
        return new AccountBuilder();
    }

    public AccountBuilder id(long id) {
        account.setId(id);
        return this;
    }

    public AccountBuilder email(String email) {
        account.setEmail(email);
        return this;
    }

    public AccountBuilder passwordHash(String passwordHash) {
        account.setPasswordHash(passwordHash);
        return this;
    }

    public AccountBuilder role(Role role) {
        account.setRole(role);
        return this;
    }

    public AccountBuilder activationStatus(ActivationStatus status) {
        account.setActivationStatus(status);
        return this;
    }

    public AccountBuilder registrationDate(LocalDateTime date) {
        account.setRegistrationDate(date);
        return this;
    }

    public AccountBuilder language(Language language) {
        account.setLanguage(language);
        return this;
    }

    public AccountBuilder profile(Profile profile) {
        account.setProfile(profile);
        return this;
    }

    public Account build() {
        return account;
    }
}