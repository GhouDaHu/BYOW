package core.state;

import core.Context;
import utils.AssertUtils;

abstract public class AbstractGlobalState implements IGlobalState {

    private Context context;
    private KeyStates keyStates;

    public AbstractGlobalState() {
        this(null);
    }

    public AbstractGlobalState(Context context) {
        this(context, KeyStates.NORMAL);
    }

    public AbstractGlobalState(Context context, KeyStates keyStates) {
        this.context = context;
        this.keyStates = keyStates;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public KeyStates getKeyStates() {
        return keyStates;
    }

    public void setKeyStates(KeyStates keyStates) {
        this.keyStates = keyStates;
    }

    @Override
    public void handleKeyInput() {

        Character currentChar = getContext().getCurrentChar();
        AssertUtils.notNull(currentChar, "currentChar");

        switch (getKeyStates()) {
            case COLON -> {
                switch (currentChar) {
                    case 'q':
                        onLeaving(new Saving(getContext()));
//                        shouldLeave = true;
                        break;
                    case ':':
                        break;
                    default:
                        setKeyStates(KeyStates.NORMAL);
                        break;
                }
            }
            case NORMAL -> handleKeyInput(currentChar);
        }
    }

    abstract protected void handleKeyInput(Character currentChar);

    @Override
    public void onLeaving(IGlobalState to) {
        System.out.println("Goodbye " + this + ", hello " + to);
        getContext().setGlobalState(to);
    }
}
