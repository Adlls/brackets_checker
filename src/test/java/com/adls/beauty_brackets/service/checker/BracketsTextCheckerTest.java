package com.adls.beauty_brackets.service.checker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.adls.beauty_brackets.service.checker.impl.BracketsTextChecker;

public class BracketsTextCheckerTest {
    
    @InjectMocks
    private BracketsTextChecker bracketsTextChecker;

    private final static String CORRECT_TEXT = "Вчера я отправился в поход в лес (это мое любимое место для отдыха) вместе с друзьями. Мы выбрали маршрут, который проходил через горные потоки и поля (для разнообразия). В начале пути погода была отличной, солнце светило ярко, и птицы радостно пели. Однако, когда мы подошли ближе к вершине горы, небо стало покрываться облаками, (как будто природа готовила нам небольшой сюрприз). Несмотря на это, виды были захватывающими, особенно когда мы достигли высшей точки и увидели прекрасный вид на долину (я почувствовал, что все усилия стоили того).";
    private final static String INCORRECT_TEXT_EMPTY_BRACKETS = "(text)()(text)";
    private final static String INCORRECT_TEXT_BRACKETS = "text)()(text)";

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void check_hasCorrectText_returnIsCorrectTrue() {
       final boolean result = bracketsTextChecker.check(CORRECT_TEXT);

       Assertions.assertTrue(result);
    }

    @Test
    void check_hasEmptyTextInBrackets_returnIsCorrectFalse() {
       final boolean result = bracketsTextChecker.check(INCORRECT_TEXT_EMPTY_BRACKETS);

       Assertions.assertFalse(result);
    }

    @Test
    void check_hasIncorrectBrackets_returnIsCorrectFalse() {
       final boolean result = bracketsTextChecker.check(INCORRECT_TEXT_BRACKETS);

       Assertions.assertFalse(result);
    }

    @Test
    void check_hasEmptyString_returnIsCorrectFalse() {
       final boolean result = bracketsTextChecker.check("");

       Assertions.assertFalse(result);
    }
}
