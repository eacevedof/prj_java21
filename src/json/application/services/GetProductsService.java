package json.application.services;

import shared.infrastructure.io.Log;
import shared.infrastructure.file.FileGetContent;

public final class GetProductsService {
    // Readonly property (not directly available in PHP, but could be achieved through getters only)
    private final String readonlyProperty = "https://json.theframework.es/index.php?getfile=app_product.json";

    public void Invoke() {
        String json = FileGetContent.getInstance().GetContentFromUrlOrFail(this.readonlyProperty);
        Log.InfoNl(json);
    }
}