package from_api.application.dtos;
import java.util.HashMap;
import java.util.Map;

public record GetProductDto(
     int id,
     int codeErp,
     String description,
     int idProductBrand,
     String name
) {
    public static GetProductDto fromPrimitives(Map<String, Object> primitives) {
        return new GetProductDto(
            (int) primitives.getOrDefault("id", null),
            (int) primitives.getOrDefault("codeErp", ""),
            (String) primitives.getOrDefault("description", ""),
            (int) primitives.getOrDefault("idProductBrand", null),
            (String) primitives.getOrDefault("name", "")
        );
    }
}
