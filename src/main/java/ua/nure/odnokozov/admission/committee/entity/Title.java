package ua.nure.odnokozov.admission.committee.entity;

import java.util.Objects;

public class Title {

    private long id;
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        Title other = (Title) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Title [id=" + id + ", value=" + value + "]";
    }
}
