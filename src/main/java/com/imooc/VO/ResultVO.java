package com.imooc.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by guofei on 2017/11/10.
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    /*错误码*/
    private Integer code;

    /*提示信息. */
    private String msg;

    /*具体信息内容. */
    private T data;
}

