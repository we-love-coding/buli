package com.bibu.promotion.common.enums;

import com.bibu.promotion.common.exceptions.PromotionException;
import lombok.Getter;

import java.util.Date;

/**
 * Author XY
 * Date 2024/5/10 下午1:51
 */
@Getter
public enum RStatusConvertEnum {

    UN_PUBLISHED(1, "未发布", StatusEnum.UN_PUBLISHED),

    UN_STARTED(10, "未开始", StatusEnum.PUBLISHED),

    IN_PROGRESS(15, "进行中", StatusEnum.PUBLISHED),

    COMPLETED(20, "已结束", StatusEnum.PUBLISHED),

    CANCELLED(25, "已取消", StatusEnum.PUBLISHED),;

    private final int rStatus;
    private final String rName;
    private final StatusEnum statusEnum;

    RStatusConvertEnum(int rStatus, String rName, StatusEnum statusEnum) {
        this.rStatus = rStatus;
        this.rName = rName;
        this.statusEnum = statusEnum;
    }

    public static RStatusConvertEnum getEnumByRStatus(int rStatus) {
        for (RStatusConvertEnum rStatusConvertEnum : RStatusConvertEnum.values()) {
            if (rStatusConvertEnum.getRStatus() == rStatus) {
                return rStatusConvertEnum;
            }
        }
        throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
    }

    public static RStatusConvertEnum convertRStatusEnum(Integer status, Date startTime, Date endTime, Date date) {
        RStatusConvertEnum rStatusConvertEnum = RStatusConvertEnum.UN_PUBLISHED;
        switch (StatusEnum.getEnumByStatus(status)) {
            case PUBLISHED:
                if (date.compareTo(startTime) < 0) {
                    rStatusConvertEnum = RStatusConvertEnum.UN_STARTED;
                } else if (date.compareTo(endTime) > 0) {
                    rStatusConvertEnum = RStatusConvertEnum.COMPLETED;
                } else if (date.compareTo(startTime) >= 0 && date.compareTo(endTime) <= 0) {
                    rStatusConvertEnum = RStatusConvertEnum.IN_PROGRESS;
                }
                break;
            case CANCELLED:
                rStatusConvertEnum = RStatusConvertEnum.CANCELLED;
                break;
            case UN_PUBLISHED:
                rStatusConvertEnum = RStatusConvertEnum.UN_PUBLISHED;
                break;
        }
        return rStatusConvertEnum;
    }






}
