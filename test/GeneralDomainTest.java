import domain.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeneralDomainTest {

    /**
     * have all the necessary conditions been created?
     */
    @Test
    public void HumanAttentionConcentrated() {
        assertNotNull("CONCENTRATED", HumanAttention.valueOf("CONCENTRATED"));
    }

    @Test
    public void HumanAttentionAbsentMinded() {
        assertNotNull("ABSENT_MINDED", HumanAttention.valueOf("ABSENT_MINDED"));
    }

    @Test
    public void HumanPositionSit() {
        assertNotNull("SIT", BodyPosition.valueOf("SIT"));
    }

    @Test
    public void HumanPositionGo() {
        assertNotNull("GO", BodyPosition.valueOf("GO"));
    }

    @Test
    public void HumanPositionStand() {
        assertNotNull("STAND", BodyPosition.valueOf("STAND"));
    }

    @Test
    public void EmotionalConditionNervous() {
        assertNotNull("NERVOUS", EmotionalCondition.valueOf("NERVOUS"));
    }

    @Test
    public void EmotionalConditionSurprised() {
        assertNotNull("SURPRISED", EmotionalCondition.valueOf("SURPRISED"));
    }

    @Test
    public void EmotionalConditionHappy() {
        assertNotNull("HAPPY", EmotionalCondition.valueOf("HAPPY"));
    }

    @Test
    public void EmotionalConditionRowDropped() {
        assertNotNull("JAW_DROPPED", EmotionalCondition.valueOf("JAW_DROPPED"));
    }

    @Test
    public void EmotionalConditionCalm() {
        assertNotNull("CALM", EmotionalCondition.valueOf("CALM"));
    }

    /**
     * human tests (): general for usual and mutated human
     */
    @Test
    public void standUpFromStand() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.standUp();
        assertEquals("stand up from STAND position", BodyPosition.STAND, human.getBody().getPosition());
    }

    @Test
    public void sitDownFromStand() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.sitDown();
        assertEquals("sit down from STAND position", BodyPosition.SIT, human.getBody().getPosition());
    }

    @Test
    public void goFromStand() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.go();
        assertEquals("go from STAND position", BodyPosition.GO, human.getBody().getPosition());
    }

    @Test
    public void standUpFromSit() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.sitDown();
        human.standUp();
        assertEquals("stand up from SIT position", BodyPosition.STAND, human.getBody().getPosition());
    }

    @Test
    public void sitDownFromSit() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.sitDown();
        human.sitDown();
        assertEquals("sit down from SIT position", BodyPosition.SIT, human.getBody().getPosition());
    }

    @Test
    public void goFromSit() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.sitDown();
        human.go();
        assertEquals("go from Sit position", BodyPosition.GO, human.getBody().getPosition());
    }

    @Test
    public void standUpFromGo() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.go();
        human.standUp();
        assertEquals("stand up from GO position", BodyPosition.STAND, human.getBody().getPosition());
    }

    @Test
    public void sitDownFromGo() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.go();
        human.sitDown();
        assertEquals("sit down from GO position", BodyPosition.SIT, human.getBody().getPosition());
    }

    @Test
    public void goFromGo() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.go();
        human.go();
        assertEquals("go from GO position", BodyPosition.GO, human.getBody().getPosition());
    }

    @Test
    public void calmDownFromNervous() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.calmDown();
        assertTrue("calm down from nervous",
                human.getEmotionalCondition().contains(EmotionalCondition.CALM) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }

    @Test
    public void becomeHappyFromNervous() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.becomeHappy();
        assertTrue("become happy from nervous",
                human.getEmotionalCondition().contains(EmotionalCondition.CALM) && human.getEmotionalCondition().contains(EmotionalCondition.HAPPY) &&
                        human.getEmotionalCondition().size() == 2 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }

    @Test
    public void shockedFromNervous() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.shocked();
        assertTrue("shocked from nervous",
                human.getEmotionalCondition().contains(EmotionalCondition.SURPRISED) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.CONCENTRATED);
    }

    @Test
    public void becomeNervousFromNervous() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.becomeNervous();
        assertTrue("become nervous from nervous",
                human.getEmotionalCondition().contains(EmotionalCondition.NERVOUS) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }


    @Test
    public void calmDownFromCalm() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.calmDown();
        human.calmDown();
        assertTrue("calm down from calm",
                human.getEmotionalCondition().contains(EmotionalCondition.CALM) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }

    @Test
    public void becomeHappyFromCalm() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.calmDown();
        human.becomeHappy();
        assertTrue("become happy from calm",
                human.getEmotionalCondition().contains(EmotionalCondition.CALM) && human.getEmotionalCondition().contains(EmotionalCondition.HAPPY) &&
                        human.getEmotionalCondition().size() == 2 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }

    @Test
    public void shockedFromCalm() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.calmDown();
        human.shocked();
        assertTrue("shocked from calm",
                human.getEmotionalCondition().contains(EmotionalCondition.SURPRISED) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.CONCENTRATED);
    }

    @Test
    public void becomeNervousFromCalm() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.calmDown();
        human.becomeNervous();
        assertTrue("become nervous from calm",
                human.getEmotionalCondition().contains(EmotionalCondition.NERVOUS) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }


    @Test
    public void calmDownFromHappy() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.becomeHappy();
        human.calmDown();
        assertTrue("calm down from happy",
                human.getEmotionalCondition().contains(EmotionalCondition.CALM) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }

    @Test
    public void becomeHappyFromHappy() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.becomeHappy();
        human.becomeHappy();
        assertTrue("become happy from happy",
                human.getEmotionalCondition().contains(EmotionalCondition.CALM) && human.getEmotionalCondition().contains(EmotionalCondition.HAPPY) &&
                        human.getEmotionalCondition().size() == 2 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }

    @Test
    public void shockedFromHappy() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.becomeHappy();
        human.shocked();
        assertTrue("shocked from happy",
                human.getEmotionalCondition().contains(EmotionalCondition.SURPRISED) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.CONCENTRATED);
    }

    @Test
    public void becomeNervousFromHappy() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.becomeHappy();
        human.becomeNervous();
        assertTrue("become nervous from happy",
                human.getEmotionalCondition().contains(EmotionalCondition.NERVOUS) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }


    @Test
    public void calmDownFromShocked() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.shocked();
        human.calmDown();
        assertTrue("calm down from surprised",
                human.getEmotionalCondition().contains(EmotionalCondition.CALM) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }

    @Test
    public void becomeHappyFromShocked() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.shocked();
        human.becomeHappy();
        assertTrue("become happy from surprised",
                human.getEmotionalCondition().contains(EmotionalCondition.CALM) && human.getEmotionalCondition().contains(EmotionalCondition.HAPPY) &&
                        human.getEmotionalCondition().size() == 2 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }

    @Test
    public void shockedFromShocked() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.shocked();
        human.shocked();
        assertTrue("shocked from surprised",
                human.getEmotionalCondition().contains(EmotionalCondition.JAW_DROPPED) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.CONCENTRATED);
    }

    @Test
    public void becomeNervousFromShocked() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.shocked();
        human.becomeNervous();
        assertTrue("become nervous from surprised",
                human.getEmotionalCondition().contains(EmotionalCondition.NERVOUS) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }


    @Test
    public void calmDownFromRawDropped() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.shocked();
        human.shocked();
        human.calmDown();
        assertTrue("calm down from raw-dropped",
                human.getEmotionalCondition().contains(EmotionalCondition.CALM) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }

    @Test
    public void becomeHappyFromRawDropped() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.shocked();
        human.shocked();
        human.becomeHappy();
        assertTrue("become happy from raw-dropped",
                human.getEmotionalCondition().contains(EmotionalCondition.CALM) && human.getEmotionalCondition().contains(EmotionalCondition.HAPPY) &&
                        human.getEmotionalCondition().size() == 2 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }

    @Test
    public void shockedFromRawDropped() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.shocked();
        human.shocked();
        human.shocked();
        assertTrue("shocked from raw-dropped",
                human.getEmotionalCondition().contains(EmotionalCondition.JAW_DROPPED) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.CONCENTRATED);
    }

    @Test
    public void becomeNervousFromRawDropped() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        human.shocked();
        human.shocked();
        human.becomeNervous();
        assertTrue("become nervous from raw-dropped",
                human.getEmotionalCondition().contains(EmotionalCondition.NERVOUS) && human.getEmotionalCondition().size() == 1 &&
                        human.getAttention() == HumanAttention.ABSENT_MINDED);
    }

}
