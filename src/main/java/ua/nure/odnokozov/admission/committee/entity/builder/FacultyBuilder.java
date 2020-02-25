package ua.nure.odnokozov.admission.committee.entity.builder;

import java.util.List;
import java.util.Map;

import ua.nure.odnokozov.admission.committee.entity.Faculty;
import ua.nure.odnokozov.admission.committee.entity.Language;
import ua.nure.odnokozov.admission.committee.entity.Subject;

public class FacultyBuilder {

    private Faculty faculty;
    
    private FacultyBuilder() {
        this.faculty = new Faculty();
    }
    
    public static FacultyBuilder getInstance() {
        return new FacultyBuilder();
    }
    
    public FacultyBuilder id(long id) {
        faculty.setId(id);
        return this;
    }
    
    public FacultyBuilder titles(Map<Language, String> titles) {
        faculty.setTitles(titles);
        return this;
    }
    
    public FacultyBuilder descriptions(Map<Language, String> descriptions) {
        faculty.setDescriptions(descriptions);
        return this;
    }
    
    public FacultyBuilder totalSeats(int totalSeats) {
        faculty.setTotalSeats(totalSeats);
        return this;
    }
    
    public FacultyBuilder budgetSeats(int budgetSeats) {
        faculty.setBudgetSeats(budgetSeats);
        return this;
    }
    
    public FacultyBuilder examSubjects(List<Subject> subjects) {
        faculty.setExamSubjects(subjects);
        return this;
    }
    
    public Faculty build() {
        return faculty;
    }
}
