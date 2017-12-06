package org.terrasdepontevedra.petra.util.extensions;

public class StringExtensions {
    public static boolean isNullOrEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static String replace(String text, String oldString, String newString) {
        return text.replace(oldString, newString);
    }
}
