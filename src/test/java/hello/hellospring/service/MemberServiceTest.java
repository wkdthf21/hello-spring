package hello.hellospring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {

	MemoryMemberRepository memoryMemberRepository;
	MemberService memberService;

	@BeforeEach
	public void beforeEach() {
		memoryMemberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memoryMemberRepository);
	}

	@AfterEach
	public void afterEach() {
		memoryMemberRepository.clearStore();
	}

	@Test
	void 회원가입() {

		// given
		Member member = new Member();
		member.setName("hello");

		// when
		Long saveId = memberService.join(member);

		// then
		Member findMember = memoryMemberRepository.findById(saveId).get();
		assertEquals(member.getName(), findMember.getName());
	}

	/**
	 * 예외 상황 테스트도 필요
	 */
	@Test
	public void 중복_회원_저장() {

		//given
		Member member1 = new Member();
		member1.setName("spring");
		Member member2 = new Member();
		member2.setName("spring");

		// when
		memberService.join(member1);
		// 예외가 발생해야 한다
		IllegalStateException e = assertThrows(IllegalStateException.class,
			() -> memberService.join(member2));

		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

	}

	@Test
	void findMembers() {
	}

	@Test
	void findOne() {
	}
}