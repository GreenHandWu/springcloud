package com.wzm.baidu.translate;

import lombok.Data;

import java.util.List;

@Data
public class BaiduResult {
    private String from;
    private String to;
    private List<TransResult> trans_result;
}
