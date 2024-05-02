package com.eduardoaf.demo_loop.infrastructure.controllers;

import com.eduardoaf.shared.infrastructure.io.Echo;
import com.eduardoaf.demo_loop.application.services.LoopService;

public final class PrintLoopController {

    public void Invoke()
    {
        Echo.getInstance().info("PrintController");
        (new LoopService()).Invoke();
    }
}
