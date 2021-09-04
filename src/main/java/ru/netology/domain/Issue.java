package ru.netology.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Issue implements Comparable<Issue> {
    private boolean open = true;

    private int id;
    private String title;
    private String author;
    private Set<String> label;
    private Set<String> assignee;

    private int lifetimeIssue; //in minutes

    public Issue(int id, String title, String author, HashSet<String> label, HashSet<String> assignee,
                 int lifetimeIssue) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.label = label;
        this.assignee = assignee;
        this.lifetimeIssue = lifetimeIssue;
    }

    @Override
    public int compareTo(Issue i) {
        return id - i.id;
    }
}

