package com.dominik.quizservice;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {


	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres";
	private static final String POSTGRESQL = "postgresql";
	private static final String POSTGRESQL_CONTAINER = "postgres:15.0";


	@Bean
	@Qualifier(POSTGRESQL)
	PostgreSQLContainer<?> postgresqlContainer() {
		PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>(POSTGRESQL_CONTAINER)
				.withUsername(USERNAME)
				.withPassword(PASSWORD);
		postgresqlContainer.start();
		return postgresqlContainer;
	}

	@Bean
	DataSource dataSource(final PostgreSQLContainer<?> container) {
		return DataSourceBuilder.create()
				.type(HikariDataSource.class)
				.driverClassName(container.getDriverClassName())
				.url(container.getJdbcUrl())
				.username(container.getUsername())
				.password(container.getPassword())
				.build();
	}

}