package com.merchant.rewards.controller;


import com.merchant.rewards.domain.Rewards;
import com.merchant.rewards.entity.User;
import com.merchant.rewards.repository.UserRepository;
import com.merchant.rewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class RewardsController {
    @Autowired
    RewardsService rewardsService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/{userNum}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("userNum") Long userNum){
        User user = userRepository.findByUserNum(userNum);
        if(user == null)
        {
            throw new RuntimeException("Invalid User Number");
        }
        Rewards customerRewards = rewardsService.getRewardsByUserNum(userNum);
        return new ResponseEntity<>(customerRewards, HttpStatus.OK);
    }

}
