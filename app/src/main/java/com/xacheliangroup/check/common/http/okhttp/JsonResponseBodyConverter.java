package com.xacheliangroup.check.common.http.okhttp;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by yangshuai on 2017/8/19.
 * {link http://afra55.github.io}
 */

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private Type mType;

    public JsonResponseBodyConverter(Type type) {
        this.mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        //解析数据
        BufferedReader br = new BufferedReader(value.charStream());
        String line;
        StringBuilder buffer = new StringBuilder();
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        String result = buffer.toString();
        ResultBean response = JSON.parseObject(result, ResultBean.class);

        try {
            if (response != null && response.isSuccess()) {
                String bizData = response.getData();
                return getBizBean(bizData);
            } else {
                return returnError(response);
            }
        } catch (Exception e) {
            if (e.toString().contains("NullPointerException")) {
                try {
                    return getBizBean(result);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    throw new IOException("Unknown Exception: response is null");
                }
            } else {
                e.printStackTrace();
                returnError(response);
            }
        }

        return null;
    }

    private T returnError(ResultBean response) throws IOException {
        if (response != null) {
            throw new HttpRuntimeException(response.getStatus(), response.getMsg());
        } else {
            throw new IOException("Unknown Exception: response is null");
        }
    }

    private T getBizBean(String bizData) {
        try {
            if (bizData != null && bizData.equals("")) {
                bizData = "返回了一个空的业务数据";
            }
            return JSON.parseObject(bizData, mType);
        } catch (Exception e) {
            try {
                return JSON.parseObject(JSON.toJSONString(bizData), mType);
            } catch (Exception e1) {
                try {
                    return new Gson().fromJson(bizData, mType);
                } catch (JsonSyntaxException e2) {
                    e2.printStackTrace();
                    //noinspection unchecked
                    return null;
                }
            }
        }
    }
}