package com.chainsys.grocery.util;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

public class Jdbctemplate {
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate obj = new JdbcTemplate(dataSource);
		return obj;
	}
}
