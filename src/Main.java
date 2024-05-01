import shared.infrastructure.io.Log;

import loopdemo.infrastructure.controllers.PrintLoopController;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Log.InfoNl("** Main.main ***");
        (new PrintLoopController()).Invoke();
    }
}