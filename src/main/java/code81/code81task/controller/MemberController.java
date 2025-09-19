// package code81.code81task.controller;

// import code81.code81task.service.UserActivityService;
// import code81.code81task.model.Member;
// import code81.code81task.service.MemberService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/v1/members")
// public class MemberController {
    
//     @Autowired
//     private MemberService memberService;
    
//     @Autowired
//     private UserActivityService userActivityService;
    
//     @GetMapping
//     @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'STAFF')")
//     public List<Member> getAllMembers() {
//         userActivityService.logActivity("GET all members");
//         return memberService.findAll();
//     }
    
//     @GetMapping("/{id}")
//     @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'STAFF')")
//     public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
//         userActivityService.logActivity("GET member by id: " + id);
//         Optional<Member> member = memberService.findById(id);
//         return member.map(ResponseEntity::ok)
//                     .orElse(ResponseEntity.notFound().build());
//     }
    
//     @PostMapping
//     @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'STAFF')")
//     public Member createMember(@RequestBody Member member) {
//         userActivityService.logActivity("CREATE member: " + member.getFirstName() + " " + member.getLastName());
//         return memberService.save(member);
//     }
    
//     @PutMapping("/{id}")
//     @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'STAFF')")
//     public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
//         userActivityService.logActivity("UPDATE member: " + id);
//         Optional<Member> member = memberService.findById(id);
//         if (member.isPresent()) {
//             Member updatedMember = member.get();
//             updatedMember.setFirstName(memberDetails.getFirstName());
//             updatedMember.setLastName(memberDetails.getLastName());
//             updatedMember.setEmail(memberDetails.getEmail());
//             updatedMember.setPhone(memberDetails.getPhone());
//             updatedMember.setAddress(memberDetails.getAddress());
//             return ResponseEntity.ok(memberService.save(updatedMember));
//         }
//         return ResponseEntity.notFound().build();
//     }
    
//     @DeleteMapping("/{id}")
//     @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
//     public ResponseEntity<?> deleteMember(@PathVariable Long id) {
//         userActivityService.logActivity("DELETE member: " + id);
//         if (memberService.findById(id).isPresent()) {
//             memberService.deleteById(id);
//             return ResponseEntity.ok().build();
//         }
//         return ResponseEntity.notFound().build();
//     }
    
//     @GetMapping("/search/name/{name}")
//     @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'STAFF')")
//     public List<Member> searchByName(@PathVariable String name) {
//         userActivityService.logActivity("SEARCH members by name: " + name);
//         return memberService.searchByName(name);
//     }
// }