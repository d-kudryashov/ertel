package erteltest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CakeConverter {

    private CakeConverter() {

    }

    public static CakeDto cakeToCakeDTO(Cake cake) {
        if (cake != null) {
            return new CakeDto(cake.getId(), cake.getName(), cake.getStatusType().getName());
        } else {
            return null;
        }
    }

    public static List<CakeDto> cakeToCakeDTO(List<Cake> cakes) {
        if (cakes != null) {
            return cakes.stream()
                    .map(CakeConverter::cakeToCakeDTO)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public static Cake cakeDTOToCake(CakeDto cakeDto) {
        if (cakeDto != null) {
            return new Cake(cakeDto.getName(), StatusType.valueOf(cakeDto.getStatus()));
        } else {
            return null;
        }
    }

}
