package project.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import project.jpa.entity.Hello;
import project.jpa.entity.QHello;
import project.jpa.entity.User;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.Period;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
class JpaApplicationTests {

	@Autowired
	EntityManager em;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {

	}

	@Test
	public void sample() throws Exception {
		this.mockMvc.perform(get("/"))
				.andExpect(status().isOk());
	}

	@Test
	void contextLoads(){
		Hello hello = new Hello();
		em.persist(hello);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QHello qHello = new QHello("h");

		Hello result = query
				.selectFrom(qHello)
				.fetchOne();

		Assertions.assertEquals(result,hello);

	}


	@Test
	public void 생일을_나이로_변경(){
		// given
		LocalDate birthDate = LocalDate.of(1989, 02, 27);
		LocalDate currentDate = LocalDate.now();
		// when
		System.out.println(Period.between(birthDate, currentDate).getYears());

		// then
	}

	@Test
	public void test(){
		int i = 5;
		int i2 = 15;
		int i3 = 25;

		System.out.println(i/10);
		System.out.println(i2/10);
		System.out.println(i3/10);
	}

}
