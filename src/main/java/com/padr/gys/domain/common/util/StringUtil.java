package com.padr.gys.domain.common.util;

public class StringUtil {

    private static final char[] TURKISH_CHARS = new char[] { 0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7,
            0xC7, 0x11F,
            0x11E };

    private static final char[] ENGLISH_CHARS = new char[] { 'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g',
            'G' };

    public static String clearTurkishChars(String string) {
        String replacedString = string;

        for (int i = 0; i < TURKISH_CHARS.length; i++) {
            replacedString = replacedString.replaceAll(new String(new char[] { TURKISH_CHARS[i] }),
                    new String(new char[] { ENGLISH_CHARS[i] }));
        }
        return replacedString;
    }
}
