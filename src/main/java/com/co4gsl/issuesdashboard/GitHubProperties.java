package com.co4gsl.issuesdashboard;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

/**
 * Since the GitHub API is rate-limited, we should create a `GithubProperties` properties class
 * bound to the "github" configuration namespace.
 * 
 * Use that property in a RestTemplate interceptor to add an authentication request header to each
 * request.
 */
@ConfigurationProperties("github")
@Validated
public class GitHubProperties {

    /**
     * Github private access token "user:token"
     */
    @Pattern(regexp = "\\w+:\\w+")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
