package net.runelite.client.plugins.easyscape;


import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("easyscape")
public interface EasyscapePluginConfig extends Config {

    @ConfigItem(
            keyName = "swapShop",
            name = "Shop",
            description = "Enables swapping of items in the shop with their buy-50 option."
    )
    default boolean getSwapShop() {
        return true;
    }

    @ConfigItem(
            keyName = "swappedItems",
            name = "Shop Items",
            description = "Items listed here will have their value and buy-50 options swapped."
    )
    default String getSwappedItems() {
        return "";
    }

    @ConfigItem(
            keyName = "easyConstruction",
            name = "Swap Build",
            description = ""
    )

    default boolean getEasyConstruction() {
        return true;
    }

    @ConfigItem(
            keyName = "constructionItems",
            name = "Build Items",
            description = ""
    )

    default String getConstructionItems() {
        return "";
    }

    @ConfigItem(
            keyName = "removeMonster",
            name = "Remove Mobs",
            description = ""
    )
    default boolean getRemoveMonster() {
        return true;
    }

    @ConfigItem(
            keyName = "removedMonsters",
            name = "Removed Mobs",
            description = ""
    )
    default String getRemovedMonsters() {
        return "";
    }

    @ConfigItem(
            keyName = "swapSmithing",
            name = "Swap Smithing",
            description = "Enables swapping of smith-1 and smith-all options."
    )
    default boolean getSwapSmithing() {
        return true;
    }

    @ConfigItem(
            keyName = "swapTanning",
            name = "Swap Tanning",
            description = "Enables swapping of tan-1 and tan-all options."
    )
    default boolean getSwapTanning() {
        return true;
    }

    @ConfigItem(
            keyName = "swapArdougneCape",
            name = "Swap Ardougne Cape",
            description = "Enables swapping of teleport and wear."
    )
    default boolean getSwapArdougneCape() {
        return true;
    }

    @ConfigItem(
            keyName = "swapStairs",
            name = "Swap Stairs",
            description = ""
    )

    default boolean getSwapStairs() {
        return true;
    }

    @ConfigItem(
            keyName = "swapSawmill",
            name = "Swap Sawmill Operator",
            description = ""
    )
    default boolean getSwapSawmill() {
        return true;
    }

    @ConfigItem(
            keyName = "swapSawmillPlanks",
            name = "Swap Buy Planks",
            description = ""
    )

    default boolean getSwapSawmillPlanks() {
        return true;
    }

    @ConfigItem(
            keyName = "swapPuroPuro",
            name = "Swap Puro Puro Wheat",
            description = ""
    )
    default boolean getSwapPuro() {
        return true;
    }

    @ConfigItem(
            keyName = "swapEssencePounch",
            name = "Swap Essence Pouch",
            description = "Enables swapping of fill and empty for essence pounch."
    )
    default boolean getSwapEssencePouch() {
        return true;
    }

    @ConfigItem(
            keyName = "essenceMode",
            name = "Essence Pouch Mode",
            description = "Runecrafting or essence mining mode."
    )

    default EssenceMode getEssenceMode() {
        return EssenceMode.RUNECRAFTING;
    }

    @ConfigItem(
            keyName = "swapGamesNecklace",
            name = "Swap Games Necklace",
            description = "Enables swapping of games necklace."
    )
    default boolean getGamesNecklace() {
        return true;
    }

    @ConfigItem(
            keyName = "gamesNecklaceMode",
            name = "Games Necklace Mode",
            description = "Teleport location mode."
    )

    default GamesNecklaceMode getGamesNecklaceMode() {
        return GamesNecklaceMode.BURTHORPE;
    }

    @ConfigItem(
            keyName = "swapDuelingRing",
            name = "Swap Dueling Ring",
            description = "Enables swapping of dueling ring."
    )
    default boolean getDuelingRing() {
        return true;
    }

    @ConfigItem(
            keyName = "duelingRingMode",
            name = "Dueling Ring Mode",
            description = "Teleport location mode."
    )

    default DuelingRingMode getDuelingRingMode() {
        return DuelingRingMode.DUEL_ARENA;
    }
}
