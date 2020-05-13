package com.test03.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Auther wise wu
 * @Date 2020/5/11 17:21
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class JsonResult {
    private int state ;//返回状态
    private String msg;
    private Object date;

    public  static JsonResult success(String msg, Object date) {

        return new JsonResult(200, msg, date);
    }
    public  static JsonResult success() {

        return new JsonResult(200, null, null);
    }

    public static JsonResult success(Object date) {
        return new JsonResult(200, null, date);
    }

    public  static  JsonResult fail() {
        return new JsonResult(201, "出现错误", null);
    }

}
