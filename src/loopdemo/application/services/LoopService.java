package loopdemo.application.services;

import shared.infrastructure.io.Log;

public final class LoopService {
    // Readonly property (not directly available in PHP, but could be achieved through getters only)
    //private final String readonlyProperty;
    public void Invoke() {
        Log.console("LoopService");
        for (int i = 1; i <= 5; i++) {
            Log.console("i = " + i);
        }
    }
}