package com.adls.beauty_brackets.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adls.beauty_brackets.model.BracketsCheckerRq;
import com.adls.beauty_brackets.model.BracketsCheckerRs;
import com.adls.beauty_brackets.service.BracketsTextCheckerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("v1/api/checker")
@Tag(name = "Checker API")
public class CheckerController {

    private final BracketsTextCheckerService bracketsCheckerService;

    @PostMapping("text/bracket")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"), 
        @ApiResponse(responseCode = "404", description = "Веб-ресурс не найден"),
        @ApiResponse(responseCode = "400", description = "Невалидный текст"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
    })
    @Operation(summary = "Проверка текста на наличие корректных скобочек", description = "Вовзращает логическое значение")
    public BracketsCheckerRs checkBrackets(@RequestBody @Valid BracketsCheckerRq bracketsCheckerRq) {
        return bracketsCheckerService.check(bracketsCheckerRq.text);
    }
}
