package com.sshpr.cgnznt.demo.model.dto;

import com.sshpr.cgnznt.demo.model.LanguageChoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: amen
 * Date: 06/04/2021
 * Project: demo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionRequest {
    private LanguageChoice languageChoice;
    private String input;

    private String user;
    private Long taskId;
}
