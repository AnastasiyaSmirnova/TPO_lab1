import domain.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class DomainTest {

    /**
     * have all the necessary conditions been created?
     */
    @Test
    public void HumanAttentionConcentrated() {
        assertEquals("CONCENTRATED", HumanAttention.CONCENTRATED.name());
    }

    @Test
    public void HumanAttentionAbsentMinded() {
        assertEquals("ABSENT_MINDED", HumanAttention.ABSENT_MINDED.name());
    }

    @Test
    public void HumanPositionSit() {
        assertEquals("SIT", BodyPosition.SIT.name());
    }

    @Test
    public void HumanPositionGo() {
        assertEquals("GO", BodyPosition.GO.name());
    }

    @Test
    public void HumanPositionStand() {
        assertEquals("STAND", BodyPosition.STAND.name());
    }

    @Test
    public void EmotionalConditionNervous() {
        assertEquals("NERVOUS", EmotionalCondition.NERVOUS.name());
    }

    @Test
    public void EmotionalConditionSurprised() {
        assertEquals("SURPRISED", EmotionalCondition.SURPRISED.name());
    }

    @Test
    public void EmotionalConditionHappy() {
        assertEquals("HAPPY", EmotionalCondition.HAPPY.name());
    }

    @Test
    public void EmotionalConditionRowDropped() {
        assertEquals("JAW_DROPPED", EmotionalCondition.JAW_DROPPED.name());
    }

    @Test
    public void EmotionalConditionCalm() {
        assertEquals("CALM", EmotionalCondition.CALM.name());
    }

    /**
     * usual human tests
     */
    @Test(expected = IncorrectNameException.class)
    public void usualHumanEmptyName_1() throws IncorrectNameException {
        new UsualHuman("");
    }

    @Test(expected = IncorrectNameException.class)
    public void usualHumanNullName_1() throws IncorrectNameException {
        new UsualHuman(null);
    }

    @Test(expected = IncorrectNameException.class)
    public void usualHumanEmptyName_2() throws IncorrectNameException {
        new UsualHuman("", false);
    }

    @Test(expected = IncorrectNameException.class)
    public void usualHumanNullName_2() throws IncorrectNameException {
        new UsualHuman(null, false);
    }

    @Test
    public void correctUsualHumanNameCreation() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        assertEquals("Correct usual human name creation", "Person", human.getName());
    }

    @Test
    public void correctUsualHumanWithoutOpportunitiesCreation_1() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        assertFalse("Correct usual human without unique opportunities creation (no constructor params)", human.isUniqueOpportunities());
    }

    @Test
    public void correctUsualHumanWithoutOpportunitiesCreation_2() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person", false);
        assertFalse("Correct usual human without unique opportunities creation", human.isUniqueOpportunities());
    }

    @Test
    public void correctUsualHumanWithOpportunitiesCreation() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person", true);
        assertTrue("Correct usual human with unique opportunities creation", human.isUniqueOpportunities());
    }

    @Test
    public void correctUsualHumanStartBodyPosition_1() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        assertEquals("Correct usual human start body position (constructor 1)", BodyPosition.STAND, human.getBody().getPosition());
    }

    @Test
    public void correctUsualHumanStartBodyPosition_2() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        assertEquals("Correct usual human start body position (constructor 2)", BodyPosition.STAND, human.getBody().getPosition());
    }

    @Test
    public void correctUsualHumanStartAttention_1() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        assertEquals("Correct usual human start attention (constructor 1)", HumanAttention.ABSENT_MINDED, human.getAttention());
    }

    @Test
    public void correctUsualHumanStartAttention_2() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        assertEquals("Correct usual human start start attention (constructor 2)", HumanAttention.ABSENT_MINDED, human.getAttention());
    }

    @Test
    public void correctUsualHumanStartEmotionalCondition_1() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        assertTrue("Correct usual human start emotional condition (constructor 1)", human.getEmotionalCondition().contains(EmotionalCondition.NERVOUS) && human.getEmotionalCondition().size() == 1);
    }

    @Test
    public void correctUsualHumanStartEmotionalCondition_2() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        assertTrue("Correct usual human start emotional condition (constructor 2)", human.getEmotionalCondition().contains(EmotionalCondition.NERVOUS) && human.getEmotionalCondition().size() == 1);
    }

    @Test
    public void startStrangeThingsNumber_1() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        assertEquals("start strange things number: without opportunities - constructor 1", 0, human.getThingsNumber());
    }

    @Test
    public void startStrangeThingsNumber_2() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person", false);
        assertEquals("start strange things number: without opportunities - constructor 2", 0, human.getThingsNumber());
    }

    @Test
    public void startStrangeThingsNumber_3() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person", true);
        assertEquals("start strange things number: with opportunities", 0, human.getThingsNumber());
    }

    @Test
    public void increasingThingsByUsualHumanWithoutOpportunities() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person");
        int startThingsNumber = human.getThingsNumber();
        human.increaseThings();
        int resultThingsNumber = human.getThingsNumber();
        assertEquals("increasing things number by usual person without unique opportunities", startThingsNumber, resultThingsNumber);
    }

    @Test
    public void increasingThingsByUsualHumanWithOpportunities() throws IncorrectNameException {
        UsualHuman human = new UsualHuman("Person", true);
        int startThingsNumber = human.getThingsNumber();
        human.increaseThings();
        int resultThingsNumber = human.getThingsNumber();
        assertEquals("increasing things number by usual person without unique opportunities", 100, resultThingsNumber - startThingsNumber);
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

    /**
     * mutated human tests
     */
    @Test(expected = IncorrectNameException.class)
    public void mutatedHumanEmptyName() throws IncorrectNameException {
        new MutateHuman("", 1);
    }

    @Test(expected = IncorrectNameException.class)
    public void mutatedHumanNullName() throws IncorrectNameException {
        new MutateHuman(null, 1);
    }

    @Test
    public void mutatedHumanStartBodyPosition() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 1);
        assertEquals("start value of body position for mutated human", BodyPosition.SIT, human.getBody().getPosition());
    }

    @Test
    public void mutatedHumanStartAttention() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 1);
        assertEquals("start value of attention for mutated human", HumanAttention.ABSENT_MINDED, human.getAttention());
    }

    @Test
    public void mutatedHumanEmotionalCondition() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 1);
        assertTrue("start value of emotional condition for mutated human", human.getEmotionalCondition().contains(EmotionalCondition.HAPPY) && human.getEmotionalCondition().size() == 1);
    }

    @Test
    public void NumberOfHeadSetting__negative() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", -1);
        assertEquals("start number of heads - negative argument", 1, human.getBody().getNumberOfHeads());
    }

    @Test
    public void NumberOfHeadSetting() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 5);
        assertEquals("start number of heads", 5, human.getBody().getNumberOfHeads());
    }

    @Test
    public void isHeadFreeAfterCreation() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 1);
        assertTrue("is head free after creation", human.getBody().isHeadFree(1));
    }

    @Test
    public void addNewHead() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 1);
        human.addNewHead();
        assertEquals("add new head", 2, human.getBody().getNumberOfHeads());
    }

    @Test
    public void removeSingleHead() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 1);
        human.removeHead(1);
        assertEquals("remove single head", 1, human.getBody().getNumberOfHeads());
    }

    @Test
    public void removeHead() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 2);
        human.removeHead(1);
        assertEquals("remove one of 2 heads", 1, human.getBody().getNumberOfHeads());
    }

    /**
     * level = log2(head_number); but there are only 4 levels: 0, 1, 2, 3
     * head number is always more that 0 -> control points: 1, 2, 4, 8, 16 and 3, 7, 10
     */
    @Test
    public void mutatedHumanLevel_0() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 1);
        assertTrue("1 heads - 0 level", human.getAllowedActions().contains("control usual humans") && human.getAllowedActions().size() == 1);
    }

    @Test
    public void mutatedHumanLevel_1_2() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 2);
        assertTrue("2 heads - 1 level",
                human.getAllowedActions().contains("control usual humans") && human.getAllowedActions().contains("use control panel") &&
                        human.getAllowedActions().size() == 2);
    }

    @Test
    public void mutatedHumanLevel_1_3() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 3);
        assertTrue("3 heads - 1 level",
                human.getAllowedActions().contains("control usual humans") && human.getAllowedActions().contains("use control panel") &&
                        human.getAllowedActions().size() == 2);
    }

    @Test
    public void mutatedHumanLevel_2_4() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 4);
        assertTrue("4 heads - 2 level",
                human.getAllowedActions().contains("control usual humans") &&
                        human.getAllowedActions().contains("use control panel") &&
                        human.getAllowedActions().contains("use the main computer") &&
                        human.getAllowedActions().size() == 3);
    }

    @Test
    public void mutatedHumanLevel_2_7() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 7);
        assertTrue("7 heads - 2 level",
                human.getAllowedActions().contains("control usual humans") &&
                        human.getAllowedActions().contains("use control panel") &&
                        human.getAllowedActions().contains("use the main computer") &&
                        human.getAllowedActions().size() == 3);
    }

    @Test
    public void mutatedHumanLevel_3_8() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 8);
        assertTrue("8 heads - 3 level",
                human.getAllowedActions().contains("control usual humans") &&
                        human.getAllowedActions().contains("use control panel") &&
                        human.getAllowedActions().contains("use the main computer") &&
                        human.getAllowedActions().contains("absolute power") &&
                        human.getAllowedActions().size() == 4);
    }

    @Test
    public void mutatedHumanLevel_3_8_addHead() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 7);
        human.addNewHead();
        assertTrue("8 heads - 3 level",
                human.getAllowedActions().contains("control usual humans") &&
                        human.getAllowedActions().contains("use control panel") &&
                        human.getAllowedActions().contains("use the main computer") &&
                        human.getAllowedActions().contains("absolute power") &&
                        human.getAllowedActions().size() == 4);
    }

    @Test
    public void mutatedHumanLevel_3_10_addHead() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 10);
        assertTrue("10 heads - 3 level",
                human.getAllowedActions().contains("control usual humans") &&
                        human.getAllowedActions().contains("use control panel") &&
                        human.getAllowedActions().contains("use the main computer") &&
                        human.getAllowedActions().contains("absolute power") &&
                        human.getAllowedActions().size() == 4);
    }

    @Test
    public void mutatedHumanLevel_3_16() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 16);
        assertTrue("16 heads  > 3 level",
                human.getAllowedActions().contains("control usual humans") &&
                        human.getAllowedActions().contains("use control panel") &&
                        human.getAllowedActions().contains("use the main computer") &&
                        human.getAllowedActions().contains("absolute power") &&
                        human.getAllowedActions().size() == 4);
    }
//    todo: check add new activity & remove activity
}
