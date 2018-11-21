package net.runelite.client.plugins.easyscape;


import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("easyscape")
public interface EasyScapePluginConfig extends Config {

    @ConfigItem(
            keyName = "swapShop",
            name = "Shop",
            description = "Enables swapping of items in the shop with their buy-50 option.",
            position = 0
    )
    default boolean getSwapShop() {
        return true;
    }

    @ConfigItem(
            keyName = "swappedItems",
            name = "Shop Items",
            description = "Items listed here will have their value and buy-50 options swapped.",
            position = 1
    )
    default String getSwappedItems() {
        return "";
    }

    @ConfigItem(
            keyName = "swapSmithing",
            name = "Smithing",
            description = "Enables swapping of smith-1 and smith-all options.",
            position = 2
    )
    default boolean getSwapSmithing() {
        return true;
    }

    @ConfigItem(
            keyName = "swapTanning",
            name = "Tanning",
            description = "Enables swapping of tan-1 and tan-all options.",
            position = 3
    )
    default boolean getSwapTanning() {
        return true;
    }

    @ConfigItem(
            keyName = "swapArdougneCape",
            name = "Ardougne Cape",
            description = "Enables swapping of teleport and wear.",
            position = 4
    )
    default boolean getSwapArdougneCape() {
        return true;
    }

    @ConfigItem(
            keyName = "swapEssencePounch",
            name = "Essence Pouch",
            description = "Enables swapping of fill and empty for essence pounch.",
            position = 5
    )
    default boolean getSwapEssencePouch() {
        return true;
    }

    @ConfigItem(
            keyName = "essenceMode",
            name = "Essence Pouch Mode",
            description = "Runecrafting or essence mining mode.",
            position = 6
    )

    default EssenceMode getEssenceMode() {
        return EssenceMode.RUNECRAFTING;
    }

    @ConfigItem(
            keyName = "swapGamesNecklace",
            name = "Games Necklace",
            description = "Enables swapping of games necklace.",
            position = 7
    )
    default boolean getGamesNecklace() {
        return true;
    }

    @ConfigItem(
            keyName = "gamesNecklaceMode",
            name = "Games Necklace Mode",
            description = "Teleport location mode.",
            position = 8
    )

    default GamesNecklaceMode getGamesNecklaceMode() {
        return GamesNecklaceMode.BURTHORPE;
    }

    @ConfigItem(
            keyName = "swapDuelingRing",
            name = "Dueling Ring",
            description = "Enables swapping of dueling ring.",
            position = 9
    )
    default boolean getDuelingRing() {
        return true;
    }

    @ConfigItem(
            keyName = "duelingRingMode",
            name = "Dueling Ring Mode",
            description = "Teleport location mode.",
            position = 10
    )

    default DuelingRingMode getDuelingRingMode() {
        return DuelingRingMode.DUEL_ARENA;
    }
}
