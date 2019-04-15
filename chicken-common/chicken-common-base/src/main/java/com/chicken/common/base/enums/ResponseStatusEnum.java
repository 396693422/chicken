package com.chicken.common.base.enums;

/**
 * 响应状态对象
 * 
 * @author huangjianwen
 * @date 2018-03-15
 */
public enum ResponseStatusEnum {
	
    OK("200", "成功"),
    ERROR("990", "系统开小差啦"),
    FAIL("991", "操作失败"),
    PARAMETER_ERROR("301", "参数错误"),
    NO_AUTHORIZATION("302", "无Authorization传入"),
    ZUUL_FALL_BACK("303", "{0}无法访问服务，该服务可能由于某种未知原因被关闭，请重启服务！"),
    INTERNAL_ERROR("304", "内部服务异常，异常信息：{0}"),
    UNKNOW_ERROR("305", "未知错误"),
    ANALYSIS_TOKEN_FAIL("306","解析token失效"),
    UNAUTHORIZED("307","无访问权限"),
    LOGINTOKEN_REQUEST_FAIL("308","请求认证中心获取token失败"),
    GET_CURRENT_LOGIN_INFO_FAIL("309","获取当前登录信息失败");

    private String code;
    private String message;

    public static ResponseStatusEnum getEnum(String code) {
        for (ResponseStatusEnum ele : ResponseStatusEnum.values()) {
            if (ele.code.equals(code)){
                return ele;
            }
        }
        return null;
    }

    /**
     * 构造方法
     *
     * @param code    错误码
     * @param message 错误消息
     */
    ResponseStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return 返回成员变量值
     */
    public String getCode() {
        return code;
    }

    /**
     * @return 返回成员变量值
     */
    public String getMessage() {
        return message;
    }
}