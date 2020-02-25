package ua.nure.odnokozov.admission.committee.entity;

import java.util.List;
import java.util.Map;

public class Faculty extends Entity {

    private static final long serialVersionUID = 5661316074436236121L;

    private int totalSeats;
    private int budgetSeats;
    private Map<Language, String> titles;
    private Map<Language, String> descriptions;
    private List<Subject> examSubjects;

    public Faculty() {
    }

    public Map<Language, String> getTitles() {
        return titles;
    }

    public void setTitles(Map<Language, String> titles) {
        this.titles = titles;
    }

    public Map<Language, String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Map<Language, String> description) {
        this.descriptions = description;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getBudgetSeats() {
        return budgetSeats;
    }

    public void setBudgetSeats(int budgetSeats) {
        this.budgetSeats = budgetSeats;
    }    

    public List<Subject> getExamSubjects() {
        return examSubjects;
    }

    public void setExamSubjects(List<Subject> subjects) {
        this.examSubjects = subjects;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Faculty [id=").append(super.getId())
                .append(", title=").append(titles)
                .append(", description=").append(descriptions)
                .append(", totalSeats=").append(totalSeats)
                .append(", budgetSeats=").append(budgetSeats)
                .append("]")
                .toString();
    }
}
