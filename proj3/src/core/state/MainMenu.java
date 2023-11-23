package core.state;

import core.Context;
import core.Setting;
import edu.princeton.cs.algs4.StdDraw;
import utils.AssertUtils;

import java.awt.*;

public class MainMenu extends AbstractGlobalState {

    public MainMenu() {
        this(null);
    }

    public MainMenu(Context context) {
        super(context);
    }

    public MainMenu(Context context, KeyStates keyStates) {
        super(context, keyStates);
    }

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
        setContext(context);
        initWindow();

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char newTyped = Character.toLowerCase(StdDraw.nextKeyTyped());
                context.setCurrentChar(newTyped);
                handleKeyInput();
                break;
            }
        }
    }


    @Override
    protected void handleKeyInput(Character currentChar) {
        switch (currentChar) {
            case 'n' -> onLeaving(new GettingSeed(getContext()));
            case 'l' -> onLeaving(new Loading(getContext()));
            case 'q' -> onLeaving(new Saving(getContext()));
            case ':' -> setKeyStates(KeyStates.COLON);
            default -> {
            }
        }
    }


    @Override
    public GlobalStateType getStateType() {
        return GlobalStateType.MAIN_MENU;
    }

    @Override
    public String toString() {
        return "MAIN MENU";
    }
}
