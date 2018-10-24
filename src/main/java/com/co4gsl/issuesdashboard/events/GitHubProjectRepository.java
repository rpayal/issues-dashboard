package com.co4gsl.issuesdashboard.events;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author rpayal
 */
public interface GitHubProjectRepository extends PagingAndSortingRepository<GitHubProject, Long> {
    GitHubProject findByRepoName(String repoName);
}
