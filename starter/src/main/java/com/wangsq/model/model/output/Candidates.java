package com.wangsq.model.model.output;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Candidates {

    //候选项标题
    private String title;

    //候选项
    private List<Candidate> candidate;
}