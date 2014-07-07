/**
 *
 */
package jp.co.xwave.sc.tool.config;

/**
 *
 */
public class ScConfig {

    /** テナントコード */
    private String tenantCode;

    /** PAASサーバー設定 */
    private ScServerConfig paasServer;

    /** ターゲットサーバー設定 */
    private ScServerConfig targetServer;

    /** プロキシサーバーホスト */
    private String proxyHost;

    /** プロキシサーバーポート */
    private int proxyPort;

    /**
     * @return tenantCode
     */
    public String getTenantCode() {
        return tenantCode;
    }

    /**
     * @param tenantCode セットする tenantCode
     */
    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    /**
     * @return paasServer
     */
    public ScServerConfig getPaasServer() {
        return paasServer;
    }

    /**
     * @param paasServer セットする paasServer
     */
    public void setPaasServer(ScServerConfig paasServer) {
        this.paasServer = paasServer;
    }

    /**
     * @return targetServer
     */
    public ScServerConfig getTargetServer() {
        return targetServer;
    }

    /**
     * @param targetServer セットする targetServer
     */
    public void setTargetServer(ScServerConfig targetServer) {
        this.targetServer = targetServer;
    }

    /**
     * @return proxyHost
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * @param proxyHost セットする proxyHost
     */
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    /**
     * @return proxyPort
     */
    public int getProxyPort() {
        return proxyPort;
    }

    /**
     * @param proxyPort セットする proxyPort
     */
    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * サーバー設定
     */
    public static class ScServerConfig {

        /** URL */
        private String url;

        /** ユーザ */
        private String user;

        /** パスワード */
        private String password;

        /**
         * @return url
         */
        public String getUrl() {
            return url;
        }

        /**
         * @param url セットする url
         */
        public void setUrl(String url) {
            this.url = url;
        }

        /**
         * @return user
         */
        public String getUser() {
            return user;
        }

        /**
         * @param user セットする user
         */
        public void setUser(String user) {
            this.user = user;
        }

        /**
         * @return password
         */
        public String getPassword() {
            return password;
        }

        /**
         * @param password セットする password
         */
        public void setPassword(String password) {
            this.password = password;
        }

    }
}
