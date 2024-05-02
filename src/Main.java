import shared.infrastructure.io.Echo;

import from_api.infrastructure.controllers.GetProductsController;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Echo.info("** Main.main ***");
        //(new PrintLoopController()).Invoke();
        (new GetProductsController()).invoke();
    }
}