import java.awt.*;
import java.util.ArrayList;

public class Collision {
    private static int rec1Left;
    private static int rec1Right;
    private static int pattyLeft;
    private static int pattyRight;
    private static int rec1Top;
    private static int rec1Bottom;
    private static int pattyTop;
    private static int pattyBottom;
    private static int deltaLeft;
    private static int deltaRight;
    private static int deltaTop;
    private static int deltaBottom;
    private static int deltaHorizontal;
    private static int deltaVertical;
    private static int deltaDirection;

    public static ArrayList<GameSpriteBricks> blocks = new ArrayList<>();


    public static int MrKrabbyCollision(MrKrabby star, GameSpriteBricks brick1) {
        Rectangle pattyRec = star.rec1;
        Rectangle rec1 = brick1.rec1;

        if (pattyRec.intersects(rec1)) {
            rec1Left = rec1.x;
            rec1Right = rec1.x + rec1.width;
            pattyLeft = pattyRec.x;
            pattyRight = pattyRec.x + pattyRec.width;
            rec1Top = rec1.y;
            rec1Bottom = rec1.y + rec1.height;
            pattyTop = pattyRec.y;
            pattyBottom = pattyRec.y + pattyRec.height;

            deltaLeft = (rec1Right - pattyLeft) * (rec1Right - pattyLeft);
            deltaRight = (rec1Left - pattyRight) * (rec1Left - pattyRight);
            deltaTop = (rec1Top - pattyBottom) * (rec1Top - pattyBottom);
            deltaBottom = (rec1Bottom - pattyTop) * (rec1Bottom - pattyTop);

            deltaHorizontal = (deltaLeft < deltaRight) ? deltaLeft : deltaRight;
            deltaVertical = (deltaTop < deltaBottom) ? deltaTop : deltaBottom;

            deltaDirection = (deltaHorizontal < deltaVertical) ? 1 : -1;

            GameStarter.mrKrab.collisionDirection = deltaDirection;
            return deltaDirection;
        }

        return 0;
    }//close method

    public static void KrabPipeCollision(MrKrabby patRick) {

        Rectangle rec1 = patRick.rec1;
        Rectangle rec2 = GameStarter.katch.rec1;


        int x1 = rec1.x + rec1.width / 2;
        int x2 = rec2.x;

        if (rec1.intersects(rec2)) {
            int pipeWidth = GameStarter.pipeSizeX;

            double currentVX = patRick.getVx();
            if (x1 > x2 && x1 < (x2 + (pipeWidth / 2))) {
                if (patRick.getVx() > 0) {
                    patRick.setVx(-currentVX);
                }
                patRick.setX(patRick.getX() - 20);
                patRick.setVy(-patRick.getVy() - 0.1);
                patRick.setY(patRick.getY() - 20);
            }
            if ((x1 >= x2 + (pipeWidth / 2)) && (x1 < (x2 + pipeWidth))) {
                if (patRick.getVx() < 0) {
                    patRick.setVx(-currentVX);
                }
                patRick.setX(patRick.getX() + 20);
                patRick.setVy(-patRick.getVy() - 0.1);
                patRick.setY(patRick.getY() - 20);
            }
        }
    }



    public static int Burger(PowerUp gStar, GameSpriteBricks brick1) {
        Rectangle gPattyRec = gStar.rec1;
        Rectangle rec1 = brick1.rec1;

        if (gPattyRec.intersects(rec1)) {

            GameStarter.krabBurger.collisionDirection = 0;

            return 100;
        }

        return 0;
    }//close method

    public static void BurgerPipeCollision(PowerUp patRick) {

        Rectangle rec1 = GameStarter.krabBurger.rec1;
        Rectangle rec2 = GameStarter.katch.rec1;


        int x1 = rec1.x + rec1.width / 2;
        int x2 = rec2.x;

        if (rec1.intersects(rec2)) {

            int pipeWidth = GameStarter.pipeSizeX;

            double currentVX = patRick.getVx();
            if (x1 > x2 && x1 < (x2 + (pipeWidth / 2))) {
                if (patRick.getVx() > 0) {
                    patRick.setVx(-currentVX);
                }
                patRick.setX(patRick.getX() - 20);
                patRick.setVy(-patRick.getVy() - 0.1);
                patRick.setY(patRick.getY() - 20);
            }
            if ((x1 >= x2 + (pipeWidth / 2)) && (x1 < (x2 + pipeWidth))) {
                if (patRick.getVx() < 0) {
                    patRick.setVx(-currentVX);
                }
                patRick.setX(patRick.getX() + 20);
                patRick.setVy(-patRick.getVy() - 0.1);
                patRick.setY(patRick.getY() - 20);
            }
        }


    }

}
