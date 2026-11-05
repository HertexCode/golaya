package com.devPortes.statistics.controller;

import com.devPortes.statistics.dto.PublicStatisticsResponseDto;
import com.devPortes.statistics.dto.UserStatisticsResponseDto;
import com.devPortes.statistics.service.PublicStatisticsService;
import com.devPortes.statistics.service.UserStatisticsService;
import com.devPortes.users.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/statistics")
public class StatisticsController {
    private final UserStatisticsService userStatisticsService;
    private final PublicStatisticsService publicStatisticsService;

    @GetMapping("me")
    public ResponseEntity<UserStatisticsResponseDto> myStatistics(
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(userStatisticsService.execute(customUserDetails.getUser().getId()));
    }

    @GetMapping("public")
    public ResponseEntity<PublicStatisticsResponseDto> publicStatistics() {
        return ResponseEntity.ok(publicStatisticsService.execute());
    }
}
