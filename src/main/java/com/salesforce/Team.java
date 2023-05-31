package com.salesforce;

public class Team {

    private String city;
    private String mascot;

    public Team(String city, String mascot) {
        this.city = city;
        this.mascot = mascot;
    }

    public int getWins() {
        return 0;
    }

    public int getLosses() {
        return 0;
    }

    public String getCity() {
        return city;
    }

    public String getMascot() {
        return mascot;
    }

    public Team moveTeamTo(String newCity) {
        return new Team(newCity, mascot);
    }
}
