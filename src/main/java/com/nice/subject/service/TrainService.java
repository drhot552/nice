package com.nice.subject.service;

import com.nice.subject.domain.ResponseDTO;
import com.nice.subject.domain.SeoulTrain;
import com.nice.subject.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class TrainService {

    @Autowired
    //JPA를 직접사용하는 계층 엔티티 매니저 사용
    private TrainRepository trainRepository;

    // loadfile
    public void loadfile() throws IOException {

        ClassPathResource resource =  new ClassPathResource("file.csv");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "euc-kr"));
        String line = "";
        trainRepository.trainAlldelete();
        bufferedReader.readLine();
        while((line = bufferedReader.readLine()) != null){
            SeoulTrain train = new SeoulTrain();
            //csv , 구분자로 나누어서 객체에 저장 후 DB에 저장 (단 "로 묶여있는 숫자는 제외)
            String[] array = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
            for(int i=0; i<array.length; i++){
                //0 -> 호선, 1-> 역번호, 2-> 역명 (3~12) -> 월
                array[i] = array[i].replace("\"","");
                array[i] = array[i].replace(",","");

                if(i == 0){
                    train.setTrain_ho(array[i]);
                } else if(i == 1) {
                    train.setTrian_num(Integer.parseInt(array[i]));
                } else if (i == 2) {
                    train.setTrain_name(array[i]);
                } else if (i == 3) {
                    train.setJan_month(Integer.parseInt(array[i]));
                } else if (i == 4) {
                    train.setFeb_month(Integer.parseInt(array[i]));
                } else if (i == 5) {
                    train.setMar_month(Integer.parseInt(array[i]));
                } else if (i == 6) {
                    train.setApr_month(Integer.parseInt(array[i]));
                } else if (i == 7) {
                    train.setMay_month(Integer.parseInt(array[i]));
                } else if (i == 8) {
                    train.setJun_month(Integer.parseInt(array[i]));
                } else if (i == 9) {
                    train.setJul_month(Integer.parseInt(array[i]));
                } else if (i == 10) {
                    train.setAug_month(Integer.parseInt(array[i]));
                } else if (i == 11) {
                    train.setSep_month(Integer.parseInt(array[i]));
                } else if (i == 12) {
                    train.setOct_month(Integer.parseInt(array[i]));
                } else if (i == 13) {
                    train.setNov_month(Integer.parseInt(array[i]));
                } else if (i == 14) {
                    train.setDec_month(Integer.parseInt(array[i]));
                }
            }
            trainRepository.traininsert(train);

        }
        bufferedReader.close();

    }
    public String traintopten() {
        return trainRepository.traintopten();
    }
    public String trainlowest() {
        return trainRepository.trainlowest();
    }
    public String traindifference() {
        return trainRepository.traindifference();
    }

}
