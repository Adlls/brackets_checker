package com.adls.beauty_brackets.service;

import com.adls.beauty_brackets.service.checker.TextCheckerProvider;
import com.adls.beauty_brackets.service.checker.impl.TextCheckerType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BracketsTextCheckerServiceTest {

    @Mock
    private TextCheckerProvider checkerProvider;

    @InjectMocks
    private BracketsTextCheckerService bracketsCheckerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void check_happyPath_verifyInvocation() {
        final String someText = "someRqText";

        bracketsCheckerService.check(someText);

        Mockito.verify(checkerProvider).check(someText, TextCheckerType.BRACKETS);
    }

}
