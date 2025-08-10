package com.michal.github_api_task;

import com.michal.github_api_task.model.BranchDTO;
import com.michal.github_api_task.model.RepoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubApiTaskApplicationTests {

    String USER_NAME = "cheloniid";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void integrationHappyPath() {

        String url = "/repo/" + USER_NAME;

        ResponseEntity<RepoDTO[]> response = restTemplate.getForEntity(url, RepoDTO[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        RepoDTO[] repos = response.getBody();

        assertNotNull(repos, "Error: repository list is null");
        assertTrue(repos.length > 0, "Error: repository list is empty");

        // A flag meaning this valid non fork repo is on the list
        boolean containsValidRepo = false;

        for (RepoDTO repo : repos) {
            assertNotNull(repo, "Error: repository is null");

            if (repo.getRepoName().equals("github-api-task")) {
                containsValidRepo = true;
            }

            assertNotNull(repo.getRepoName(), "Error: repository name is null");
            assertTrue(repo.getRepoName().length() > 0, "Error: repository name is empty");

            assertNotNull(repo.getOwnerLogin(), "Error: repository owner login is null");
            assertTrue(repo.getOwnerLogin().length() > 0, "Error: repository owner login is empty");

            assertNotNull(repo.getBranches(), "Error: repository branches is null");
            assertTrue(repo.getBranches().size() > 0, "Error: repository branches is empty");

            for (BranchDTO branch : repo.getBranches()) {
                assertNotNull(branch, "Error: repository branch is null");
                assertTrue(branch.getName().length() > 0, "Error: repository branch name is empty");
                assertTrue(branch.getLastCommitSHA().length() > 0, "Error: repository branch last commit hash is empty");
            }

            // Tests if repository list doesn't contain a fork
            assertNotEquals(repo.getRepoName(),
                    "Iza-TicTacToe",
                    "Error: repository list contains a fork");
        }
        assertTrue(containsValidRepo,
                "Error: repository list doesn't contain this valid non fork repo");
    }
}
