/**
 *
 */
package jp.co.xwave.sc.tool.common.command;

import jp.co.xwave.sc.tool.common.SCSession;

/**
 * @author hirai
 *
 */
public interface ScCommand<REQ, RES> {

    /**
     *
     * @param res
     * @return
     */
    RES execute(SCSession session, REQ req);
}
