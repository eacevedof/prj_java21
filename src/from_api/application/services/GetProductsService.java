package from_api.application.services;

import from_api.application.dtos.GetProductDto;
import from_api.domain.entities.ProductEntity;
import from_api.domain.repositories.InterfaceProductsRepository;
import from_api.infrastructure.repositories.ProductsRepository;
import shared.infrastructure.io.Echo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public final class GetProductsService {

    public List<GetProductDto> invoke() throws Exception {
        List<GetProductDto> products = new ArrayList<>();
        try {
            InterfaceProductsRepository productsRepository = ProductsRepository.getInstance();
            var productsEntities = productsRepository.getAllProducts();

            for (ProductEntity productEntity : productsEntities) {
                Map<String, Object> primitives = new HashMap<>() {{
                    put("id", productEntity.id);
                    put("codeErp", productEntity.codeErp);
                    put("description", productEntity.description);
                    put("idProductBrand", productEntity.idProductBrand);
                    put("name", productEntity.name);
                }};
                var productDto = GetProductDto.fromPrimitives(primitives);
                products.add(productDto);
                //Log.console(productEntity.toString());
            }
            return products;
        }
        catch (Exception e) {
            Echo.info(e.getMessage());
            return products;
        }
    }
}