package demo_loop.infrastructure.controllers;

import shared.infrastructure.io.Echo;
import demo_loop.application.services.LoopService;

public final class PrintLoopController {

    public void Invoke()
    {
        Echo.console("PrintController");
        (new LoopService()).Invoke();
    }
}
