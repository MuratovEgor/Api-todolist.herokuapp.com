package com.herokuapp.todolist.mocks;

public class LoginMocks {
    public String unregisteredUser() {
        return  "{\n" +
                "    \"email\": \"unregistered@User.com\",\n" +
                "    \"password\": \"0000\"\n" +
                "}";
    }

    public String newUserWithShortPassword() {
        return  "{\n" +
                "\t\"name\": \"Harry Potter\",\n" +
                "\t\"email\": \"harry@potter.com\",\n" +
                "\t\"password\": \"42\",\n" +
                "\t\"age\": 14\n" +
                "}";
    }

    public String newUser() {
        return  "{\n" +
                "\t\"name\": \"Muhammad Nur Ali\",\n" +
                "\t\"email\": \"muh.nurali43@gmail.com\",\n" +
                "\t\"password\": \"12345678\",\n" +
                "\t\"age\": 20\n" +
                "}";
    }
}
