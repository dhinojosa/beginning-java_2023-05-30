package com.salesforce;

import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Testing a team with a record of 3-1")
    void testWinLossRecordWithThreeWinsAndOneLoss() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        Team baltimoreOriolesWithThreeWinsAndALoss = baltimoreOrioles.addWin().addWin().addWin().addLoss();
        String record = baltimoreOriolesWithThreeWinsAndALoss.getRecord();
        assertThat(record).isEqualTo("3-1");
    }


    @Test
    @DisplayName("Testing a team with a record of 2-2")
    void testWinLossRecordWithTwoWinsAndTwoLosses() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        Team baltimoreOriolesWithThreeWinsAndALoss = baltimoreOrioles.addWin().addWin().addLoss().addLoss();
        String record = baltimoreOriolesWithThreeWinsAndALoss.getRecord();
        assertThat(record).isEqualTo("2-2");
    }

    @Test
    void testStringRepresentationOfTheBaltimoreOrioles() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        assertThat(baltimoreOrioles).hasToString("Team{city=Baltimore, mascot=Orioles}");
    }

    @Test
    void testStringRepresentationOfTheFloridaMarlins() {
        Team floridaMarlins = new Team("Florida", "Marlins"); //instantiation
        assertThat(floridaMarlins.toString()).isEqualTo("Team{city=Florida, mascot=Marlins}");
    }

    @Test
    void testEqualityOfTheSameTeam() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        Team baltimoreOrioles2 = new Team("Baltimore", "Orioles"); //instantiation
        assertThat(baltimoreOrioles).isEqualTo(baltimoreOrioles2);
    }

    @Test
    void testNotTheSameReferenceOfTheSameTeam() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        Team baltimoreOrioles2 = new Team("Baltimore", "Orioles"); //instantiation
        assertThat(baltimoreOrioles).isNotSameAs(baltimoreOrioles2);
    }


    @SuppressWarnings({"UnnecessaryLocalVariable"})
    @Test
    void testTheSameReferenceOfTheSameTeam() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles"); //instantiation
        Team baltimoreOrioles2 = baltimoreOrioles;
        assertThat(baltimoreOrioles).isSameAs(baltimoreOrioles2);
    }

    @Test
    void testIfTheyAreEqualThenTheyMustHaveTheSameHashCode() {
        Team baltimoreOrioles = new Team("Baltimore", "Orioles");
        Team baltimoreOrioles2 = new Team("Baltimore", "Orioles");
        assertThat(baltimoreOrioles.hashCode()).isEqualTo(baltimoreOrioles2.hashCode());
    }
}
