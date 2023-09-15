package com.adls.beauty_brackets.service;

import org.springframework.stereotype.Service;
import com.adls.beauty_brackets.model.BracketsCheckerRs;
import com.adls.beauty_brackets.service.checker.TextCheckerProvider;
import com.adls.beauty_brackets.service.checker.impl.TextCheckerType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BracketsTextCheckerService {

    private final TextCheckerProvider checkerProvider;

    public BracketsCheckerRs check(String text) {
        final boolean result = checkerProvider.check(text, TextCheckerType.BRACKETS);

        return BracketsCheckerRs.builder().isCorrect(result).build();
    }

}
