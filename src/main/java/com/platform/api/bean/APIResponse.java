package com.platform.api.bean;

import lombok.Data;

/**
 * @author : yanl
 * @version V1.0
 * @Description: 接口返回结果
 * @date : 2022/7/9
 */
@Data
public class APIResponse {
    protected int resultcode = ResponseResult.COMMON_SUCCESS.getResultcode();

    protected String resultmsg = ResponseResult.COMMON_SUCCESS.getResultmsg();

    protected Object data;

    public APIResponse() {
    }


    public APIResponse(int resultcode, String resultmsg) {
        this.resultcode = resultcode;
        this.resultmsg = resultmsg;
    }

    public APIResponse(int resultcode, String resultmsg, Object data) {
        this.resultcode = resultcode;
        this.resultmsg = resultmsg;
        this.data = data;
    }

    public APIResponse(Object data) {
        this.data = data;
    }

    public APIResponse(ResponseResult resp) {
        this.resultcode = resp.getResultcode();
        this.resultmsg = resp.getResultmsg();
    }

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getResultmsg() {
        return resultmsg;
    }

    public void setResultmsg(String resultmsg) {
        this.resultmsg = resultmsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean equals(ResponseResult r) {
        return (this.resultcode == r.getResultcode()) ? true : false;
    }
}
