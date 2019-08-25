package com.zh.springbootcurd.provider;

import com.alibaba.fastjson.JSON;
import com.zh.springbootcurd.dto.AccessTokenDto;
import com.zh.springbootcurd.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDto accessTokenDto){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDto));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String s = response.body().string();
                System.out.println(s);
                String[] ss = s.split("&");
                String[] sss = ss[0].split("=");
                String string = sss[1];
                //System.out.println(string);
                return string;
        } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }
    public GithubUser githubUser(String accesstoken) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.github.com/user?access_token="+accesstoken)
                .build();
        Response response = client.newCall(request).execute();
        String s = response.body().string();
        GithubUser githubUser = JSON.parseObject(s, GithubUser.class);
        return githubUser;
    }
}
