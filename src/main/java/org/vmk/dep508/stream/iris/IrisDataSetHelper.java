package org.vmk.dep508.stream.iris;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class IrisDataSetHelper {

    private List<Iris> dataSet;

    public IrisDataSetHelper(List<Iris> dataSet) {
        this.dataSet = dataSet;
    }

    public Double getAverage(ToDoubleFunction<Iris> func) {
        return dataSet.stream().mapToDouble(func).average().orElse(0);
    }

    public List<Iris> filter(Predicate<Iris> predicate) {
        return dataSet.stream().filter(predicate).collect(Collectors.toList());
    }

    public Double getAverageWithFilter(Predicate<Iris> filter, ToDoubleFunction<Iris> mapFunction) {
        return dataSet.stream().filter(filter).mapToDouble(mapFunction).average().orElse(0);
    }

    public Map groupBy(Function groupFunction) {
        return (Map) dataSet.stream().collect(Collectors.groupingBy(groupFunction));
    }

    public Object maxFromGroupedBy(Function groupFunction, ToDoubleFunction<Iris> obtainMaximisationValueFunction) {
        return dataSet.stream().collect(Collectors.groupingBy(groupFunction, Collectors.maxBy((Comparator.comparingDouble(obtainMaximisationValueFunction)))));
    }
}
