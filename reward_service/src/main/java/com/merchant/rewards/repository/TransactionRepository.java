package com.merchant.rewards.repository;

import com.merchant.rewards.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    public List<Transaction> findAllByUserNum(Long userNum);

    public List<Transaction> findAllByUserNumAndTransactionDateBetween(Long userNum, Timestamp from, Timestamp to);
}
