package ua.nure.odnokozov.admission.committee.entity;

import java.util.Objects;

public class Language {

    private long id;
    private String lable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lable == null) ? 0 : lable.hashCode());
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
        Language other = (Language) obj;
        return Objects.equals(lable, other.lable);
    }

    @Override
    public String toString() {
        return "Language [id=" + id + ", lable=" + lable + "]";
    }
}
