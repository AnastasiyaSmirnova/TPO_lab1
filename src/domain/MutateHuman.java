package domain;

import java.util.ArrayList;

public class MutateHuman extends Human {
    /* dif between usual VS mutate => number of heads; number of heads define status of being */
    private int level;
    private ArrayList<String> allowedActions;

    public MutateHuman(String name, int headNumbers) throws IncorrectNameException {
        super(name, EmotionalCondition.HAPPY, HumanAttention.ABSENT_MINDED, Math.max(headNumbers, 1), BodyPosition.SIT);
        headNumbers = Math.max(headNumbers, 1);
        setLevel(headNumbers);
        allowedActions = new ArrayList<>();
        setAllowedActions();
    }

    private int getLevel() {
        return level;
    }

    private static int log2(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }

    private void setLevel(int headNumber) { // 4 levels
        this.level = Math.min( log2(headNumber), 3);
    }

    private void changePriority() {
        setLevel(getBody().getNumberOfHeads());
        setAllowedActions();
    }

    public void addNewHead() {
        boolean r = getBody().addNewHead();
        if (r) {
            changePriority();
        }
    }

    public void addNewActivity(int headNumber, String activity) {
        if (isActivityAllowed(activity)) {
            getBody().addActivityToHead(headNumber, activity);
        }
    }

    public void removeHead(int headNumber) {
        if (getBody().getNumberOfHeads() > 1) {
            boolean r = getBody().removeHead(headNumber);
            if (r) changePriority();
        }
    }

    public ArrayList<String> getAllowedActions() {
        return allowedActions;
    }

    private boolean isActivityAllowed(String action) {
        if (action == null || action.length() == 0 ){
            return false;
        }

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

    private void setAllowedActions() {
        allowedActions.clear();
        allowedActions.add("control usual humans");

        int currentPriority = getLevel();
        if (currentPriority > 0) {
            allowedActions.add("use control panel");
        }
        if (currentPriority > 1) {
            allowedActions.add("use the main computer");
        }
        if (currentPriority > 2) {
            allowedActions.add("absolute power");
        }
    }
}
