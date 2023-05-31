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
}
