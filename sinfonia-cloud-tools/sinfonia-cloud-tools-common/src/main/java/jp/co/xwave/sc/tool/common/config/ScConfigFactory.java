/**
 *
 */
package jp.co.xwave.sc.tool.common.config;

import static org.apache.commons.lang.StringUtils.*;
import jp.co.xwave.sc.tool.common.config.ScConfig.ScServerConfig;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author hirai
 *
 */
public class ScConfigFactory {

    /**
     *
     * @param path
     * @return
     */
    public static ScConfig create(String path) {
        try {
            Configuration c = new PropertiesConfiguration(path);

            ScConfig config = new ScConfig();
            config.setTenantCode(c.getString("tenantcode"));
            // 対象サーバ
            ScServerConfig target = new ScServerConfig();
            target.setUrl(c.getString("target.url"));
            target.setUser(c.getString("target.user"));
            target.setPassword(c.getString("target.password"));
            config.setTargetServer(target);
            // PAASサーバ
            if (isNotEmpty(c.getString("paas.url"))) {
                ScServerConfig paas = new ScServerConfig();
                paas.setUrl(c.getString("paas.url"));
                paas.setUser(c.getString("paas.user"));
                paas.setPassword(c.getString("paas.password"));
                config.setPaasServer(paas);
            }
            // プロキシサーバ
            if (isNotEmpty(c.getString("proxy.host"))) {
                config.setProxyHost(c.getString("proxy.host"));
                config.setProxyPort(c.getInt("proxy.port", 8888));
            }
            return config;
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
