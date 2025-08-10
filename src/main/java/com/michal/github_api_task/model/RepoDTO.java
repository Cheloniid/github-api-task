package com.michal.github_api_task.model;

import java.util.List;

public class RepoDTO {
    private String repoName;
    private String ownerLogin;
    private List<BranchDTO> branches;

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public List<BranchDTO> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchDTO> branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "RepoDTO{" +
                "branches=" + branches +
                ", repoName='" + repoName + '\'' +
                ", ownerLogin='" + ownerLogin + '\'' +
                '}';
    }
}
