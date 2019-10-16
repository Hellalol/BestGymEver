package Gym;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


class GymMembershipToolsTest {

    @Test
    void isDataIsInTheRightFormat() throws IOException {

        Path textFileWithCorrectMembersInput = Paths.get("Test\\DataTest\\DataBaseTestCorrect");
        Scanner inputGymInfo = new Scanner(textFileWithCorrectMembersInput);
        List<Member> memberListTestCorrect = GymMembershipTools.scannerInputToArrayList(inputGymInfo);

        assertEquals(memberListTestCorrect.size(),3);
        assertEquals(memberListTestCorrect.get(0).getMemberFirstName(),"Alhambra");
        assertEquals(memberListTestCorrect.get(0).getMemberLastName(),"Aromes");
        assertEquals(memberListTestCorrect.get(0).getSocialSecurityNumber(),"7603021234");
        assertNotEquals(memberListTestCorrect.get(0).getSocialSecurityNumber(),"7603021234,");
        assertEquals(memberListTestCorrect.get(0).getLastPayment(),LocalDate.parse("2018-07-01"));
        assertNotNull(memberListTestCorrect.get(0).getLastPayment());

    }


    @Test
    void isDataInAnIncorrectFormat() throws IOException {

        Path textFileWitIncorrectMembersInput = Paths.get("Test\\DataTest\\DatabasTestNotCorrect.txt");
        Scanner inputGymInfo2 = new Scanner(textFileWitIncorrectMembersInput);
        List<Member> memberListTestInCorrect = GymMembershipTools.scannerInputToArrayList(inputGymInfo2);

        assertNotEquals(memberListTestInCorrect.size(),3);
        assertNotEquals(memberListTestInCorrect.get(0).getMemberFirstName(),"Alhambra");
        assertNotEquals(memberListTestInCorrect.get(0).getMemberLastName(),"Aromes");
        assertNotEquals(memberListTestInCorrect.get(0).getSocialSecurityNumber(),"7603021234");

    }

    @Test
    void testMemberNameInPutJOptionPane() throws IOException {

        Path textFileWithCorrectMembersInput = Paths.get("Test\\DataTest\\DataBaseTestCorrect");
        Scanner inputGymInfo = new Scanner(textFileWithCorrectMembersInput);
        List<Member> memberListTestCorrect = GymMembershipTools.scannerInputToArrayList(inputGymInfo);

        //Mocking values from String to replace JOptionPane
        String testNameInput1 = "Alhambra Aromes";
        String testNameInput2 = "AlhambraAromes";
        String testNameInput3 = "";
        String testNameInput4 = "d1df";

        assertTrue(GymMembershipTools.compareFullName(memberListTestCorrect.get(0), testNameInput1));
        assertTrue(GymMembershipTools.compareFullName(memberListTestCorrect.get(0), testNameInput2));
        assertFalse(GymMembershipTools.compareFullName(memberListTestCorrect.get(0), testNameInput3));
        assertFalse(GymMembershipTools.compareFullName(memberListTestCorrect.get(0), testNameInput4));
    }

    @Test
    void testMemberSocialSecurityNumberInPutJOptionPane() throws IOException {

        Path textFileWithCorrectMembersInput = Paths.get("Test\\DataTest\\DataBaseTestCorrect");
        Scanner inputGymInfo = new Scanner(textFileWithCorrectMembersInput);
        List<Member> memberListTestCorrect = GymMembershipTools.scannerInputToArrayList(inputGymInfo);

        //Mocking values from String to replace JOptionPane
        String testSocialSecurityNumberInput1 = "7603021234";
        String testSocialSecurityNumberInput2 = "760302-1234";
        String testSocialSecurityNumberInput3 = "7    60 30 212 34";
        String testSocialSecurityNumberInput4 = "0";

        assertTrue(GymMembershipTools.compareSocialSecurityNumber(memberListTestCorrect.get(0),
                testSocialSecurityNumberInput1));
        assertTrue(GymMembershipTools.compareSocialSecurityNumber(memberListTestCorrect.get(0),
                testSocialSecurityNumberInput2));
        assertTrue(GymMembershipTools.compareSocialSecurityNumber(memberListTestCorrect.get(0),
                testSocialSecurityNumberInput3));
        assertFalse(GymMembershipTools.compareSocialSecurityNumber(memberListTestCorrect.get(0),
                testSocialSecurityNumberInput4));
    }



}