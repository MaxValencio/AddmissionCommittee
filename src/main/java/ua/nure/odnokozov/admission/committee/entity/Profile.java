package ua.nure.odnokozov.admission.committee.entity;

import ua.nure.odnokozov.admission.committee.enums.BlockStatus;

import java.util.Set;

public class Profile extends Entity {

    private static final long serialVersionUID = 2422174494598871251L;

    private BlockStatus blockStatus;
    private String firstName;
    private String lastName;
    private String city;
    private String region;
    private String school;
    private Certificate certificate;
    private Set<Faculty> selectedFacultyies;

    public Profile() { 
    }
    
    public BlockStatus getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(BlockStatus blockStatus) {
        this.blockStatus = blockStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
    
    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Set<Faculty> getSelectedFacultyies() {
        return selectedFacultyies;
    }

    public void setSelectedFacultyies(Set<Faculty> selectedFacultyies) {
        this.selectedFacultyies = selectedFacultyies;
    }
    
    @Override
    public String toString() {
        return new StringBuilder()
                .append("Profile [id=").append(super.getId())
                .append(", blockStatus=").append(blockStatus)
                .append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append("]")
                .toString();
    }
}
