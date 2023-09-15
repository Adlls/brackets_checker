package com.adls.beauty_brackets.service.checker;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.adls.beauty_brackets.service.checker.impl.BracketsTextChecker;
import com.adls.beauty_brackets.service.checker.impl.TextCheckerType;

public class TextCheckerProviderTest {

    @Mock
    private BracketsTextChecker bracketsTextChecker;

    @InjectMocks
    private TextCheckerProvider textCheckerProvider;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void check_happyPath_verifyInvocation() {
        final String text = "something";
        textCheckerProvider.register(TextCheckerType.BRACKETS, bracketsTextChecker);

        textCheckerProvider.check(text, TextCheckerType.BRACKETS);

        Mockito.verify(bracketsTextChecker).check(text);
    }

    @Test 
    void check_unsupportedCheckerType_unsupportedOperationException() {
        final String text = "something";

        assertThrows(UnsupportedOperationException.class, () -> textCheckerProvider.check(text, TextCheckerType.BRACKETS));
    }
}
