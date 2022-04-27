package com.wangsq.model.model.output;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DialogOutput {

    private List<DialogAnswer> answers = Lists.newArrayList();

    private int errorCode;

    private String message;

}