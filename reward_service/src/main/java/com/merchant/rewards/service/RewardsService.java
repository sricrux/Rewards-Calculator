package com.merchant.rewards.service;

import com.merchant.rewards.domain.Rewards;

public interface RewardsService {
    public Rewards getRewardsByUserNum(Long userNum);
}
