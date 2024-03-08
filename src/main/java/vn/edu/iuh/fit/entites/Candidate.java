package vn.edu.iuh.fit.entites;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @Column(name = "can_id")
    private long id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "candidate")
    private List<Experience> experience;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public Candidate() {
    }

    public Candidate(long id, String name, String email, List<Experience> experience) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", experience=" + experience +
                '}';
    }
}
