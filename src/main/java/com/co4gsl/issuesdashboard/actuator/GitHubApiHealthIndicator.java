package com.co4gsl.issuesdashboard.actuator;

import com.co4gsl.issuesdashboard.github.GitHubClient;
import com.co4gsl.issuesdashboard.github.RepositoryEvent;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GitHubApiHealthIndicator implements HealthIndicator {

    private GitHubClient gitHubClient;

    public GitHubApiHealthIndicator(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    @Override
    public Health health() {
        try {
            ResponseEntity<RepositoryEvent[]> responseEntity = gitHubClient
                    .fetchEvents("spring-projects", "spring-boot");
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return Health.up()
                        .withDetail("X-RateLimit-Remaining", responseEntity.getHeaders().getFirst("X-RateLimit-Remaining"))
                        .build();
            }
            else {
                return Health.down().withDetail("status", responseEntity.getStatusCode()).build();
            }
        }
        catch (Exception e) {
            return Health.down(e).build();
        }
    }
}
