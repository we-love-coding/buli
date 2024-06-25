package com.bibu.promotion.facade.parent;

import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/5/6 下午1:59
 */
@Setter
@Getter
public class PageParentRequest extends ParentRequest {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

}
