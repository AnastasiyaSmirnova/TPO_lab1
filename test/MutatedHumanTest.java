import domain.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class MutatedHumanTest {

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
        assertTrue("is head free after creation", human.isHeadFree(1));
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

    /**
     * add activity:
     * - simple addition
     * - null or empty addition
     * - prohibited activity - for each level!  - to test allowed action checking
     * - check addition to head ID (2 heads - dif activities)
     * - remove/add - unknown head number
     * - clear from empty list
     */

    @Test
    public void getActivitiesListOfUnknowmHead() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        assertNull("get activities list of unknown head", mutateHuman.getHeadActivities(4));
    }


    @Test
    public void isActivityListFirstlyEmpty() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        assertEquals("is activity list is empty", 0, mutateHuman.getHeadActivities(1).size());

    }

    @Test
    public void addSimpleActivity() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        String newActivityName = "control usual humans";
        mutateHuman.addNewActivity(1, newActivityName);
        assertTrue("simple activity addition", mutateHuman.getHeadActivities(1).contains(newActivityName) && !mutateHuman.isHeadFree(1));
    }


    @Test
    public void addSecondActivity() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        mutateHuman.addNewActivity(1, "control usual humans");
        int startActivityListSize = mutateHuman.getHeadActivities(1).size();

        mutateHuman.addNewActivity(1, "control usual humans");
        assertEquals("add the same activity twice", startActivityListSize, mutateHuman.getHeadActivities(1).size());
    }

    @Test
    public void addEmptyActivity() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        int startActivityListSize = mutateHuman.getHeadActivities(1).size();
        mutateHuman.addNewActivity(1, "");
        assertEquals("empty activity addition", startActivityListSize, mutateHuman.getHeadActivities(1).size());
    }

    @Test
    public void addNullActivity() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        int startActivityListSize = mutateHuman.getHeadActivities(1).size();
        mutateHuman.addNewActivity(1, null);
        assertEquals("null activity addition", startActivityListSize, mutateHuman.getHeadActivities(1).size());
    }

    @Test
    public void addToDifHeads() throws IncorrectNameException {
        MutateHuman human = new MutateHuman("Person", 2);
        human.addNewActivity(1, "head 1 act");
        human.addNewActivity(2, "head 2 act");
        assertTrue("add to diff heads",
                !human.getHeadActivities(1).contains("head 2 act") &&
                        !human.getHeadActivities(2).contains("head 1 act"));
    }

    @Test
    public void addProhibitedActivity0_1() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        int startActivityListSize = mutateHuman.getHeadActivities(1).size();
        mutateHuman.addNewActivity(1, "use control panel");
        assertEquals("prohibited activity addition: activity level 1 from 0-level human", startActivityListSize, mutateHuman.getHeadActivities(1).size());
    }

    @Test
    public void addProhibitedActivity1_2() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 2);
        int startActivityListSize = mutateHuman.getHeadActivities(1).size();
        mutateHuman.addNewActivity(1, "use the main computer");
        assertEquals("prohibited activity addition: activity level 2 from 1-level human", startActivityListSize, mutateHuman.getHeadActivities(1).size());
    }

    @Test
    public void addProhibitedActivity2_3() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 4);
        int startActivityListSize = mutateHuman.getHeadActivities(1).size();
        mutateHuman.addNewActivity(1, "absolute power");
        assertEquals("prohibited activity addition: activity level 2 from 1-level human", startActivityListSize, mutateHuman.getHeadActivities(1).size());
    }

    @Test
    public void addActivityToUnknownHead() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        assertFalse("add activity to unknown head", mutateHuman.addNewActivity(4, "activity"));
    }

    @Test
    public void simpleClearActivityList() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        mutateHuman.addNewActivity(1, "activity_1");
        mutateHuman.addNewActivity(1, "activity_2");
        mutateHuman.addNewActivity(1, "activity_3");
        mutateHuman.addNewActivity(1, "activity_4");
        mutateHuman.addNewActivity(1, "activity_5");
        mutateHuman.clearHeadActivityList(1);
        assertEquals("simple clear activities list", 0, mutateHuman.getHeadActivities(1).size());
    }

    @Test
    public void clearFromEmptyActivityList() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        mutateHuman.clearHeadActivityList(1);
        assertEquals("clear empty activity list", 0, mutateHuman.getHeadActivities(1).size());
    }

    @Test
    public void clearFromActivityListOfUnknownHead() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        mutateHuman.clearHeadActivityList(4);
        assertFalse("clear list of unknown head", mutateHuman.clearHeadActivityList(5));
    }

    @Test
    public void removeSimpleActivity() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        String activityName = "some activity";
        mutateHuman.addNewActivity(1, activityName);
        mutateHuman.removeActivity(1, activityName);
        assertTrue("remove simple activity", mutateHuman.getHeadActivities(1).size() == 0 && mutateHuman.isHeadFree(1));
    }

    @Test
    public void removeUnknownActivity() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        mutateHuman.addNewActivity(1, "activity 1");
        assertFalse("remove unknown activity", mutateHuman.removeActivity(1, "activity 2"));
    }

    @Test
    public void removeActivityFromEmptyList() throws IncorrectNameException {
        MutateHuman mutateHuman = new MutateHuman("Person", 1);
        assertFalse("remove unknown activity", mutateHuman.removeActivity(1, "activity 2"));
    }

}
