package com.adls.beauty_brackets.service.checker;

import org.springframework.beans.factory.annotation.Autowired;

import com.adls.beauty_brackets.service.checker.impl.TextCheckerType;

public interface TextChecker {
    boolean check(String text);

    TextCheckerType supportedTypeAs();

    @Autowired
    default void registerMyself(TextCheckerProvider checkerProvider) {
        checkerProvider.register(supportedTypeAs(), this);
    }

}
