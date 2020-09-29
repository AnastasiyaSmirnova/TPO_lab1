import domain.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UsualHumanTest {

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

}
