package com.copperdevs.xpstorage.coppersxpstorage;

import com.copperdevs.xpstorage.coppersxpstorage.config.ConfigData;
import com.copperdevs.xpstorage.coppersxpstorage.config.ConfigManager;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.minecraft.server.command.CommandManager.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoppersXpStorage implements ModInitializer {

    public static final String MOD_ID = "coppersxpstorage";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ConfigData CONFIG = new ConfigData();

    @Override
    public void onInitialize() {
        if (ConfigManager.ConfigFileExists()) CONFIG = ConfigManager.Load();
        else ConfigManager.Save(CONFIG);

        LoadCommand();
    }

    private void LoadCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("copperdevs").executes(context -> {

            context.getSource().sendFeedback(() -> Text.of("https://www.github.com/copperdevs/"), true);

            return 1;
        })));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("copperdevs").then(literal("coppersxpstorage").executes(context -> {

            context.getSource().sendFeedback(() -> Text.literal("coppersxpstorage"), true);

            return 1;
        }))));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("copperdevs").then(literal("coppersxpstorage").then(literal("config").executes(context -> {

            context.getSource().sendFeedback(() -> Text.literal("Called /foo with no arguments"), true);

            return 1;
        })))));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("copperdevs").then(literal("coppersxpstorage").then(literal("config").then(literal("reload").executes(context -> {

            context.getSource().sendFeedback(() -> Text.literal("Called foo with bar"), true);

            return 1;
        }))))));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("copperdevs").then(literal("coppersxpstorage").then(literal("config").then(literal("enabled").then(argument("value", BoolArgumentType.bool()).executes(context -> {

            context.getSource().sendFeedback(() -> Text.literal("Called foo with bar"), true);

            return 1;
        })))))));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("copperdevs").then(literal("coppersxpstorage").then(literal("config").then(literal("bottlingConsumption").then(argument("value", FloatArgumentType.floatArg()).executes(context -> {

            context.getSource().sendFeedback(() -> Text.literal("Called foo with bar"), true);

            return 1;
        })))))));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("copperdevs").then(literal("coppersxpstorage").then(literal("config").then(literal("sneakingrequired").then(argument("value", BoolArgumentType.bool()).executes(context -> {

            context.getSource().sendFeedback(() -> Text.literal("Called foo with bar"), true);

            return 1;
        })))))));
    }

    public static Identifier id(String id) {
        return Identifier.of(MOD_ID);
    }
}