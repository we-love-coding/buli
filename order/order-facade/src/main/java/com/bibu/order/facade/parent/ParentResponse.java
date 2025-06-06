package com.bibu.order.facade.parent;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Author XY
 * Date 2024/4/26 下午4:51
 */
@Setter
@Getter
public class ParentResponse implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
