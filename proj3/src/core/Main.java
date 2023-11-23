package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;

import java.awt.*;

import static core.Setting.*;
import static java.awt.event.KeyEvent.VK_ENTER;

public class Main {
    public static void showMainMenu() {
        StdDraw.clear(new Color(0, 0, 0));
        // set font
        StdDraw.setPenColor(new Color(255, 255, 255));
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 60));
        StdDraw.text(0.5, 0.6, "CS61B: THE GAME");
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 25));
        StdDraw.text(0.5, 0.4, "New Game (N)");
        StdDraw.text(0.5, 0.35, "Load Game (L)");
        StdDraw.text(0.5, 0.3, "Quit (Q)");
        StdDraw.show();
    }

    public static void initWindow() {
        StdDraw.setCanvasSize(Setting.MAIN_MENU_WIDTH, Setting.MAIN_MENU_HEIGHT);
        StdDraw.enableDoubleBuffering();
    }

    public static void main(String[] args) {
        initWindow();
        boolean gameOver = false;
        while (true) {

            showMainMenu();
            if (StdDraw.hasNextKeyTyped()) {
                char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                switch (key) {
                    case 'n':
                        newGame();
                        break;
                    case 'l':
                        loadGame();
                        break;
                    case 'q':
                        gameOver = quitGame();
                        break;
                    default:
                }
            }
            if (gameOver) {
                break;
            }

        }

        GameRunner runner = new GameRunner();
        runner.init();
        runner.run();
    }

    private void run(Context context) {
        context.globalState.onEntered();
    }

    private static boolean quitGame() {
        saveGame();
        StdDraw.clear(new Color(0, 0, 0));
        StdDraw.setPenColor(new Color(255, 255, 255));
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 60));
        StdDraw.text(0.5, 0.5, "Bye ~");
        StdDraw.show();
        return true;
    }

    private static void saveGame() {

    }

    private static void loadGame() {

    }

    private static String getSeed() {

        StdDraw.setPenColor(new Color(255, 255, 255));
        StringBuilder seed = new StringBuilder();
        while (true) {
            StdDraw.clear(new Color(0, 0, 0));
            StdDraw.setFont(new Font("Monaco", Font.BOLD, 30));
            StdDraw.text(0.5, 0.5, "Input your seed: ");
            StdDraw.setFont(new Font("Monaco", Font.BOLD, 20));
            StdDraw.text(0.5, 0.4, String.valueOf(seed));
            StdDraw.show();

            if (StdDraw.hasNextKeyTyped()) {

                if (StdDraw.isKeyPressed(VK_ENTER)) {
                    break;
                } else {
                    char key = StdDraw.nextKeyTyped();
                    if (Character.isDigit(key) || Character.isLetter(key)) {
                        seed.append(key);
                    }
                }

            }
        }
        return String.valueOf(seed);
    }

    private static void newGame() {
        String seed = getSeed();
        TETile[][] tiles = AutograderBuddy.getWorldFromInput(seed);
        TERenderer ter = new TERenderer();
        ter.initialize(tiles.length, tiles[0].length);
        ter.renderFrame(tiles);

//        StdDraw.pause(10000); // pause for 5 seconds so you can see the output

        // write game logic here
//        while(){
//            // paint world
//            TERenderer ter = new TERenderer();
//            ter.initialize(tiles.length, tiles[0].length);
//            ter.renderFrame(tiles);
//
//            // paint people
//
//            // paint things
//
//            // hud
//
//            // get input
//
//            // logic ->   people  ->  if over -> ....
//
//        }

        initWindow();
    }
}
