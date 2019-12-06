package com.xacheliangroup.check.checkTickets.download;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.xacheliangroup.check.common.api.APIField;
import com.xacheliangroup.check.common.api.AppCache;
import com.xacheliangroup.check.common.http.okhttp.BaseDisposableObserver;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.http.okhttp.RxPresenter;
import com.xacheliangroup.check.common.sharepreference.AppSharePreference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * author:yz
 * data: 2019/6/10,14:57
 */
public class DownloadUtil extends RxPresenter {

    private final String TAG = DownloadUtil.class.getSimpleName();

    private static List<String> isDownloadingList = new ArrayList<>();

    public DownloadUtil(Context context, IActionListener.ViewAction view) {
        super(context, view);
    }

    public static String getAPKNameByPath(String downloadPath) {
        String name = "app.apk";
        if (downloadPath.endsWith("apk") && downloadPath.contains("/")) {
            name = downloadPath.substring(downloadPath.lastIndexOf("/"));
        }
        return name;
    }

    public interface DownloadListener {
        void onStart();

        void onFailure(int errorCode, String errorMsg);

        void onSuccess(String filePath);

        /**
         * 非 UI 线程， 不能操作 view。
         *
         * @param fileTotalSize
         * @param fileDownloadSize
         * @param isDown
         */
        void onDownloading(long fileTotalSize, long fileDownloadSize, boolean isDown);
    }

    private static class SingletonHolder {
        static DownloadUtil sInstance;
    }

    public static DownloadUtil getInstance() {
        if (SingletonHolder.sInstance == null) {
            SingletonHolder.sInstance = new DownloadUtil(AppCache.getContext(), null);
        }
        return SingletonHolder.sInstance;
    }

    private DownloadListener listener;

    public DownloadUtil setListener(DownloadListener listener) {
        this.listener = listener;
        return this;
    }


    public File getDownloadNewFileByName(@NonNull String name, File cacheDir) {
        File file = null;
        try {
            file = new File(cacheDir, name);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public File getDownloadFileByName(@NonNull String name) {
        File file = null;
        try {
            file = new File(getDownloadPath(), name);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public void download(@NonNull String fileUrl, final String fileName) {
        download(fileUrl, getDownloadPath(), fileName);
    }

    @NonNull
    private String getDownloadPath() {
        try {
            return AppCache.getContext().getApplicationContext().getExternalCacheDir().getPath();
        } catch (Exception e) {
            return AppCache.getContext().getApplicationContext().getFilesDir().getAbsolutePath();
        }
    }

    public void download(@NonNull final String fileUrl, @NonNull final String fileSavedPath, final String fileName) {

        if (isDownloading(fileUrl)) return;

        ProgressInterceptor interceptor = new ProgressInterceptor(listener);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        String baseUrl = APIField.getAppServiceUrl();

        DownloadModel downloadModel = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(DownloadModel.class);

        downloadModel.downloadFile(fileUrl)

                .flatMap(new Function<Response<ResponseBody>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@io.reactivex.annotations.NonNull Response<ResponseBody> responseBodyResponse) throws Exception {
                        if (responseBodyResponse.isSuccessful()) {
                            File dirFile = new File(fileSavedPath);
                            if (!dirFile.exists()) {
                                dirFile.mkdir();
                            } else if (!dirFile.isDirectory()) {
                                dirFile.delete();
                                dirFile.mkdir();
                            }
                            File savedFile = getDownloadNewFileByName(fileName + ".1", dirFile);

                            InputStream inputStream = null;
                            OutputStream outputStream = null;
                            try {

                                ResponseBody body = responseBodyResponse.body();

                                long fileSize = body.contentLength();
                                long fileSizeDownloaded = 0;

                                int fileReaderMutil = (int) (fileSize / 1024) + 1;

                                byte[] fileReader = new byte[1024 * fileReaderMutil];

                                inputStream = body.byteStream();
                                outputStream = new FileOutputStream(savedFile);

                                while (true) {
                                    int read = inputStream.read(fileReader);
                                    if (read == -1) {
                                        break;
                                    }

                                    outputStream.write(fileReader, 0, read);

                                    fileSizeDownloaded += read;


                                }

                                outputStream.flush();

                                return Observable.just(savedFile.getAbsolutePath());

                            } catch (Exception e) {
                                e.printStackTrace();
                                if (listener != null) {
                                    if (isDownloadingList.contains(fileUrl)) {
                                        isDownloadingList.remove(fileUrl);
                                    }
                                    listener.onFailure(0, e.toString());
                                }
                            } finally {
                                try {
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    if (outputStream != null) {
                                        outputStream.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    if (listener != null) {
                                        if (isDownloadingList.contains(fileUrl)) {
                                            isDownloadingList.remove(fileUrl);
                                        }
                                        listener.onFailure(0, e.toString());
                                    }
                                }
                            }

                        }
                        return null;
                    }


                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addSubscriber(new BaseDisposableObserver<String>() {
                    @Override
                    public void onSuccess(String filePath) {
                        if (!TextUtils.isEmpty(filePath) && listener != null) {

                            try {
                                if (filePath.endsWith(".1")) {
                                    File file = new File(filePath);
                                    if (file.exists()) {
                                        String newFilePath = filePath.substring(0, filePath.lastIndexOf(".1"));
                                        File dest = new File(newFilePath);
                                        if (dest.exists()) {
                                            dest.delete();
                                        }
                                        file.renameTo(dest);
                                        filePath = newFilePath;
                                    }
                                }
                                listener.onSuccess(filePath);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            onFailure(200, "Download failure");
                        }
                        isDownloadingList.remove(fileUrl);
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (listener != null) {
                            try {
                                listener.onFailure(errorCode, errorMsg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        isDownloadingList.remove(fileUrl);
                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                        if (listener != null) {
                            try {
                                listener.onStart();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }));

    }

    public boolean isDownloading(@NonNull String fileUrl) {
        if (isDownloadingList.contains(fileUrl)) {


            return true;
        }
        return false;
    }

    public boolean isDownloaded(String fileUrl) {
        if (fileUrl.endsWith(".apk")) {
            String fileName = getAPKNameByPath(fileUrl);
            File file = getDownloadFileByName(fileName);
            if (file != null && file.exists()) {
                try {
                    String absolutePath = file.getAbsolutePath();
                    if (AppSharePreference.getInstance().isAPKAlreadyOpen(absolutePath)) {
                        file.delete();
                        AppSharePreference.getInstance().setAPKAlreadyOpen(absolutePath, false);
                    } else {

                        AppSharePreference.getInstance().setAPKAlreadyOpen(absolutePath, true);
                        InstallUtil.installApk(AppCache.getContext(), absolutePath);
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }


    private class ProgressInterceptor implements Interceptor {

        private DownloadListener listener;

        public ProgressInterceptor(DownloadListener listener) {
            this.listener = listener;
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());

            return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), listener))
                    .build();
        }
    }

    private class ProgressResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final DownloadListener progressListener;
        private BufferedSource bufferedSource;

        public ProgressResponseBody(ResponseBody responseBody, DownloadListener progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();
        }


        @Override
        public long contentLength() {
            return responseBody.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    if (progressListener != null) {
                        progressListener.onDownloading(responseBody.contentLength(), totalBytesRead, bytesRead == -1);
                    }
                    return bytesRead;
                }
            };
        }
    }
}
