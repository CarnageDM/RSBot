package oreo.gatestones.impl;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * User: OreoCream
 * Date: 10/7/12
 * Time: 5:27 PM\
 */

public class EnterDungeon extends Node
{
    Area OUTSIDE_LOC = new Area(new Tile[] {
            new Tile(3439, 3737, 0), new Tile(3458, 3737, 0), new Tile(3458, 3725, 0),
            new Tile(3453, 3725, 0), new Tile(3453, 3716, 0), new Tile(3444, 3716, 0),
            new Tile(3444, 3725, 0), new Tile(3439, 3725, 0)
    });

    Area NULL_LOC = new Area(new Tile[] {
            new Tile(3465, 3724, 0), new Tile(3454, 3716, 0)
    });

    @Override
    public boolean activate()
    {
        return OUTSIDE_LOC.contains(Players.getLocal().getLocation()) || NULL_LOC.contains(Players.getLocal().getLocation());
    }

    @Override
    public void execute()
    {
        if(OUTSIDE_LOC.contains(Players.getLocal().getLocation()))
        {
            System.out.println("oreo.gatestones.impl.EnterDungeon: Entering Dungeon.");
            if(Widgets.get(1186, 10).validate())
            {
                Widgets.get(1186, 10).click(true);
            }
            else if(Widgets.get(1188, 11).validate())
            {
                Widgets.get(1188, 11).click(true);
            }
            else if(Widgets.get(947, 766).validate())
            {
                Widgets.get(947, 766).click(true);
            }
            else if (Widgets.get(938, 39).validate())
            {
                Widgets.get(938, 39).click(true);
            }
            else
            {
                SceneObject DungeonEntrance = SceneEntities.getNearest(48495);
                if(DungeonEntrance.isOnScreen())
                {
                    DungeonEntrance.interact("Climb-down");
                }
                else
                {
                   Camera.turnTo(DungeonEntrance);
                   if (!DungeonEntrance.isOnScreen())
                   {
                        Walking.walk(DungeonEntrance);
                    }
                }
            }
        }

        if(NULL_LOC.contains(Players.getLocal().getLocation()))
        {
            System.out.println("oreo.gatestones.impl.EnterDungeon: Leaving null location.");
            SceneObject BrokenStairs = SceneEntities.getNearest(50552);
            if(BrokenStairs.isOnScreen())
            {
                BrokenStairs.interact("Jump-down");
            }
            else
            {
                Camera.turnTo(BrokenStairs);
                if (!BrokenStairs.isOnScreen())
                {
                    Walking.walk(BrokenStairs);
                }
            }
        }
    }
}
