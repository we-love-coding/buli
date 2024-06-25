package com.bibu.content.facade.request;

import com.bibu.content.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/5/17 下午7:21
 */
@Setter
@Getter
public class ContentSearchReq extends ParentRequest {

    private Long id;
}
