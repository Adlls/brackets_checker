package com.adls.beauty_brackets.service.checker;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.adls.beauty_brackets.service.checker.impl.TextCheckerType;

@Component
public class TextCheckerProvider {
    private final Map<TextCheckerType, TextChecker> textCheckerByType = new HashMap<>();


    public void register(TextCheckerType checkerType, TextChecker textChecker) {
        textCheckerByType.put(checkerType, textChecker);
    }

    public boolean check(String text, TextCheckerType checkerType) {
       final TextChecker textChecker = textCheckerByType.get(checkerType);

       if (textChecker == null) {
            throw new UnsupportedOperationException(String.format("Chekcer type %s not supported", checkerType));
       }

       return textChecker.check(text);
    }
}
