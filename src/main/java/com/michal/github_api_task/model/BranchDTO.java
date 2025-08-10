package com.michal.github_api_task.model;

public class BranchDTO {

    private String name;
    private String lastCommitSHA;

    public String getLastCommitSHA() {
        return lastCommitSHA;
    }

    public void setLastCommitSHA(String lastCommitSHA) {
        this.lastCommitSHA = lastCommitSHA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BranchDTO{" +
                "lastCommitSHA='" + lastCommitSHA + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
