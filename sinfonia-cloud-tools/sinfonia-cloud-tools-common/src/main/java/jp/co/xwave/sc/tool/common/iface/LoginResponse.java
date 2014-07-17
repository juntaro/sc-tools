/**
 *
 */
package jp.co.xwave.sc.tool.common.iface;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hirai
 */
@XmlRootElement(name = "response")
public class LoginResponse extends ToStringObject {

    /** エラー有無 */
    private boolean isError;

    /** Body. */
    private Body body;

    /**
     * @return isError
     */
    @XmlAttribute
    public boolean isError() {
        return isError;
    }

    /**
     * @param isError
     *            セットする isError
     */
    public void setError(boolean isError) {
        this.isError = isError;
    }

    /**
     * @return body
     */
    @XmlElement
    public Body getBody() {
        return body;
    }

    /**
     * @param body
     *            セットする body
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     *
     * @author hirai
     */
    public static class Body extends ToStringObject {

        /** 管理者フラグ */
        private String adminFlg;

        /** カンパニーコード */
        private String companyCD;

        /** 管理ツール利用有無 */
        private boolean configAvailable;

        /** 言語 */
        private String lang;

        /** パスワード有効期限日 */
        private String passwordExpiryDate;

        /** ロール */
        private String role;

        /** システムテナントコード */
        private String sys_tenantcode;

        /** テナント名 */
        private String tenantName;

        /** タイムゾーン */
        private String timeZone;

        /** ユーザーID */
        private String userID;

        /** ユーザー名 */
        private String userName;

        /**
         * @return adminFlg
         */
        @XmlAttribute
        public String getAdminFlg() {
            return adminFlg;
        }

        /**
         * @param adminFlg
         *            セットする adminFlg
         */
        public void setAdminFlg(String adminFlg) {
            this.adminFlg = adminFlg;
        }

        /**
         * @return companyCD
         */
        @XmlAttribute
        public String getCompanyCD() {
            return companyCD;
        }

        /**
         * @param companyCD
         *            セットする companyCD
         */
        public void setCompanyCD(String companyCD) {
            this.companyCD = companyCD;
        }

        /**
         * @return configAvailable
         */
        @XmlAttribute(name = "configAvailable")
        public boolean isConfigAvailable() {
            return configAvailable;
        }

        /**
         * @param configAvailable
         *            セットする configAvailable
         */
        public void setConfigAvailable(boolean configAvailable) {
            this.configAvailable = configAvailable;
        }

        /**
         * @return lang
         */
        @XmlAttribute
        public String getLang() {
            return lang;
        }

        /**
         * @param lang
         *            セットする lang
         */
        public void setLang(String lang) {
            this.lang = lang;
        }

        /**
         * @return passwordExpiryDate
         */
        @XmlAttribute
        public String getPasswordExpiryDate() {
            return passwordExpiryDate;
        }

        /**
         * @param passwordExpiryDate
         *            セットする passwordExpiryDate
         */
        public void setPasswordExpiryDate(String passwordExpiryDate) {
            this.passwordExpiryDate = passwordExpiryDate;
        }

        /**
         * @return role
         */
        @XmlAttribute
        public String getRole() {
            return role;
        }

        /**
         * @param role
         *            セットする role
         */
        public void setRole(String role) {
            this.role = role;
        }

        /**
         * @return sys_tenantcode
         */
        @XmlAttribute
        public String getSys_tenantcode() {
            return sys_tenantcode;
        }

        /**
         * @param sys_tenantcode
         *            セットする sys_tenantcode
         */
        public void setSys_tenantcode(String sys_tenantcode) {
            this.sys_tenantcode = sys_tenantcode;
        }

        /**
         * @return tenantName
         */
        @XmlAttribute
        public String getTenantName() {
            return tenantName;
        }

        /**
         * @param tenantName
         *            セットする tenantName
         */
        public void setTenantName(String tenantName) {
            this.tenantName = tenantName;
        }

        /**
         * @return timeZone
         */
        @XmlAttribute
        public String getTimeZone() {
            return timeZone;
        }

        /**
         * @param timeZone
         *            セットする timeZone
         */
        public void setTimeZone(String timeZone) {
            this.timeZone = timeZone;
        }

        /**
         * @return userID
         */
        @XmlAttribute
        public String getUserID() {
            return userID;
        }

        /**
         * @param userID
         *            セットする userID
         */
        public void setUserID(String userID) {
            this.userID = userID;
        }

        /**
         * @return userName
         */
        @XmlAttribute
        public String getUserName() {
            return userName;
        }

        /**
         * @param userName
         *            セットする userName
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

    }
}
