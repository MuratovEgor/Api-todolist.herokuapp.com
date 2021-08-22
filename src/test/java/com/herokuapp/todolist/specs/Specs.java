package com.herokuapp.todolist.specs;

import com.herokuapp.todolist.config.Project;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;

public class Specs {
    public static RequestSpecification userRequestSpec = with()
            .baseUri(Project.config.baseUrl())
            .basePath("/user");

    public static RequestSpecification taskRequestSpec = with()
            .baseUri(Project.config.baseUrl())
            .basePath("/task");
}
