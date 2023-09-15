package com.adls.beauty_brackets.service.checker.impl;

import org.springframework.stereotype.Component;
import com.adls.beauty_brackets.service.checker.TextChecker;

@Component
public class BracketsTextChecker implements TextChecker {

    @Override
    public boolean check(String text) {
        if (text != null && 1 >= text.length()) {
            return false;
        }

        if (!isCorrectcBrackets(text)) {
            return false;
        }

        return isTextBetweenBrackets(text);
    }

    private boolean isTextBetweenBrackets(String text) {
        final char[] charArray = text.toCharArray();

        for (int index = 0; index < charArray.length; index++) {
            final char currentChar = charArray[index];

            if (currentChar == '(' || currentChar == ')' && charArray.length > index + 1) {
                final char nextChar = charArray[index + 1];
                if (currentChar == '(' && nextChar == ')') {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isCorrectcBrackets(String text) {

        final char[] charArray = text.toCharArray();
        int counter = 0;

        for (char currentChar : charArray) {
            if (currentChar == '(') {
                counter++;
            }
            if (currentChar == ')') {
                counter--;
            }
            if (counter < 0) {
                return false;
            }
        }

        return counter == 0;
    }

    @Override
    public TextCheckerType supportedTypeAs() {
        return TextCheckerType.BRACKETS;
    }

}
