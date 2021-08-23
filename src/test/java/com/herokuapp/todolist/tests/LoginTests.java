package com.herokuapp.todolist.tests;

import com.herokuapp.todolist.annotations.JiraIssue;
import com.herokuapp.todolist.annotations.JiraIssues;
import com.herokuapp.todolist.annotations.Layer;
import com.herokuapp.todolist.mocks.LoginMocks;
import com.herokuapp.todolist.specs.Specs;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Layer("Api")
@Owner("egormuratov")
public class LoginTests {

    LoginMocks loginMocks = new LoginMocks();

    @Test
    @Story("Login")
    @JiraIssues({@JiraIssue("EM-200")})
    @Tags({@Tag("api"), @Tag("regress")})
    @DisplayName("Show 'Unable to login' message if user is not found")
    void unSuccessfulLogin() {
        final String[] response = new String[1];
        step("Sent POST request with body:" + loginMocks.unregisteredUser(), () -> {
            response[0] =
                    Specs.userRequestSpec
                            .given()
                            .contentType(JSON)
                            .body(loginMocks.unregisteredUser())
                            .when()
                            .post("/login")
                            .then()
                            .statusCode(400).extract().response().asString();
        });

        step("Check that the message \"Unable to log in\" has been returned.", () -> {
            assertEquals("\"Unable to login\"", response[0]);
        });
    }

}
