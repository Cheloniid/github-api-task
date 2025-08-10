package com.michal.github_api_task.service;

import com.michal.github_api_task.exception.UserNotFoundException;
import com.michal.github_api_task.model.BranchDTO;
import com.michal.github_api_task.model.RepoDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GitHubService {

    //https://api.github.com/users/USERNAME/repos

    private List<RepoDTO> getReposFromGithub(String userName) {
        String url = String.format("https://api.github.com/users/%s/repos", userName);
        List<RepoDTO> reposDTO = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<List> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, List.class
            );
            List<Map<String, Object>> repos = response.getBody();

            for (Map<String, Object> repo : repos) {
                Boolean fork = (Boolean) repo.get("fork");
                if (fork) {
                    continue;
                }

                String repoName = (String) repo.get("name");
                Map<String, Object> owner = (Map<String, Object>) repo.get("owner");
                String ownerLogin = (String) owner.get("login");

                RepoDTO repoDTO = new RepoDTO();
                repoDTO.setRepoName(repoName);
                repoDTO.setOwnerLogin(ownerLogin);

                reposDTO.add(repoDTO);
            }
            return reposDTO;
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException("Could not find user: " + userName);
        }
    }

    private List<RepoDTO> updateRepoBranches(List<RepoDTO> repos) {
        for (RepoDTO repoDTO : repos) {
            String url =
                    String.format("https://api.github.com/repos/%s/%s/branches",
                            repoDTO.getOwnerLogin(), repoDTO.getRepoName());

            List<BranchDTO> branchesDTO = new ArrayList<>();

            RestTemplate restTemplate = new RestTemplate();
            List<Map<String, Object>> branches = restTemplate.getForObject(url, List.class);

            for (Map<String, Object> branch : branches) {
                String branchName = (String) branch.get("name");
                Map<String, Object> commit = (Map<String, Object>) branch.get("commit");
                String commitSHA = (String) commit.get("sha");

                BranchDTO branchDTO = new BranchDTO();
                branchDTO.setName(branchName);
                branchDTO.setLastCommitSHA(commitSHA);

                branchesDTO.add(branchDTO);

            }
            repoDTO.setBranches(branchesDTO);
        }
        return repos;
    }

    public List<RepoDTO> getReposWithBranches(String userName) {
        return updateRepoBranches(getReposFromGithub(userName));
    }
}
