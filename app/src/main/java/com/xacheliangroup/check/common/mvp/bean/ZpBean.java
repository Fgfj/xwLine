package com.xacheliangroup.check.common.mvp.bean;

/**
 * author:yz
 * data: 2019/5/17,14:26
 */
public class ZpBean {

    /**
     * success : true
     * code : 200
     * data : {"expires":1562054855000,"scope":"multi_store shop item trade logistics coupon_advanced user pay_qrcode trade_virtual reviews item_category storage retail_goods open_market ","access_token":"2b9d2cc8c71194275d7cc8b3ccb6326d","authority_id":"43149121"}
     * message : sad
     */

    private boolean success;
    private int code;
    private DataBean data;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * expires : 1562054855000
         * scope : multi_store shop item trade logistics coupon_advanced user pay_qrcode trade_virtual reviews item_category storage retail_goods open_market
         * access_token : 2b9d2cc8c71194275d7cc8b3ccb6326d
         * authority_id : 43149121
         */

        private long expires;
        private String scope;
        private String access_token;
        private String authority_id;

        public long getExpires() {
            return expires;
        }

        public void setExpires(long expires) {
            this.expires = expires;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getAuthority_id() {
            return authority_id;
        }

        public void setAuthority_id(String authority_id) {
            this.authority_id = authority_id;
        }
    }
}
