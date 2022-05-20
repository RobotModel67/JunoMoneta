package net.robotmodel67.workshop.stage0001.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import net.robotmodel67.workshop.stage0001.model.Persona;


@Component
public class JobListener extends JobExecutionListenerSupport {

	private static final Logger LOG = LoggerFactory.getLogger(JobListener.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JobListener(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOG.info("== Finalizó el Job ==");
			jdbcTemplate
			.query("SELECT primer_nombre, segundo_nombre, telefono from persona",
					(rs, row) -> new Persona(rs.getString(1),rs.getString(2),rs.getString(3)))
			.forEach(persona -> LOG.info("Registro <" + persona + ">"));
		}
	
	}
	
	
}
