package vn.edu.iuh.fit.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.entites.Candidate;
import vn.edu.iuh.fit.entites.Experience;

import java.util.Optional;

@Repository
public interface CandidateReponsitory extends JpaRepository<Candidate,Long> {
        Candidate findByUsername(String username);
}
