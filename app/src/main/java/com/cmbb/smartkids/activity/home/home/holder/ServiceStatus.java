package com.cmbb.smartkids.activity.home.home.holder;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/28 9:36
 */
public enum ServiceStatus {
    WEI_KAI_SHI{
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String toString() {
            return "未开始";
        }
    },
    YI_KAI_SHI{
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String toString() {
            return "已开始";
        }
    },
    YU_DING_ZHONG{
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String toString() {
            return "预定中";
        }
    },
    YI_JIE_SHU{
        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String toString() {
            return "已结束";
        }
    };

    public int getValue(){
        return 0;
    }

    public static ServiceStatus getStatusByValue(int value) {
        for (ServiceStatus status : ServiceStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return ServiceStatus.WEI_KAI_SHI;
    }
}
