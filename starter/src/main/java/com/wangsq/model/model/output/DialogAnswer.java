package com.wangsq.model.model.output;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DialogAnswer {

    //文本结果
    private String txt;

    //候选项
    private Candidates candidates;

    //富文本结果
    private String richTxtContent;

    //输出类型
    private int outputType;

}