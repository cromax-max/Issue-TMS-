package ru.netology.domain;

import java.util.Comparator;

public class ByNewestComparator implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getLifetimeIssue() - o2.getLifetimeIssue();
    }
}
