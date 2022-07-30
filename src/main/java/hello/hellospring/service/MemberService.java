package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member);
        memberRepository.save(member);                  // 아니면 저장해라
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())  // 회원이름을 받아 리포지토리에서 찾아보고
            .ifPresent(m ->{                            // 이름이 있으면
                throw new IllegalStateException("이미 존재하는 회원입니다.");
        } );
    }
    // 전체 회원조회
    public List<Member> findMember(){
        return memberRepository.findAll();
    }
    // 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
