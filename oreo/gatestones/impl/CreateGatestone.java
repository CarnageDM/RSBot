package oreo.gatestones.impl;


import oreo.gatestones.Main;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

/**
 * User: OreoCream
 * Date: 10/7/12
 * Time: 7:49 PM
 */

public class CreateGatestone extends Node
{
    @Override
    public boolean activate()
    {
        return Inventory.getCount(Main.COSMIC_RUNE) >= 1 && NPCs.getNearest(11226).isOnScreen();
    }

    @Override
    public void execute()
    {
        System.out.println("oreo.gatestones.imp.CreateGatestone: Creating Gatestones.");
        if(Inventory.getCount(17489) == 1)
        {
            if(Tabs.INVENTORY.isOpen())
            {
                for(Item i : Inventory.getItems())
                {
                    if (i.getId() == 17489)
                    {
                        Mouse.hop(i.getWidgetChild().getCentralPoint().x, i.getWidgetChild().getCentralPoint().y);
                        i.getWidgetChild().interact("drop");
                    }
                    else
                    {
                    Tabs.INVENTORY.open();
                    }
                }
            }
        }
        else if (Inventory.getCount(17489) == 0);
        {
            if(Tabs.MAGIC.isOpen())  {
                if(Widgets.get(950, 38).validate()); {
                    Widgets.get(950, 38).interact("Quick-cast");
                }
            }
            else
            {
                Tabs.MAGIC.open();
            }
        }
    }
}
