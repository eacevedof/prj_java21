package loopdemo.infrastructure.controllers;

import shared.infrastructure.io.Log;
import loopdemo.application.services.LoopService;

public final class PrintLoopController {

    public void Invoke()
    {
        Log.console("PrintController");
        (new LoopService()).Invoke();
    }
}
