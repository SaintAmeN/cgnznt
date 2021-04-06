package com.sshpr.cgnznt.demo.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sshpr.cgnznt.demo.model.LanguageChoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Author: amen
 * Date: 06/04/2021
 * Project: demo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class RexTexAPIRequest {

    @Enumerated(EnumType.ORDINAL)
    private LanguageChoice languageChoice;
    private String program;
    private String input;
    private String compilerArgs;
}
