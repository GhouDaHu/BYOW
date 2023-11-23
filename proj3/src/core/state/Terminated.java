package core.state;

public class Terminated implements IGlobalState {
    @Override
    public GlobalStateType getStateType() {
        return GlobalStateType.TERMINATED;
    }
}
