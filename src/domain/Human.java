package domain;

import java.util.ArrayList;

public class Human {
    private String name;
    private ArrayList<EmotionalCondition> emotionalConditions;
    private Body body;
    private HumanAttention attention;

    public Human(String name, EmotionalCondition startEmotionCondition, HumanAttention attention, BodyPosition position) {
        this.name = name;
        this.emotionalConditions = new ArrayList<>();
        emotionalConditions.add(startEmotionCondition);
        this.attention = attention;
        body = new Body(position);
    }

    public Human(String name, EmotionalCondition startEmotionCondition, HumanAttention attention, int headNumbers, BodyPosition position) {
        this.name = name;
        this.emotionalConditions = new ArrayList<>();
        this.emotionalConditions = new ArrayList<>();
        emotionalConditions.add(startEmotionCondition);
        this.attention = attention;
        this.body = new Body(position, headNumbers);
    }

    /* getters & setters */
    public String getName() {
        return name;
    }

    public ArrayList<EmotionalCondition> getEmotionalCondition() {
        return emotionalConditions;
    }

    public Body getBody() {
        return body;
    }

    public HumanAttention getAttention() {
        return attention;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmotionalConditions(ArrayList<EmotionalCondition> emotionalConditions) {
        this.emotionalConditions = emotionalConditions;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void setAttention(HumanAttention attention) {
        this.attention = attention;
    }

    /* body position: go/stand up/sit down */
    public void go() {
        if (body.getPosition() == BodyPosition.SIT) {
            standUp();
        }
    }

    public void standUp() {
        body.setPosition(BodyPosition.STAND);
    }

    public void sitDown() {
        if (body.getPosition() == BodyPosition.GO) {
            standUp();
        }
        body.setPosition(BodyPosition.SIT);
    }

    /* emotion condition (happy, calm, nervous, jaw dropped ) & human attention (concentrated, absent-minded) */
    public void becomeHappy() {
        emotionalConditions.clear();
        emotionalConditions.add(EmotionalCondition.HAPPY);
        emotionalConditions.add(EmotionalCondition.CALM);
    }

    public void calmDown() {
        emotionalConditions.clear();
        emotionalConditions.add(EmotionalCondition.CALM);
        setAttention(HumanAttention.ABSENT_MINDED);
    }

    public void shocked() { // there are two staged of shock: small -> nervous, big -> absent-minded)
        if (getEmotionalCondition().contains(EmotionalCondition.SURPRISED)) {
            emotionalConditions.clear();
            emotionalConditions.add(EmotionalCondition.JAW_DROPPED);
        } else {
            emotionalConditions.clear();
            emotionalConditions.add(EmotionalCondition.SURPRISED);
        }
        setAttention(HumanAttention.CONCENTRATED);
    }
}
