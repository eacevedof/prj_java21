package loopdemo.services.application;

import shared.infrastructure.io.Log;

public final class LoopService {
    // Readonly property (not directly available in PHP, but could be achieved through getters only)
    //private final String readonlyProperty;
    public void Invoke() {
        Log.InfoNl("LoopService");
        for (int i = 1; i <= 5; i++) {
            Log.InfoNl("i = " + i);
        }
    }
}