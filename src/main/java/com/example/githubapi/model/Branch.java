package com.example.githubapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {

    private String name;
    private Commit commit;

    @Getter
    @Setter
    public static class Commit {
        private String sha;
    }
}
