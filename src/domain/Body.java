package domain;

import java.util.ArrayList;

public class Body {
    private BodyPosition position;
    private int numberOfHeads;
    private static ArrayList<Head> headsList;

    public Body(BodyPosition position) {
        this.position = position;
        this.numberOfHeads = 1;
        headsList = null;
    }

    public Body(BodyPosition position, int numberOfHeads) {
        this.position = position;
        this.numberOfHeads = numberOfHeads;
        headsList = new ArrayList<>();
        for (int i = 1; i <= numberOfHeads; i++) {
            Head head = new Head(i);
            headsList.add(head);
        }
    }

    void setPosition(BodyPosition position) {
        this.position = position;
    }

    public BodyPosition getPosition() {
        return position;
    }

    public int getNumberOfHeads() {
        return numberOfHeads;
    }

    private void setNumberOfHeads(int numberOfHeads) {
        this.numberOfHeads = numberOfHeads;
    }

    boolean addNewHead() {
        boolean r = headsList.add(new Head(headsList.size() + 1));
        if (r) {
            setNumberOfHeads(headsList.size());
        }
        return r;
    }

    boolean removeHead(int headNumber) {
        boolean r = headsList.remove(getHeadByNumber(headNumber));
        if (r) {
            setNumberOfHeads(headsList.size());
        }
        return r;
    }

    boolean addActivityToHead(int headNumber, String newActivity) {
        if (!getHeadActivities(headNumber).contains(newActivity)) {
            return getHeadByNumber(headNumber).addActivity(newActivity);
        }
        return false;
    }

    boolean removeActivity(int headNumber, String activity) {
        return getHeadByNumber(headNumber).removeActivity(activity);
    }

    boolean clearHeadActivities(int headNumber) {
        return getHeadByNumber(headNumber).clearActivitiesList();
    }

    ArrayList<String> getHeadActivities(int headNumber) {
        return getHeadByNumber(headNumber).getActivities();
    }

    boolean isHeadFree(int headNumber) {
        return getHeadByNumber(headNumber).isFree();
    }

    private Head getHeadByNumber(int number) {
        Head r = null;
        for (Head head : headsList) {
            if (head.getHeadNumber() == number) {
                r = head;
            }
        }
        return r;
    }

    private static class Head {
        private int headNumber;
        private boolean isFree;
        private ArrayList<String> activities;

        private Head(int number) {
            this.headNumber = number;
            this.isFree = true;
            this.activities = new ArrayList<>();
        }

        void setFree(boolean free) {
            isFree = free;
        }

        boolean addActivity(String newActivity) {
            boolean r = this.activities.add(newActivity);
            if (r) {
                setFree(false);
            }
            return r;
        }

        boolean removeActivity(String activity) {
            boolean r = activities.remove(activity);
            if (r && activities.size() == 0) {
                setFree(true);
            }
            return r;
        }

        boolean clearActivitiesList() {
            activities.clear();
            setFree(true);
            return true;
        }

        int getHeadNumber() {
            return headNumber;
        }

        boolean isFree() {
            return isFree;
        }

        ArrayList<String> getActivities() {
            return activities;
        }
    }
}
