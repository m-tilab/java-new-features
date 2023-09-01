package java18_to_21.SequencedCollection;

import java.util.ArrayList;

public class SequencedCollectionSample {

    public static void main(String[] args) {

        // Traditional - first and last element
        var arrayList = new ArrayList<Integer>();

        arrayList.add(1);                           // [1]

//        arrayList.get(arrayList.iterator().next()); // first element
//        arrayList.get(arrayList.size() - 1);        // last element

        // SequencedCollection interface

        arrayList = new ArrayList<>();

        arrayList.add(1);                   // [1]

        arrayList.addFirst(0);      // [0, 1]
        arrayList.addLast(2);       // [0, 1, 2]

        arrayList.getFirst();               // 0
        arrayList.getLast();                // 2

        arrayList.forEach(System.out::println);  // [0, 1, 2]
    }

}

