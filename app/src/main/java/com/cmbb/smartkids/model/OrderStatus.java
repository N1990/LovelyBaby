package com.cmbb.smartkids.model;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/23 15:23
 */
public enum OrderStatus {
    ALL_STATUS {
        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public String toString() {
            return "全部";
        }
    },
    WEI_ZHI_FU {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String toString() {
            return "未支付";
        }
    },

    YI_ZHI_FU {  //已预订
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String toString() {
            return "已预订";
        }
    },
    YI_CAN_JIA {
        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String toString() {
            return "已参加";
        }
    },
    YI_GUO_QI {
        @Override
        public int getValue() {
            return 4;
        }

        @Override
        public String toString() {
            return "已过期";
        }
    },
    YI_PING_JIA {
        @Override
        public int getValue() {
            return 5;
        }

        @Override
        public String toString() {
            return "已评价";
        }
    },
    YI_TUI_KUAN {
        @Override
        public int getValue() {
            return 6;
        }

        @Override
        public String toString() {
            return "已退款";
        }
    },
    YI_QU_XIAO {
        @Override
        public int getValue() {
            return 7;
        }

        @Override
        public String toString() {
            return "已取消";
        }
    },
    TUI_KUAN_ZHONG {
        @Override
        public int getValue() {
            return 8;
        }

        @Override
        public String toString() {
            return "退款中";
        }
    };

    public int getValue() {
        return -1;
    }

    public static OrderStatus getStatusByValue(int value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return OrderStatus.ALL_STATUS;
    }

}
