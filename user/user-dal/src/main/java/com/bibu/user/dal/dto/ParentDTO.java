package com.bibu.user.dal.dto;

import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Author XY
 * Date 2024/3/30 下午5:28
 */
@Setter
@Getter
public class ParentDTO implements Serializable {
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
