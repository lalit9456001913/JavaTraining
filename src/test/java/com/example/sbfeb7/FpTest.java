package com.example.sbfeb7;

import com.example.sbfeb7.lib.errors.fp.Error;
import com.example.sbfeb7.lib.errors.fp.Ok;
import com.example.sbfeb7.lib.errors.fp.Result;
import com.example.sbfeb7.resources.users.request.UserRequest;
import org.junit.jupiter.api.Test;

public class FpTest {
//    @Test
//    public void testFp() {
//        switch (foo(9).map(userRequest -> userRequest.getName() + " map1").map(s -> s + " map2")) {
//            case Error<String, String> error -> System.out.println("none" + error.value());
//            case Ok<String, String> success -> System.out.println("some " + success.value());
//        }
//        switch (foo(12)) {
//            case Error<UserRequest, String> e -> System.out.println("none " + e.value());
//            case Ok<UserRequest, String> s -> System.out.println("some " + s.value().getName());
//        }
//
//        Result<String, String> fooResult = foo(5)
//                .map(userRequest -> userRequest.getName() + " map1")
//                .map(s -> s + " map2")
//                .map(s -> s + " map3");
//        if (fooResult instanceof Ok<String, String> ok) {
//            System.out.println("some " + ok.value());
//        } else if (fooResult instanceof Error<String, String> error) {
//            System.out.println("none " + error.value());
//        }
//    }
//    public static Result<UserRequest, String> foo(int bar){
//        if(bar < 10) {
//            return new Ok<>(UserRequest.builder().name("hiren").build());
//        }
//        return new Error<>("err");
//    }
}
