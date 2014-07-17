/**
 *
 */
package jp.co.xwave.sc.tool.common.command.impl;

import jp.co.xwave.sc.tool.common.SCSession;
import jp.co.xwave.sc.tool.common.command.ScCommand;

/**
 * SCログアウト
 * 
 * @author hirai
 */
public class ScLogout implements ScCommand<Void, Void> {

    /**
     * {@inheritDoc}
     * <dl>
     * <dt>事前条件
     * <dd>ScSessionがログイン状態であること
     * <dt>事後条件
     * <dd>SCのログアウト要求を行う
     * </dl>
     */
    @Override
    public Void execute(SCSession session, Void req) {
        session.callApiByGet("Logout.xml");
        return null;
    }

}
