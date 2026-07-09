package com.deliveryrisk.platform.controller;

import com.deliveryrisk.platform.dto.DashboardSummaryDto;
import com.deliveryrisk.platform.dto.RiskScoreDto;
import com.deliveryrisk.platform.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public DashboardSummaryDto getSummary() {
        return dashboardService.getSummary();
    }

    @GetMapping("/risk")
    public List<RiskScoreDto> getRiskRanking() {
        return dashboardService.getRiskRanking();
    }
}
