import java.awt.image.BufferedImage;
import java.util.Observable;

public class GameSpriteBricks extends GameSprite {

    public int debugBrickNum;

    private int brickType;
    private int brickLifeCount;
    public static int currentLevel = 0;

    private boolean breakableTruth = true;

    public GameSpriteBricks() {
    }

    public GameSpriteBricks(int x, int y, BufferedImage img) {
        super(x, y, img);
        this.brickLifeCount = 1;
        this.brickPointValue = 0;
    }

    public void setBrickPointValue(int brickValue) {
        this.brickPointValue = brickValue;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static void setCurrentLevel(int currentLevel) {
        GameSpriteBricks.currentLevel = currentLevel;
    }

    private int brickPointValue;

    public void setBrickType(int brickType) {
        this.brickType = brickType;
    }

    public void setBrickLifeCount(int brickLifeCount) {
        this.brickLifeCount = brickLifeCount;
    }

    public void setBreakableTruth(boolean breakableTruth) {
        this.breakableTruth = breakableTruth;
    }

    @Override
    public void update(Observable observable, Object o) {

        rec1.x = x;
        rec1.y = y;

        collisionDirection = Collision.MrKrabbyCollision(GameStarter.mrKrab, this);
        collisionHelper(collisionDirection, GameStarter.mrKrab);
        collisionDirection2 = Collision.Burger(GameStarter.krabBurger, this);
        ghostCollisionHelper(collisionDirection2, GameStarter.krabBurger);

    }

    private void collisionHelper(int collisionDirection, MrKrabby patty) {
        if (collisionDirection != 0) {
            brickLifeCount--;
        }


        if (collisionDirection != 0 && breakableTruth && brickLifeCount == 0) {

            GameStarter.geobv.deleteObserver(this);
            Collision.blocks.remove(this);
            GameStarter.brickPoints += this.brickPointValue;
            if (this.brickType == 5 || this.brickType == 6) {
                GameStarter.krakenCount--;

                if (GameStarter.krakenCount == 0 && getCurrentLevel() == 1) {
                    MapLayout.deleteMap();
                    MapLayout.mapMaker(MapLayout.Maps2);
                    setCurrentLevel(2);
                    GameStarter.mrKrab.setX(GameStarter.katch.getX());
                    GameStarter.mrKrab.setY(GameStarter.screenHeight - 500);
                    GameStarter.mrKrab.setVy(0);

                    GameStarter.geobv.deleteObserver(GameStarter.krabBurger);
                    GameStarter.krabBurger.setX(-1050);
                    GameStarter.krabBurger.setY(-1050);
                }

                if (GameStarter.krakenCount == 0 && getCurrentLevel() == 2) {
                    MapLayout.deleteMap();
                    setCurrentLevel(3);
                    GameStarter.mrKrab.setX(-1500);
                    GameStarter.mrKrab.setY(-1500);
                    GameStarter.mrKrab.setVy(0);
                    GameStarter.mrKrab.setLifeCount(0);
                    GameStarter.mrKrab.setImg(GameStarter.patrick8);
                    GameStarter.geobv.deleteObserver(GameStarter.mrKrab);
                    GameStarter.geobv.deleteObserver(GameStarter.krabBurger);
                    GameStarter.krabBurger.setX(-1050);
                    GameStarter.krabBurger.setY(-1050);
                }

            }
            if (this.brickType == 10) {
                GameStarter.geobv.addObserver(GameStarter.krabBurger);
                GameStarter.krabBurger.setImg(GameStarter.burger);
                GameStarter.krabBurger.setX(200);
                GameStarter.krabBurger.setY(500);
                GameStarter.krabBurger.setVx(1);
                GameStarter.krabBurger.setVy(1);
                GameStarter.krabBurger.setLifeCount(1);
            }
        }

        if (collisionDirection != 0 && this.brickType == 9) {
            patty.setLifeCount(patty.getLifeCount() + 1);
        }
        collisionDirection = 0;
    }






    private void ghostCollisionHelper(int collisionDirection2, PowerUp patty) {

        if (collisionDirection2 != 0) {
            GameStarter.geobv.deleteObserver(this);
            Collision.blocks.remove(this);
            GameStarter.brickPoints += this.brickPointValue;
            if (this.brickType == 5 || this.brickType == 6) {
                GameStarter.krakenCount--;

                if (GameStarter.krakenCount == 0 && getCurrentLevel() == 1) {
                    MapLayout.deleteMap();
                    MapLayout.mapMaker(MapLayout.Maps2);
                    setCurrentLevel(2);
                    GameStarter.mrKrab.setX(GameStarter.katch.getX());
                    GameStarter.mrKrab.setY(GameStarter.screenHeight - 500);
                    GameStarter.mrKrab.setVy(0);

                    GameStarter.geobv.deleteObserver(GameStarter.krabBurger);
                    GameStarter.krabBurger.setX(-1050);
                    GameStarter.krabBurger.setY(-1050);
                }

                if (GameStarter.krakenCount == 0 && getCurrentLevel() == 2) {
                    MapLayout.deleteMap();
                    setCurrentLevel(3);
                    GameStarter.mrKrab.setX(-1500);
                    GameStarter.mrKrab.setY(-1500);
                    GameStarter.mrKrab.setVy(0);
                    GameStarter.mrKrab.setLifeCount(0);
                    GameStarter.mrKrab.setImg(GameStarter.patrick8);
                    GameStarter.geobv.deleteObserver(GameStarter.mrKrab);
                    GameStarter.geobv.deleteObserver(GameStarter.krabBurger);
                    GameStarter.krabBurger.setX(-1050);
                    GameStarter.krabBurger.setY(-1050);
                }
            }
        }



        if (collisionDirection2 != 0 && this.brickType == 9) {
            patty.setLifeCount(patty.getLifeCount() + 1);
        }
        collisionDirection2 = 0;
    }


}
