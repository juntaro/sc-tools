/**
 * 
 */
package jp.co.xwave.sc.tool.cui;

import jp.co.xwave.sc.tool.common.command.impl.ScQuickTestDeploy;

/**
 * QT環境構築バッチ
 * 
 * @author hirai
 */
public class QuickTestDeployer extends AbstractScToolBatch {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process() {
        session.executeCommand(new ScQuickTestDeploy(), null);
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        new QuickTestDeployer().execute(args);
    }

}
