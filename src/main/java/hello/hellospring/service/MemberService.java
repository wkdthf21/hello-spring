package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

@Transactional
public class MemberService {

	private final MemberRepository memberRepository;

	/**
	 * DI가 가능하도록 만든다.
	 * @param MemberRepository
	 */
	@Autowired
	public MemberService(MemberRepository MemberRepository) {
		this.memberRepository = MemberRepository;
	}

	/**
	 * 회원 가입
	 */
	public Long join(Member member) {
		// 중복 회원은 안된다
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName()).ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}

	/**
	 * 전체 회원 조회
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}

}
