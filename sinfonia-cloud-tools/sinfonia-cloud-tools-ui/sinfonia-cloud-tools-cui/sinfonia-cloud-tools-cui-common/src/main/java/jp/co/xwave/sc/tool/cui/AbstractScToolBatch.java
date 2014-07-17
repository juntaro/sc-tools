/**
 * 
 */
package jp.co.xwave.sc.tool.cui;

import java.io.File;
import java.io.FileNotFoundException;

import jp.co.xwave.sc.tool.common.SCSession;
import jp.co.xwave.sc.tool.common.config.ScConfig;
import jp.co.xwave.sc.tool.common.config.ScConfigFactory;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * @author hirai
 */
public abstract class AbstractScToolBatch {

    /** SC設定 */
    private ScConfig config;

    /** コマンドラインオプション */
    protected Options commandLineOptions;

    /** SCセッション */
    protected SCSession session;

    /** PAASセッション */
    protected SCSession paasSession;

    /**
     * コンストラクタ.
     */
    protected AbstractScToolBatch() {
        commandLineOptions = new Options();
        initCmdOptions();
    }

    /**
     * コマンドラインオプションの初期化
     */
    protected void initCmdOptions() {
        @SuppressWarnings("static-access")
        Option configfile = OptionBuilder.withArgName("config")
                        .isRequired()
                        .hasArg()
                        .withDescription("configuration file")
                        .create("config");
        commandLineOptions.addOption(configfile);
    }

    /**
     * バッチメイン処理実行.
     * 
     * @param args
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public void execute(String[] args) throws FileNotFoundException, ParseException {
        CommandLine cmd = new PosixParser().parse(commandLineOptions, args);
        String configPath = cmd.getOptionValue("config");

        if (!new File(configPath).exists()) {
            throw new FileNotFoundException(String.format("%s is not found.", configPath));
        }
        config = ScConfigFactory.create(configPath);

        SCSession session = new SCSession(config.getTargetServer().getUrl(), config.getTenantCode(),
                        config.getTargetServer().getUser(), config.getTargetServer().getPassword(),
                        config.getProxyHost(), config.getProxyPort());
        if (config.getPaasServer() != null) {
            paasSession = new SCSession(config.getPaasServer().getUrl(), config.getTenantCode(),
                            config.getPaasServer().getUser(), config.getPaasServer().getPassword(),
                            config.getProxyHost(), config.getProxyPort());
        }
        try {
            // SCログイン
            session.login();

            // 処理実行
            process();
        } finally {
            // SCログアウト
            session.logout();
        }
    }

    /**
     * 処理実行
     */
    abstract protected void process();
}
