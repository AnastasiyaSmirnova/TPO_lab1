package domain;

public class UsualHuman extends Human {
    private boolean isUniqueOpportunities;
    private StrangeThings things;

    public UsualHuman(String name, boolean isUniqueOpportunities) throws IncorrectNameException {
        super(name, EmotionalCondition.NERVOUS, HumanAttention.ABSENT_MINDED, BodyPosition.STAND);
        this.isUniqueOpportunities = isUniqueOpportunities;
        things = new StrangeThings(0);
    }

    public UsualHuman(String name) throws IncorrectNameException {
        super(name, EmotionalCondition.NERVOUS, HumanAttention.ABSENT_MINDED, BodyPosition.STAND);
        this.isUniqueOpportunities = false;
        things = new StrangeThings(0);
    }

    public boolean isUniqueOpportunities() {
        return isUniqueOpportunities;
    }

    public void increaseThings() {
        if (isUniqueOpportunities()) {
            things.setNumber(things.getNumber() + 100);
//            System.out.println(things.toString());
        }
    }

    public int getThingsNumber() {
        return things.getNumber();
    }

    private static class StrangeThings {
        private int number;

        public StrangeThings(int number) {
            this.number = number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return "Current number of strange things is " + number;
        }
    }

}
