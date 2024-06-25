package com.bibu.content.facade.parent;

import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Author XY
 * Date 2024/5/20 下午12:00
 */
@Setter
@Getter
public class ParentRequest implements Serializable {
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
