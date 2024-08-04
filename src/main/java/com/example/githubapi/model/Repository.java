package com.example.githubapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

    private String name;
    private Owner owner;
    private boolean fork;
    private List<Branch> branches;

    public void setOwnerLogin(String text) {
    }

    @Getter
    @Setter
    public static class Owner {
        private String login;
    }
}
