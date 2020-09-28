package domain;

import java.util.ArrayList;

public class MutateHuman extends Human {
    /* dif between usual VS mutate => number of heads; number of heads define status of being */
    private int priority;
    private ArrayList<String> allowedActions;

    public MutateHuman(String name, int headNumbers) {
        super(name, EmotionalCondition.HAPPY, HumanAttention.ABSENT_MINDED, headNumbers, BodyPosition.SIT);
        this.priority = headNumbers * 2;
        allowedActions = new ArrayList<>();
        setAllowedActions();
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    private void changePriority() {
        setPriority(getBody().getNumberOfHeads() * 2);
        setAllowedActions();
    }

    public void addNewHead() {
        boolean r = getBody().addNewHead();
        if (r) {
            changePriority();
        }
    }

    public void addNewActivity(int headNumber, String activity) {
        if (isActivityAllowed(activity)){
            getBody().addActivityToHead(headNumber, activity);
        }
    }

    public void removeHead(int headNumber) {
        boolean r = getBody().removeHead(headNumber);
        if (r) changePriority();
    }

    public ArrayList<String> getAllowedActions() {
        return allowedActions;
    }

    private boolean isActivityAllowed(String action) {
        switch (action) {
            case "control usual humans":
            case "use control panel":
            case "use the main computer":
            case "absolute power": {
                return allowedActions.indexOf(action) >= 0;
            }
            default: {
                return true;
            }
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
