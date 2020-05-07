package br.com.codenation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticUtil {

    public static int average(int[] elements) {
        return (int) Arrays.stream(elements).average().getAsDouble();
    }


    public static int mode(int[] elements) {
        return Arrays.stream(elements).boxed().collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        ).entrySet().stream().max(Comparator.comparingLong(item -> item.getValue())).get().getKey();
    }

    public static int median(int[] elements) {
        List<Integer> elementsList = Arrays.stream(elements).boxed().collect(Collectors.toList());
        elementsList.sort(Comparator.naturalOrder());
        final int index = elementsList.size() / 2;
        final int lesserIndex = index - 1;
        final int greaterIndex = index + 1;
        if (elementsList.size() % 2 == 0) {
            return (int) elementsList.subList(lesserIndex, greaterIndex).stream()
                    .mapToInt(Integer::intValue).average().getAsDouble();
        }
        return elementsList.get(index);
    }
}