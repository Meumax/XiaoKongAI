package cn.edu.baiyunu.myapplication.wenxinyiyan;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class YiClient {

    // API_URL 从 https://console.bce.baidu.com/qianfan/ais/console/onlineService 点击服务名称后面的复制按钮进行复制
    private static final String API_URL = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/yi_34b_chat"; // 替换为文心一言API的URL

    // API_KEY 和 SECRET_KEY 从 https://console.bce.baidu.com/qianfan/ais/console/applicationConsole/application 获取
    private static final String API_KEY = "ZscVI7S5KENSEKVRyAvqrtuH"; // 替换为你的API Key
    private static final String SECRET_KEY = "MEiaXZfnTlpmWkOloDe9Nie5cCMVsB3u";

    private static final ArrayList<YiMessage> messages = new ArrayList<>(); // 对话上下文

    private Handler handler;    // 用户子线程向主线程发送消息

    public YiClient(Handler handler) {
        this.handler = handler;
    }

    public void sendRequest(String question)  {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .connectTimeout(0, TimeUnit.SECONDS) // 连接超时
                            .writeTimeout(0, TimeUnit.SECONDS)   // 写入超时
                            .readTimeout(0, TimeUnit.SECONDS)    // 读取超时
                            .build();

                    // 创建请求体
                    RequestBody body = createRequestBody(question);

                    // 发送请求
                    Request request = new Request.Builder()
                            .url(API_URL + "?access_token=" + getAccessToken())
                            .header("Content-Type", "application/json")
                            .post(body)
                            .build();

                    // 处理响应数据
                    Response response = client.newCall(request).execute();
                    YiRespondBody respondBody = new Gson().fromJson(response.body().string(), YiRespondBody.class);
                    messages.add(new YiMessage("assistant", respondBody.getResult()));

                    // 获取AI响应文本并传递给UI线程
                    Message message = Message.obtain();
                    message.obj = respondBody.getResult();
                    handler.sendMessage(message);
                }catch (Exception e){
                    Log.i("MyTag", "sendRequest: "+e);
                }
            }
        }).start();
    }

    // 获取鉴权
    private static String getAccessToken(){
        String accessToken = "";
        try {
//            OkHttpClient client = new OkHttpClient();
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(0, TimeUnit.SECONDS) // 连接超时
                    .writeTimeout(0, TimeUnit.SECONDS)   // 写入超时
                    .readTimeout(0, TimeUnit.SECONDS)    // 读取超时
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://aip.baidubce.com/oauth/2.0/token?client_id="+API_KEY+"&client_secret="+SECRET_KEY+"&grant_type=client_credentials")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            Gson gson = new Gson();
            AccessTokenResponBody accessTokenResponBody = gson.fromJson(response.body().string(), AccessTokenResponBody.class);
            return accessTokenResponBody.getAccess_token();
        }catch (Exception e){
            Log.i("MyTAG", "getAccessToken: "+e);
        }
        return accessToken;
    }

    // 创建请求 body，字段参考官方文档
    private static RequestBody createRequestBody(String question){
        if (messages.size()!=0) {
            YiMessage lastMessage = messages.get(messages.size() - 1);
            if (lastMessage.getRole().equals("user")){
                messages.add(new YiMessage("assistant", question));
            }else {
                messages.add(new YiMessage("user", question));
            }
        }else {
            messages.add(new YiMessage("user", question));
        }
        YiRequestBody requestBody = new YiRequestBody(messages);

        String s = new Gson().toJson(requestBody);
//        System.out.println(s);

        // 构建请求体（这里假设API需要POST请求并包含问题作为参数）
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, s);
        return body;
    }
}
