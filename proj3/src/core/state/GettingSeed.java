package core.state;

import core.AutograderBuddy;
import core.Context;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;

import java.awt.*;

import static java.awt.event.KeyEvent.VK_ENTER;

public class GettingSeed extends AbstractGlobalState {
    public GettingSeed(Context context) {
        super(context);
    }

    public GettingSeed(Context context, KeyStates keyStates) {
        super(context, keyStates);
    }

    @Override
    public void onEntered(Context context) {
        setContext(context);
        String seed = getSeed();
        TETile[][] tiles = AutograderBuddy.getWorldFromInput(seed);
        getContext().setTiles(tiles);
        onLeaving(new Gaming(getContext()));
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

    @Override
    protected void handleKeyInput(Character currentChar) {
        // no op
    }

    @Override
    public GlobalStateType getStateType() {
        return GlobalStateType.GETTING_SEED;
    }

    @Override
    public String toString() {
        return "GETTING SEED";
    }
}
