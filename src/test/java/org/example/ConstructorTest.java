package org.example;


import io.qameta.allure.junit4.DisplayName;
import org.example.base.BaseUiTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConstructorTest extends BaseUiTest {


    @Test
    @DisplayName("Переход к разделу Соусы")
    public void navigateToSaucesTab() {
        String currentTabClassValue = "tab_tab_type_current";
        wait.until(ExpectedConditions.visibilityOf(mainPage.getTransientButton()));
        mainPage.selectSaucesTab();
        Assert.assertTrue("Текущий раздел не Соусы", mainPage.getSaucesSpan().getAttribute("class").contains(currentTabClassValue));
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void navigateToIngredientsTab() {
        String currentTabClassValue = "tab_tab_type_current";
        wait.until(ExpectedConditions.visibilityOf(mainPage.getTransientButton()));
        mainPage.selectIngredientsTab();
        Assert.assertTrue("Текущий раздел не Начинки", mainPage.getIngredientsSpan().getAttribute("class").contains(currentTabClassValue));
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void navigateToBunsTab() throws InterruptedException {
        String currentTabClassValue = "tab_tab_type_current";
        wait.until(ExpectedConditions.visibilityOf(mainPage.getTransientButton()));
        Assert.assertTrue("Текущий раздел не Булки", mainPage.getBunsSpan().getAttribute("class").contains(currentTabClassValue));
    }


}