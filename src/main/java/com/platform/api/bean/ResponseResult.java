package com.platform.api.bean;

/**
 * 枚举类定义，定义返回结果
 */
public enum ResponseResult  {
    COMMON_SUCCESS(0, "成功");


    private int resultcode;

    private String resultmsg;

    private ResponseResult(int resultcode, String resultmsg) {
        this.resultcode = resultcode;
        this.resultmsg = resultmsg;
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
}
