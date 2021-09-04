package ru.netology.manager;

import ru.netology.domain.ByNewestComparator;
import ru.netology.domain.Issue;
import ru.netology.repository.IssuesRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IssuesManager {
    private IssuesRepository repo;

    public IssuesManager(IssuesRepository repo) {
        this.repo = repo;
    }

    public void add(Issue e) {
        repo.add(e);
    }

    public List<Issue> findOpenIssues() {
        List<Issue> result = repo.getOpenIssues();
        sortByNewest(result, new ByNewestComparator());
        return result;
    }

    public List<Issue> findCloseIssues() {
        List<Issue> result = repo.getCloseIssues();
        sortByNewest(result, new ByNewestComparator());
        return result;
    }

    public List<Issue> filterByAuthor(String name) {
        List<Issue> result = repo.filterByAuthor(name);
        Collections.sort(result);
        return result;
    }

    public List<Issue> filterByLabel(String label) {
        List<Issue> result = repo.filterByLabel(label);
        Collections.sort(result);
        return result;
    }

    public List<Issue> filterByAssignee(String assignee) {
        List<Issue> result = repo.filterByAssignee(assignee);
        Collections.sort(result);
        return result;
    }

    public void sortByNewest(List<Issue> list, Comparator<Issue> comparator) {
        list.sort(comparator);
    }

    public void close(int id) {
        repo.closeById(id);
    }

    public void open(int id) {
        repo.openById(id);
    }
}
