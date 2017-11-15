package com.zenoton.MoviePersonDB;

import com.zenoton.MoviePersonDB.db.data.DefaultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories(basePackages = {"com.zenoton.MoviePersonDB.db.dao"})
@SpringBootApplication
public class MoviePersonDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviePersonDbApplication.class, args);
	}
	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner(ApplicationContext ctx){
		return args -> {
			new DefaultData().generateData();
		};
	}
}
