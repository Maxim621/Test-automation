package com.solvd.ui.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ElementsPage extends AbstractPage {

    @FindBy(xpath = "//span[text()='Text Box']")
    private ExtendedWebElement textBoxMenu;

    @FindBy(xpath = "//span[text()='Check Box']")
    private ExtendedWebElement checkBoxMenu;

    @FindBy(id = "userName")
    private ExtendedWebElement fullNameInput;

    @FindBy(id = "userEmail")
    private ExtendedWebElement emailInput;

    @FindBy(id = "submit")
    private ExtendedWebElement submitButton;

    @FindBy(id = "output")
    private ExtendedWebElement outputDiv;

    @FindBy(xpath = "//span[@class='rct-title']")
    private List<ExtendedWebElement> checkBoxOptions;

    @FindBy(xpath = "//input[@type='checkbox']")
    private List<ExtendedWebElement> checkBoxInputs;

    @FindBy(xpath = "//svg[@class='rct-icon rct-icon-check']")
    private List<ExtendedWebElement> checkedIcons;

    @FindBy(xpath = "//span[text()='Radio Button']")
    private ExtendedWebElement radioButtonMenu;

    @FindBy(xpath = "//label[contains(text(),'Yes')]")
    private ExtendedWebElement yesRadioButton;

    @FindBy(xpath = "//label[contains(text(),'Impressive')]")
    private ExtendedWebElement impressiveRadioButton;

    @FindBy(className = "text-success")
    private ExtendedWebElement radioButtonResult;

    public void clickRadioButtonMenu() {
        radioButtonMenu.click();
    }

    public void clickYesRadioButton() {
        yesRadioButton.click();
    }

    public void clickImpressiveRadioButton() {
        impressiveRadioButton.click();
    }

    public String getRadioButtonResult() {
        return radioButtonResult.getText();
    }

    public boolean isCheckBoxSelected(int index) {
        if (index < checkBoxInputs.size()) {
            return checkBoxInputs.get(index).getElement().isSelected();
        }
        return false;
    }

    public boolean isCheckBoxCheckedVisible(int index) {
        if (index < checkedIcons.size()) {
            return checkedIcons.get(index).isElementPresent();
        }
        return false;
    }

    public ElementsPage(WebDriver driver) {
        super(driver);
    }

    public void clickTextBoxMenu() {
        textBoxMenu.click();
    }

    public void clickCheckBoxMenu() {
        checkBoxMenu.click();
    }

    public void fillTextBoxForm(String fullName, String email) {
        fullNameInput.type(fullName);
        emailInput.type(email);
    }

    public void clickSubmit() {
        // Спочатку скроллимо до кнопки
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", submitButton.getElement());

        // Коротка пауза для стабільності
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Використовуємо JavaScript клік
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", submitButton.getElement());

        // Не чекаємо на outputDiv тут - це буде робити тест
    }

    public String getOutputText(int i) {
        return outputDiv.getText();
    }

    public void clickCheckBoxOption(int index) {
        if (index < checkBoxOptions.size()) {
            checkBoxOptions.get(index).click();
        }
    }
}