package core.state;

import core.Context;
import core.Setting;
import edu.princeton.cs.algs4.StdDraw;
import utils.AssertUtils;

import java.awt.*;
import java.util.List;

public class MainMenu implements IGlobalState {

    private void initWindow() {
        StdDraw.setCanvasSize(Setting.MAIN_MENU_WIDTH, Setting.MAIN_MENU_HEIGHT);
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

    @Override
    public void onEntered(Context context) {
        initWindow();

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                break;
            }
        }
        char key = Character.toLowerCase(StdDraw.nextKeyTyped());
        List<Character> inputBuffer = context.getInputBuffer();
        AssertUtils.notNull(inputBuffer, "inputBuffer");
        inputBuffer.add(key);
        handleInput(context);
    }

    @Override
    public void handleInput(Context context) {
        List<Character> inputBuffer = context.getInputBuffer();
        AssertUtils.notNull(inputBuffer, "inputBuffer");

        if (inputBuffer.size() < 1) {
            System.err.println("inputBuffer has no char exist.");
            return;
        }
        Character first = inputBuffer.get(0);
        boolean shouldLeave = false;
        switch (first) {
            case 'n':
                context.setGlobalState(new Gaming());
                shouldLeave = true;
                break;
            case 'l':
                context.setGlobalState(new Loading());
                shouldLeave = true;
                break;
            case 'q':
                context.setGlobalState(new Saving());
                shouldLeave = true;
                break;
            case ':':
                break;
        }


        if (!shouldLeave) {
            inputBuffer.remove(0);
            if (inputBuffer.size() < 1) {
                System.err.println("inputBuffer should have more than 1 chars");
            }
            Character second = inputBuffer.
        }

        if (shouldLeave) {
            onLeaving(context);
        }

    }

    @Override
    public void onLeaving(Context context) {
        context.getInputBuffer().clear();
        System.out.println("Goodbye MainMenu, hello " + context.getGlobalState());
    }

    @Override
    public GlobalStateType getStateType() {
        return null;
    }

    @Override
    public String toString() {
        return "MAIN MENU";
    }
}
