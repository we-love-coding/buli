package com.bibu.user.facade.parent;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Author XY
 * Date 2024/3/30 下午5:01
 */
@Setter
@Getter
public class ParentRequest implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
