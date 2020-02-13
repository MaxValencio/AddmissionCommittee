package ua.nure.odnokozov.admission.committee.entity;

import java.util.Map;
import java.util.Objects;

public class Faculty {

    private long id;
    private Map<Language, Title> titles;
    private Map<Language, Title> descriptions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<Language, Title> getTitles() {
        return titles;
    }

    public void setTitles(Map<Language, Title> titles) {
        this.titles = titles;
    }

    public Map<Language, Title> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Map<Language, Title> descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descriptions == null) ? 0 : descriptions.hashCode());
        result = prime * result + ((titles == null) ? 0 : titles.hashCode());
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
        Faculty other = (Faculty) obj;
        return Objects.equals(descriptions, other.descriptions) && Objects.equals(titles, other.titles);
    }

    @Override
    public String toString() {
        return "Faculty [id=" + id + ", titles=" + titles + ", descriptions=" + descriptions + "]";
    }
}
