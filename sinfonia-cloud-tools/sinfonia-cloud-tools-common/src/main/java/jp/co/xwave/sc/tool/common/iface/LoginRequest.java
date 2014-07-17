package jp.co.xwave.sc.tool.common.iface;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hirai
 *
 */
@XmlRootElement(name = "request")
public class LoginRequest {

    /** Body. */
    private Body body;

    /**
     * @return body
     */
    @XmlElement
    public Body getBody() {
        return body;
    }

    /**
     * @param body セットする body
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * @author hirai
     */
    public static class Body {

        /** */
        private String companyCD;

        /** */
        private String userID;

        /** */
        private String password;

        /**
         * @return companyCD
         */
        @XmlAttribute
        public String getCompanyCD() {
            return companyCD;
        }

        /**
         * @return userID
         */
        @XmlAttribute
        public String getUserID() {
            return userID;
        }

        /**
         * @return password
         */
        @XmlAttribute
        public String getPassword() {
            return password;
        }

        /**
         * @param companyCD セットする companyCD
         */
        public void setCompanyCD(String companyCD) {
            this.companyCD = companyCD;
        }

        /**
         * @param userID セットする userID
         */
        public void setUserID(String userID) {
            this.userID = userID;
        }

        /**
         * @param password セットする password
         */
        public void setPassword(String password) {
            this.password = password;
        }


    }
}
