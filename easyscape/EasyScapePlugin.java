package net.runelite.client.plugins.easyscape;


import com.google.common.eventbus.Subscribe;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;
import org.apache.commons.lang3.ArrayUtils;

import javax.inject.Inject;

import static net.runelite.api.MenuAction.MENU_ACTION_DEPRIORITIZE_OFFSET;
import static net.runelite.api.MenuAction.WALK;


@PluginDescriptor(
        name = "Easyscape",
        description = "Easyscape.",
        tags = {"Easyscape"},
        enabledByDefault = false
)

@Slf4j
public class EasyScapePlugin extends Plugin {

    private static final int PURO_PURO_REGION_ID = 10307;

    @Inject
    private Client client;

    @Inject
    private EasyScapePluginConfig config;

    @Provides
    EasyScapePluginConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(EasyScapePluginConfig.class);
    }

    @Override
    public void startUp() {
        log.debug("Easyscape Started.");
    }

    @Override
    public void shutDown() {
        log.debug("Easyscape Stopped.");
    }

    @Subscribe
    public void onMenuEntryAdded(MenuEntryAdded event) {

        final String option = Text.removeTags(event.getOption()).toLowerCase();
        final String target = Text.removeTags(event.getTarget()).toLowerCase();

        log.debug("Target {} : Option {}", target, option);

        if (config.getSwapShop()) {
            for (String swapped : config.getSwappedItems().split(",")) {
                swapped = swapped.trim();
                if (target.equalsIgnoreCase(swapped)) {
                    swap("Buy 50", "Value", target);
                }
            }
        }

        if (config.getSwapPuro() && isPuroPuro()) {
            if (event.getType() == WALK.getId()) {
                MenuEntry[] menuEntries = client.getMenuEntries();
                MenuEntry menuEntry = menuEntries[menuEntries.length - 1];
                menuEntry.setType(MenuAction.WALK.getId() + MENU_ACTION_DEPRIORITIZE_OFFSET);
                client.setMenuEntries(menuEntries);
            }
            else if (option.equals("examine")) {
                swap("push-through", option, target);
            }
            else if (option.equals("use")) {
                swap("escape", option, target);
            }
        }

        if (config.getRemoveMonster()) {
            for (String removed : config.getRemovedMonsters().split(",")) {
                removed = removed.trim();
                for(String found : target.split(" ")) {
                    if (found.equalsIgnoreCase(removed) && target.substring(0, removed.length()).equalsIgnoreCase(removed)) {
                        delete(target);
                        break;
                    }
                }
            }
        }

        if (config.getSwapSmithing()) {
            if (option.equalsIgnoreCase("Smith-1")) {
                swap("Smith-All", "Smith-1", target);
            } else {
                swap("Smith-All-Sets", "Smith-1-Set", target);
            }
        }

        if (config.getSwapTanning()) {
            swap("Tan 1", "Tan All", target);
        }

        if (config.getSwapSawmill()) {
            swap("Trade", "Buy-plank", target);
        }

        if (config.getSwapPlanks()) {
            swap("Buy 1", "Buy All", target);
        }

        if (config.getSwapStairs()) {
            swap("Climb Up Stairs", "Climb Stairs", target);
        }

        if (option.equalsIgnoreCase("Clear-All") && target.equalsIgnoreCase("Bank Filler")) {
            swap("Clear", "Clear-All", target);
        }

        if (target.toLowerCase().contains("ardougne cloak") && config.getSwapArdougneCape()) {
            swap("Kandarin Monastery", option, target);
            swap("Monastery Teleport", option, target);
        }

        if (config.getSwapEssencePouch()) {
            if (isEssencePouch(target)) {
                Widget widgetBankTitleBar = client.getWidget(WidgetInfo.BANK_TITLE_BAR);
                switch (config.getEssenceMode()) {
                    case RUNECRAFTING:
                        if (widgetBankTitleBar == null || widgetBankTitleBar.isHidden()) {
                            swap("Empty", option, target);
                        } else {
                            swap("Fill", option, target);
                        }
                        break;
                    case ESSENCE_MINING:
                        if (widgetBankTitleBar == null || widgetBankTitleBar.isHidden()) {
                            swap("Fill", option, target);
                        } else {
                            swap("Empty", option, target);
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        if (config.getGamesNecklace()) {
            if (target.toLowerCase().contains("games necklace")) {
                switch (config.getGamesNecklaceMode()) {
                    case BURTHORPE:
                        swap("Burthorpe", option, target);
                        break;
                    case BARBARIAN_OUTPOST:
                        swap("Barbarian Outpost", option, target);
                        break;
                    case CORPOREAL_BEAST:
                        swap("Corporeal Beast", option, target);
                        break;
                    case TEARS_OF_GUTHIX:
                        swap("Tears of Guthix", option, target);
                        break;
                    case WINTERTODT:
                        swap("Wintertodt Camp", option, target);
                        break;
                    default:
                        break;
                }
            }
        }

        if (config.getDuelingRing()) {
            if (target.toLowerCase().contains("dueling ring")) {
                switch (config.getDuelingRingMode()) {
                    case DUEL_ARENA:
                        swap("Duel Arena", option, target);
                        break;
                    case CASTLE_WARS:
                        swap("Castle Wars", option, target);
                        break;
                    case CLAN_WARS:
                        swap("Clan Wars", option, target);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private int searchIndex(MenuEntry[] entries, String option, String target) {
        for (int i = entries.length - 1; i >= 0; i--) {
            MenuEntry entry = entries[i];
            String entryOption = Text.removeTags(entry.getOption()).toLowerCase();
            String entryTarget = Text.removeTags(entry.getTarget()).toLowerCase();

            if (entryOption.equalsIgnoreCase(option) && entryTarget.equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

    private void swap(String optionA, String optionB, String target) {
        MenuEntry[] entries = client.getMenuEntries();

        int idxA = searchIndex(entries, optionA, target);
        int idxB = searchIndex(entries, optionB, target);

        if (idxA >= 0 && idxB >= 0) {
            MenuEntry entry = entries[idxA];
            entries[idxA] = entries[idxB];
            entries[idxB] = entry;
            client.setMenuEntries(entries);
        }
    }

    private void delete(String target) {
        MenuEntry[] entries = client.getMenuEntries();

        for (int i = entries.length - 1; i >= 0; i--) {
            final String tar = Text.removeTags(entries[i].getTarget()).toLowerCase();
            if (tar.equalsIgnoreCase(target)) {
                entries = ArrayUtils.remove(entries, i);
            }
        }
        client.setMenuEntries(entries);
    }

    private boolean isEssencePouch(String target) {
        return (target.equalsIgnoreCase("Small Pouch") || target.equalsIgnoreCase("Medium Pouch") || target.equalsIgnoreCase("Large Pouch") || target.equalsIgnoreCase("Giant Pouch"));
    }

    private boolean isPuroPuro() {
        Player player = client.getLocalPlayer();

        if (player == null) {
            return false;
        } else {
            WorldPoint location = player.getWorldLocation();
            return location.getRegionID() == PURO_PURO_REGION_ID;
        }
    }

    // Locate a safespot based on a certain monster.
    private WorldPoint findSafespot(NPC mob) {
        Player player = client.getLocalPlayer();

        if (player == null) {
            return null;
        } else {
            WorldArea playerArea = player.getWorldArea();
            WorldArea mobArea = mob.getWorldArea();

            Tile[][][] map = client.getScene().getTiles();

            boolean inMelee = mobArea.isInMeleeDistance(playerArea);
            boolean inRange = playerArea.hasLineOfSightTo(client, playerArea);
            boolean isSafe = !inMelee && !inRange;

            if (isSafe) {
                return player.getWorldLocation();
            } else {
                return null;
            }
        }
    }

}