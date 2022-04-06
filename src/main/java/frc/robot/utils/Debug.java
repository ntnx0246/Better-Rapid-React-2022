package frc.robot.utils;

import java.util.EnumMap;

public class Debug {
    public static EnumMap<Constants.Subsystems, Boolean> values = new EnumMap<>(Constants.Subsystems.class);

    public static void initialize(){
        for (Constants.Subsystems sub : Constants.Subsystems.values()) {
            values.put(sub, false);
        }
    }
}