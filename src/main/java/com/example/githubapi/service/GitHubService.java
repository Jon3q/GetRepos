package com.example.githubapi.service;

import com.example.githubapi.model.Branch;
import com.example.githubapi.model.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubService {

    private final RestTemplate restTemplate;

    public GitHubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Repository> getNonForkRepositories(String username) throws UserNotFoundException {
        String url = "https://api.github.com/users/" + username + "/repos";
        Repository[] repositories = restTemplate.getForObject(url, Repository[].class);

        if (repositories == null || repositories.length == 0) {
            throw new UserNotFoundException("User not found");
        }

        return Arrays.stream(repositories)
                .filter(repo -> !repo.isFork())
                .peek(repo -> repo.setBranches(getBranches(username, repo.getName())))
                .collect(Collectors.toList());
    }

    private List<Branch> getBranches(String username, String repoName) {
        String url = "https://api.github.com/repos/" + username + "/" + repoName + "/branches";
        Branch[] branches = restTemplate.getForObject(url, Branch[].class);
        return Arrays.asList(branches);
    }
}
