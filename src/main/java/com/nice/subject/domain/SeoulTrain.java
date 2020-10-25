package com.nice.subject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class SeoulTrain {

    @Id @GeneratedValue
    private Long id;
    private String train_ho;
    private int trian_num;
    private String train_name;
    private int jan_month;
    private int feb_month;
    private int mar_month;
    private int apr_month;
    private int may_month;
    private int jun_month;
    private int jul_month;
    private int aug_month;
    private int sep_month;
    private int oct_month;
    private int nov_month;
    private int dec_month;
}
