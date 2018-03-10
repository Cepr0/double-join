package io.github.cepr0.doublejoin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sergei Poznanski, 2018-03-10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParentRepoTest {

	@Autowired private ParentRepo parentRepo;

	@Test
	@Transactional
	public void getParentProjectionsDoubleJoinedWithChildren() {
		List<ParentProjection> parents = parentRepo.getParentProjectionsDoubleJoinedWithStepchild();
		assertThat(parents).isNotNull();
		assertThat(parents).hasSize(4);
	}

	@Test
	public void findBy() {
		List<Parent> parents = parentRepo.findByOrderById();
		assertThat(parents).isNotNull();
		assertThat(parents).hasSize(3);
		assertThat(parents.get(0).getChildren()).hasSize(1);
		assertThat(parents.get(1).getChildren()).hasSize(2);
		assertThat(parents.get(2).getChildren()).hasSize(0);
	}

	@Test
	public void findDistinctBy() {
		List<Parent> parents = parentRepo.findDistinctByOrderById();
		assertThat(parents).isNotNull();
		assertThat(parents).hasSize(3);
		assertThat(parents.get(0).getChildren()).hasSize(1);
		assertThat(parents.get(1).getChildren()).hasSize(2);
		assertThat(parents.get(2).getChildren()).hasSize(0);
	}
}