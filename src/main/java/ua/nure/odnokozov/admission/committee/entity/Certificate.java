package ua.nure.odnokozov.admission.committee.entity;

import java.util.Map;
import java.util.Set;

public class Certificate extends Entity {

    private static final long serialVersionUID = 2727090609651144545L;

    private long userId;
    private Set<String> imageUrls;
    private Map<Subject, Integer> subjectsRatings;  
    
    public Certificate(long id, Set<String> urls, long userId) {
        super(id);
        this.imageUrls = urls;
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
    
    public void setUserId(long userId) {
        this.userId = userId;
    }
    
    public void setUrls(Set<String> urls) {
        this.imageUrls = urls;
    }
    
    public Set<String> getUrls() {
        return imageUrls;
    }
    
    public Map<Subject, Integer> getSubjectsRatings() {
        return subjectsRatings;
    }
    
    public void setSubjectsRatings(Map<Subject, Integer> subjectsRatings) {
        this.subjectsRatings = subjectsRatings;
    }

    @Override
    public String toString() {
        return "Certificate [id=" + getId() + ", userId=" + userId + "imagesUrls=" + imageUrls + "]";
    }
}
