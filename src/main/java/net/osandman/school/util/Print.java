package net.osandman.school.util;

import java.util.List;

public final class Print {
    static final String RESET = "\033[0m";
    static final String GREEN = "\033[0;32m";
    static final String YELLOW = "\033[0;33m";

    public static <T> void printList(List<T> list) {
        int i = 1;
        for (T current : list) {
            System.out.println(i++ + ": " + current.toString());
        }
    }
}
