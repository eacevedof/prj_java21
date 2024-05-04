package com.eduardoaf.balance.demo_loop.infrastructure.controllers;

import com.eduardoaf.balance.shared.infrastructure.io.Echo;
import com.eduardoaf.balance.demo_loop.application.services.LoopService;

public final class PrintLoopController {

    public void Invoke()
    {
        Echo.getInstance().info("PrintController");
        (new LoopService()).invoke();
    }
}
