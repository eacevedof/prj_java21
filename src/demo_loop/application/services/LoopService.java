package demo_loop.application.services;

import shared.infrastructure.io.Echo;

public final class LoopService {
    // Readonly property (not directly available in PHP, but could be achieved through getters only)
    //private final String readonlyProperty;
    public void Invoke() {
        Echo.info("LoopService");
        for (int i = 1; i <= 5; i++) {
            Echo.info("i = " + i);
        }
    }
}