package of_for_collections;

import java.util.*;

public class CollectionsExample {

    public void CollectionsExample () {

        Set<String> namesSet = new HashSet<>();
        namesSet.add("Mehdi");
        namesSet.add("Mohammad");

        HashMap<String, String> countriesMap = new HashMap<>();
        countriesMap.put("IRAN", "Tehran");
        countriesMap.put("IRAN", "Tehran");

        ArrayList<String> namesList = new ArrayList<>();
        namesList.add("Zahra");
        namesList.add("Fatemeh");

        List<String> namesList2 = Arrays.asList("Taghi", "Naghi");


        List<String> names = List.of("Mehdi", "Mohammad");
        Set<String> newSet = Set.of("Mehdi", "Mohammad");
        Map<String, String> countries = Map.of("IRAN", "Tehran", "IRAN", "Tehran");
        Map<String, String> countries2 = Map.ofEntries(Map.entry("IRAN", "Tehran"), Map.entry("IRAN", "Tehran"));



    }
}
