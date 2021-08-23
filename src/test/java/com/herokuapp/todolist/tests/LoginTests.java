package com.herokuapp.todolist.tests;

import com.herokuapp.todolist.annotations.JiraIssue;
import com.herokuapp.todolist.annotations.JiraIssues;
import com.herokuapp.todolist.annotations.Layer;
import com.herokuapp.todolist.mocks.LoginMocks;
import com.herokuapp.todolist.specs.Specs;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

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
    void unSuccessfulLoginUserNotFound() {
        final String[] response = new String[1];
        step("Sent POST request with unregistered user:" + loginMocks.unregisteredUser(), () -> {
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

    @Test
    @Story("Registration")
    @JiraIssues({@JiraIssue("EM-201")})
    @Tags({@Tag("api"), @Tag("regress")})
    @DisplayName("The password cannot be shorter than the minimum length allowed (7)")
    void unSuccessfulRegistrationShortPass() {
        final String[] response = new String[1];
        step("Sent POST request with new user with short password:" + loginMocks.newUserWithShortPassword(), () -> {
            response[0] =
                    Specs.userRequestSpec
                            .given()
                            .contentType(JSON)
                            .body(loginMocks.newUserWithShortPassword())
                            .when()
                            .post("/register")
                            .then()
                            .statusCode(400).extract().response().asString();
        });

        step("Check that the message \"Password is shorter than the minimum allowed length\" has been returned.", () -> {
            assertEquals("\"User validation failed: password: Path `password` (`42`) is shorter than the minimum allowed length (7).\"", response[0]);
        });
    }

    @Test
    @Story("Registration")
    @JiraIssues({@JiraIssue("EM-202")})
    @Tags({@Tag("api"), @Tag("regress")})
    @DisplayName("Successful registration")
    @Disabled("Not finished")
    void successfulRegistration() {
        final String[] response = new String[1];
        step("Sent POST request with new user:" + loginMocks.newUser(), () -> {
            response[0] =
                    Specs.userRequestSpec
                            .given()
                            .contentType(JSON)
                            .body(loginMocks.newUser())
                            .log().body()
                            .when()
                            .post("/register")
                            .then()
                            .statusCode(400)
                            .log().body().extract().response().asString();
        });

//        step("Check that the message \"Password is shorter than the minimum allowed length\" has been returned.", () -> {
//            assertEquals("\"User validation failed: password: Path `password` (`42`) is shorter than the minimum allowed length (7).\"", response[0]);
//        });
    }
}
