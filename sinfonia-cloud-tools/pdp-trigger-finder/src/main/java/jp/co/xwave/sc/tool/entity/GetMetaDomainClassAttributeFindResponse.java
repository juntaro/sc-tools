/**
 *
 */
package jp.co.xwave.sc.tool.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hirai
 *
 */
@XmlRootElement(name = "response")
public class GetMetaDomainClassAttributeFindResponse extends ToStringObject {
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
     * @param classPhysicalName
     * @return
     */
    public Collection<String> getTablePrimaryKeys(String classPhysicalName) {
        // TODO
        if (isError) {
            throw new RuntimeException("GetMetaDomainClassAttributeFind is ERROR.");
        }
        Collection<String> primaryKeys = new ArrayList<>();
        for (MetaAttribute attribute : getBody().getObj0List().getMetaAttributes()) {
            if (attribute.getClassPhysicalName().equals(classPhysicalName) && "pk".equalsIgnoreCase(attribute.getStereotype())) {
                primaryKeys.add(attribute.getDbColumnName());
            }
        }
        if (primaryKeys.size() == 0) {
            throw new RuntimeException(String.format("%s table primary key not found.", classPhysicalName));
        }
        return primaryKeys;
    }

    /**
     *
     * @author hirai
     */
    public static class Body extends ToStringObject {

        /** */
        private Obj0List obj0List;

        /**
         * @return obj0List
         */
        @XmlElement(name = "_obj0List")
        public Obj0List getObj0List() {
            return obj0List;
        }

        /**
         * @param obj0List セットする obj0List
         */
        public void setObj0List(Obj0List obj0List) {
            this.obj0List = obj0List;
        }

    }

    /**
     *
     * @author hirai
     */
    public static class Obj0List extends ToStringObject {

        /** */
        private Collection<MetaAttribute> metaAttributes;

        /**
         * @return metaAttributes
         */
        @XmlElement(name = "_obj0")
        public Collection<MetaAttribute> getMetaAttributes() {
            return metaAttributes;
        }

        /**
         * @param metaAttributes セットする metaAttributes
         */
        public void setMetaAttributes(Collection<MetaAttribute> metaAttributes) {
            this.metaAttributes = metaAttributes;
        }

    }

    /**
     *
     * @author hirai
     */
    public static class MetaAttribute extends ToStringObject {

        /** */
        private String CID;
        /** */
        private String attributeJavaType;
        /** */
        private String attributeName;
        /** */
        private String attributePhysicalName;
        /** */
        private boolean autoGenerate;
        /** */
        private String className;
        /** */
        private String classPhysicalName;
        /** */
        private String createDate;
        /** */
        private String createTime;
        /** */
        private String createdBy;
        /** */
        private String dbColType;
        /** */
        private String dbColumnLength;
        /** */
        private String dbColumnName;
        /** */
        private boolean deleteFlag;
        /** */
        private boolean encryptFlg;
        /** */
        private String fkList;
        /** */
        private String initialValue;
        /** */
        private String lengthCheckErrMsgId;
        /** */
        private String note;
        /** */
        private boolean nullable;
        /** */
        private String packageName;
        /** */
        private String packagePhysicalName;
        /** */
        private String programName;
        /** */
        private String requiredCheckErrMsgId;
        /** */
        private String sortKey;
        /** */
        private String staticOrInstance;
        /** */
        private String stereotype;
        /** */
        private String sys_tenantCode;
        /** */
        private String typeCheckErrMsgId;
        /** */
        private String typePatternName;
        /** */
        private String updateCount;
        /** */
        private String updateDate;
        /** */
        private String updateTime;
        /** */
        private String updatedBy;
        /** */
        private String valueCheckErrMsgId;
        /** */
        private String valueCheckType;

        /**
         * @return cID
         */
        @XmlAttribute(name = "CID")
        public String getCID() {
            return CID;
        }

        /**
         * @param cID セットする cID
         */
        public void setCID(String cID) {
            CID = cID;
        }

        /**
         * @return attributeJavaType
         */
        @XmlAttribute
        public String getAttributeJavaType() {
            return attributeJavaType;
        }

        /**
         * @param attributeJavaType セットする attributeJavaType
         */
        public void setAttributeJavaType(String attributeJavaType) {
            this.attributeJavaType = attributeJavaType;
        }

        /**
         * @return attributeName
         */
        @XmlAttribute
        public String getAttributeName() {
            return attributeName;
        }

        /**
         * @param attributeName セットする attributeName
         */
        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }

        /**
         * @return attributePhysicalName
         */
        @XmlAttribute
        public String getAttributePhysicalName() {
            return attributePhysicalName;
        }

        /**
         * @param attributePhysicalName セットする attributePhysicalName
         */
        public void setAttributePhysicalName(String attributePhysicalName) {
            this.attributePhysicalName = attributePhysicalName;
        }

        /**
         * @return autoGenerate
         */
        @XmlAttribute(name = "autoGenerate")
        public boolean isAutoGenerate() {
            return autoGenerate;
        }

        /**
         * @param autoGenerate セットする autoGenerate
         */
        public void setAutoGenerate(boolean autoGenerate) {
            this.autoGenerate = autoGenerate;
        }

        /**
         * @return className
         */
        @XmlAttribute
        public String getClassName() {
            return className;
        }

        /**
         * @param className セットする className
         */
        public void setClassName(String className) {
            this.className = className;
        }

        /**
         * @return classPhysicalName
         */
        @XmlAttribute
        public String getClassPhysicalName() {
            return classPhysicalName;
        }

        /**
         * @param classPhysicalName セットする classPhysicalName
         */
        public void setClassPhysicalName(String classPhysicalName) {
            this.classPhysicalName = classPhysicalName;
        }

        /**
         * @return createDate
         */
        @XmlAttribute
        public String getCreateDate() {
            return createDate;
        }

        /**
         * @param createDate セットする createDate
         */
        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        /**
         * @return createTime
         */
        @XmlAttribute
        public String getCreateTime() {
            return createTime;
        }

        /**
         * @param createTime セットする createTime
         */
        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        /**
         * @return createdBy
         */
        @XmlAttribute
        public String getCreatedBy() {
            return createdBy;
        }

        /**
         * @param createdBy セットする createdBy
         */
        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        /**
         * @return dbColType
         */
        @XmlAttribute
        public String getDbColType() {
            return dbColType;
        }

        /**
         * @param dbColType セットする dbColType
         */
        public void setDbColType(String dbColType) {
            this.dbColType = dbColType;
        }

        /**
         * @return dbColumnLength
         */
        @XmlAttribute
        public String getDbColumnLength() {
            return dbColumnLength;
        }

        /**
         * @param dbColumnLength セットする dbColumnLength
         */
        public void setDbColumnLength(String dbColumnLength) {
            this.dbColumnLength = dbColumnLength;
        }

        /**
         * @return dbColumnName
         */
        @XmlAttribute
        public String getDbColumnName() {
            return dbColumnName;
        }

        /**
         * @param dbColumnName セットする dbColumnName
         */
        public void setDbColumnName(String dbColumnName) {
            this.dbColumnName = dbColumnName;
        }

        /**
         * @return deleteFlag
         */
        @XmlAttribute(name = "deleteFlag")
        public boolean isDeleteFlag() {
            return deleteFlag;
        }

        /**
         * @param deleteFlag セットする deleteFlag
         */
        public void setDeleteFlag(boolean deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        /**
         * @return encryptFlg
         */
        @XmlAttribute(name = "encryptFlg")
        public boolean isEncryptFlg() {
            return encryptFlg;
        }

        /**
         * @param encryptFlg セットする encryptFlg
         */
        public void setEncryptFlg(boolean encryptFlg) {
            this.encryptFlg = encryptFlg;
        }

        /**
         * @return fkList
         */
        @XmlAttribute
        public String getFkList() {
            return fkList;
        }

        /**
         * @param fkList セットする fkList
         */
        public void setFkList(String fkList) {
            this.fkList = fkList;
        }

        /**
         * @return initialValue
         */
        @XmlAttribute
        public String getInitialValue() {
            return initialValue;
        }

        /**
         * @param initialValue セットする initialValue
         */
        public void setInitialValue(String initialValue) {
            this.initialValue = initialValue;
        }

        /**
         * @return lengthCheckErrMsgId
         */
        @XmlAttribute
        public String getLengthCheckErrMsgId() {
            return lengthCheckErrMsgId;
        }

        /**
         * @param lengthCheckErrMsgId セットする lengthCheckErrMsgId
         */
        public void setLengthCheckErrMsgId(String lengthCheckErrMsgId) {
            this.lengthCheckErrMsgId = lengthCheckErrMsgId;
        }

        /**
         * @return note
         */
        @XmlAttribute
        public String getNote() {
            return note;
        }

        /**
         * @param note セットする note
         */
        public void setNote(String note) {
            this.note = note;
        }

        /**
         * @return nullable
         */
        @XmlAttribute(name = "nullable")
        public boolean isNullable() {
            return nullable;
        }

        /**
         * @param nullable セットする nullable
         */
        public void setNullable(boolean nullable) {
            this.nullable = nullable;
        }

        /**
         * @return packageName
         */
        @XmlAttribute
        public String getPackageName() {
            return packageName;
        }

        /**
         * @param packageName セットする packageName
         */
        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        /**
         * @return packagePhysicalName
         */
        @XmlAttribute
        public String getPackagePhysicalName() {
            return packagePhysicalName;
        }

        /**
         * @param packagePhysicalName セットする packagePhysicalName
         */
        public void setPackagePhysicalName(String packagePhysicalName) {
            this.packagePhysicalName = packagePhysicalName;
        }

        /**
         * @return programName
         */
        @XmlAttribute
        public String getProgramName() {
            return programName;
        }

        /**
         * @param programName セットする programName
         */
        public void setProgramName(String programName) {
            this.programName = programName;
        }

        /**
         * @return requiredCheckErrMsgId
         */
        @XmlAttribute
        public String getRequiredCheckErrMsgId() {
            return requiredCheckErrMsgId;
        }

        /**
         * @param requiredCheckErrMsgId セットする requiredCheckErrMsgId
         */
        public void setRequiredCheckErrMsgId(String requiredCheckErrMsgId) {
            this.requiredCheckErrMsgId = requiredCheckErrMsgId;
        }

        /**
         * @return sortKey
         */
        @XmlAttribute
        public String getSortKey() {
            return sortKey;
        }

        /**
         * @param sortKey セットする sortKey
         */
        public void setSortKey(String sortKey) {
            this.sortKey = sortKey;
        }

        /**
         * @return staticOrInstance
         */
        @XmlAttribute
        public String getStaticOrInstance() {
            return staticOrInstance;
        }

        /**
         * @param staticOrInstance セットする staticOrInstance
         */
        public void setStaticOrInstance(String staticOrInstance) {
            this.staticOrInstance = staticOrInstance;
        }

        /**
         * @return stereotype
         */
        @XmlAttribute
        public String getStereotype() {
            return stereotype;
        }

        /**
         * @param stereotype セットする stereotype
         */
        public void setStereotype(String stereotype) {
            this.stereotype = stereotype;
        }

        /**
         * @return sys_tenantCode
         */
        @XmlAttribute
        public String getSys_tenantCode() {
            return sys_tenantCode;
        }

        /**
         * @param sys_tenantCode セットする sys_tenantCode
         */
        public void setSys_tenantCode(String sys_tenantCode) {
            this.sys_tenantCode = sys_tenantCode;
        }

        /**
         * @return typeCheckErrMsgId
         */
        @XmlAttribute
        public String getTypeCheckErrMsgId() {
            return typeCheckErrMsgId;
        }

        /**
         * @param typeCheckErrMsgId セットする typeCheckErrMsgId
         */
        public void setTypeCheckErrMsgId(String typeCheckErrMsgId) {
            this.typeCheckErrMsgId = typeCheckErrMsgId;
        }

        /**
         * @return typePatternName
         */
        @XmlAttribute
        public String getTypePatternName() {
            return typePatternName;
        }

        /**
         * @param typePatternName セットする typePatternName
         */
        public void setTypePatternName(String typePatternName) {
            this.typePatternName = typePatternName;
        }

        /**
         * @return updateCount
         */
        @XmlAttribute
        public String getUpdateCount() {
            return updateCount;
        }

        /**
         * @param updateCount セットする updateCount
         */
        public void setUpdateCount(String updateCount) {
            this.updateCount = updateCount;
        }

        /**
         * @return updateDate
         */
        @XmlAttribute
        public String getUpdateDate() {
            return updateDate;
        }

        /**
         * @param updateDate セットする updateDate
         */
        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        /**
         * @return updateTime
         */
        @XmlAttribute
        public String getUpdateTime() {
            return updateTime;
        }

        /**
         * @param updateTime セットする updateTime
         */
        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        /**
         * @return updatedBy
         */
        @XmlAttribute
        public String getUpdatedBy() {
            return updatedBy;
        }

        /**
         * @param updatedBy セットする updatedBy
         */
        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        /**
         * @return valueCheckErrMsgId
         */
        @XmlAttribute
        public String getValueCheckErrMsgId() {
            return valueCheckErrMsgId;
        }

        /**
         * @param valueCheckErrMsgId セットする valueCheckErrMsgId
         */
        public void setValueCheckErrMsgId(String valueCheckErrMsgId) {
            this.valueCheckErrMsgId = valueCheckErrMsgId;
        }

        /**
         * @return valueCheckType
         */
        @XmlAttribute
        public String getValueCheckType() {
            return valueCheckType;
        }

        /**
         * @param valueCheckType セットする valueCheckType
         */
        public void setValueCheckType(String valueCheckType) {
            this.valueCheckType = valueCheckType;
        }

    }
}
