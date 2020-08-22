package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

class MemoryMemberRepositoryTest {

	MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

	@AfterEach
	public void afterEach() {
		memoryMemberRepository.clearStore();
	}

	@Test
	public void save() {
		// given
		Member member = new Member();
		member.setName("spring");

		// when
		memoryMemberRepository.save(member);

		// then
		Member result = memoryMemberRepository.findById(member.getId()).get();
		assertThat(member).isEqualTo(result);
	}

	@Test
	public void findByName() {

		// given
		Member member1 = new Member();
		member1.setName("spring1");
		memoryMemberRepository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		memoryMemberRepository.save(member2);

		// when
		Member result = memoryMemberRepository.findByName("spring1").get();

		// then
		assertThat(result).isEqualTo(member1);
	}

	@Test
	public void findAll() {

		//given
		Member member1 = new Member();
		member1.setName("spring1");
		memoryMemberRepository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		memoryMemberRepository.save(member2);

		// when
		List<Member> result = memoryMemberRepository.findAll();

		// then
		assertThat(result.size()).isEqualTo(2);

	}
}