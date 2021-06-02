package com.example.meetime.Service;

import com.example.meetime.Domain.Response.ResponseDto;
import com.example.meetime.Domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private List<User> usersOnMemory = new ArrayList<>();


    public ResponseDto create(User user) {
        List<String> errors = new ArrayList<>();

        if (user.getName() == null) {
            errors.add("Name cannot be null");
        }

        if (!isValidEmail(user.getEmail())) {
            errors.add("Email is invalid!");
        }

        if (!errors.isEmpty()) {
            return new ResponseDto(400L, errors);
        }

        usersOnMemory.add(user);
        return new ResponseDto(201L, user);
    }

    public static boolean isValidEmail(String email) {
        boolean isEmailValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailValid = true;
            }
        }
        return isEmailValid;
    }
}
