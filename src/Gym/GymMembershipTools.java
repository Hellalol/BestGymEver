package Gym;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GymMembershipTools {

    public static void executeProgram() throws IOException {
        Path textFileWithMembersInput = Paths.get("src\\Data\\Databas\\Databas.txt");
        Scanner inputGymInfo = new Scanner(textFileWithMembersInput);

        List<Member> listOfMembers = GymMembershipTools.scannerInputToArrayList(inputGymInfo);
        GymMembershipTools.checkMembershipStatusWholeList(listOfMembers);

    }

    public static List<Member> scannerInputToArrayList(Scanner inputGymInfo) {
        List<Member> memberList = new ArrayList<>();
        while (inputGymInfo.hasNext()) {
            memberList.add(new Member(inputGymInfo.next().replace(",", "").trim(),
                    inputGymInfo.next(), inputGymInfo.next(), LocalDate.parse(inputGymInfo.next())));
        }
        return memberList;
    }

    public static boolean compareFullName(Member member, String userInput) {
        return userInput.trim().replace(" ","").equalsIgnoreCase(member.getFullName());
    }

    public static boolean compareSocialSecurityNumber(Member member, String userInput) {
        return userInput.replace("-","").replace(" ","").equals
                (member.getSocialSecurityNumber().replace(" ",""));
    }

    public static void checkMembershipStatusWholeList(List<Member> listOfMembers) {

        LocalDate aYearFromNow = LocalDate.now().minusYears(1);
        String userInput = JOptionPane.showInputDialog(
                null,
                "Skriv namnet eller personnummret på kunden!",
                "MedlemsChecker2000",
                JOptionPane.QUESTION_MESSAGE);

        try {
            boolean memberFoundInList = false;
            boolean emptyInputChecker = false;

            for (Member memberInList : listOfMembers) {
                if ((((GymMembershipTools.compareFullName(memberInList, userInput)) ||
                        GymMembershipTools.compareSocialSecurityNumber(memberInList, userInput)) &&
                        memberInList.getLastPayment().isAfter(aYearFromNow))) {

                    memberFoundInList = true;
                    JOptionPane.showMessageDialog(null,
                            memberInList.getValidMessage(),"Aktiv Medlem",
                            JOptionPane.INFORMATION_MESSAGE);

                    GymMembershipTools.printActiveMemberToLog(memberInList);
                    GymMembershipTools.printActiveMemberToSeparateLog(memberInList);

                } else if ((((GymMembershipTools.compareFullName(memberInList, userInput)) ||
                        GymMembershipTools.compareSocialSecurityNumber(memberInList, userInput))) &&
                        memberInList.getLastPayment().isBefore(aYearFromNow)) {

                    memberFoundInList = true;
                    JOptionPane.showMessageDialog(null,
                            memberInList.getNotValidMessage(),"Ej aktiv medlem",
                            JOptionPane.INFORMATION_MESSAGE);

                }else if(userInput.isEmpty()){
                    emptyInputChecker = true;
                    JOptionPane.showMessageDialog(null,"Inget skrevs i dialogrutan.\n" +
                            "Börjar om!");
                    break;
                }
            }
            if (!memberFoundInList && !emptyInputChecker) {
                JOptionPane.showMessageDialog(null, "Medlemmen finns inte i databasen.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Filen hittades inte!");

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Avslutar programmet");
            System.exit(0);
        }
    }

    public static void printActiveMemberToLog(Member member) throws IOException {

        String nameOfWorkoutLog = "Log-" + LocalDate.now().getYear();

        Path workOutLogOutPut = Paths.get("src\\Data\\WorkoutLog\\" + nameOfWorkoutLog + ".txt");
        try(PrintWriter outPutLog = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(String.valueOf(workOutLogOutPut), true)));) {
            outPutLog.println(member.getLogOutputAll());
        }
    }

    public static void printActiveMemberToSeparateLog(Member member) throws IOException {

        String nameOfWorkoutLog = member.getFullNameLastnameFirst();

        Path workOutLogOutPut = Paths.get("src\\Data\\ActiveMembers\\" + nameOfWorkoutLog + ".txt");
        try(PrintWriter outPutLog = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(String.valueOf(workOutLogOutPut), true)));){
            outPutLog.println(member.getOutputTimeDate());
        }
    }
}
