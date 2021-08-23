package com.herokuapp.todolist.tests;

import com.herokuapp.todolist.mocks.LoginMocks;
import com.herokuapp.todolist.specs.Specs;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.Is.is;

public class TaskTests {
    static final ValidatableResponse[] loginResponse = new ValidatableResponse[1];
    static LoginMocks loginMocks = new LoginMocks();

    @BeforeAll
    static void getToken() {
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
    }

    @Test
    void AddTask() {
        final ValidatableResponse[] response = new ValidatableResponse[1];

        step("Send a POST request to add a new task with the request body: { \"description\": \"reading book\" }", () -> {
            response[0] = Specs.taskRequestSpec
                    .given()
                    .headers("Authorization",
                            loginResponse[0].extract().body().path("token").toString())
                    .contentType(JSON)
                    .body("{ \"description\": \"reading book\" }")
                    .when()
                    .post("")
                    .then()
                    .statusCode(201);
        });

        step("Check response status", () -> {
            assertThat(response[0].body("success", is(true)));
        });

        step("Check completed status", () -> {
            assertThat(response[0].body("data.completed", is(false)));
        });

        step("Check the task description", () -> {
            assertThat(response[0].body("data.description", is("reading book")));
        });

    }

    @Test
    void DeleteTaskById() {

        final String[] taskId = new String[1];
        step("Send a POST request to add a new task with the request body: { \"description\": \"reading book\" }", () -> {
            taskId[0] = Specs.taskRequestSpec
                    .given()
                    .headers("Authorization",
                            loginResponse[0].extract().body().path("token").toString())
                    .contentType(JSON)
                    .body("{ \"description\": \"This task needs to be deleted\" }")
                    .when()
                    .post("")
                    .then()
                    .statusCode(201).extract().body().path("data._id").toString();
        });


        final ValidatableResponse[] response = new ValidatableResponse[1];
        step("Send a DELETE request to delete the task with id:" + taskId[0], () -> {
            response[0] = Specs.taskRequestSpec
                    .given()
                    .headers("Authorization",
                            loginResponse[0].extract().body().path("token").toString())
                    .when()
                    .log().body()
                    .delete("/" + taskId[0])
                    .then()
                    .log().body()
                    .statusCode(200);
        });

        step("Check response status", () -> {
            assertThat(response[0].body("success", is(true)));
        });
    }
}
