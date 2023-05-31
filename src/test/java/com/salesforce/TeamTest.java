package com.salesforce;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class TeamTest {
    //Z - Zero
    //O - One
    //M - Many
    //B - Boundary
    //I - Interface
    //E - Exception
    //S - Simple


    @Test
    void testCreateATeamWithAZeroZeroRecord() {
        Team team = new Team("Baltimore", "Orioles"); //instantiation
        int wins = team.getWins();
        int losses = team.getLosses();
        assertThat(wins).isZero();
        assertThat(losses).isZero();
    }

    @Test
    void testCreateATeamAndVerifyItsProperties() {
        Team team = new Team("Baltimore", "Orioles"); //instantiation
        String city = team.getCity();
        String mascot = team.getMascot();
        assertThat(city).isEqualTo("Baltimore");
        assertThat(mascot).isEqualTo("Orioles");
    }

    @Test
    void testMoveTheTeamIndianapolis() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        //Option 1
        //Team indianapolisOrioles = baltimoreOrioles.setCity("Indianpolis");

        //Option 2
        Team indianapolisOrioles = baltimoreOrioles.moveTeamTo("Indianapolis");
        assertThat(indianapolisOrioles.getCity()).isEqualTo("Indianapolis");
        assertThat(baltimoreOrioles.getCity()).isEqualTo("Baltimore");
    }

    @Test
    void testMoveTheTeamIndianapolisThenToChattanooga() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        Team chattanoogaOrioles = baltimoreOrioles.moveTeamTo("Indianapolis").moveTeamTo("Chattanooga");
        assertThat(chattanoogaOrioles.getCity()).isEqualTo("Chattanooga");
        System.out.println(baltimoreOrioles.getCity());
    }

    @Test
    void testThatTheTeamWonOneGame() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        //Command/Query Separation
        //1. win()
        //2. won()     1
        //3. addWin(); 2
        Team baltimoreOriolesWithAWin = baltimoreOrioles.addWin();
        assertThat(baltimoreOriolesWithAWin.getWins()).isEqualTo(1);
    }

    @Test
    void testThatTheTeamWonTwoGames() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        Team baltimoreOriolesWithTwoWins = baltimoreOrioles.addWin().addWin();
        assertThat(baltimoreOriolesWithTwoWins.getWins()).isEqualTo(2);
    }

    @Test
    void testThatTheTeamWonThreeGames() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        Team baltimoreOriolesWithTwoWins = baltimoreOrioles.addWin().addWin().addWin();
        assertThat(baltimoreOriolesWithTwoWins.getWins()).isEqualTo(3);
    }

    @Test
    void testThatTheTeamWonThreeGamesAndLostOne() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        Team baltimoreOriolesWithThreeWinsAndALoss = baltimoreOrioles.addWin().addWin().addWin().addLoss();
        assertThat(baltimoreOriolesWithThreeWinsAndALoss.getWins()).isEqualTo(3);
        assertThat(baltimoreOriolesWithThreeWinsAndALoss.getLosses()).isEqualTo(1);
    }

    @Test
    void testWinLossRecord() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        Team baltimoreOriolesWithThreeWinsAndALoss = baltimoreOrioles.addWin().addWin().addWin().addLoss();
        String record = baltimoreOriolesWithThreeWinsAndALoss.getRecord();
        assertThat(record).isEqualTo("3-1");
    }
}
