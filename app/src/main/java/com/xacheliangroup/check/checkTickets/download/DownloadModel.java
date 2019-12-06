package com.xacheliangroup.check.checkTickets.download;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * author:yz
 * data: 2019/6/10,14:58
 */
interface DownloadModel {

    @Streaming
    @GET
    Observable<Response<ResponseBody>> downloadFile(@Url String fileUrl);
}
