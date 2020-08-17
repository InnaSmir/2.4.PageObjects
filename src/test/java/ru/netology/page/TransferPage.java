package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromCardInput = $("[data-test-id='from'] input");
    private SelenideElement toCardInput = $("[data-test-id='to'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");


    public void transfer(DataHelper.Card card, int amount) {
        amountInput.setValue(String.valueOf(amount));
        fromCardInput.setValue(card.getNumber());
        transferButton.click();
    }

    public void transferCancel(DataHelper.Card card, int amount) {
        amountInput.setValue(String.valueOf(amount));
        fromCardInput.setValue(card.getNumber());
        cancelButton.click();
    }

    public SelenideElement showWarningMessage() {
        return $(withText("У вас недостаточно средств для перевода такой суммы")).shouldBe(Condition.visible);
    }
}
