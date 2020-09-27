package domain;

import java.util.ArrayList;

public class UsualHuman extends Human {
    private boolean isUniqueOpportunities;
    private StrangeThings things;

    public UsualHuman(String name, boolean isUniqueOpportunities) {
        super(name, EmotionalCondition.NERVOUS, HumanAttention.ABSENT_MINDED, BodyPosition.SIT);
        this.isUniqueOpportunities = isUniqueOpportunities;
        things = new StrangeThings(0);
    }

    public void setUniqueOpportunities(boolean uniqueOpportunities) {
        isUniqueOpportunities = uniqueOpportunities;
    }

    public boolean isUniqueOpportunities() {
        return isUniqueOpportunities;
    }

    public void increaseThings() {
        if (isUniqueOpportunities()) {
            things.setNumber(things.getNumber() + 100);
            System.out.println(things.toString());
        }
    }

}
