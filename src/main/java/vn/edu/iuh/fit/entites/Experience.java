package vn.edu.iuh.fit.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @Column(name ="exp_id")
    private long id;
    private String companyName;
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Experience(long id, String companyName, Candidate candidate) {
        this.id = id;
        this.companyName = companyName;
        this.candidate = candidate;
    }

    public Experience() {
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", candidate=" + candidate +
                '}';
    }
}
