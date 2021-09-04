package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IssuesRepository {
    private List<Issue> issues = new ArrayList<>();

    public void add(Issue e) {
        issues.add(e);
    }

    public List<Issue> getOpenIssues() {
        Iterator<Issue> iterator = issues.iterator();
        List<Issue> tmp = new ArrayList<>();
        while (iterator.hasNext()) {
            Issue issue = iterator.next();
            if (issue.isOpen()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public List<Issue> getCloseIssues() {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (!issue.isOpen()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public List<Issue> filterByAuthor(String name) {
        Iterator<Issue> iterator = issues.iterator();
        List<Issue> tmp = new ArrayList<>();
        while (iterator.hasNext()) {
            Issue issue = iterator.next();
            if (issue.getAuthor().contains(name)) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public List<Issue> filterByLabel(String label) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getLabel().contains(label)) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public List<Issue> filterByAssignee(String assignee) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getAssignee().contains(assignee)) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public void closeById(int id) {
        for (Issue i : getOpenIssues()) {
            if (i.getId() == id) {
                i.setOpen(false);
            }
        }
    }

    public void openById(int id) {
        for (Issue i : getCloseIssues()) {
            if (i.getId() == id) {
                i.setOpen(true);
            }
        }
    }
}
