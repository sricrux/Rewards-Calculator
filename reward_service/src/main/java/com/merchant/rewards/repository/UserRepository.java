package com.merchant.rewards.repository;

import com.merchant.rewards.entity.User;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User,Long> {
    public User findByUserNum(Long userNum);
}
