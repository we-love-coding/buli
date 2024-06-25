package com.bibu.user.facade.request;

import com.bibu.user.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author XY
 * Date 2024/5/21 下午1:16
 */
@Setter
@Getter
public class UserBatchSearchReq extends ParentRequest {

    private List<Long> userIdList;
}
