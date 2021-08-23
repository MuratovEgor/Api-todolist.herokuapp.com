package com.herokuapp.todolist.mocks;

public class LoginMocks {
    public String unregisteredUser() {
        return "{\n" +
                "    \"email\": \"unregistered@User.com\",\n" +
                "    \"password\": \"0000\"\n" +
                "}";
    }

    public String newUserWithShortPassword() {
        return "{\n" +
                "\t\"name\": \"Harry Potter\",\n" +
                "\t\"email\": \"harry@potter.com\",\n" +
                "\t\"password\": \"42\",\n" +
                "\t\"age\": 14\n" +
                "}";
    }

    public String testUser() {
        return "{\n" +
                "\t\"name\": \"Harry Potter\",\n" +
                "\t\"email\": \"harry@potter.com\",\n" +
                "\t\"password\": \"123581321\",\n" +
                "\t\"age\": 14\n" +
                "}";
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
