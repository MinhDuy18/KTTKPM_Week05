package vn.edu.iuh.fit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.entites.Candidate;
import vn.edu.iuh.fit.reponsitory.CandidateReponsitory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService implements UserDetailsService {

    @Autowired
    private CandidateReponsitory candidateRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Candidate candidate = candidateRepository.findByUsername(username);
        if (candidate == null) {
            throw new UsernameNotFoundException("Candidate not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(candidate.getName(), candidate.getEmail(), new ArrayList<>());
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Optional<Candidate> getCandidateById(long id) {
        return candidateRepository.findById(id);
    }

    public Candidate findByName(String name){
        return  candidateRepository.findByUsername(name);
    }


    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(long id, Candidate candidateDetails) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);
        if (optionalCandidate.isPresent()) {
            Candidate candidate = optionalCandidate.get();
            candidate.setName(candidateDetails.getName());
            candidate.setEmail(candidateDetails.getEmail());
            // Cập nhật các trường khác tùy thuộc vào yêu cầu
            return candidateRepository.save(candidate);
        } else {
            return null; // Hoặc có thể ném một exception tùy thuộc vào yêu cầu
        }
    }

    public void deleteCandidate(long id) {
        candidateRepository.deleteById(id);
    }
}

