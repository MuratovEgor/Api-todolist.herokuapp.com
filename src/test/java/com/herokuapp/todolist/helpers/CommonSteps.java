package com.herokuapp.todolist.helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CommonSteps {
    @Step("Open page {url}")
    public void openPage(String url) {
        open(url);
    }


    @Step("Edit Cookie: {cookieName}: {cookieValue}")
    public void editCookie(String cookieName, String cookieValue) {
        openPage("");
        sleep(5000);
        Cookie cookie = getWebDriver().manage().getCookieNamed(cookieName);
        getWebDriver().manage().deleteCookie(cookie);
        getWebDriver().manage().addCookie(new Cookie(cookieName, cookieValue));
        refresh();
    }

    @Step("Set Cookie: {cookieName}: {cookieValue}")
    public void setCookie(String cookieName, String cookieValue) {
        openPage("");
        getWebDriver().manage().addCookie(new Cookie(cookieName, cookieValue));
        refresh();
    }
}
