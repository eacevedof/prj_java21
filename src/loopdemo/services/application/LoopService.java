package loopdemo.services.application;

import shared.infrastructure.io.Log;

final class LoopService {
    // Readonly property (not directly available in PHP, but could be achieved through getters only)
    //private final String readonlyProperty;
    public void Invoke() {
        Log.Info("start loop service");
    }
}