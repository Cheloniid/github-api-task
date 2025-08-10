package com.michal.github_api_task.controller;

import com.michal.github_api_task.model.RepoDTO;
import com.michal.github_api_task.service.GitHubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitHubController {

    @GetMapping("/repo/{username}")
    public List<RepoDTO> getRepos(@PathVariable String username) {

        GitHubService gitHubService = new GitHubService();
        List<RepoDTO> repos = gitHubService.getReposWithBranches(username);
        return repos;
    }
}
