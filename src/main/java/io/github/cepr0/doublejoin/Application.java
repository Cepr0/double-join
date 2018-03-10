package io.github.cepr0.doublejoin;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;
import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
public class Application {

	private final ParentRepo parentRepo;
	private final ChildRepo childRepo;
	private final StepchildRepo stepchildRepo;

	public Application(ParentRepo parentRepo, ChildRepo childRepo, StepchildRepo stepchildRepo) {
		this.parentRepo = parentRepo;
		this.childRepo = childRepo;
		this.stepchildRepo = stepchildRepo;
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener
	public void onReady(ApplicationReadyEvent e) {

		List<Parent> parents = parentRepo.saveAll(asList(
				new Parent(10, "parent1"),
				new Parent(20, "parent2"),
				new Parent(30, "parent3")
		));

		childRepo.saveAll(asList(
				new Child(null, parents.get(0).getId(), "child1"),
				new Child(10, parents.get(0).getId(), "child2"),
				new Child(11, parents.get(0).getId(), "child3"),
				new Child(null, parents.get(1).getId(), "child4"),
				new Child(20, parents.get(1).getId(), "child5"),
				new Child(21, parents.get(1).getId(), "child6"),
				new Child(20, parents.get(1).getId(), "child7")
		));

		stepchildRepo.saveAll(asList(
				new Stepchild(null, parents.get(0).getId(), "stepchild1"),
				new Stepchild(10, parents.get(0).getId(), "stepchild2"),
				new Stepchild(11, parents.get(0).getId(), "stepchild3"),
				new Stepchild(null, parents.get(1).getId(), "stepchild4"),
				new Stepchild(20, parents.get(1).getId(), "stepchild5"),
				new Stepchild(21, parents.get(1).getId(), "stepchild6"),
				new Stepchild(20, parents.get(1).getId(), "stepchild7")
		));
	}
}
