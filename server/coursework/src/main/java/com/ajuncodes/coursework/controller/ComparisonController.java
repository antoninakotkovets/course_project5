package com.ajuncodes.coursework.controller;

import com.ajuncodes.coursework.dto.ComparisonDto;
import com.ajuncodes.coursework.dto.ComparisonResultDto;
import com.ajuncodes.coursework.util.ComparisonTextHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comparing")
public class ComparisonController {

    private final ComparisonTextHandler textHandler;

    public ComparisonController(ComparisonTextHandler textHandler) {
        this.textHandler = textHandler;
    }

    @ResponseBody
    @PostMapping("/compare")
    public ComparisonResultDto compareFiles(@RequestBody ComparisonDto comparisonDto) {
        return textHandler.compareTexts(
            comparisonDto.getFirstText(),
            comparisonDto.getSecondText(),
            comparisonDto.getLanguage()
        );
    }
}
