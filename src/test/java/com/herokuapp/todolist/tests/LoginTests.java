package com.herokuapp.todolist.tests;

import com.herokuapp.todolist.annotations.JiraIssue;
import com.herokuapp.todolist.annotations.JiraIssues;
import com.herokuapp.todolist.annotations.Layer;
import com.herokuapp.todolist.mocks.LoginMocks;
import com.herokuapp.todolist.specs.Specs;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

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
        String response =
                Specs.userRequestSpec
                        .given()
                        .contentType(JSON)
                        .body(loginMocks.unregisteredUser())
                        .when()
                        .post("/login")
                        .then()
                        .statusCode(400).extract().response().asString();

        assertEquals("\"Unable to login\"", response);
    }

}
