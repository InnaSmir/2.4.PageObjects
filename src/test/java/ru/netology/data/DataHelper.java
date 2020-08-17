package ru.netology.data;

import lombok.Value;
import lombok.val;
import ru.netology.page.DashboardPage;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthorizationInfo {
        private String login;
        private String password;
    }

    public static AuthorizationInfo getAuthorizationInfo() {
        return new AuthorizationInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String verificationCode;
    }

    public static VerificationCode getVerificationCode(AuthorizationInfo authorizationInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class Card {
        private String number;
        private int balance;

        public static Card getFirstCardBalance() {
            val dashboard = new DashboardPage();
            return new Card("5559 0000 0000 0001", dashboard.getCardBalance("01"));
        }

        public static Card getSecondCardBalance() {
            val dashboard = new DashboardPage();
            return new Card("5559 0000 0000 0002", dashboard.getCardBalance("02"));
        }

        public static int cardBalanceAfterTransferMoney(int balance, int amount) {
            int total = balance - amount;
            return total;
        }

        public static int cardBalanceAfterGetMoney(int balance, int amount) {
            int total = balance + amount;
            return total;
        }
    }
}
