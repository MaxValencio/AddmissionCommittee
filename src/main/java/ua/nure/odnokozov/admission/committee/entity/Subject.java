package ua.nure.odnokozov.admission.committee.entity;

import java.util.Map;
import java.util.Objects;

public class Subject {

    private long id;
    private Map<Language, Title> titles;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        Subject other = (Subject) obj;
        return Objects.equals(titles, other.titles);
    }

    @Override
    public String toString() {
        return "Subject [id=" + id + ", titles=" + titles + "]";
    }
}
