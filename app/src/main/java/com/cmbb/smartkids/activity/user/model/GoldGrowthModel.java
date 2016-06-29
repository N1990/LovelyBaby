package com.cmbb.smartkids.activity.user.model;

import com.cmbb.smartkids.base.BaseApplication;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.network.OkHttpClientManager;

import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/6/20 下午3:25
 * 修改人：N.Sun
 * 修改时间：16/6/20 下午3:25
 * 修改备注：
 */
public class GoldGrowthModel {

    /**
     * page : 1
     * records : 1
     * rows : [{"id":155056,"getDate":"2015-12-30 16:17:20","relateId":"","contents":"资料完整","modualType":"data_integrity","growth":100}]
     * total : 1
     */

    private DataEntity data;
    /**
     * data : {"page":1,"records":1,"rows":[{"id":155056,"getDate":"2015-12-30 16:17:20","relateId":"","contents":"资料完整","modualType":"data_integrity","growth":100}],"total":1}
     * msg : 数据加载成功
     */

    private String msg;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataEntity {
        private int page;
        private int records;
        private int total;
        /**
         * id : 155056
         * getDate : 2015-12-30 16:17:20
         * relateId :
         * contents : 资料完整
         * modualType : data_integrity
         * growth : 100
         */

        private List<RowsEntity> rows;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getRecords() {
            return records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsEntity> getRows() {
            return rows;
        }

        public void setRows(List<RowsEntity> rows) {
            this.rows = rows;
        }

        public static class RowsEntity {
            private int id;
            private String getDate;
            private String relateId;
            private String contents;
            private String modualType;
            private int growth;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGetDate() {
                return getDate;
            }

            public void setGetDate(String getDate) {
                this.getDate = getDate;
            }

            public String getRelateId() {
                return relateId;
            }

            public void setRelateId(String relateId) {
                this.relateId = relateId;
            }

            public String getContents() {
                return contents;
            }

            public void setContents(String contents) {
                this.contents = contents;
            }

            public String getModualType() {
                return modualType;
            }

            public void setModualType(String modualType) {
                this.modualType = modualType;
            }

            public int getGrowth() {
                return growth;
            }

            public void setGrowth(int growth) {
                this.growth = growth;
            }
        }
    }

    public static void getGoldGrowthRequest(int pager, int pagerSize, OkHttpClientManager.ResultCallback<GoldGrowthModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", String.valueOf(pager));
        params.put("numberOfPerPage", String.valueOf(pagerSize));
        params.put("token", BaseApplication.token);
        OkHttpClientManager.postAsyn(Constants.ServiceInfo.GOLD_GROWTH_DETAIL, params, callback);
    }
}
