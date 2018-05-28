package erteltest;

import java.util.List;
import java.util.stream.Collectors;

public class CakeConverter {

    private CakeConverter() {

    }

    public static CakeDto cakeToCakeDTO(Cake cake) {
        return new CakeDto(cake.getId(), cake.getName(), cake.getStatusType().getName());
    }

    public static List<CakeDto> cakeToCakeDTO(List<Cake> cakes) {
        return cakes.stream().map(CakeConverter::cakeToCakeDTO).collect(Collectors.toList());
    }

}
