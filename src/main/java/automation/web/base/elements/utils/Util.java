package automation.web.base.elements.utils;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<String> parse(String s) {
        boolean op = false;
        int balance = 0;
        StringBuilder sb = new StringBuilder();
        List<String> path = new ArrayList<>();
        for (int i = 0; i <= s.length(); ++i) {
            if (i == s.length() || s.charAt(i) == '-' && i + 1 < s.length() && s.charAt(i + 1) == '>') {
                if (sb.length() != 0) {
                    path.add(sb.toString());
                    sb.setLength(0);
                }
                op = true;
                balance = 0;
            } else if (op) {
                if (s.charAt(i) == '[') {
                    if (balance == 0) {
                        sb.append(s.charAt(i));
                        balance++;
                    }
                } else if (s.charAt(i) == ']') {
                    balance--;
                    if (balance == 0) sb.append(s.charAt(i));
                } else {
                    if (s.charAt(i) == '>') {
                        if ((i == 0 || s.charAt(i - 1) != '-')) sb.append(s.charAt(i));
                    } else {
                        sb.append(s.charAt(i));
                    }
                }
            }
        }
        return path;
    }
}
