package ua.nure.odnokozov.admission.committee.entity;

import java.util.Map;
import java.util.Objects;

public class Subject extends Entity{

    private static final long serialVersionUID = 5672088183357688364L;

    private Map<Language, String> titles;

    public Map<Language, String> getTitles() {
        return titles;
    }

    public void setTitles(Map<Language, String> titles) {
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
        return new StringBuilder()
                .append("Subject [id=").append(super.getId())
                .append(", titles=").append(titles)
                .append("]")
                .toString();
    }
}
