package from_api.application.services;


import from_api.domain.entities.ProductEntity;
import from_api.infrastructure.repositories.ProductsRepository;
import shared.infrastructure.io.Log;

public final class GetProductsService {


    public void invoke() throws Exception {
        try {
            var products = ProductsRepository.getInstance().getAllProducts();
            for (ProductEntity product : products) {
                Log.console(product.toString());
            }
        }
        catch (Exception e) {
            Log.console(e.getMessage());
        }
    }
}