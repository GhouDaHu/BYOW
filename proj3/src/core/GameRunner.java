package core;

import core.state.IGlobalState;
import edu.princeton.cs.algs4.StdDraw;
import utils.AssertUtils;

public class GameRunner {
    public void run() {
        Context context = new Context();
        run(context);
    }

    public void run(Context context) {
        AssertUtils.notNull(context, "context");
        IGlobalState globalState = context.getGlobalState();
        AssertUtils.notNull(globalState, "globalState");

        while (!context.isOver()) {
            globalState.onEntered(context);
        }
    }

    public void init() {
        StdDraw.enableDoubleBuffering();
    }
}
