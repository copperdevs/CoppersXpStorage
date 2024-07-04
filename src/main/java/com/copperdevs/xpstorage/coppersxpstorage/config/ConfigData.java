package com.copperdevs.xpstorage.coppersxpstorage.config;

public class ConfigData {
    public boolean enabled = true;
    public float bottlingConsumption = 0.85f;
    public BottleUsage usage = BottleUsage.SneakRightClick;

    public enum BottleUsage {
        RightClick, SneakRightClick
    }
}
