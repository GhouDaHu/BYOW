package core;

import core.state.IGlobalState;
import core.state.MainMenu;
import tileengine.TETile;

public class Context {
    private IGlobalState globalState;
    private boolean over;
    private Character currentChar;
    private TETile[][] tiles;
    private String predefinedInputSequence;

    public Context() {
        this(new MainMenu());
    }

    public Context(IGlobalState globalState) {
        this(globalState, false, null, null);
    }

    public Context(IGlobalState globalState, boolean over, Character currentChar, TETile[][] tiles) {
        this.globalState = globalState;
        this.over = over;
        this.currentChar = currentChar;
        this.tiles = tiles;
    }

    public IGlobalState getGlobalState() {
        return globalState;
    }

    public void setGlobalState(IGlobalState globalState) {
        this.globalState = globalState;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Character getCurrentChar() {
        return currentChar;
    }

    public void setCurrentChar(Character currentChar) {
        this.currentChar = currentChar;
    }

    public TETile[][] getTiles() {
        return tiles;
    }

    public void setTiles(TETile[][] tiles) {
        this.tiles = tiles;
    }

    public String getPredefinedInputSequence() {
        return predefinedInputSequence;
    }

    public void setPredefinedInputSequence(String predefinedInputSequence) {
        this.predefinedInputSequence = predefinedInputSequence;
    }
}
