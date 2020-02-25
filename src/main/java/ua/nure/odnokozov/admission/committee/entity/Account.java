package ua.nure.odnokozov.admission.committee.entity;

import java.time.LocalDateTime;

import ua.nure.odnokozov.admission.committee.enums.ActivationStatus;
import ua.nure.odnokozov.admission.committee.enums.Role;

public class Account extends Entity {

    private static final long serialVersionUID = 4544372613031710090L;

    private String email;
    private String passwordHash;
    private Role role;
    private ActivationStatus activationStatus;
    private LocalDateTime registrationDate;
    private Language language;
    private Profile profile;
    
    public Account() {
    }

    public Account(long id, String email, String passwordHash, Role role, 
            ActivationStatus activationStatus, LocalDateTime registrationDate, Language language) {
        super(id);
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.activationStatus = activationStatus;
        this.registrationDate = registrationDate;
        this.language = language;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ActivationStatus getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(ActivationStatus activationStatus) {
        this.activationStatus = activationStatus;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Account [id=").append(super.getId())                
                .append(", role=").append(role)
                .append(", email=").append(email)
                .append(", registrationDate=").append(registrationDate)
                .append(", activationStatus=").append(activationStatus)
                .append(", language=").append(language.getLabel())
                .append("]").toString();
    }   
}
