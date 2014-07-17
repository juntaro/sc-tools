/**
 *
 */
package jp.co.xwave.sc.tool.common.command.impl;

import jp.co.xwave.sc.tool.common.SCSession;

/**
 * @author hirai
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SCSession session = new SCSession("http://localhost/sinfoniacloud/", "TENANT", "user", "password", "localhost", 8888);
        session.login();
        session.executeCommand(new ScQuickTestDeploy(), null);
        session.logout();
    }

}
