package core.state;

import core.Context;

public class Saving extends AbstractGlobalState {
    public Saving(Context context) {
        super(context);
    }

    public Saving(Context context, KeyStates keyStates) {
        super(context, keyStates);
    }

    @Override
    protected void handleKeyInput(Character currentChar) {

    }

    @Override
    public GlobalStateType getStateType() {
        return GlobalStateType.SAVING;
    }
}
