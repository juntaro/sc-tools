/**
 *
 */
package jp.co.xwave.sc.tool;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import javax.xml.bind.JAXBException;

import jp.co.xwave.sc.tool.config.ScConfig;
import jp.co.xwave.sc.tool.config.ScConfigFactory;
import jp.co.xwave.sc.tool.entity.GetMetaDomainClassAttributeFindResponse;
import jp.co.xwave.sc.tool.entity.GetMetaDomainClassFindResponse;
import jp.co.xwave.sc.tool.entity.SqlSelectResponse;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.lang.StringUtils;

/**
 * @author hirai
 *
 */
public class SCInsertStatementCreator {

    /**
     *
     */
    static Options options;

    static {
        options = new Options();
        @SuppressWarnings("static-access")
        Option configfile = OptionBuilder.withArgName("config")
                .isRequired()
                .hasArg()
                .withDescription("configuration file")
                .create("config");
        options.addOption(configfile);
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        CommandLine cmd = new PosixParser().parse(options, args);
        String configPath = cmd.getOptionValue("config");
        String[] tables = cmd.getArgs();

        if (!new File(configPath).exists()) {
            System.out.println(String.format("%s is not exists.", configPath));
            System.exit(9);
        }
        ScConfig config = ScConfigFactory.create(configPath);
        new SCInsertStatementCreator().execute(config, tables);
    }

    ScConfig config;

    SCSession session;

    SCSession paasSession;

    /**
     * @param args
     */
    public void execute(ScConfig config, String... tableNames) throws Exception {
        System.out.println("処理を開始しました。対象テーブル：" + Arrays.asList(tableNames));

        if (isEmpty(config.getProxyHost())) {
            session = new SCSession(config.getTargetServer().getUrl(), config.getTenantCode(), config.getTargetServer().getUser(), config.getTargetServer().getPassword());
            if (config.getPaasServer() != null) {
                paasSession = new SCSession(config.getPaasServer().getUrl(), config.getTenantCode(), config.getPaasServer().getUser(), config.getPaasServer().getPassword());
                ;
            }
        } else {
            session = new SCSession(config.getTargetServer().getUrl(), config.getTenantCode(),
                    config.getTargetServer().getUser(), config.getTargetServer().getPassword(),
                    config.getProxyHost(), config.getProxyPort());
            if (config.getPaasServer() != null) {
                paasSession = new SCSession(config.getPaasServer().getUrl(), config.getTenantCode(),
                        config.getPaasServer().getUser(), config.getPaasServer().getPassword(),
                        config.getProxyHost(), config.getProxyPort());
            }
        }

        // TODO ファイル名をまた考える by shin
        String fileName = "INSERT.sql";

        BufferedWriter bw = null;
        // ログイン
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8"));
            session.login();
            if (paasSession != null) {
                paasSession.login();
            }

            // TODO メタ情報を取得してテーブル毎にPKを取得。
            GetMetaDomainClassFindResponse domain = getMetaDomainClass();
            GetMetaDomainClassAttributeFindResponse attribute = getMetaDomainClassAttribute();

            for (String tableName : tableNames) {
                String classPhysicalName = domain.getClassPhysicalName(tableName);
                Collection<String> primaryKeys = attribute.getTablePrimaryKeys(classPhysicalName);

                SqlSelectResponse resp = selectAll(tableName);

                // 全件削除はなしにする予定。
                // bw.write("#DELETE FROM " + tableName + ";\r\n");
                // SQLを組み立てる
                for (Map<String, Object> dataMap : resp.getTableDataList()) {
                    // メタ情報から取得したPKを使ってDELETE文を発行する
                    bw.write(createDeleteStatement(tableName, primaryKeys, dataMap));
                    // INSERT文を発行する
                    bw.write(createInsertStatement(tableName, dataMap));

                }
                bw.write("#COMMIT;\r\n");
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
            // ログアウト
            session.logout();
            if (paasSession != null) {
                paasSession.logout();
            }
        }
        System.out.println("処理を終了しました。");
    }

    /**
     *
     * @param value
     * @return
     */
    private static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }

    /**
     *
     * @param tableName
     * @return
     * @throws UnsupportedEncodingException
     * @throws JAXBException
     */
    private SqlSelectResponse selectAll(String tableName) throws UnsupportedEncodingException, JAXBException {
        String responseXML = session
                .sendRequestByPost("DataAccessSequence.xml?_limitCount=100000",
                        String.format("<request><body><_obj0List><_obj0 lineNo=\"1\" value=\"select * from %s\" tableName=\"Table1\" /></_obj0List></body></request>", tableName), false);

        return XmlParser.convert(new ByteArrayInputStream(responseXML.getBytes("UTF-8")), SqlSelectResponse.class);
    }

    /**
     *
     * @return
     * @throws JAXBException
     * @throws UnsupportedEncodingException
     */
    private GetMetaDomainClassFindResponse getMetaDomainClass() throws UnsupportedEncodingException, JAXBException {
        SCSession metaSession = paasSession == null ? session : paasSession;
        String responseXML = metaSession
                .sendRequestByGet("MetaInformation.getMetaDomainClassFind.xml?stereotype=DomainClass", true);
        return XmlParser.convert(new ByteArrayInputStream(responseXML.getBytes("UTF-8")), GetMetaDomainClassFindResponse.class);
    }

    /**
     *
     * @return
     * @throws UnsupportedEncodingException
     * @throws JAXBException
     */
    private GetMetaDomainClassAttributeFindResponse getMetaDomainClassAttribute() throws UnsupportedEncodingException, JAXBException {
        SCSession metaSession = paasSession == null ? session : paasSession;
        String responseXML = metaSession
                .sendRequestByGet("DomainAttributeUtility.getMetaDomainClassAttributeFind.xml?stereotype=DomainClass", true);
        return XmlParser.convert(new ByteArrayInputStream(responseXML.getBytes("UTF-8")), GetMetaDomainClassAttributeFindResponse.class);
    }

    /**
     *
     * @param tableName
     * @param primaryKeys
     * @param dataMap
     * @return
     */
    private String createDeleteStatement(String tableName, Collection<String> primaryKeys, Map<String, Object> dataMap) {
        StringBuilder buf = new StringBuilder();
        buf.append("#DELETE FROM ");
        buf.append(tableName);
        buf.append(" WHERE ");
        boolean isFirst = true;
        for (String primaryKey : primaryKeys) {
            if (isFirst) {
                isFirst = false;
            } else {
                buf.append(" AND ");
            }
            buf.append(primaryKey);
            buf.append(" = '");
            buf.append(dataMap.get(primaryKey.toLowerCase())); //TODO カラムのメタ情報から取れる名前が全部小文字なので。
            buf.append("'");
        }
        // システムテナントコードが必ずPKに入る。
        buf.append(" AND sys_tenantcode = '");
        buf.append(dataMap.get("sys_tenantcode"));
        buf.append("'");
        buf.append(";\r\n");
        return buf.toString();
    }

    /**
     *
     * @param tableName
     * @param dataMap
     * @return
     */
    private String createInsertStatement(String tableName, Map<String, Object> dataMap) {
        StringBuilder buf = new StringBuilder();
        buf.append("#INSERT INTO ");
        buf.append(tableName);
        buf.append(" (");
        buf.append(StringUtils.join(dataMap.keySet().iterator(), ","));
        buf.append(") VALUES ('");
        buf.append(StringUtils.join(dataMap.values().iterator(), "','"));
        buf.append("');\r\n");
        return buf.toString();
    }
}
