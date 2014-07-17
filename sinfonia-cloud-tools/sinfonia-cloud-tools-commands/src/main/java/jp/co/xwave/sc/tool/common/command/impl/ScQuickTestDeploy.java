/**
 *
 */
package jp.co.xwave.sc.tool.common.command.impl;

import jp.co.xwave.sc.tool.common.SCSession;
import jp.co.xwave.sc.tool.common.command.ScCommand;

/**
 * @author hirai
 *
 */
public class ScQuickTestDeploy implements ScCommand<Void, Void> {

    /* (非 Javadoc)
     * @see jp.co.xwave.sc.tool.common.command.ScCommand#execute(jp.co.xwave.sc.tool.common.SCSession, java.lang.Object)
     */
    @Override
    public Void execute(SCSession session, Void req) {
        session.callApiByGet("QuickTestView.json");
        return null;
    }
}
