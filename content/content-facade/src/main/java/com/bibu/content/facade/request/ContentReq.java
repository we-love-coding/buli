package com.bibu.content.facade.request;

import com.bibu.content.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/5/20 下午3:36
 */
@Setter
@Getter
public class ContentReq extends ParentRequest {

    private Long id;
}
