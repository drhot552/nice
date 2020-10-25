package com.nice.subject.controller;

import com.nice.subject.domain.ResponseDTO;
import com.nice.subject.domain.SeoulTrain;
import com.nice.subject.service.TrainService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/v1/train/api")
    public String apiExplain(){
        return "index";
    }

    @GetMapping("/v1/train/insert")
    @ResponseBody
    public String insert(){
        String api = "";
        try{
            trainService.loadfile();
            api = "{ code : success }";
        } catch (IOException e) {
            e.printStackTrace();
            api = "{ code : error }";
        }
        //성공 json 값
        return api;
    }

    //반환값 모델 및 값
    @GetMapping("/v1/train/topten")
    @ResponseBody
    public String toptenApi(){
        String api = trainService.traintopten();
        return api;
    }

    @GetMapping("/v1/train/lowlist")
    @ResponseBody
    public String lowlistApi(){
        String api = trainService.trainlowest();
        return api;
    }

    @GetMapping("/v1/train/difflist")
    @ResponseBody
    public String difflistApi(){
        String api = trainService.traindifference();

        return api;
    }

}
