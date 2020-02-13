package ua.nure.odnokozov.admission.committee.entity;

import java.sql.Timestamp;

import ua.nure.odnokozov.admission.committee.enums.BlockStatus;
import ua.nure.odnokozov.admission.committee.enums.ProfileStatus;
import ua.nure.odnokozov.admission.committee.enums.Role;

public class User {

    private long id;
    private String email;
    private String passwordHash;
    private Role role;
    private Language language;
    private BlockStatus blockStatus;
    private ProfileStatus profileStatus;
    private Timestamp registrationDate;
    private Timestamp lastVisitDate;
    private Profile profile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public BlockStatus getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(BlockStatus blockStatus) {
        this.blockStatus = blockStatus;
    }

    public ProfileStatus getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(ProfileStatus profileStatus) {
        this.profileStatus = profileStatus;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Timestamp getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(Timestamp lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    @Override
    public String toString() {
        return "User [" + formatData() + ", role=" + role.getName() + "]";
    }

    protected final String formatData() {
        return "id=" + id + ", email=" + email;
    }
}
