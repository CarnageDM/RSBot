package oreo.gatestones;

import oreo.gatestones.impl.CreateGatestone;
import oreo.gatestones.impl.EnterDungeon;
import oreo.gatestones.impl.LeaveDungeon;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import oreo.gatestones.impl.Paint;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Job;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;

import javax.imageio.ImageIO;

/**.
 * User: OreoCream
 * Date: 10/7/12
 * Time: 5:21 PM
 */

@Manifest( authors = { "OreoCream" }, name = "Oreo Gatestones", description = "Gains fast, free magic experience!")
public class Main extends ActiveScript implements PaintListener
{
    public final static int COSMIC_RUNE = 17789;

    public static Timer runTime = new Timer(0);

    public static long startTime;

    public static int StartExp;
    public static int StartLevel;

    public static BufferedImage normal = null;
    public static BufferedImage clicked = null;

    private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
    private Tree jobContainer = null;

    public final void provide(final Node... jobs) {
        for (final Node job : jobs) {
            if (!jobsCollection.contains(job)) {
                jobsCollection.add(job);
            }
        }
        jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
    }

    public final void submit(final Job job) {
        getContainer().submit(job);
    }

    @Override
    public void onStart()
    {
        System.out.println("oreo.gatestones.Main: Script Started.");
        StartExp = Skills.getExperience(Skills.MAGIC);
        StartLevel = Skills.getLevel(Skills.MAGIC);
        startTime = System.currentTimeMillis();
        provide(new EnterDungeon());
        provide(new LeaveDungeon());
        provide(new CreateGatestone());
        try
        {
            final URL cursorURL = new URL("http://i48.tinypic.com/313623n.png");
            final URL cursor80URL = new URL("http://i46.tinypic.com/9prjnt.png");
            normal = ImageIO.read(cursorURL);
            clicked = ImageIO.read(cursor80URL);
        } catch (MalformedURLException e)
        {
        } catch (IOException e)
        {
        }
    }

    @Override
    public int loop()
    {
        if (jobContainer != null) {
            final Node job = jobContainer.state();
            if (job != null) {
                jobContainer.set(job);
                getContainer().submit(job);
                job.join();
            }
        }
        return Random.nextInt(10, 50);
    }

    @Override
    public void onRepaint(Graphics render) {
        Paint.drawPaint(render);
    }

}
