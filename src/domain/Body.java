package domain;

import java.util.ArrayList;

public class Body {
    private BodyPosition position;
    private int numberOfHeads;
    private ArrayList<Head> headsList;

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

    public void setPosition(BodyPosition position) {
        this.position = position;
    }

    public BodyPosition getPosition() {
        return position;
    }

    public int getNumberOfHeads() {
        return numberOfHeads;
    }

    public void setNumberOfHeads(int numberOfHeads) {
        this.numberOfHeads = numberOfHeads;
    }

    public boolean addNewHead(){
        boolean r = headsList.add(new Head(headsList.size()+1));
        if (r){
            setNumberOfHeads(headsList.size());
        }
        return r;
    }

    public boolean removeHead(int headNumber) {
        boolean r = headsList.remove(getHeadByNumber(headNumber));
        if (r) {
            setNumberOfHeads(headsList.size());
        }
        return r;
    }

    public void addActivityToHead(int headNumber, String newActivity) {
        getHeadByNumber(numberOfHeads).addActivity(newActivity);
    }

    public boolean removeActivity(int headNumber, String activity) {
        return getHeadByNumber(headNumber).removeActivity(activity);
    }

    public void clearHeadActivities(int headNumber) {
        getHeadByNumber(headNumber).clearActivitiesList();
    }

    public ArrayList<String> getHeadActivities(int headNumber) {
        return getHeadByNumber(headNumber).getActivities();
    }

    public boolean isHeadFree(int headNumber) {
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

        public void setFree(boolean free) {
            isFree = free;
        }

        public boolean addActivity(String newActivity) {
            boolean r = this.activities.add(newActivity);
            if (r) {
                setFree(false);
            }
            return r;
        }

        public boolean removeActivity(String activity) {
            boolean r = activities.remove(activity);
            if (r && activities.size() == 0) {
                setFree(true);
            }
            return r;
        }

        public void clearActivitiesList() {
            activities.clear();
            setFree(true);
        }

        public int getHeadNumber() {
            return headNumber;
        }

        public boolean isFree() {
            return isFree;
        }

        public ArrayList<String> getActivities() {
            return activities;
        }
    }
}
