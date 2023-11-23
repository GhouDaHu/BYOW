package core.state;

import core.Context;

public interface IGlobalState {
    void onEntered(Context context);
    void handleInput(Context context);
    void onLeaving(Context context);

    GlobalStateType getStateType();
}
