import sun.audio.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author anthony-pc
 */

public class GameStarter extends JPanel {
    public static final int screenWidth = 1040;
    public static final int screenHeight = 1360;
    public static final int pipeSizeX = 160;
    public static final int pipeSizeY = 60;

    //Loading all the images
    public static BufferedImage background1;
    public static BufferedImage pipe1;
    public static BufferedImage krab;
    public static BufferedImage burger;
    public static BufferedImage patrick8;
    public static BufferedImage block1;
    public static BufferedImage block2;
    public static BufferedImage block3;
    public static BufferedImage block4;
    public static BufferedImage blockSplit;
    public static BufferedImage KrakenBig;
    public static BufferedImage KrakenSmall;
    public static BufferedImage unbreakableBlock;
    public static BufferedImage UnbreakableLong;
    public static BufferedImage PowerUp;

    public static int pipeX = (screenWidth / 2) - (pipeSizeX / 2);
    public static int pipeY = screenHeight - 200;
    public static int pipeSpeed = 5;
    public static int smallBlockWidth = 40;
    public static int smallBlockHeight = 40;
    public static int krakenCount;
    public static int brickPoints;
    public static boolean finalLevelChecker = false;

    //Object's variables
    public static Pipe katch;
    public static MrKrabby mrKrab;
    public static PowerUp krabBurger;
    public static JPanel container = new JPanel();
    public static JFrame jf = new JFrame();
    public static GameEventObservable geobv;

    //Loading all the images with BufferReader
    {
        try {
            background1 = ImageIO.read(new File("./res/krusty.png"));
            pipe1 = ImageIO.read(new File("./res/Katch.png"));
            krab = ImageIO.read(new File("./res/krab.png"));
            burger = ImageIO.read(new File("./res/Krabby_Patty.png"));
            patrick8 = ImageIO.read(new File("./res/Tank8.png"));
            block1 = ImageIO.read(new File("./res/Block5.png"));
            block2 = ImageIO.read(new File("./res/Block3.png"));
            block3 = ImageIO.read(new File("./res/Block1.png"));
            block4 = ImageIO.read(new File("./res/Block2.png"));
            blockSplit = ImageIO.read(new File("./res/Block_split.png"));
            KrakenBig = ImageIO.read(new File("./res/Bigleg.png"));
            KrakenSmall = ImageIO.read(new File("./res/Bigleg_small.png"));
            unbreakableBlock = ImageIO.read(new File("./res/Wall.png"));
            UnbreakableLong = ImageIO.read(new File("./res/Block_solid.png"));
            PowerUp = ImageIO.read(new File("./res/Block_life.png"));


        } catch (IOException e) {
            System.out.println("Image not loaded.");
        }
    }

    //
    public GameStarter() {
        this.geobv = new GameEventObservable();
    }

    public static void main(String[] args) {
        Thread x;
        GameStarter trex = new GameStarter();
        trex.init();
        playMusic("./res/JustDance.wav"); //music path

        try {
            while (true) {
                trex.geobv.setChanged();
                trex.geobv.notifyObservers();
                Thread.sleep(1000 / 144);

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GameStarter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void init() {


        katch = new Pipe(pipeX + 200, pipeY, pipeSpeed);
        mrKrab = new MrKrabby(krab, 200, 500, 1, 1, 3);
        krabBurger = new PowerUp(patrick8, -15000, -15000, 0, 0, 1);
        MapLayout.mapMaker(MapLayout.Maps1);
        GameSpriteBricks.setCurrentLevel(1);

        container.setLayout(new OverlayLayout(container));
        PipeControl stick = new PipeControl(katch, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT); //controls for the pipe
        container.add(this);
        container.add(katch);

        jf.add(container);
        jf.addKeyListener(stick);
        jf.setTitle("Bottom of Bikini");
        jf.setSize(screenWidth, screenHeight);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        this.geobv.addObserver(katch);
        this.geobv.addObserver(mrKrab);
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background1, 0, 0, screenWidth, screenHeight, null);
        katch.paintComponent(g2);
        mrKrab.paintComponent(g2);
        krabBurger.paintComponent(g2);

        for (int i = 0; i < Collision.blocks.size(); i++) {
            Collision.blocks.get(i).paintComponent(g2);
        }

        if (mrKrab.getLifeCount() >= 0) {
            for (int i = 0; i < mrKrab.getLifeCount(); i++) {
                g2.drawImage(krab, 50 + i * 50, screenHeight - 115, 40, 40, null);
            }
            if (mrKrab.getLifeCount() == 0 && finalLevelChecker == false) {
                g.setFont(new Font("Arial", Font.BOLD, 72));
                g2.drawString("GAME OVER!", 300, 680);
                GameStarter.geobv.deleteObserver(krabBurger);
                Collision.blocks.remove(krabBurger);

            }
            if (GameSpriteBricks.getCurrentLevel() == 3) {
                g.setFont(new Font("Arial", Font.BOLD, 72));
                g2.drawString("You Win!!", 300, 680);
                g2.drawString("Score: " + brickPoints, 300, 780);
                finalLevelChecker = true;

            }
        }

        if (finalLevelChecker == false) {
            g.setFont(new Font("Arial", Font.PLAIN, 48));
            g2.drawString("Score: " + brickPoints, screenWidth - 325, screenHeight - 75);

        }
    }



    private static void playMusic(String filePath) {
        InputStream music;

        try {
            music = new FileInputStream(new File(filePath));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");


        }
    }


}
