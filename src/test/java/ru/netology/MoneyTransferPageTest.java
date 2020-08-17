package ru.netology;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferPageTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:7777");
    }

    @Test
    void shouldMoneyTransferFromFirstCard() {
        int amount = 200 + (int) (Math.random() * 5000);
        val loginPage = new LoginPage();
        val authorizationInfo = DataHelper.getAuthorizationInfo();
        val verificationPage = loginPage.validLogin(authorizationInfo);
        val verificationCode = DataHelper.getVerificationCode(authorizationInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val cardBalanceFirst = dashboard.getFirstCardBalance();
        val cardBalanceSecond = dashboard.getSecondCardBalance();
        val cardInfo = DataHelper.Card.getFirstCardBalance();
        val transferMoney = dashboard.secondCardDepositClick();
        transferMoney.transfer(cardInfo, amount);
        val cardBalanceAfterSendFirst = DataHelper.Card.cardBalanceAfterTransferMoney(cardBalanceFirst, amount);
        val cardBalanceAfterSendSecond = DataHelper.Card.cardBalanceAfterGetMoney(cardBalanceSecond, amount);
        assertEquals(cardBalanceAfterSendFirst, dashboard.getFirstCardBalance());
        assertEquals(cardBalanceAfterSendSecond, dashboard.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyFromSecondCard() {
        int amount = 200 + (int) (Math.random() * 5000);
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthorizationInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val cardBalanceFirst = dashboard.getFirstCardBalance();
        val cardBalanceSecond = dashboard.getSecondCardBalance();
        val cardInfo = DataHelper.Card.getSecondCardBalance();
        val transferMoney = dashboard.firstCardDepositClick();
        transferMoney.transfer(cardInfo, amount);
        val cardBalanceAfterSendFirst = DataHelper.Card.cardBalanceAfterGetMoney(cardBalanceFirst, amount);
        val cardBalanceAfterSendSecond = DataHelper.Card.cardBalanceAfterTransferMoney(cardBalanceSecond, amount);
        assertEquals(cardBalanceAfterSendFirst, dashboard.getFirstCardBalance());
        assertEquals(cardBalanceAfterSendSecond, dashboard.getSecondCardBalance());
    }


    @Test
    void shouldReturnToDashboardPageIfClickCancel() {
        int amount = 200 + (int) (Math.random() * 5000);
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthorizationInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val cardInfo = DataHelper.Card.getFirstCardBalance();
        val transferMoney = dashboard.firstCardDepositClick();
        transferMoney.transferCancel(cardInfo, amount);
        dashboard.displayDashboardPageMessage();
    }

    @Test
    void shouldReturnWarningMessageIfTransferMoreMoney() {
        int amount = 15000;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthorizationInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val cardInfo = DataHelper.Card.getSecondCardBalance();
        val transferMoney = dashboard.firstCardDepositClick();
        transferMoney.transfer(cardInfo, amount);
        transferMoney.showWarningMessage();
    }
}
