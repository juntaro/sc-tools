/**
 *
 */
package jp.co.xwave.sc.tool.common.command;

import jp.co.xwave.sc.tool.common.SCSession;

/**
 * SCコマンドI/F
 * 
 * @author hirai
 */
public interface ScCommand<REQ, RES> {

    /**
     * 指定されたSCセッション上でSCコマンドの実行を行う.
     * 
     * @param session SCセッション
     * @param req 要求
     * @return レスポンス
     */
    RES execute(SCSession session, REQ req);
}
