/**
 *
 */
package jp.co.xwave.sc.tool.common;

/**
 * @author hirai
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ
        SCSession session = new SCSession("http://210.129.198.137/sinfoniacloud/", "HIRAI", "jhirai", "jhirai", "localhost", 8888);
        session.login();
        session.logout();
    }

}
