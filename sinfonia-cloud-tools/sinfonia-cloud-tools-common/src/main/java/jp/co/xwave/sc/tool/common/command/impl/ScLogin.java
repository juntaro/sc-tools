/**
 *
 */
package jp.co.xwave.sc.tool.common.command.impl;

import jp.co.xwave.sc.tool.common.SCSession;
import jp.co.xwave.sc.tool.common.XmlParser;
import jp.co.xwave.sc.tool.common.command.ScCommand;
import jp.co.xwave.sc.tool.common.iface.LoginRequest;
import jp.co.xwave.sc.tool.common.iface.LoginResponse;

/**
 * SCログイン.
 * 
 * @author hirai
 */
public class ScLogin implements ScCommand<LoginRequest, LoginResponse> {

    /**
     * {@inheritDoc}
     * <dl>
     * <dt>事前条件
     * <dd>SCSessionにログインする環境の接続情報が設定されていること（URL、テナント、ユーザー、パスワード）
     * <dt>事後条件
     * <dd>SCの認証サービスの呼び出しを行い、結果を返却する
     * </dl>
     */
    @Override
    public LoginResponse execute(SCSession session, LoginRequest req) {
        String response = session.callApiByPost("Login.xml", XmlParser.convert(req, LoginRequest.class));
        return XmlParser.convert(response, LoginResponse.class);
    }

}
