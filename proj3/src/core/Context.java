package core;

import core.state.IGlobalState;
import core.state.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class Context {
    private IGlobalState globalState;
    private boolean over;

    private List<Character> inputBuffer;

    public Context() {
        this(new MainMenu());
    }

    public Context(IGlobalState globalState) {
        this.globalState = globalState;
        this.over = false;
        this.inputBuffer = new ArrayList<>();
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

    public List<Character> getInputBuffer() {
        return inputBuffer;
    }

    public void setInputBuffer(List<Character> inputBuffer) {
        this.inputBuffer = inputBuffer;
    }
}
