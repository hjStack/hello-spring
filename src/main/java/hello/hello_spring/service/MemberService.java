package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository)  {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(member1 ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(long memberId){
        return memberRepository.findById(memberId);
    }



}
