package oreo.gatestones.impl;

import oreo.gatestones.Main;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;

import java.awt.*;
import java.text.NumberFormat;

/**
 * User: OreoCream
 * Date: 10/7/12
 * Time: 7:52 PM
 */

public class Paint
{
    public static int expHour;
    public static int expGained;
    public static int LevelsGained;

    public final static void drawPaint(final Graphics render)
    {
        final Color color1 = new Color(0, 0, 255, 180);
        final Color color2 = new Color(255, 255, 255);

        final Font font1 = new Font("Arial", 1, 10);

        expGained = Skills.getExperience(Skills.MAGIC) - Main.StartExp;
        expHour = (int) ((expGained) * 3600000D / (System.currentTimeMillis() - Main.startTime));
        LevelsGained = Skills.getLevel(Skills.MAGIC) - Main.StartLevel;

        Graphics2D g = (Graphics2D) render;
        g.setColor(color1);
        g.fillRoundRect(12, 8, 328, 59, 16, 16);
        g.setFont(font1);
        g.setColor(color2);
        g.drawString("Time Ran: " + Main.runTime.toElapsedString(), 29, 25);
        g.drawString("Experience: " + NumberFormat.getInstance().format(expGained) + " (" + NumberFormat.getInstance().format(expHour) +"/hr)", 29, 40);
        g.drawString("Level: " + Skills.getLevel(Skills.MAGIC) + " (+" + LevelsGained + ")", 29, 55);
        Widgets.get(950, 38).draw(g);
        final Point p = Mouse.getLocation();
        final long mpt = System.currentTimeMillis()- Mouse.getPressTime();
        if (Mouse.getPressTime() == -1 || mpt >= 1000)
        {
            g.drawImage(Main.normal, p.x - 8, p.y - 8, null); // this
        }
        if (mpt < 1000)
        {
            g.drawImage(Main.clicked, Mouse.getPressX() - 8, Mouse.getPressY() - 8, null);
        }
    }
}
