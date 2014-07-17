/**
 *
 */
package jp.co.xwave.sc.tool.common.command.impl;

import javax.xml.bind.JAXBException;

import jp.co.xwave.sc.tool.common.SCSession;
import jp.co.xwave.sc.tool.common.XmlParser;
import jp.co.xwave.sc.tool.common.command.ScCommand;
import jp.co.xwave.sc.tool.common.iface.LoginRequest;
import jp.co.xwave.sc.tool.common.iface.LoginResponse;

/**
 * @author hirai
 *
 */
public class ScLogin implements ScCommand<LoginRequest, LoginResponse> {

    /* (非 Javadoc)
     * @see jp.co.xwave.sc.tool.common.command.ScCommand#execute(jp.co.xwave.sc.tool.common.SCSession, java.lang.Object)
     */
    @Override
    public LoginResponse execute(SCSession session, LoginRequest req) {
        try {
            String response = session.sendRequestByPost("Login.xml", XmlParser.convert(req, LoginRequest.class));
            return XmlParser.convert(response, LoginResponse.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
