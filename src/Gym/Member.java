package Gym;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Member {

    private String socSecNum;
    private String memberFirstName;
    private String memberLastName;
    private LocalDate LastPayment;

    Member(String socSecNum, String memberFirstName, String memberLastName, LocalDate LastPayment) {
        this.socSecNum = socSecNum;
        this.memberFirstName = memberFirstName;
        this.memberLastName = memberLastName;
        this.LastPayment = LastPayment;
    }

    public long getLastPaymentInDays() {
        LocalDate then = LastPayment;
        LocalDate now = LocalDate.now();
        Duration difference = Duration.between(then.atStartOfDay(), now.atStartOfDay());
        return difference.toDays();
    }

    public String getFullName() {
        return memberFirstName.concat(memberLastName);
    }

    public String getFullNameLastnameFirst() {
        return memberLastName.concat(memberFirstName);
    }

    public String getFullNameWithSpace() {
        return memberFirstName.concat(" ").concat(memberLastName);
    }

    public String getSocialSecurityNumberCorrectFormat() {
        return socSecNum.substring(0, 6) + "-" + socSecNum.substring(6, 10);
    }

    public String getValidMessage() {
        return "Namn: " + getFullNameWithSpace() +
                "\nMedlemsstatus: Aktiv medlem\nDagar sedan senaste betalning: " +
                getLastPaymentInDays();
    }

    public String getNotValidMessage() {
        return "Namn: " + getFullNameWithSpace() +
                "\nMedlemsstatus: Ej aktiv medlem.\nDagar sedan senaste betalning: " +
                getLastPaymentInDays();
    }

    public String getLogOutputAll() {
        return getFullNameWithSpace() + " " +
                getSocialSecurityNumberCorrectFormat() + "\nBesökte senast: " + LocalDate.now() + "\nKlockan: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n";
    }

    public String getOutputTimeDate() {
        return "Datum: " + LocalDate.now() + " Klockslag: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public String getSocialSecurityNumber() {
        return socSecNum;
    }

    public String getMemberFirstName() {
        return memberFirstName;
    }

    public String getMemberLastName() {
        return memberLastName;
    }

    public LocalDate getLastPayment() {
        return LastPayment;
    }
}
