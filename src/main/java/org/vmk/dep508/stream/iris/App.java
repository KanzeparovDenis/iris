package org.vmk.dep508.stream.iris;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {
        App a = new App();
        a.test();
    }

    public void test() throws IOException {

        List<Iris> irisList = Files.lines(Paths.get("iris.data")).map(Iris::parse).collect(Collectors.toList()); //load data from file iris.data
        IrisDataSetHelper helper = new IrisDataSetHelper(irisList);

        //get average sepal width
        Double avgSetalLength = helper.getAverage(Iris::getSepalLength);
        System.out.println(avgSetalLength);

        //получаем среднюю площадь лепестка - ширину лепестка, умноженную на длину лепестка
        Double avgPetalLength =  helper.getAverage(x -> x.getPetalLength() * x.getPetalWidth());;
        System.out.println(avgPetalLength);

        //получаем среднюю площадь лепестка для цветов с шириной чашелистика > 4
        Double avgPetalSquare = helper.getAverageWithFilter(x -> x.getSepalLength() > 4,
                x -> x.getPetalLength() * x.getPetalWidth());;
        System.out.println(avgPetalSquare);

        //получить цветы, сгруппированные по размеру лепестка (Лепесток.МАЛЕНЬКИЙ и т.д.)
        Map groupsByPetalSize = helper.groupBy(x -> Iris.classifyByPatel((Iris) x));;
        System.out.println(groupsByPetalSize);

        //получить максимальную ширину чашелистика для цветов, сгруппированных по видам
        Map maxSepalWidthForGroupsBySpecies = (Map) helper.maxFromGroupedBy(x -> Iris.getSpecies(), Iris::getSepalWidth);
        System.out.println(maxSepalWidthForGroupsBySpecies);
    }

}

