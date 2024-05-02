package shared.infrastructure.system;

import java.util.Map;
import java.lang.*;

public final class InfraSystem {

    private static InfraSystem infraSystem = null;

    private InfraSystem () {}

    public static InfraSystem getInstance() {
        if (infraSystem == null) {
            return infraSystem = new InfraSystem();
        }
        return infraSystem;
    }

    public InfraSystem die() {
        System.exit(0);
        return infraSystem;
    }

    public InfraSystem printEnvs() {
        Map<String, String> envVariables = java.lang.System.getenv();

        // Iterate over the map and print each environment variable
        for (Map.Entry<String, String> entry : envVariables.entrySet()) {
            String variableName = entry.getKey();
            String variableValue = entry.getValue();
            System.out.println(variableName + " = " + variableValue);
        }
        return infraSystem;
    }

    public String getEnv(String envKey) {
        return System.getenv(envKey);
    }

    public String getUserDir() {
        return System.getProperty("user.dir");
    }
}
