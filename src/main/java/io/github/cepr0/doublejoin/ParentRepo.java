package io.github.cepr0.doublejoin;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParentRepo extends JpaRepository<Parent, Integer> {

	@Query("select distinct p as parent from Parent p left join fetch p.children c order by p.id, c.id")
	List<Parent> findByOrderById();

	@EntityGraph(attributePaths = "children")
	List<Parent> findDistinctByOrderById();

	@Query("select p as parent, n as neighbour from Parent p left join Stepchild n on n.parentId = p.id and n.partitionKey = p.partitionKey order by p.id, n.id")
	List<ParentProjection> getParentProjectionsDoubleJoinedWithStepchild();
}
