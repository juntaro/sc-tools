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
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import jp.co.xwave.sc.tool.entity.GetMetaDomainClassFindResponse;
import jp.co.xwave.sc.tool.entity.SqlSelectResponse;
import jp.co.xwave.sc.tool.entity.SqlSelectResponse.Column;
import jp.co.xwave.sc.tool.entity.SqlSelectResponse.Data;
import jp.co.xwave.sc.tool.entity.SqlSelectResponse.Obj0;

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
        if (args.length < 5 || isEmpty(args[0]) || isEmpty(args[1])
                || isEmpty(args[2]) || isEmpty(args[3]) || isEmpty(args[4])) {
            usage();
            System.exit(9);
        }
        new SCInsertStatementCreator().execute(
                args[0],
                args[1],
                args[2],
                args[3],
                Arrays.asList(args).subList(4, args.length)
                        .toArray(new String[0]));
    }

    SCSession session;

    /**
     * @param args
     */
    public void execute(String apiUrl, String tenant, String user,
            String password, String... tableNames) throws Exception {

        System.out.println("処理を開始しました。対象テーブル：" + Arrays.asList(tableNames));
        String proxyHost = "localhost";
        int proxyPort = 8888;

        session = new SCSession(apiUrl, tenant, user, password, proxyHost,
                proxyPort);

        // TODO ファイル名をまた考える by shin
        String fileName = "INSERT.sql";

        BufferedWriter bw = null;
        // ログイン
        try {
            bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(fileName)), "UTF-8"));
            session.login();

            // TODO メタ情報を取得してテーブル毎にPKを取得。
            GetMetaDomainClassFindResponse domain = getMetaDomainClass();
            System.out.println(domain);

            for (String tableName : tableNames) {
                SqlSelectResponse resp = selectAll(tableName);

                // TODO 全件削除はなしにする予定。DELETE文投げるだけでよいので。
                bw.write("#DELETE FROM " + tableName + ";\r\n");
                // SQLを組み立てる
                if (!resp.isError()) {
                    for (Obj0 obj0 : resp.getBody().get_obj0List()
                            .get_obj0List()) {
                        // TODO この辺はSqlSelectResponseに移動する予定。
                        // カラム名のMapを組み立てる
                        int i = 1;
                        Map<Integer, String> columnNameMap = new LinkedHashMap<>();
                        for (Column column : obj0.getColumnList()
                                .getColumnList()) {
                            columnNameMap.put(Integer.valueOf(i),
                                    column.getColumnPhyName());
                            i++;
                        }
                        for (Data data : obj0.getDataList().getDataList()) {
                            Map<String, Object> dataMap = new LinkedHashMap<>();
                            for (Map.Entry<QName, Object> entry : data
                                    .getAttributes().entrySet()) {
                                Integer columnNo = Integer.valueOf(entry
                                        .getKey().toString()
                                        .replaceAll("column_", ""));
                                dataMap.put(columnNameMap.get(columnNo),
                                        entry.getValue());
                            }
                            // TODO メタ情報から取得したPKを使ってDELETE文を発行する

                            StringBuilder buf = new StringBuilder();
                            buf.append("#INSERT INTO ");
                            buf.append(tableName);
                            buf.append(" (");
                            buf.append(StringUtils.join(dataMap.keySet()
                                    .iterator(), ","));
                            buf.append(") VALUES ('");
                            buf.append(StringUtils.join(dataMap.values()
                                    .iterator(), "','"));
                            buf.append("');\r\n");

                            bw.write(buf.toString());
                        }
                    }
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
        System.out
                .println("Usage: PDPTriggerFinder [apiURL] [tenantcode] [username] [password] [tableNames...]");
        System.out
                .println("ex) PDPTriggerFinder http://127.0.0.1/sinfoniacloud/api/ TEST user pass TBL1");
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
    private SqlSelectResponse selectAll(String tableName)
            throws UnsupportedEncodingException, JAXBException {
        String responseXML = session
                .sendRequestByPost(
                        "DataAccessSequence.xml?_limitCount=100000",
                        String.format(
                                "<request><body><_obj0List><_obj0 lineNo=\"1\" value=\"select * from %s\" tableName=\"Table1\" /></_obj0List></body></request>",
                                tableName), false);

        return XmlParser.convert(
                new ByteArrayInputStream(responseXML.getBytes("UTF-8")),
                SqlSelectResponse.class);
    }

    /**
     *
     * @return
     * @throws JAXBException
     * @throws UnsupportedEncodingException
     */
    private GetMetaDomainClassFindResponse getMetaDomainClass()
            throws UnsupportedEncodingException, JAXBException {
        String responseXML = session
                .sendRequestByGet(
                        "MetaInformation.getMetaDomainClassFind.xml?stereotype=DomainClass",
                        true);
        return XmlParser.convert(
                new ByteArrayInputStream(responseXML.getBytes("UTF-8")),
                GetMetaDomainClassFindResponse.class);
    }
}