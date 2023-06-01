package com.salesforce;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionsTest {
    private List<Team> teamList = List.of(
        new Team("Charlotte", "Hornets"),
        new Team("Washington", "Nationals"),
        new Team("Buffalo", "Bills"),
        new Team("Dallas", "Stars"),
        new Team("Seattle", "Mariners")
    );

    @Test
    void testFilteringUsingPredicate() {
        List<Team> result = Functions.myFilter(teamList, item -> item.getMascot().length() > 5);
        System.out.println(result);
    }

    @Test
    void testMapUsingFunction() {
        List<Integer> numbers = List.of(2, 4, 5, 1, 9, 15, 19,
            21, 33, 78, 93, 10);
        List<Integer> mapped = Functions.myMap(numbers, item -> item + 2);
        System.out.println(numbers);
        System.out.println("-----");
        System.out.println(mapped);
    }

    @Test
    void testMapUsingFunctionButTheReturnIsListString() {
        List<Integer> numbers = List.of(2, 4, 5, 1, 9, 15, 19,
            21, 33, 78, 93, 10);
        List<String> result = Functions.myMap(numbers, String::valueOf);

        System.out.println(result);
    }

    @Test
    void testMapUsingFunctionButTheReturnIsAlsoAList() {
        List<Integer> numbers = List.of(2, 4, 5, 1, 9, 15, 19,
            21, 33, 78, 93, 10);
        List<Integer> lists = Functions.myFlatMap(numbers, integer ->
            List.of(integer, integer + 2, integer * 2));
        System.out.println(lists);
    }

    @Test
    void testMapUsingFunctionButWithLyrics() {
        List<String> lyrics = List.of(
            "I see trees of green",
            "Red roses too",
            "I see them bloom",
            "For me and you",
            "And I think to myself",
            "What a wonderful world");

        List<List<String>> list1 =
            Functions.myMap(lyrics, stanza -> Arrays.asList(stanza.split(" ")));

        List<String> list2 =
            Functions.myFlatMap(lyrics, stanza -> Arrays.asList(stanza.split(" ")));

        List<String> allLowerCase = Functions.myMap(list2, w -> w.toLowerCase());
        System.out.println(list1);
        System.out.println("-----");
        System.out.println(list2);
        System.out.println("-----");
        System.out.println(allLowerCase);
    }


    @Test
    void testConsumerWithForEach() {
        Functions.myForEach(teamList, System.out::println);
    }

    @Test
    void testMapWithAConsumerWithForEach() {
        Functions.myForEach(Functions.myMap(teamList, Team::getMascot), System.out::println);
    }

    @Test
    void testMapAndForEachUsingAStream() {
        teamList
            .stream()
            .map(Team::getMascot)
            .filter(w -> w.length() > 5)
            .forEach(System.out::println);
    }

    @Test
    void testReferenceWithTeam() {
        List.of(1, 2, 3, 4, 5, 6)
            .stream()
            .map(Object::toString)
            .forEach(System.out::println);

        teamList
            .stream()
            .map(Team::toString)
            .forEach(System.out::println);
    }

    @Test
    void testIterateThoughTeamsGetMascotStringAndPutItIntoCustomClass() {
        List<Mascot> result = teamList
            .stream()
            .map(Team::getMascot)
            .map(Mascot::new)
            .collect(Collectors.toList());
        System.out.println(result);

    }

    @Test
    public void testMyGenerate() {
        List<LocalDateTime> localDateTimes =
            Functions.myGenerate(() -> LocalDateTime.now(), 10);
        System.out.println(localDateTimes);
    }

    @Test
    public void testMyGenerateIncorrect() {
        List<LocalDateTime> localDateTimes =
            Functions.myGenerateIncorrect(LocalDateTime.now(), 10);
        System.out.println(localDateTimes);
    }

    @Test
    public void testLambdasWithRunnable() {
        Thread t = new Thread(() -> {
            String threadName =
                Thread.currentThread().getName();
            System.out.format("%s: %s%n",
                threadName,
                "Hello from another thread");
        });
        t.start();
    }

    public static Integer foo(Function<Integer, Integer> f) {
        return f.apply(5);
    }

    public void otherMethod() {
        final Integer x = 3;
        Function<Integer, Integer> add3 = z -> x + z;
        System.out.println(foo(add3));

    }

    @Test
    void testClosure() {
        otherMethod();
    }

    public MyPredicate<String> stringHasSizeOf(final int length) {
        return string -> string.length() == length;
    }

    @Test
    public void testClosuresAvoidRepeats() {
        List<String> names = Arrays.asList("Banana", "Ramen", "Naan", "Ravioli");
        System.out.println(Functions.myFilter(names, stringHasSizeOf(4)));
        System.out.println(Functions.myFilter(names, stringHasSizeOf(2)));
    }

    @Test
    void testStreamFromMap() {
        Map<String, String> countriesAndCapitals =
            Map.of("Denmark", "Copenhagen",
                "China", "Beijing",
                "Japan", "Tokyo",
                "Argentina", "Buenos Aires",
                "Zimbabwe", "Harare",
                "Mongolia", "Ulaan Batur");
        List<String> result =
            countriesAndCapitals
                .entrySet()
                .stream()
                .filter(e -> e.getValue().contains(" "))
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(result);
    }

    @Test
    void testSplitTimeZoneStringWithTwoElements() {
        String value = "America/Denver";
        String substring = value.substring(value.indexOf("/") + 1);
        String result = substring.replaceAll("/", "-");
        assertThat(result).isEqualTo("Denver");
    }

    @Test
    void testSplitTimeZoneStringWithThreeElements() {
        String value = "America/Indiana/Knox";
        String replace = TimeZoneUtility.extractTimeZone(value);
        assertThat(replace).isEqualTo("Indiana-Knox");
    }
    @Test
    void testGetAllTimeZonesInAmericaAndSortThem() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        List<String> result = availableZoneIds
            .stream()
            .filter(s -> s.startsWith("America"))
            .map(TimeZoneUtility::extractTimeZone)
            .map(s -> s.replaceAll("_", " "))
            .sorted().toList();
        System.out.println(result);
    }

    @Test
    void testGetAllTimeZonesInAmericaAndSortThemCreatingANiceList() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        String commaDelimited = availableZoneIds
            .stream()
            .filter(s -> s.startsWith("America"))
            .map(TimeZoneUtility::extractTimeZone)
            .map(s -> s.replaceAll("_", " "))
            .map("\"%s\""::formatted)
            .sorted()
            .collect(Collectors.joining(", ", " americanTimeZones: [ ", "]"));
        System.out.println(commaDelimited);
    }

}
