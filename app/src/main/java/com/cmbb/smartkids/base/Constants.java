package com.cmbb.smartkids.base;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/10 13:09
 */
public class Constants {
    public final static String TOKEN = "token";
    public final static String USER_ID = "userId";
    public static String packageName = "com.cmbb.smartkids";
    public static final String INTENT_ACTION_EXIT_APP = packageName + ".intent.action.exitapp";
    public final static int CANCEL_WAIT_DIALOG = 10001;

    public static class SharePreference {
        public static final String SCREEN_WIDTH = "screen_width";
        public static final String SCREEN_HEIGHT = "screen_height";
        public static final String OPEN_ID = "open_id";
        public static final String SCREEN_NAME = "screen_name";
    }

    public static final class Share {
        public static final String WEIXIN_APP_KEY = "wx766b807ef51aa8da";
        public static final String WEIXIN_APPSECRET = "139fc6ccddba72262d94688368082312";

        public static final String QQ_APP_KEY = "1104000906";
        public static final String QQ_APPSECRET = "T73NH4Tz75dWsPdy";

        public static final String DESCRIPTOR = "com.cmbb.smartkids";

        public static String getTopicShareUrl(int id) {
            return "http://120.26.88.135:8090/wine-rest/share/share.jsp?id=" + id + "&token=YzQyYTEwZDYtOGZhMi00ODAyLWI0NTYtYTcxMmVkY2FmYzRh";
        }

        public static String getServerShareUrl(int id) {
            return "http://120.26.88.135:8090/wine-rest/share/service.jsp?id=" + id + "&token=YzQyYTEwZDYtOGZhMi00ODAyLWI0NTYtYTcxMmVkY2FmYzRh";
        }
    }

    public static final class Broadcast {
        public static final String COUNT_TIME = "com.cmbb.smartkids.counttimen";
    }


    //接口
    public static final class ServiceInfo {
        public static final String HOEM_MAIN_POPMAN_REQUEST = "smart/recommonedEredar"; // 推荐达人
        public static final String HOME_MAIN_HOT_SERVICE = "smart/services/getPage";  //热门服务
        public static final String HOME_MAIN_AD = "smart/topic/getAD";  //广告位
        public static final String UPDATA_IMG_FOR_USER = "smart/personal/updateBackgroundImg"; //更换个人的图片
        public static final String REGISTER_VERIFY_CODE = "smart/getRegisterSecurityCode"; // 获取注册验证码
        public static final String VERIFY_CODE = "smart/getSecurityCode"; //获取验证码，除注册外
        public static final String REGISTER_NEXT_REQUEST = "smart/registerNext"; //注册下一步
        public static final String FORGEST_PWD_REQUEST = "smart/forgetPWD"; //忘记密码
        public static final String LOGIN_REQUEST = "smart/login"; //登录
        public static final String USER_INFO_REQUEST = "smart/personal/getPersonal"; //获取用户信息
        public static final String APPLY_POPMAN_REQUEST = "smart/eredar/apply"; //达人申请
        public static final String CARE_LIST_REQUEST = "smart/attention/getList"; // 关注列表
        public static final String MODIFY_USER_INFO = "smart/personal/updatePersonal"; // 修改用户信息
        public static final String CHANGE_USER_ACCOUNT = "smart/logout"; // 切换账号
        public static final String HANDLE_CARE_REQUEST = "smart/attention/attentionUser"; // 关注; 取消关注
        public static final String SERVICE_DETAIL_REQUEST = "smart/services/getServicesDetails"; // 服务详情
        public static final String HANDLE_COLLECT_SERVICE = "smart/services/serviceCollect"; //收藏 取消收藏
        public static final String HANDLE_ORDER_SERVICE = "smart/services/Reserve"; //预定服务
        public static final String SUBMIT_ORDER_REQUEST = "smart/services/commitOrder"; //提交订单
        public static final String CANCEL_ORDER_REQUEST = "smart/order/cancelOrder"; // 取消订单
        public static final String ORDER_DETAIL_REQUEST = "smart/order/getOrderDetails"; // 查看订单详情
        public static final String COLLECT_SERVICE_REQUEST = "smart/personal/getServiceCollect";  // 收藏服务
        public static final String ORDER_LIST_REQUEST = "smart/order/getMyOrder"; // 我的订单  我的接单
        public static final String VERIFY_ORDER_REQUEST = "smart/order/validateOrder"; //验证订单
        public static final String MY_SERVICE_REQUEST = "smart/personal/getService"; //我的服务
        public static final String EVALUATE_POPMAN_REQUEST = "smart/eredar/saveEvaluate";//评价达人
        public static final String EVALUATE_POPMAN_LIST = "smart/services/getServiceEredar"; //评价达人列表
        public static final String EVALUATE_LIST_REQUEST = "smart/personal/getEredarEvaluate"; //评价列表
        public static final String FEEDBACK_SUGGEST_REQUEST = "smart/feedback/opinion";//意见反馈


        public static final String PAY_WAY_LIST = "smart/services/payOrder"; //获取支付列表
        public static final String APPLY_REFUND_REQUEST = "smart/services/applyrefund";//申请退款
        public static final String MESSAGE_LIST_REQUEST = "smart/message/getPage";// 消息列表
        public static final String MESSAGE_REEAD_REQUEST = "smart/message/read";// 读消息
        public static final String MESSAGE_DELETE_REQUEST = "smart/message/delete";// 删除消息
        public static final String MESSAGE_HOME_REQUEST = "smart/message/getMessage";// 删除消息
        public static final String MY_COMMUNITY_LIST = "smart/personal/getTopic";// 我的话题列表
        public static final String COLLECT_COMMUNITY_LIST = "smart/personal/getTopicCollect";// 收藏话题
        public static final String SIGN_ARRIVE_REQUEST = "smart/user/sign";// 签到
        public static final String SMARTS_SORT_REQUEST = "smart/system/dictList";// 各种类型
        public static final String SEARCH_USER_REQUEST = "smart/user/search";// 搜索用户
        public static final String SERVICE_SORT_REQUEST = "smart/system/getMultipleDict";// 服务列表字典
        public static final String USER_GOLD_LIST = "smart/personal/goldList";// 用户积分


    }

    public static final class Community {
        public static final String TOPIC_PUBLISH = "smart/topic/publish"; // 话题发布
        public static final String TOPIC_TYPE = "smart/system/dictList"; // 话题类型
        public static final String TOPIC_DETIAL = "smart/topic/detial"; // 话题详情
        public static final String TOPIC_REPLAY = "smart/topic/replyList"; //
        public static final String TOPIC_LIST = "smart/topic/getPage"; // 话题列表
        public static final String REPLAY_DETAIL = "smart/topic/getReplyDetial"; // 话题列表
        public static final String TOPIC_SPOT = "smart/topic/spot"; // 话题列表
        public static final String TOPIC_COLLECT = "smart/topic/collect"; // 话题列表
        public static final String TOPIC_DELETE = "smart/topic/delete"; // 删除话题
        public static final String TOPIC_REPORT = "smart/topic/reportTopic"; // 举报话题
        public static final String TOPIC_REPORTREPLY = "smart/topic/reportReply"; // 举报话题
        public static final String TOPIC_REPLYTOPIC = "smart/topic/replyTopic"; // 举报话题
        public static final String TOPIC_DELETEREPLY = "smart/topic/deleteReply"; // 举报话题

    }


}
