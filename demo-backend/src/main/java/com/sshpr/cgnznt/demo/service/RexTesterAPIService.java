package com.sshpr.cgnznt.demo.service;

import com.sshpr.cgnznt.demo.model.dto.RexTexAPIRequest;
import com.sshpr.cgnznt.demo.model.dto.RexTexAPIResult;
import com.sshpr.cgnznt.demo.model.dto.SubmissionRequest;
import com.sshpr.cgnznt.demo.repository.AccountScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Author: amen
 * Date: 06/04/2021
 * Project: demo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RexTesterAPIService {
    private final static String URL = "https://rextester.com/rundotnet/api";
    private final AccountScoreRepository accountScoreRepository;
    private final RestTemplate restTemplate;

    public boolean submit(SubmissionRequest request) {
        RexTexAPIRequest requestBody = new RexTexAPIRequest(
                request.getLanguageChoice(),
                request.getInput(),
                "7",
                "");
        HttpEntity<RexTexAPIRequest> requestContent = new HttpEntity<>(requestBody);
        ResponseEntity<RexTexAPIResult> resp = restTemplate.exchange(URL, HttpMethod.POST, requestContent, RexTexAPIResult.class);

        log.info("Result: " + resp.getBody());
        return false;
    }
}
