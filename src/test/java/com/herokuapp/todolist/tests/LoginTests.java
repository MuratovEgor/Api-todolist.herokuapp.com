package com.herokuapp.todolist.tests;

import com.herokuapp.todolist.annotations.JiraIssue;
import com.herokuapp.todolist.annotations.JiraIssues;
import com.herokuapp.todolist.annotations.Layer;
import com.herokuapp.todolist.mocks.LoginMocks;
import com.herokuapp.todolist.specs.Specs;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Layer("Api")
@Owner("egormuratov")
public class LoginTests {

    LoginMocks loginMocks = new LoginMocks();

    @Test
    @Story("Login")
    @JiraIssues({@JiraIssue("HOM-233")})
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
        step("Check that the message \"Unable to log in\" has been returned.", () ->
                assertEquals("\"Unable to login\"", response[0])
        );
    }

    @Test
    @Story("Login")
    @JiraIssues({@JiraIssue("HOM-233")})
    @Tags({@Tag("api"), @Tag("regress")})
    @DisplayName("Successful Login")
    void successfulLogin() {
        final ValidatableResponse[] response1 = new ValidatableResponse[1];

        step("Send a POST request to log in as user:" + loginMocks.testUser(), () -> {
            response1[0] = Specs.userRequestSpec
                    .given()
                    .contentType(JSON)
                    .body(loginMocks.testUser())
                    .when()
                    .post("/login")
                    .then()
                    .statusCode(200);
        });
        step("Check user id" + loginMocks.testUserId(), () -> {
            response1[0].body("user._id", is(loginMocks.testUserId()));
        });
        step("Check user email" + loginMocks.testUserEmail(), () -> {
            response1[0].body("user.email", is(loginMocks.testUserEmail()));
        });
        step("Check user name" + loginMocks.testUserName(), () -> {
            response1[0].body("user.name", is(loginMocks.testUserName()));
        });
    }

    @Test
    @Story("Login")
    @JiraIssues({@JiraIssue("HOM-233")})
    @Tags({@Tag("api"), @Tag("regress")})
    @DisplayName("Successful Logout")
    void successfulLogout() {
        final ValidatableResponse[] loginResponse = new ValidatableResponse[1];
        final ValidatableResponse[] logoutResponse = new ValidatableResponse[1];

        step("Send a POST request to log in as user:" + loginMocks.testUser(), () -> {
            loginResponse[0] = Specs.userRequestSpec
                    .given()
                    .contentType(JSON)
                    .body(loginMocks.testUser())
                    .when()
                    .post("/login")
                    .then()
                    .statusCode(200);
        });
        step("Send a POST request to logout", () -> {
            logoutResponse[0] = Specs.userRequestSpec
                    .given()
                    .when()
                    .headers("Authorization",
                            loginResponse[0].extract().body().path("token").toString())
                    .post("/logout")
                    .then()
                    .statusCode(200);
        });
        step("Check body have an object: {'success': true}", () -> {
            logoutResponse[0].body("success", is(true));
        });
    }

    @Test
    @Story("Registration")
    @JiraIssues({@JiraIssue("HOM-233")})
    @Tags({@Tag("api"), @Tag("regress")})
    @DisplayName("The password cannot be shorter than the minimum length allowed (7)")
    void unSuccessfulRegistrationShortPass() {
        final String[] response = new String[1];
        step("Sent POST request with new user with short password:"
                + loginMocks.newUserWithShortPassword(), () -> {
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
        step("Check that the message \"Password is shorter than the minimum allowed length\" has been returned.", () ->
                assertEquals("\"User validation failed: password: Path `password` (`42`) is shorter than the minimum allowed length (7).\"", response[0])
        );
    }
}