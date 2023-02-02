package com.merchant.rewards.service;

import com.merchant.rewards.constants.Constants;
import com.merchant.rewards.domain.Rewards;
import com.merchant.rewards.entity.Transaction;
import com.merchant.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardsServiceImpl implements RewardsService {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Rewards getRewardsByUserNum(Long userNum) {
        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(Constants.DAYS_MONTH);
        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*Constants.DAYS_MONTH);
        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*Constants.DAYS_MONTH);

        List<Transaction> lastMonthTransactions = transactionRepository.findAllByUserNumAndTransactionDateBetween(
                userNum, lastMonthTimestamp, Timestamp.from(Instant.now()));
        List<Transaction> lastSecondMonthTransactions = transactionRepository
                .findAllByUserNumAndTransactionDateBetween(userNum, lastSecondMonthTimestamp, lastMonthTimestamp);
        List<Transaction> lastThirdMonthTransactions = transactionRepository
                .findAllByUserNumAndTransactionDateBetween(userNum, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Long lastMonthPoints = getRewardsPerMonth(lastMonthTransactions);
        Long lastSecondMonthPoints = getRewardsPerMonth(lastSecondMonthTransactions);
        Long lastThirdMonthPoints = getRewardsPerMonth(lastThirdMonthTransactions);

        Rewards userRewards = new Rewards();
        userRewards.setUserNum(userNum);
        userRewards.setLastMonthPoints(lastMonthPoints);
        userRewards.setLastSecondMonthPoints(lastSecondMonthPoints);
        userRewards.setLastThirdMonthPoints(lastThirdMonthPoints);
        userRewards.setTotalPoints(lastMonthPoints + lastSecondMonthPoints + lastThirdMonthPoints);

        return userRewards;

    }

    private Long getRewardsPerMonth(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> calculateRewards(transaction))
                .collect(Collectors.summingLong(r -> r.longValue()));
    }

    private Long calculateRewards(Transaction t) {
        if (t.getTransactionTotal() > Constants.REWARD_50 && t.getTransactionTotal() <= Constants.REWARD_100) {
            return Math.round(t.getTransactionTotal() - Constants.REWARD_50);
        } else if (t.getTransactionTotal() > Constants.REWARD_100) {
            return Math.round(t.getTransactionTotal() - Constants.REWARD_100) * 2
                    + (Constants.REWARD_100 - Constants.REWARD_50);
        } else
            return 0l;

    }

    public Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }

}

