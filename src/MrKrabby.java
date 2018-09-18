import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import javax.swing.*;
import java.util.Observer;

public class MrKrabby extends JComponent implements Observer {
    private int x;
    private int y;
    private BufferedImage img;
    public Rectangle rec1 = new Rectangle();
    protected int collisionDirection = 0;
    private double vx;
    private double vy;
    private double gravitationalConstant = 0.03;
    public static int lifeCount;


    //Default constructor
    public MrKrabby() {
        this.img = GameStarter.krab;
        this.x = 200;
        this.y = 500;
        this.vx = 1;
        this.vy = 1;
        this.rec1.width = img.getWidth();
        this.rec1.height = img.getHeight();
        this.lifeCount = 100;

    }

    public MrKrabby(BufferedImage img, int x, int y, double vx, double vy, int lifeCount) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.rec1.width = img.getWidth();
        this.rec1.height = img.getHeight();
        this.lifeCount = lifeCount;


    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }


    @Override
    public void update(Observable o, Object arg) {
        this.rec1.x = this.x;
        this.rec1.y = this.y;
        int moveVar = 10;

        if(GameStarter.mrKrab.getLifeCount() == 0){

        }

        //Update the direction of MrKrabby every time it hits
        if (collisionDirection == -1) {
            this.vy = -this.vy;
            int deltaY;
            if (this.vy < 0) {

                deltaY = -moveVar;
            } else {
                deltaY = moveVar;

            }
            this.y += this.vy + deltaY;
            this.x += this.vx;
            this.rec1.y = this.y;
        }
        if (collisionDirection == 1) {
            this.vx = -this.vx;
            int deltaX;
            if (this.vx < 0) {
                deltaX = -moveVar;

            } else {

                deltaX = moveVar;
            }
            this.x += this.vx + deltaX;
            this.y += this.vy;
            this.rec1.x = this.x;
        }
        if (collisionDirection == 2) {
            this.vx = -this.vx;
            this.vy = -this.vy;
            int deltaX;
            int deltaY;
            if (this.vx < 0) {

                deltaX = -moveVar;
            } else {
                deltaX = moveVar;

            }
            if (this.vy < 0) {

                deltaY = -moveVar;
            } else {

                deltaY = moveVar;
            }
            this.x += this.vx + deltaX;
            this.y += this.vy + deltaY;
            this.rec1.x = this.x;
            this.rec1.y = this.y;
        } else {
            this.x += this.vx;
            this.y += this.vy;
        }

        collisionDirection = 0;
        checkBorder();
        gravitation();
        super.repaint();

    }

    private void checkBorder() {
        if (x <= GameStarter.smallBlockWidth + 1) {
            x = GameStarter.smallBlockWidth + 1;
            vx = -vx;
        }
        if (x >= GameStarter.screenWidth - this.img.getWidth() - GameStarter.smallBlockWidth - 1) {
            x = GameStarter.screenWidth - this.img.getWidth() - GameStarter.smallBlockWidth - 1;
            vx = -vx;
        }
        if (y <= GameStarter.smallBlockHeight + 1) {
            y = GameStarter.smallBlockHeight + 25;
            vy = -vy;
        }


        if (y >= GameStarter.screenHeight) {
            this.lifeCount--;
            this.x = 200;
            this.y = 680;
            this.vx = 1;
            this.vy = 1;

            if (lifeCount == 0) {
                this.y = -1000;
                this.x = -1000;
                this.vx = 0;
                this.vy = 0;
                GameStarter.geobv.deleteObserver(this);
                Collision.blocks.remove(this);
            }
        }
    }


    private void gravitation() {
        this.vy = this.vy + gravitationalConstant;
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(img, x, y, null);
    }
}
