package me.huseinnashr.pma.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import me.huseinnashr.pma.ProjectManagementApplication;
import me.huseinnashr.pma.entities.Project;

@ContextConfiguration(classes = ProjectManagementApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@SqlGroup({
		@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = { "classpath:db_data.sql",
				"classpath:db_schema.sql" }),
		@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db_drop.sql") })
public class ProjectRepositoryIntegrationTest {
	@Autowired
	ProjectRepository proRepo;

	@Test
	public void ifNewProjectSaved_thenSuccess() {
		Project newProject = new Project("New Test Project", "COMPLETE", "Test Description");
		proRepo.save(newProject);

		assertEquals(5, proRepo.findAll().size());
	}
}
