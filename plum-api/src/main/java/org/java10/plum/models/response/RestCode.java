package org.java10.plum.models.response;

/**
 * Created by Tate on 2017/2/27.
 */
public enum RestCode {

    OK(0,"请求成功"),
    FAIL(-1,"系统繁忙"),

    REQUESTERROR(100,"请求错误"),
    PARAMETERERROR(101,"参数错误"),
    TOKENERROR(102,"TOKEN无效"),
    TOKENEXPIRE(103,"TOKEN过期"),

    UserNamePasswordError(201, "用户名或密码错误"),
    Unauthorized(202,"未授权"),
    ARTICLE_NOT_FOUND(203, "文章不存在"),
    DATA_OCCUPY_ERROR(204, "数据被占用，无法删除");


    private int code;
    private String msg;

    RestCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}
