package from_api.application.dtos;

public record GetProductDto(
     int id,
     int codeErp,
     String description,
     int idProductBrand,
     String name
) {
}
