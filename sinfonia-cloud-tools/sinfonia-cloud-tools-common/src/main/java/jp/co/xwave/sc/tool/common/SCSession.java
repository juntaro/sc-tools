/**
 *
 */
package jp.co.xwave.sc.tool.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import jp.co.xwave.sc.tool.common.command.ScCommand;
import jp.co.xwave.sc.tool.common.command.impl.ScLogin;
import jp.co.xwave.sc.tool.common.command.impl.ScLogout;
import jp.co.xwave.sc.tool.common.iface.LoginRequest;
import jp.co.xwave.sc.tool.common.iface.LoginResponse;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author hirai
 *
 */
public class SCSession {

    private boolean isLoggedIn;
    private final String apiUrl;
    private final String tenantCode;
    private final String userName;
    private final String password;
    final BasicCookieStore cookieStore;
    final CloseableHttpClient client;
    private HttpHost proxy;
    private RequestConfig config;

    /**
     * コンストラクタ
     *
     * @param apiUrl
     * @param tenantCode
     * @param userName
     * @param password
     */
    public SCSession(String apiUrl, String tenantCode, String userName,
            String password) {
        this.tenantCode = tenantCode;
        this.userName = userName;
        this.password = password;
        this.apiUrl = apiUrl;
        cookieStore = new BasicCookieStore();
        client = HttpClients.custom().setDefaultCookieStore(cookieStore)
                .build();
        config = RequestConfig.custom().build();
    }

    /**
     * コンストラクタ
     *
     * @param apiUrl
     * @param tenantCode
     * @param userName
     * @param password
     * @param proxyHost
     * @param proxyPort
     */
    public SCSession(String apiUrl, String tenantCode, String userName,
            String password, String proxyHost, int proxyPort) {
        this(apiUrl, tenantCode, userName, password);
        proxy = new HttpHost("127.0.0.1", 8888, "http");
        config = RequestConfig.custom().setProxy(proxy).build();
    }

    /**
     * ログイン.
     *
     * @throws UnsupportedEncodingException
     */
    public void login() {
        LoginRequest req = new LoginRequest();
        req.setBody(new LoginRequest.Body());
        req.getBody().setCompanyCD(tenantCode);
        req.getBody().setUserID(userName);
        req.getBody().setPassword(password);
        LoginResponse resp = new ScLogin().execute(this, req);
        if (!resp.isError()) {
            isLoggedIn = true;
        }
    }

    /**
     * ログアウト.
     */
    public void logout() {
        if (isLoggedIn) {
            new ScLogout().execute(this, null);
            isLoggedIn = false;
        }
    }

    /**
     * コマンド実行.
     * @param command
     * @param req
     * @return
     */
    public <T, S> S executeCommand(ScCommand<T, S> command, T req) {
        return command.execute(this, req);
    }

    // --- ここから下はScCommandからしか使いたくない。
    /**
     * リクエスト送受信.
     *
     * @param requestBody
     * @return
     */
    public String sendRequestByPost(String xmlUrl, String requestBody) {
        return sendRequestByPost(xmlUrl, requestBody, false);
    }

    /**
     * リクエスト送受信.
     * @param requestBody
     * @param asService
     *
     * @return
     */
    public String sendRequestByPost(String xmlUrl, String requestBody, boolean asService) {
        HttpPost post = new HttpPost(apiUrl + (asService ? "service/" : "api/") + xmlUrl);
        StringEntity entity = new StringEntity(requestBody, "UTF-8");
        post.setEntity(entity);
        HttpResponse res = sendHttpRequest(post);
        return toString(res);
    }

    /**
     *
     * @param xmlUrl
     * @return
     */
    public String sendRequestByGet(String xmlUrl) {
        return sendRequestByGet(xmlUrl, false);
    }

    /**
     *
     * @param xmlUrl
     * @param asService
     * @return
     */
    public String sendRequestByGet(String xmlUrl, boolean asService) {
        HttpGet get = new HttpGet(apiUrl + (asService ? "service/" : "api/") + xmlUrl);
        HttpResponse res = sendHttpRequest(get);
        return toString(res);
    }

    /**
     *
     * @param response
     * @return
     */
    private String toString(HttpResponse response) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try {
            response.getEntity().writeTo(bs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return bs.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * HTTPリクエスト送信.
     *
     * @param request
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    private HttpResponse sendHttpRequest(HttpRequestBase req) {
        req.setHeader("Content-Type", "text/xml");
        req.setHeader("Accept-Language", "ja");
        req.setHeader("X-UserAgent", "2.4.0.8");
        req.setHeader("Host", req.getURI().getHost());
        req.setConfig(config);
        try {
            HttpResponse res = client.execute(req);
            if (res.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException(String.format(
                        "HTTPリクエスト失敗。URL:%s, HTTPステータス・コード：%d", req.getURI(),
                        res.getStatusLine().getStatusCode()));
            }
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
