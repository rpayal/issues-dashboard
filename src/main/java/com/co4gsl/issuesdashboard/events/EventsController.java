package com.co4gsl.issuesdashboard.events;

import com.co4gsl.issuesdashboard.github.GitHubClient;
import com.co4gsl.issuesdashboard.github.RepositoryEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class EventsController {

    private final GitHubClient gitHubClient;

    private final GitHubProjectRepository repository;

    public EventsController(GitHubClient gitHubClient, GitHubProjectRepository repository) {
        this.gitHubClient = gitHubClient;
        this.repository = repository;
    }

    @GetMapping("/events/{projectName}")
    @ResponseBody
    public ResponseEntity<RepositoryEvent[]> fetchEvents(@PathVariable String projectName) {
        GitHubProject project = this.repository.findByRepoName(projectName);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }
        ResponseEntity<RepositoryEvent[]> response = this.gitHubClient
                .fetchEvents(project.getOrgName(), project.getRepoName());
        return ResponseEntity.ok()
                .eTag(response.getHeaders().getETag())
                .body(response.getBody());
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        List<DashboardEntry> entries = StreamSupport
                .stream(this.repository.findAll().spliterator(), true)
                .map(p -> new DashboardEntry(p, gitHubClient.fetchEventsList(p.getOrgName(), p.getRepoName())))
                .collect(Collectors.toList());
        model.addAttribute("entries", entries);
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("projects", repository.findAll());
        return "admin";
    }
}
