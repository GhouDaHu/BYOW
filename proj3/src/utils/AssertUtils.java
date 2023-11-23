package utils;

import java.util.Objects;

public class AssertUtils {
    public static void notNull(Object object, String msg) {
        if (Objects.isNull(object)) {
            System.err.println(msg);
        }
    }
}
