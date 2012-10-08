package oreo.gatestones.impl;

import oreo.gatestones.Main;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * User: OreoCream
 * Date: 10/7/12
 * Time: 5:48 PM
 */

public class LeaveDungeon extends Node
{

    @Override
    public boolean activate() {
        return Inventory.getCount(Main.COSMIC_RUNE) == 0 && NPCs.getNearest(11226).isOnScreen();
    }

    @Override
    public void execute()
    {
        System.out.println("oreo.gatestones.impl.LeaveDungeon: Out of cosmic runes! Leaving dungeon!");
        if(Widgets.get(1186, 10).validate())
        {
            Widgets.get(1186, 10).click(true);
        } else if(Widgets.get(1188, 11).validate())
        {
            Widgets.get(1188, 11).click(true);
        }
        else
        {
            SceneObject DungeonExit = SceneEntities.getNearest(25636);
            if(DungeonExit.isOnScreen())
            {
                DungeonExit.interact("Climb-up");
            }
            else
            {
                Camera.turnTo(DungeonExit);
                if (!DungeonExit.isOnScreen())
                {
                    Walking.walk(DungeonExit);
                }
            }
        }
    }
}
