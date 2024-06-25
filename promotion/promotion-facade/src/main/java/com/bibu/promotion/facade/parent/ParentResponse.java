package com.bibu.promotion.facade.parent;

import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Author XY
 * Date 2024/5/6 下午8:41
 */
@Setter
@Getter
public class ParentResponse implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
