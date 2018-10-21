package com.co4gsl.issuesdashboard.events;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author rpayal
 */
@Entity
@Table(name = "GITHUB_PROJECT")
public class GitHubProject implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String orgName;

    @Column(unique = true)
    private String repoName;

    public GitHubProject() {
    }

    public GitHubProject(String orgName, String repoName) {
        this.orgName = orgName;
        this.repoName = repoName;
    }

    public Long getId() {
        return id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    @Override
    public String toString() {
        return "GitHubProject{" +
                "id=" + id +
                ", orgName='" + orgName + '\'' +
                ", repoName='" + repoName + '\'' +
                '}';
    }
}