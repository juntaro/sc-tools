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

import jp.co.xwave.sc.tool.entity.GetMetaDomainClassAttributeFindResponse;
import jp.co.xwave.sc.tool.entity.GetMetaDomainClassFindResponse;
import jp.co.xwave.sc.tool.entity.SqlSelectResponse;

import org.apache.commons.lang.StringUtils;

/**
 * @author hirai
 *
 */
public class SCInsertStatementCreator {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 5 || isEmpty(args[0]) || isEmpty(args[1]) || isEmpty(args[2]) || isEmpty(args[3]) || isEmpty(args[4])) {
            usage();
            System.exit(9);
        }
        new SCInsertStatementCreator().execute(args[0], args[1], args[2], args[3], Arrays.asList(args).subList(4, args.length).toArray(new String[0]));
    }

    SCSession session;

    /**
     * @param args
     */
    public void execute(String apiUrl, String tenant, String user, String password, String... tableNames) throws Exception {

        System.out.println("処理を開始しました。対象テーブル：" + Arrays.asList(tableNames));
        // TODO proxy無効化？
        //        String proxyHost = "localhost";
        //        int proxyPort = 8888;

        session = new SCSession(apiUrl, tenant, user, password);

        // TODO ファイル名をまた考える by shin
        String fileName = "INSERT.sql";

        BufferedWriter bw = null;
        // ログイン
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8"));
            session.login();

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
        }
        System.out.println("処理を終了しました。");
    }

    /**
     * Usage
     */
    private static void usage() {
        System.out.println("Usage: PDPTriggerFinder [apiURL] [tenantcode] [username] [password] [tableNames...]");
        System.out.println("ex) PDPTriggerFinder http://127.0.0.1/sinfoniacloud/ TEST user pass TBL1");
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
        String responseXML = session
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
        String responseXML = session
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
