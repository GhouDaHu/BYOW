package core.state;

import core.Context;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import utils.AssertUtils;

public class Gaming extends AbstractGlobalState {
    private TERenderer render;
    public Gaming(Context context) {
        super(context);
    }

    public Gaming(Context context, KeyStates keyStates) {
        super(context, keyStates);
    }

    public TERenderer getRender() {
        return render;
    }

    public void setRender(TERenderer render) {
        this.render = render;
    }

    @Override
    public void onEntered(Context context) {
        setContext(context);
        TETile[][] tiles = context.getTiles();
        AssertUtils.notNull(tiles, "tiles");
        TERenderer ter = new TERenderer();
        ter.initialize(tiles.length, tiles[0].length);
        ter.renderFrame(tiles);
        setRender(ter);
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
//        switch (currentChar) {
//            case 'w' -> ;
//            case 's' -> ;
//            case 'a' -> ;
//            case 'd' -> ;
//            default -> {
//            }
//        }
    }

    @Override
    public GlobalStateType getStateType() {
        return GlobalStateType.GAMING;
    }

    @Override
    public String toString() {
        return "GAMING";
    }
}
