package com.herokuapp.todolist.mocks;

public class LoginMocks {
    public String unregisteredUser() {
        String unregisteredUser = "{\n" +
                "    \"email\": \"unregistered@User.com\",\n" +
                "    \"password\": \"0000\"\n" +
                "}";
        return unregisteredUser;
    }
}
