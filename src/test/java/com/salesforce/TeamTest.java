package com.salesforce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void testCreateATeamWithANullCity() {
        Team team = new Team(null, "Orioles"); //instantiation
    }

    @Test
    void testCreateATeamWithANullMascot() {
        assertThatThrownBy(() -> new Team("Baltimore", null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Mascot cannot be null");
    }

    @Test
    void testCreateATeamWithABlankCity() {
        assertThatThrownBy(() -> new Team("  \t", "Giraffes"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("City cannot be blank");
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
        System.out.println(baltimoreOrioles.hashCode());
        System.out.println(baltimoreOrioles2.hashCode());
        assertThat(baltimoreOrioles.hashCode()).isEqualTo(baltimoreOrioles2.hashCode());
    }

    @Test
    void testComparingTwoTeams() {
        Team la = new Team("Los Angeles", "Lakers")
            .addWin().addWin().addWin().addLoss();
        Team chicago = new Team("Chicago", "Bulls")
            .addLoss().addLoss().addWin().addLoss();
        int result = la.compareTo(chicago);
        //1.left hand is greater, it should be positive
        //2 right hand is greater, it should be negative
        //3.both side are equal, then 0;
        // 4 wins 1 win
        assertThat(result).isEqualTo(2);
    }
}















