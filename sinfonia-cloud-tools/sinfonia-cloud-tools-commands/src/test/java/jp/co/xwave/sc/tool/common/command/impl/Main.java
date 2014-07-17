/**
 *
 */
package jp.co.xwave.sc.tool.common.command.impl;

import jp.co.xwave.sc.tool.common.SCSession;

/**
 * @author hirai
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SCSession session = new SCSession("http://210.129.198.137/sinfoniacloud/", "EDU02", "jhirai", "jhirai", "localhost", 8888);
        session.login();
        session.executeCommand(new ScQuickTestDeploy(), null);
        session.logout();
    }

}
