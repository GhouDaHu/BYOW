package core.state;

import core.Context;

public interface IGlobalState {
    default void onEntered(Context context) {}
    default void handleKeyInput() {}
    default void onLeaving(IGlobalState to) {}

    GlobalStateType getStateType();
}
