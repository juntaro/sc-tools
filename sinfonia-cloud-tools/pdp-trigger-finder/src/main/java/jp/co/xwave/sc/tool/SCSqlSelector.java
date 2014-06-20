/**
 *
 */
package jp.co.xwave.sc.tool;

import java.util.Collection;
import java.util.Map;

/**
 * @author hirai
 *
 */
public class SCSqlSelector {

    SCSession session;

    /**
     *
     * @param apiUrl
     * @param tenantCode
     * @param userName
     * @param password
     */
    public SCSqlSelector(String apiUrl, String tenantCode, String userName, String password) {
        session = new SCSession(apiUrl, tenantCode, userName, password);
    }

    /**
     *
     * @param apiUrl
     * @param tenantCode
     * @param userName
     * @param password
     * @param proxyHost
     * @param proxyPort
     */
    public SCSqlSelector(String apiUrl, String tenantCode, String userName, String password, String proxyHost, int proxyPort) {
        session = new SCSession(apiUrl, tenantCode, userName, password, proxyHost, proxyPort);
    }

    /**
     *
     * @param sql
     * @return
     */
    public Collection<Map<String, Object>> select(String... sql) {
        return null;
    }
}
