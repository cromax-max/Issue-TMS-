package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssuesRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IssuesManagerTest {
    private IssuesRepository repo = new IssuesRepository();
    private IssuesManager manager = new IssuesManager(repo);

    Issue first = new Issue(1, "ISSUE1", "Harry",
            new HashSet<>(Set.of("bug", "question")),
            new HashSet<>(Set.of("Harry", "George", "Michael")), 20);
    Issue second = new Issue(2, "ISSUE2", "Michael",
            new HashSet<>(Set.of()),
            new HashSet<>(Set.of("George", "Michael")), 90);
    Issue third = new Issue(3, "ISSUE3", "George",
            new HashSet<>(Set.of("bug")),
            new HashSet<>(Set.of("Harry", "George", "Michael")), 10);
    Issue fourth = new Issue(4, "ISSUE4", "Harry",
            new HashSet<>(Set.of("bug", "documentation", "question")),
            new HashSet<>(Set.of("Michael")), 2);
    Issue fifth = new Issue(5, "ISSUE5", "George",
            new HashSet<>(Set.of("documentation", "question")),
            new HashSet<>(Set.of()), 54);

    @BeforeEach
    public void setUp() {
        manager.add(second);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(first);
        manager.add(third);
    }

    @Test
    void shouldFindOpenIssues() {
        List<Issue> expected = new ArrayList<>(List.of(first, fifth, second));
        manager.close(4);
        manager.close(3);
        assertThat(manager.findOpenIssues(), equalTo(expected));
    }

    @Test
    void shouldFindCloseIssues() {
        List<Issue> expected = new ArrayList<>(List.of(fourth, third));
        manager.close(3);
        manager.close(4);
        assertThat(manager.findCloseIssues(), equalTo(expected));
    }

    @Test
    void shouldFilterByAuthor() {
        List<Issue> expected = new ArrayList<>(List.of(third, fifth));
        assertThat(manager.filterByAuthor("George"), equalTo(expected));
    }

    @Test
    void shouldNotFilterByAuthor() {
        List<Issue> expected = new ArrayList<>(List.of());
        assertThat(manager.filterByAuthor("Artur"), equalTo(expected));
    }

    @Test
    void shouldFilterByLabel() {
        List<Issue> expected = new ArrayList<>(List.of(fourth, fifth));
        assertThat(manager.filterByLabel("documentation"), equalTo(expected));
    }

    @Test
    void shouldNotFilterByLabel() {
        List<Issue> expected = new ArrayList<>(List.of());
        assertThat(manager.filterByLabel("doc"), equalTo(expected));
    }

    @Test
    void shouldFilterByAssignee() {
        List<Issue> expected = new ArrayList<>(List.of(first, third));
        assertThat(manager.filterByAssignee("Harry"), equalTo(expected));
    }

    @Test
    void shouldNotFilterByAssignee() {
        List<Issue> expected = new ArrayList<>(List.of());
        assertThat(manager.filterByAssignee("Maria"), equalTo(expected));
    }

    @Test
    void shouldCloseAndOpenById() {
        List<Issue> expected = new ArrayList<>(List.of(fourth, second));
        manager.close(4);
        manager.close(3);
        manager.close(1);
        manager.open(4);
        manager.close(5);
        assertThat(manager.findOpenIssues(), equalTo(expected));
    }
}