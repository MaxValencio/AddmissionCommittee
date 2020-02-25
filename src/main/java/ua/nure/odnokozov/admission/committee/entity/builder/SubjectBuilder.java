package ua.nure.odnokozov.admission.committee.entity.builder;

import java.util.Map;

import ua.nure.odnokozov.admission.committee.entity.Language;
import ua.nure.odnokozov.admission.committee.entity.Subject;

public class SubjectBuilder {

    Subject subject;
    
    private SubjectBuilder() {
        this.subject = new Subject();
    }
    
    public static SubjectBuilder getInstance() {
        return new SubjectBuilder();
    }
    
    public SubjectBuilder id(long id) {
        subject.setId(id);
        return this;
    }
    
    public SubjectBuilder titles(Map<Language, String> titles) {
        subject.setTitles(titles);
        return this;
    }
    
    public Subject build() {
        return subject;
    }
}
