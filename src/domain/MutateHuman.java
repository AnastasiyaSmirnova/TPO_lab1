package domain;

import java.util.ArrayList;

public class MutateHuman extends Human {
    private int priority;
    private ArrayList<String> allowedActions;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public MutateHuman(String name, int headNumbers) {
        super(name, EmotionalCondition.HAPPY, HumanAttention.ABSENT_MINDED, headNumbers, BodyPosition.SIT);
        this.priority = headNumbers * 2;
        allowedActions = new ArrayList<>();
        setAllowedActions();
    }

    private void changePriority() {
        setPriority(getBody().getNumberOfHeads() * 2);
        setAllowedActions();
    }

    public void addNewHead() {
        getBody().setNumberOfHeads(getBody().getNumberOfHeads() + 1);
        changePriority();
    }

    public void removeHead() {
        int currentHeadNumber = getBody().getNumberOfHeads();
        if (currentHeadNumber > 1) {
            getBody().setNumberOfHeads(currentHeadNumber - 1);
            changePriority();
        }
    }

    public void setAllowedActions() {
        allowedActions.clear();
        int currentPriority = getPriority();
        if (currentPriority > 0) {
            allowedActions.add("control usual humans");
        }
        if (currentPriority > 1) {
            allowedActions.add("use control panel");
        }
        if (currentPriority > 4) {
            allowedActions.add("use the main computer");
        }
        if (currentPriority > 8) {
            allowedActions.add("absolute power");
        }
    }
}
