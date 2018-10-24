package com.co4gsl.issuesdashboard.events;

import com.co4gsl.issuesdashboard.github.RepositoryEvent;

import java.util.List;

public class DashboardEntry {

    private GitHubProject project;

    private List<RepositoryEvent> events;

    public DashboardEntry(GitHubProject project, List<RepositoryEvent> events) {
        this.project = project;
        this.events = events;
    }
    public GitHubProject getProject() {
        return project;
    }
    public List<RepositoryEvent> getEvents() {
        return events;
    }
}
