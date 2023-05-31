package com.salesforce;

import java.util.Comparator;

public class ComparisonByCity implements Comparator<Team> {
    @Override
    public int compare(Team o1, Team o2) {
        return o1.getCity().compareTo(o2.getCity());
    }
}
