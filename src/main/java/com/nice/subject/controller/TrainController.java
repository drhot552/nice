package com.nice.subject.controller;

import com.nice.subject.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class TrainController {

    @Autowired
    private TrainService trainService;

    //과제 0.api 명세서
    @GetMapping("/v1/train/api")
    public String apiExplain(){
        return "index";
    }

    //과제 1.데이터파일(.csv)의 각 레코드를 데이터베이스에 저장하는 API 개발
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

    //과제 2. 일평균 인원이 가장 많은 상위 10개의 역 명과 인원 수를 출력하는 API 개발
    @GetMapping("/v1/train/topten")
    @ResponseBody
    public String toptenApi(){
        String api = trainService.traintopten();
        return api;
    }
    //과제 3. 월 인원수 평균이 가장 낮은 역 명과 인원수를 출력하는 API 개발
    @GetMapping("/v1/train/lowlist")
    @ResponseBody
    public String lowlistApi(){
        String api = trainService.trainlowest();
        return api;
    }
    //과제 4. 월 인원수 최대 최소의 차이가 가장 큰 역 명을 출력하는 API 개발
    @GetMapping("/v1/train/difflist")
    @ResponseBody
    public String difflistApi(){
        String api = trainService.traindifference();

        return api;
    }

}
