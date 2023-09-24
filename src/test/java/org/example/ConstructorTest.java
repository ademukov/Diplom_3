package org.example;


import org.assertj.core.api.Assertions;
import org.example.base.BaseUiTest;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConstructorTest extends BaseUiTest {


    @Test
    public void navigateBurgerIngredientsTabs() {
        wait.until(ExpectedConditions.visibilityOf(mainPage.getTransientButton()));
        mainPage.selectSaucesTab();
        wait.until(ExpectedConditions.visibilityOf(mainPage.getSaucesTabTitle()));
        Assertions.assertThat("Соусы").isEqualTo(mainPage.getSaucesTabTitle().getText());

        mainPage.selectIngredientsTab();
        wait.until(ExpectedConditions.visibilityOf(mainPage.getIngredientsTabTitle()));
        Assertions.assertThat("Начинки").isEqualTo(mainPage.getIngredientsTabTitle().getText());

        mainPage.selectBunsTab();
        wait.until(ExpectedConditions.visibilityOf(mainPage.getBunsTabTitle()));
        Assertions.assertThat("Булки").isEqualTo(mainPage.getBunsTabTitle().getText());

    }

}