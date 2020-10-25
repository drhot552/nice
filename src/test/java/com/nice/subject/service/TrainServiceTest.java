package com.nice.subject.service;

import com.nice.subject.domain.SeoulTrain;
import com.nice.subject.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class TrainServiceTest {

    @Autowired
    TrainService trainService;
    @Autowired
    TrainRepository trainRepository;

    @Test
    public void topten() {
        trainService.traintopten();
    }
    @Test
    public void lowlist() {
        trainService.trainlowest();
    }
    @Test
    public void difflist() {
        trainService.traindifference();
    }
}