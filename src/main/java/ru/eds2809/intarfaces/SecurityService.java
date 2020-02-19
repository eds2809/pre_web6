package ru.eds2809.intarfaces;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
