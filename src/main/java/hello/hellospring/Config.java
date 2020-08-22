package hello.hellospring;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;

@org.springframework.context.annotation.Configuration
public class Config {

	//private final DataSource dataSource;
	//private final EntityManager em;
	private final MemberRepository memberRepository;

	@Autowired
	public Config(MemberRepository memberRepository) {
		//this.dataSource = dataSource;
		//this.em = em;
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}

	//@Bean
	//public MemberRepository memberRepository(){

	//return new MemoryMemberRepository();
	//return new JdbcMemberRepository(dataSource);
	//return new JdbcTemplateMemberRepository(dataSource);
	//return new JpaRepository(em);
	//}
}
