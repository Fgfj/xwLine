package com.xacheliangroup.check.common.http.okhttp;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Afra55 on 2017/10/11.
 * Smile is the best name card.
 */

public class RequestField {

    private Map<String, String> map = new HashMap<>();

    public static Builder getBuilderInstance() {
        return new Builder();
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public static class Builder {

        private Map<String, String> data = new HashMap<>();

        public Builder() {

        }

        public Builder withData(Map<String, String> data) {
            if (data != null) {
                this.data = data;
            }
            return this;
        }

        public Builder withParam(String key, Object value) {
            if (value != null) {
                this.data.put(key, String.valueOf(value));
            }
            return this;
        }

        /**
         * .withObject(JSON.toJSONString(bean))
         *
         * @param jsonStr JSON.toJSONString(bean)
         * @return this
         */
        public Builder withObject(String jsonStr) {
            if (jsonStr != null) {
                JSONObject jasonObject = JSONObject.parseObject(jsonStr);
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map) jasonObject;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    this.data.put(entry.getKey(),
                            String.valueOf(entry.getValue()));
                }
            }
            return this;
        }


        public RequestField build() {
            RequestField request = new RequestField();
            request.setMap(this.data);
            return request;
        }

    }


}
