package com.nice.subject.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nice.subject.domain.ResponseDTO;
import com.nice.subject.domain.SeoulTrain;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TrainRepository {
    @PersistenceContext
    private EntityManager em;

    //과제 1.데이터파일(.csv)의 각 레코드를 데이터베이스에 저장하는 API 개발
    @Transactional
    public void trainAlldelete(){
        String sql = "delete from seoul_train";
        int query = em.createNativeQuery(sql).executeUpdate();
    }
    @Transactional
    public void traininsert(SeoulTrain train){

        em.persist(train);
    }
    //과제 2. 일평균 인원이 가장 많은 상위 10개의 역 명과 인원 수를 출력하는 API 개발
    public String traintopten(){
        String sql = "select * from (\n" +
                "                    \tselect \n" +
                "                    \t\t   train_name, \n" +
                "                    \t\t   round((apr_month + aug_month + dec_month + feb_month + jan_month + jul_month\n" +
                "                    \t\t         + jun_month + mar_month + may_month \n" +
                "                    \t\t\t\t + nov_month + oct_month + sep_month)/365) people  \n" +
                "                    \tfrom seoul_train\n" +
                "                    \t) a \n" +
                "                    order by a.people desc\n" +
                "                    LIMIT 10";
        Query nativeQuery = em.createNativeQuery(sql);
        String json = ObjectToJson(nativeQuery);
        return json;
    }
    //과제 3. 월 인원수 평균이 가장 낮은 역 명과 인원수를 출력하는 API 개발
    public String trainlowest(){
        String sql = "select * from (\n" +
                "                    \tselect \n" +
                "                    \t\t   train_name, \n" +
                "                    \t\t   round((apr_month + aug_month + dec_month + feb_month + jan_month + jul_month\n" +
                "                    \t\t         + jun_month + mar_month + may_month \n" +
                "                    \t\t\t\t + nov_month + oct_month + sep_month)/12) people  \n" +
                "                    \tfrom seoul_train\n" +
                "                    \t) a \n" +
                "                    order by a.people";
        Query nativeQuery = em.createNativeQuery(sql);
        String json = ObjectToJson(nativeQuery);
        return json;
    }
    //과제 4. 월 인원수 최대 최소의 차이가 가장 큰 역 명을 출력하는 API 개발
    public String traindifference(){
        String sql = "select *\n" +
                "                  from (\n" +
                "\t                  select train_name, GREATEST(apr_month, aug_month, dec_month, feb_month, jan_month, jul_month, jun_month, \n" +
                "\t                     \t\tmar_month, may_month, nov_month, oct_month, sep_month) - LEAST(apr_month, aug_month, dec_month, feb_month, jan_month, jul_month, jun_month, \n" +
                "\t                     \t\tmar_month, may_month, nov_month, oct_month, sep_month) as cnt\n" +
                "\t                  from seoul_train\n" +
                "                  ) a\n" +
                "                  order by a.cnt desc";
        Query nativeQuery = em.createNativeQuery(sql);
        String json = ObjectToJson(nativeQuery);
        return json;
    }

    private static String ObjectToJson(Query nativeQuery){
        //json 데이터로 mapping
        List<Object[]> objectList = nativeQuery.getResultList();
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        for (Object[] i : objectList) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("train_name",i[0]);
            map.put("cnt",i[1]);
            list.add(map);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
