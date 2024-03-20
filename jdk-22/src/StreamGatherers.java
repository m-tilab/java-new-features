import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class StreamGatherers {
    public static void main(String[] args) {

        findGroupsOfThree(10, 3);

        findGroupsOfThreeWithGatherer(10, 3);
    }

    public static ArrayList<ArrayList<Integer>> findGroupsOfThree(long fixed_size, int grouping) {

        return Stream.iterate(0, i -> i + 1)
                .limit(fixed_size * grouping)
                .collect(Collector.of(
                        () -> new ArrayList<>(),
                        (groups, element) -> {
                            if (groups.isEmpty() || groups.getLast().size() == fixed_size) {
                                var current = new ArrayList<Integer>();
                                current.add(element);
                                groups.addLast(current);
                            } else {
                                groups.getLast().add(element);
                            }
                        },
                        (left, right) -> {
                            throw new UnsupportedOperationException("Cannot be parallelized");
                        }
                ));
    }

    public static List<List<Integer>> findGroupsOfThreeWithGatherer(long fixed_size, int grouping) {

        return Stream.iterate(0, i -> i + 1)
                .gather(Gatherers.windowFixed((int) fixed_size))
                .limit(grouping)
                .collect(Collectors.toList());
    }
}