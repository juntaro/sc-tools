/**
 *
 */
package jp.co.xwave.sc.tool.common;

/**
 * @author hirai
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ
        SCSession session = new SCSession("http://localhost/sinfoniacloud/", "TENANT", "user", "password", "localhost", 8888);
        session.login();
        session.logout();
    }

}
