package com.salesforce;

public class Team {

    private final String city;
    private final String mascot;
    private final int wins;
    private final int losses;

    public Team(String city, String mascot) {
        this(city, mascot, 0, 0);
    }

    private Team(String city, String mascot, int wins, int losses) {
        this.city = city;
        this.mascot = mascot;
        this.wins = wins;
        this.losses = losses;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
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

    public Team addWin() {
        return new Team(city, mascot, this.wins + 1, this.losses);
    }

    public Team addLoss() {
        return new Team(city, mascot, this.wins, this.losses + 1 );
    }
}
