// package code81.code81task.service;
// import code81.code81task.model.Member;
// import code81.code81task.repository.MemberRepository;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class MemberService {
    
//     @Autowired
//     private MemberRepository memberRepository;
    
//     public List<Member> findAll() {
//         return memberRepository.findAll();
//     }
    
//     public Optional<Member> findById(Long id) {
//         return memberRepository.findById(id);
//     }
    
//     public Optional<Member> findByEmail(String email) {
//         return memberRepository.findByEmail(email);
//     }
    
//     public Member save(Member member) {
//         return memberRepository.save(member);
//     }
    
//     public void deleteById(Long id) {
//         memberRepository.deleteById(id);
//     }
    
//     public List<Member> searchByName(String name) {
//         return memberRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
//     }
    
//     public boolean existsByEmail(String email) {
//         return memberRepository.findByEmail(email).isPresent();
//     }
// }