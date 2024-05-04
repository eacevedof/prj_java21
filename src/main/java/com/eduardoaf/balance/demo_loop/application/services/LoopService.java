package com.eduardoaf.balance.demo_loop.application.services;

import com.eduardoaf.balance.shared.infrastructure.io.Echo;

public final class LoopService {
    // Readonly property (not directly available in PHP, but could be achieved through getters only)
    //private final String readonlyProperty;
    public void Invoke() {
        Echo.getInstance().info("LoopService");
        for (int i = 1; i <= 5; i++) {
            Echo.getInstance().info("i = " + i);
        }
    }
}