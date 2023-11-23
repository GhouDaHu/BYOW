package core.state;

import core.AutograderBuddy;
import core.Context;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;

import java.awt.*;

import static java.awt.event.KeyEvent.VK_ENTER;

public class Loading extends AbstractGlobalState {
    public Loading(Context context) {
        super(context);
    }

    public Loading(Context context, KeyStates keyStates) {
        super(context, keyStates);
    }

    @Override
    protected void handleKeyInput(Character currentChar) {

    }

    @Override
    public void onEntered(Context context) {
    }

    @Override
    public GlobalStateType getStateType() {
        return GlobalStateType.LOADING;
    }
}
