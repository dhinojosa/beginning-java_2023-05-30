package com.salesforce;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CollectionsTest {


    /**
     * 1. Arrays are lightweight and fast
     * 2. But that don't have supporting methods or a
     * good developer experience
     */
    @Test
    void testArraysWithPrimitives() {
        int[] arraysInt = new int[]{0, 1, 3, 5};
        int i = arraysInt[3];
        assertThat(i).isEqualTo(5);
    }

    @Test
    void testChangeAElement() {
        int[] arraysInt = new int[]{0, 1, 3, 5};
        arraysInt[2] = 10;
        assertThat(arraysInt[2]).isEqualTo(10);
        System.out.println(Arrays.toString(arraysInt));
    }

    @Test
    void testAddAnElementToAnAlreadyCreatedArray() {
        double[] arraysInt = new double[]{0.0, 1.1, 3.2, 5.3};
        System.out.println(arraysInt.length);
        double[] newArraysDoubleWithSize50 = Arrays.copyOf(arraysInt, 50);
        System.out.println(newArraysDoubleWithSize50.length);
        newArraysDoubleWithSize50[40] = 100;
        System.out.println(Arrays.toString(newArraysDoubleWithSize50));
    }

    @Test
    void testBadFloatingPointArithmetic() {
        double result = 3.51 * .03;
        System.out.println(result);
    }

    @Test
    void testUsingBigDecimal() {
        BigDecimal bigDecimal = new BigDecimal("3.51");
        BigDecimal result = bigDecimal.multiply(new BigDecimal(".03"));
        System.out.println(result);
    }

    @Test
    void testArraysWithTeams() {
        Team[] teams = new Team[10];
        teams[0] = new Team("Carolina", "Panthers");
        teams[1] = new Team("Miami", "Heat");
        System.out.println(Arrays.toString(teams));
    }

    @Test
    void testListOfIntegersClassic() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(10);
        integerList.add(20);
        integerList.add(40);
        System.out.println(integerList);
        assertThat(integerList.get(1)).isEqualTo(20);
        integerList.set(1, 90);
        System.out.println(integerList);
    }

    @Test
    void testListOfIntegersUtility() {
        List<Integer> integerList = Arrays.asList(10, 20, 40);
        assertThat(integerList.get(1)).isEqualTo(20);
        integerList.set(1, 90);
        System.out.println(integerList);
    }

    @Test
    void testListOfIntegersWithOf() {
        List<Integer> integerList = List.of(10, 20, 40);
        assertThat(integerList.get(1)).isEqualTo(20);
    }

    @Test
    void testSetOfIntegers() {
        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(10);
        integerSet.add(20);
        integerSet.add(30);
        integerSet.add(10);
        System.out.println(integerSet);
    }

    @Test
    void testSetOfTeams() {
        Set<Team> teamSet = new HashSet<>();
        teamSet.add(new Team("Houston", "Astros"));
        teamSet.add(new Team("Arizona", "Coyotes"));
        teamSet.add(new Team("Charlotte", "Hornets"));
        teamSet.add(new Team("Charlotte", "Hornets"));
        System.out.println(teamSet);
    }

    @Test
    void testSetOfTeamsWithMutability() {
        Set<Team> teamSet = new HashSet<>();
        teamSet.add(new Team("Houston", "Astros"));
        teamSet.add(new Team("Arizona", "Coyotes"));
        Team hornets = new Team("Charlotte", "Hornets");
        teamSet.add(hornets);
        Team hornets2 = new Team("Charlotte", "Sunshine");
        teamSet.add(hornets2);
        //question, what's in the set?
        //1. two charlotte teams
        //2. one charlotte hornets team
        //3. one charlotte sunshine with no hornets
        System.out.println(teamSet);
    }

    @Test
    void testMap() {
        Map<Integer, String> mapNumberToString = new HashMap<>();
        mapNumberToString.put(1, "One");
        mapNumberToString.put(2, "Two");
        mapNumberToString.put(3, "Three");
        assertThat(mapNumberToString.get(1)).isEqualTo("One");
        assertThat(mapNumberToString.get(4)).isNull();
    }

    @Test
    void testMapTeams() {
        Map<Team, String> mapTeamToStadium = new HashMap<>();
        mapTeamToStadium.put(new Team("Charlotte", "Hornets"), "The Nest");
        mapTeamToStadium.put(new Team("Washington", "Nationals"), "Nationals Field");
        mapTeamToStadium.put(new Team("Buffalo", "Bills"), "The Orchard");
        String actual = mapTeamToStadium.get(new Team("Buffalo", "Bills"));
        System.out.println(actual);
        assertThat(actual).isEqualTo("The Orchard");
    }

    public void doSomething(Map<String, Integer> map) {

    }

    @Test
    void testMapWithATree() {
        //abstraction on the left          = implementation on the right (specific)
        Map<Team, String> mapTeamToStadium = new TreeMap<>(Comparator.comparing(Team::getCity));
        mapTeamToStadium.put(new Team("Charlotte", "Hornets"), "The Nest");
        mapTeamToStadium.put(new Team("Washington", "Nationals"), "Nationals Field");
        mapTeamToStadium.put(new Team("Buffalo", "Bills"), "The Orchard");
        mapTeamToStadium.put(new Team("Dallas", "Stars"), "The Constellation");
        mapTeamToStadium.put(new Team("Seattle", "Mariners"), "The Docks");
        assertThat(mapTeamToStadium.get(new Team("Dallas", "Stars"))).isEqualTo("The Constellation");
    }

    @Test
    void testSortingCollection() {
        //1. Create a list of teams
        //2. Sort it by using Collections.sort(list, comparator);
        //3. THIS SORTS IN PLACE!! Means that it mutate your collection
        //4. Remember that Collections.sort returns void
        //5. Print out the list in the test
        List<Team> teamList = new ArrayList<>();
        teamList.add(new Team("Charlotte", "Hornets"));
        teamList.add(new Team("Washington", "Nationals"));
        teamList.add(new Team("Buffalo", "Bills"));
        teamList.add(new Team("Dallas", "Stars"));
        teamList.add(new Team("Seattle", "Mariners"));
        Collections.sort(teamList, Comparator.comparing(Team::getCity));
        System.out.println(teamList);
    }

    @Test
    void testSortingCollectionFromArraysAsList() {
        List<Team> teamList = Arrays.asList(
            new Team("Charlotte", "Hornets"),
            new Team("Washington", "Nationals"),
            new Team("Buffalo", "Bills"),
            new Team("Dallas", "Stars"),
            new Team("Seattle", "Mariners")
        );
        Collections.sort(teamList, Comparator.comparing(Team::getCity));
        System.out.println(teamList);
    }


    @Test
    void testSortingCollectionFromListOf() {
        List<Team> teamList = List.of (
            new Team("Charlotte", "Hornets"),
            new Team("Washington", "Nationals"),
            new Team("Buffalo", "Bills"),
            new Team("Dallas", "Stars"),
            new Team("Seattle", "Mariners")
        );

        assertThatThrownBy(() ->
            teamList.sort(Comparator.comparing(Team::getCity)))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void testSortingAnImmutableList() {
        List<Team> original = List.of (
            new Team("Charlotte", "Hornets"),
            new Team("Washington", "Nationals"),
            new Team("Buffalo", "Bills"),
            new Team("Dallas", "Stars"),
            new Team("Seattle", "Mariners")
        );

        List<Team> result = original
            .stream()
            .sorted(Comparator.comparing(Team::getCity))
            .collect(Collectors.toList());

        System.out.println(original);
        System.out.println("-----");
        System.out.println(result);
    }
}














