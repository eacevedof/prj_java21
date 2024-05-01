package demo_loop.infrastructure.controllers;

import shared.infrastructure.io.Log;
import demo_loop.application.services.LoopService;

public final class PrintLoopController {

    public void Invoke()
    {
        Log.console("PrintController");
        (new LoopService()).Invoke();
    }
}
