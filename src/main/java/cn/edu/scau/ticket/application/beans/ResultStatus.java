package cn.edu.scau.ticket.application.beans;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/5
 */

@AllArgsConstructor
@NoArgsConstructor
public enum ResultStatus {

    SUCCESS(200,"响应成功"),
    FAILED(400,"响应失败"),
    NO_LOGIN(1001,"未登录"),
    LOGIN_OUT_SUCCESS(1002,"退出登录成功"),
    LOGIN_EXPIRED(1003,"登录过期"),
    NO_PERMISSION(1004,"没有权限"),
    LOGIN_FAIL(1005,"登录失败"),
    LOGIN_SUCCESS(1006,"登录成功");

    /**
     * status-code,such as:200,300,400...
     */
    private int code;
    /**
     * Description of status
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
