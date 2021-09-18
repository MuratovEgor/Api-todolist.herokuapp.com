package com.herokuapp.todolist.mocks;

public class LoginMocks {
    public String unregisteredUser() {
        return "{\"email\":\"unregistered@User.com\"," +
                "\"password\": \"0000\"}";
    }

    public String newUserWithShortPassword() {
        return "{\"name\": \"Harry Potter\"," +
                "\"email\": \"harry@potter.com\"," +
                "\"password\": \"42\"," +
                "\"age\": 14}";
    }

    public String testUser() {
        return "{\"name\": \"Harry Potter\"," +
                "\"email\": \"harry@potter.com\"," +
                "\"password\": \"123581321\"," +
                "\"age\": 14}";
    }

    public String testUserId() {
        return "6123476d4d744d00176a292b";
    }

    public String testUserEmail() {
        return "harry@potter.com";
    }

    public String testUserName() {
        return "Harry Potter";
    }
}
