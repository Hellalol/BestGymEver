package Gym;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void firstnameAndLastNameIsInTheCorrectFormat() throws IOException {

        Path textFileWithCorrectMembersInput = Paths.get("Test\\DataTest\\DataBaseTestCorrect");
        Scanner inputGymInfo = new Scanner(textFileWithCorrectMembersInput);
        List<Member> memberListTest = GymMembershipTools.scannerInputToArrayList(inputGymInfo);

        assertNotNull(memberListTest.get(0).getFullName());
        assertEquals(memberListTest.get(0).getFullName(), "AlhambraAromes");

        assertNotNull(memberListTest.get(0).getFullNameWithSpace());
        assertEquals(memberListTest.get(0).getFullNameWithSpace(), "Alhambra Aromes");

        assertNotNull(memberListTest.get(0).getFullNameLastnameFirst());
        assertEquals(memberListTest.get(0).getFullNameLastnameFirst(), "AromesAlhambra");

    }

    @Test
    void socialSecurityNumberIsInTheCorrectFormat() throws IOException {

        Path textFileWithCorrectMembersInput = Paths.get("Test\\DataTest\\DataBaseTestCorrect");
        Scanner inputGymInfo = new Scanner(textFileWithCorrectMembersInput);
        List<Member> memberListTest = GymMembershipTools.scannerInputToArrayList(inputGymInfo);

        assertNotNull(memberListTest.get(0).getSocialSecurityNumber());
        assertEquals(memberListTest.get(0).getSocialSecurityNumber(), "7603021234");

        assertNotNull(memberListTest.get(0).getSocialSecurityNumberCorrectFormat());
        assertEquals(memberListTest.get(0).getSocialSecurityNumberCorrectFormat(), "760302-1234");



    }
}