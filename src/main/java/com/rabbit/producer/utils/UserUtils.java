package com.rabbit.producer.utils;

import com.rabbit.producer.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserUtils {
    private static final Predicate<User> IS_ADULT = x -> x.getAge() >= 18;
    private static final Predicate<User> IS_YOUNG = x -> x.getAge() < 18;

    private UserUtils() {
    }

    public static List<User> filerYoungUsers(List<User> users) {
        return filerUsers(users, IS_YOUNG);
    }

    public static List<User> filerAdultUsers(List<User> users) {
        return filerUsers(users, IS_ADULT);
    }

    private static List<User> filerUsers(List<User> users, Predicate<User> predicate) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (predicate.test(user)) {
                result.add(user);
            }
        }
        return result;
    }
}
