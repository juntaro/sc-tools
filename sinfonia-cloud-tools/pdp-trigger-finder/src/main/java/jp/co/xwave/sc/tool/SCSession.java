/**
 *
 */
package jp.co.xwave.sc.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
     * @param apiUrl TODO
     * @param tenantCode
     * @param userName
     * @param password
     */
    public SCSession(String apiUrl, String tenantCode, String userName, String password) {
        this.tenantCode = tenantCode;
        this.userName = userName;
        this.password = password;
        this.apiUrl = apiUrl;
        cookieStore = new BasicCookieStore();
        client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        config = RequestConfig.custom().build();
    }

    /**
     * コンストラクタ
     * @param apiUrl
     * @param tenantCode
     * @param userName
     * @param password
     * @param proxyHost
     * @param proxyPort
     */
    public SCSession(String apiUrl, String tenantCode, String userName, String password, String proxyHost, int proxyPort) {
        this(apiUrl, tenantCode, userName, password);
        proxy = new HttpHost("127.0.0.1", 8888, "http");
        config = RequestConfig.custom().setProxy(proxy).build();
    }

    /**
     * ログイン.
     */
    public void login() {
        String requestXml = String
                .format("<request><body companyCD=\"%s\" userID=\"%s\" password=\"%s\" /></request>",
                        tenantCode, userName, password);
        HttpPost post = new HttpPost(apiUrl + "Login.xml");
        StringEntity entity = new StringEntity(requestXml, "UTF-8");
        post.setEntity(entity);
        sendHttpRequest(post);
        sendRequestByPost("Login.xml", requestXml);
    }

    /**
     * ログアウト.
     */
    public void logout() {
        HttpGet get = new HttpGet(apiUrl + "Logout.xml");
        sendHttpRequest(get);
    }

    /**
     * リクエスト送受信.
     *
     * @param requestBody
     * @return
     */
    public String sendRequestByPost(String xmlUrl, String requestBody) {
        HttpPost post = new HttpPost(apiUrl + xmlUrl);
        StringEntity entity = new StringEntity(requestBody, "UTF-8");
        post.setEntity(entity);
        HttpResponse res = sendHttpRequest(post);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try {
            res.getEntity().writeTo(bs);
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
        req.setHeader("X-UserAgent","2.4.0.8");
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
