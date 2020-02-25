package ua.nure.odnokozov.admission.committee.entity;

import java.util.Objects;

public class Language extends Entity {

    private static final long serialVersionUID = 6500522734614149307L;

    private String label;

    public Language() {
    }
    
    public Language(long id, String label) {
        super(id);
        this.label = label;
        
    }
    
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((label == null) ? 0 : label.hashCode());
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
        return Objects.equals(label, other.label);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Language [id=").append(super.getId())
                .append(", label=").append(label)
                .append("]")
                .toString();
    }
}
