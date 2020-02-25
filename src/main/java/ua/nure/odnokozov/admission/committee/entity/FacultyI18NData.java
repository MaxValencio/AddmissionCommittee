package ua.nure.odnokozov.admission.committee.entity;

import java.util.Objects;

public class FacultyI18NData extends Entity {

    private static final long serialVersionUID = 556873726752319253L;

    private String title;
    private String description;
    private Language language;
    private Long facultyId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());
        result = prime * result + ((language == null) ? 0 : language.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } 
        FacultyI18NData other = (FacultyI18NData) obj;
        return Objects.equals(facultyId, other.getFacultyId()) && Objects.equals(language, other.getLanguage()); 
    }
    
    
}
