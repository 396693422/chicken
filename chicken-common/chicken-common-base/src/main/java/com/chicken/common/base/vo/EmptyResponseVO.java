package com.chicken.common.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("Result")
public class EmptyResponseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "响应码", required = true, example = "200")
    private String code = "200";
    @ApiModelProperty(value = "响应消息", required = true, example = "success")
    private String message = "success";

    private EmptyResponseVO() {

    }

    public EmptyResponseVO(String code, String message) {
        if (code == null || message == null) {
            throw new NullPointerException("neither of the respCode or message is null.");
        }
        this.code = code;
        this.message = message;
    }

    public static EmptyResponseVO getInstance() {
        return new EmptyResponseVO();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
