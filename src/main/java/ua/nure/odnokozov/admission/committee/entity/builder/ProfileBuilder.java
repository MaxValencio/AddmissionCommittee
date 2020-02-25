package ua.nure.odnokozov.admission.committee.entity.builder;

import java.util.Set;

import ua.nure.odnokozov.admission.committee.entity.Certificate;
import ua.nure.odnokozov.admission.committee.entity.Faculty;
import ua.nure.odnokozov.admission.committee.entity.Profile;
import ua.nure.odnokozov.admission.committee.enums.BlockStatus;

public class ProfileBuilder {

    private Profile profile;

    private ProfileBuilder() {

        this.profile = new Profile();
    }

    public static ProfileBuilder getInstance() {
        return new ProfileBuilder();
    }

    public ProfileBuilder id(long id) {
        profile.setId(id);
        return this;
    }
    
    public ProfileBuilder blockStatus(BlockStatus blockStatus) {
        profile.setBlockStatus(blockStatus);
        return this;
    }

    public ProfileBuilder firstName(String firstName) {
        profile.setFirstName(firstName);
        return this;
    }

    public ProfileBuilder lastName(String lastName) {
        profile.setLastName(lastName);
        return this;
    }

    public ProfileBuilder city(String city) {
        profile.setCity(city);
        return this;
    }

    public ProfileBuilder region(String region) {
        profile.setRegion(region);
        return this;
    }

    public ProfileBuilder school(String school) {
        profile.setSchool(school);
        return this;
    }

    public ProfileBuilder certificate(Certificate certificate) {
        profile.setCertificate(certificate);
        return this;
    }

    public ProfileBuilder selectedFaculties(Set<Faculty> selectedFaculties) {
        profile.setSelectedFacultyies(selectedFaculties);
        return this;
    }

    public Profile build() {
        return profile;
    }
}