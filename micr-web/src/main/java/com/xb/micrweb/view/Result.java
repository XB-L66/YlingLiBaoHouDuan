package com.xb.micrweb.view;

import com.xb.common.enums.RCode;
import lombok.Data;

import java.util.List;

@Data
public class Result{
    private int code;

    private String msg;

    private Object data;

    private List list;

    private String accessToken;

    private PageInfo page;
    public static Result ok(){
        Result result=new Result();
        result.setRCode(RCode.SUCC);
        return result;
    }
    public static Result fail(){
        Result result=new Result();
        result.setRCode(RCode.UNKOWN);
        return result;
    }
    public void setRCode(RCode rcode){
        this.code=rcode.getCode();
        this.msg=rcode.getText();
    }
}
