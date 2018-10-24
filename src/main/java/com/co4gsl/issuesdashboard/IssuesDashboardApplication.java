package com.co4gsl.issuesdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GitHubProperties.class)
public class IssuesDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssuesDashboardApplication.class, args);
	}
}
