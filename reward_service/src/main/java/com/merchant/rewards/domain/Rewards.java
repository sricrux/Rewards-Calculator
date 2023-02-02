package com.merchant.rewards.domain;

import lombok.Data;

@Data
public class Rewards {
    private long userNum;
    private long lastMonthPoints;
    private long lastSecondMonthPoints;
    private long lastThirdMonthPoints;
    private long totalPoints;

}
