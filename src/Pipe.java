import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;

/**
 * @author anthony-pc
 */
public class Pipe extends JComponent implements Observer
{
    public int collisionDirection = 0;
    public Rectangle rec1 = new Rectangle();
    private int x;
    private int y;
    private int vx;
    private BufferedImage img;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean collisionTruth = false;


    public Pipe(int x, int y, int vx)
    {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.img = GameStarter.pipe1;
        this.rec1.setSize(img.getWidth(), img.getHeight());

    }

    private int pipeWidth = rec1.width;
    private int pipeHeight = rec1.height;

    public void toggleRightPressed()
    {
        this.RightPressed = true;
    }

    public void toggleLeftPressed()
    {
        this.LeftPressed = true;
    }

    public void unToggleRightPressed()
    {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed()
    {
        this.LeftPressed = false;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(img, x, y, null);
        System.out.println(this.toString());
    }


    @Override
    public void update(Observable o, Object o1)
    {
        this.rec1.x = this.x;
        this.rec1.y = this.y;


        if (this.LeftPressed)
        {
            this.moveBackwards();
        }
        if (this.RightPressed)
        {
            this.moveForwards();
        }

        Collision.KrabPipeCollision(GameStarter.mrKrab);
        Collision.BurgerPipeCollision(GameStarter.krabBurger);
        this.repaint();

    }

    private void moveBackwards()
    {
        x -= vx;
        checkBorder();
    }

    private void moveForwards()
    {
        x += vx;
        checkBorder();
    }

    private void checkBorder()
    {
        if (x < 40)
        {
            x = 40;
        }
        if (x >= GameStarter.screenWidth - GameStarter.pipeSizeX - 10 - 40)
        {
            x = GameStarter.screenWidth - GameStarter.pipeSizeX - 10 - 40;
        }

    }


    @Override
    public String toString()
    {
        if (collisionTruth)
        {
            return "true";
        }
        else
        {
            return "false";
        }

    }

}
