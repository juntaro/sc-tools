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
 * SC設定のファクトリ
 * 
 * @author hirai
 */
public class ScConfigFactory {

    /**
     * 指定されたファイルパスからSC設定を生成する
     * <dl>
     * <dt>事前条件
     * <dd>正しいフォーマットの設定ファイルのパスが指定されていること
     * <dt>事後条件
     * <dd>設定ファイルの内容で{@link ScConfig}が生成される
     * </dl>
     * 設定ファイルに設定可能な項目は以下のとおり
     * <dl>
     * <dt>tenantcode
     * <dd>テナントコード
     * <dt>target.url
     * <dd>対象サーバのURL <code>http://localhost/sinfoniacloud</code>
     * <dt>target.user
     * <dd>対象サーバにログインするためのユーザID
     * <dt>target.password
     * <dd>対象サーバにログインするためのパスワード
     * <dt>paas.url
     * <dd>メタ情報を取得するためのPAAS環境のURL(対象サーバがPAAS環境の場合は不要)
     * <dt>paas.user
     * <dd>PAAS環境にログインするためのユーザID
     * <dt>paas.password
     * <dd>PAAS環境にログインするためのパスワード
     * <dt>proxy.host
     * <dd>プロキシサーバのホスト
     * <dt>proxy.port
     * <dd>プロキシサーバのポート
     * </dl>
     * 
     * @param path 設定ファイルパス
     * @return {@link ScConfig}
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
