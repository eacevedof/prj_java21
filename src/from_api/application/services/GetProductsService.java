package from_api.application.services;

import from_api.domain.entities.ProductEntity;
import from_api.domain.repositories.InterfaceProductsRepository;
import from_api.infrastructure.repositories.ProductsRepository;
import shared.infrastructure.io.Log;
import java.util.ArrayList;
import java.util.List;

public final class GetProductsService {

    public void invoke() throws Exception {
        List<String> products = new ArrayList<>();
        try {
            InterfaceProductsRepository productsRepository = ProductsRepository.getInstance();
            var productsEntities = productsRepository.getAllProducts();
            for (ProductEntity productEntity : productsEntities) {

                Log.console(productEntity.toString());
            }
        }
        catch (Exception e) {
            Log.console(e.getMessage());
        }
    }
}