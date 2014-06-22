/**
 *
 */
package jp.co.xwave.sc.tool.entity;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hirai
 *
 */
@XmlRootElement(name = "response")
public class GetMetaDomainClassFindResponse extends ToStringObject {

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
     * @param dbTableName
     * @return
     */
    public String getClassPhysicalName(String dbTableName) {
        if (isError) {
            throw new RuntimeException("GetMetaDomainClassFind is ERROR.");
        }
        for (MetaDomainClass clazz : getBody().getObj0List().getMetaDomainClasses()) {
            if (clazz.getDbTableName().equals(dbTableName)) {
                return clazz.getClassPhysicalName();
            }
        }
        throw new RuntimeException(String.format("dbTableName %s is not exists.", dbTableName));
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
         * @param obj0List
         *            セットする obj0List
         */
        public void setObj0List(Obj0List obj0List) {
            this.obj0List = obj0List;
        }
    }

    /**
     *
     * @author hirai
     *
     */
    public static class Obj0List extends ToStringObject {

        /** メタドメインクラスの集合. */
        private Collection<MetaDomainClass> metaDomainClasses;

        /**
         * @return metaDomainClasses
         */
        @XmlElement(name = "_obj0")
        public Collection<MetaDomainClass> getMetaDomainClasses() {
            return metaDomainClasses;
        }

        /**
         * @param metaDomainClasses
         *            セットする metaDomainClasses
         */
        public void setMetaDomainClasses(
                Collection<MetaDomainClass> metaDomainClasses) {
            this.metaDomainClasses = metaDomainClasses;
        }

    }

    /**
     * メタドメインクラスの定義.
     *
     * @author hirai
     */
    public static class MetaDomainClass extends ToStringObject {

        /** */
        private String CID;
        /** */
        private boolean attributeRegistrationFlg;
        /** */
        private String changeLog;
        /** */
        private String classCacheSize;
        /** */
        private String classExplanation;
        /** */
        private String className;
        /** */
        private String classNote;
        /** */
        private String classPhysicalName;
        /** */
        private boolean classRegistrationFlg;
        /** */
        private String createDate;
        /** */
        private String createTime;
        /** */
        private String createdBy;
        /** */
        private String dbTableName;
        /** */
        private boolean definedByClassDiagram;
        /** */
        private boolean deleteFlag;
        /** */
        private boolean deployFlg;
        /** */
        private boolean diClass;
        /** */
        private String diClassName;
        /** */
        private boolean evacuateAtOneTimeFlg;
        /** */
        private boolean findMethodConditionInputFlg;
        /** */
        private String imports;
        /** */
        private boolean inMemoryCache;
        /** */
        private boolean indexReflectionFlg;
        /** */
        private boolean isView;
        /** */
        private boolean messageRegistrationFlg;
        /** */
        private boolean methodCodingFlg;
        /** */
        private boolean methodDeclarationFlg;
        /** */
        private String packageName;
        /** */
        private String packagePhysicalName;
        /** */
        private String programName;
        /** */
        private String srcLockBy;
        /** */
        private boolean srcLockFlg;
        /** */
        private String stereotype;
        /** */
        private String superClass;
        /** */
        private String superClassPackage;
        /** */
        private String sys_tenantCode;
        /** */
        private boolean tableReflectionFlg;
        /** */
        private String updateCount;
        /** */
        private String updateDate;
        /** */
        private String updatePerson;
        /** */
        private String updateTime;
        /** */
        private String updatedBy;
        /** */
        private TimestampMap updateTimestampMap;
        /** */
        private TimestampMap createTimestampMap;

        /**
         * @return cID
         */
        @XmlAttribute(name = "CID")
        public String getCID() {
            return CID;
        }

        /**
         * @param cid
         *            セットする cID
         */
        public void setCID(String cid) {
            CID = cid;
        }

        /**
         * @return attributeRegistrationFlg
         */
        @XmlAttribute(name = "attributeRegistrationFlg")
        public boolean isAttributeRegistrationFlg() {
            return attributeRegistrationFlg;
        }

        /**
         * @param attributeRegistrationFlg
         *            セットする attributeRegistrationFlg
         */
        public void setAttributeRegistrationFlg(boolean attributeRegistrationFlg) {
            this.attributeRegistrationFlg = attributeRegistrationFlg;
        }

        /**
         * @return changeLog
         */
        @XmlAttribute
        public String getChangeLog() {
            return changeLog;
        }

        /**
         * @param changeLog
         *            セットする changeLog
         */
        public void setChangeLog(String changeLog) {
            this.changeLog = changeLog;
        }

        /**
         * @return classCacheSize
         */
        @XmlAttribute
        public String getClassCacheSize() {
            return classCacheSize;
        }

        /**
         * @param classCacheSize
         *            セットする classCacheSize
         */
        public void setClassCacheSize(String classCacheSize) {
            this.classCacheSize = classCacheSize;
        }

        /**
         * @return classExplanation
         */
        @XmlAttribute
        public String getClassExplanation() {
            return classExplanation;
        }

        /**
         * @param classExplanation
         *            セットする classExplanation
         */
        public void setClassExplanation(String classExplanation) {
            this.classExplanation = classExplanation;
        }

        /**
         * @return className
         */
        @XmlAttribute
        public String getClassName() {
            return className;
        }

        /**
         * @param className
         *            セットする className
         */
        public void setClassName(String className) {
            this.className = className;
        }

        /**
         * @return classNote
         */
        @XmlAttribute
        public String getClassNote() {
            return classNote;
        }

        /**
         * @param classNote
         *            セットする classNote
         */
        public void setClassNote(String classNote) {
            this.classNote = classNote;
        }

        /**
         * @return classPhysicalName
         */
        @XmlAttribute
        public String getClassPhysicalName() {
            return classPhysicalName;
        }

        /**
         * @param classPhysicalName
         *            セットする classPhysicalName
         */
        public void setClassPhysicalName(String classPhysicalName) {
            this.classPhysicalName = classPhysicalName;
        }

        /**
         * @return classRegistrationFlg
         */
        @XmlAttribute(name = "classRegistrationFlg")
        public boolean isClassRegistrationFlg() {
            return classRegistrationFlg;
        }

        /**
         * @param classRegistrationFlg
         *            セットする classRegistrationFlg
         */
        public void setClassRegistrationFlg(boolean classRegistrationFlg) {
            this.classRegistrationFlg = classRegistrationFlg;
        }

        /**
         * @return createDate
         */
        @XmlAttribute
        public String getCreateDate() {
            return createDate;
        }

        /**
         * @param createDate
         *            セットする createDate
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
         * @param createTime
         *            セットする createTime
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
         * @param createdBy
         *            セットする createdBy
         */
        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        /**
         * @return dbTableName
         */
        @XmlAttribute
        public String getDbTableName() {
            return dbTableName;
        }

        /**
         * @param dbTableName
         *            セットする dbTableName
         */
        public void setDbTableName(String dbTableName) {
            this.dbTableName = dbTableName;
        }

        /**
         * @return definedByClassDiagram
         */
        @XmlAttribute(name = "definedByClassDiagram")
        public boolean isDefinedByClassDiagram() {
            return definedByClassDiagram;
        }

        /**
         * @param definedByClassDiagram
         *            セットする definedByClassDiagram
         */
        public void setDefinedByClassDiagram(boolean definedByClassDiagram) {
            this.definedByClassDiagram = definedByClassDiagram;
        }

        /**
         * @return deleteFlag
         */
        @XmlAttribute(name = "deleteFlag")
        public boolean isDeleteFlag() {
            return deleteFlag;
        }

        /**
         * @param deleteFlag
         *            セットする deleteFlag
         */
        public void setDeleteFlag(boolean deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        /**
         * @return deployFlg
         */
        @XmlAttribute(name = "deployFlg")
        public boolean isDeployFlg() {
            return deployFlg;
        }

        /**
         * @param deployFlg
         *            セットする deployFlg
         */
        public void setDeployFlg(boolean deployFlg) {
            this.deployFlg = deployFlg;
        }

        /**
         * @return diClass
         */
        @XmlAttribute(name = "diClass")
        public boolean isDiClass() {
            return diClass;
        }

        /**
         * @param diClass
         *            セットする diClass
         */
        public void setDiClass(boolean diClass) {
            this.diClass = diClass;
        }

        /**
         * @return diClassName
         */
        @XmlAttribute
        public String getDiClassName() {
            return diClassName;
        }

        /**
         * @param diClassName
         *            セットする diClassName
         */
        public void setDiClassName(String diClassName) {
            this.diClassName = diClassName;
        }

        /**
         * @return evacuateAtOneTimeFlg
         */
        @XmlAttribute(name = "evacuateAtOneTimeFlg")
        public boolean isEvacuateAtOneTimeFlg() {
            return evacuateAtOneTimeFlg;
        }

        /**
         * @param evacuateAtOneTimeFlg
         *            セットする evacuateAtOneTimeFlg
         */
        public void setEvacuateAtOneTimeFlg(boolean evacuateAtOneTimeFlg) {
            this.evacuateAtOneTimeFlg = evacuateAtOneTimeFlg;
        }

        /**
         * @return findMethodConditionInputFlg
         */
        @XmlAttribute(name = "findMethodConditionInputFlg")
        public boolean isFindMethodConditionInputFlg() {
            return findMethodConditionInputFlg;
        }

        /**
         * @param findMethodConditionInputFlg
         *            セットする findMethodConditionInputFlg
         */
        public void setFindMethodConditionInputFlg(
                boolean findMethodConditionInputFlg) {
            this.findMethodConditionInputFlg = findMethodConditionInputFlg;
        }

        /**
         * @return imports
         */
        @XmlAttribute
        public String getImports() {
            return imports;
        }

        /**
         * @param imports
         *            セットする imports
         */
        public void setImports(String imports) {
            this.imports = imports;
        }

        /**
         * @return inMemoryCache
         */
        @XmlAttribute(name = "inMemoryCache")
        public boolean isInMemoryCache() {
            return inMemoryCache;
        }

        /**
         * @param inMemoryCache
         *            セットする inMemoryCache
         */
        public void setInMemoryCache(boolean inMemoryCache) {
            this.inMemoryCache = inMemoryCache;
        }

        /**
         * @return indexReflectionFlg
         */
        @XmlAttribute(name = "indexReflectionFlg")
        public boolean isIndexReflectionFlg() {
            return indexReflectionFlg;
        }

        /**
         * @param indexReflectionFlg
         *            セットする indexReflectionFlg
         */
        public void setIndexReflectionFlg(boolean indexReflectionFlg) {
            this.indexReflectionFlg = indexReflectionFlg;
        }

        /**
         * @return isView
         */
        @XmlAttribute
        public boolean isView() {
            return isView;
        }

        /**
         * @param isView
         *            セットする isView
         */
        public void setView(boolean isView) {
            this.isView = isView;
        }

        /**
         * @return messageRegistrationFlg
         */
        @XmlAttribute(name = "messageRegistrationFlg")
        public boolean isMessageRegistrationFlg() {
            return messageRegistrationFlg;
        }

        /**
         * @param messageRegistrationFlg
         *            セットする messageRegistrationFlg
         */
        public void setMessageRegistrationFlg(boolean messageRegistrationFlg) {
            this.messageRegistrationFlg = messageRegistrationFlg;
        }

        /**
         * @return methodCodingFlg
         */
        @XmlAttribute(name = "methodCodingFlg")
        public boolean isMethodCodingFlg() {
            return methodCodingFlg;
        }

        /**
         * @param methodCodingFlg
         *            セットする methodCodingFlg
         */
        public void setMethodCodingFlg(boolean methodCodingFlg) {
            this.methodCodingFlg = methodCodingFlg;
        }

        /**
         * @return methodDeclarationFlg
         */
        @XmlAttribute(name = "methodDeclarationFlg")
        public boolean isMethodDeclarationFlg() {
            return methodDeclarationFlg;
        }

        /**
         * @param methodDeclarationFlg
         *            セットする methodDeclarationFlg
         */
        public void setMethodDeclarationFlg(boolean methodDeclarationFlg) {
            this.methodDeclarationFlg = methodDeclarationFlg;
        }

        /**
         * @return packageName
         */
        @XmlAttribute
        public String getPackageName() {
            return packageName;
        }

        /**
         * @param packageName
         *            セットする packageName
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
         * @param packagePhysicalName
         *            セットする packagePhysicalName
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
         * @param programName
         *            セットする programName
         */
        public void setProgramName(String programName) {
            this.programName = programName;
        }

        /**
         * @return srcLockBy
         */
        @XmlAttribute
        public String getSrcLockBy() {
            return srcLockBy;
        }

        /**
         * @param srcLockBy
         *            セットする srcLockBy
         */
        public void setSrcLockBy(String srcLockBy) {
            this.srcLockBy = srcLockBy;
        }

        /**
         * @return srcLockFlg
         */
        @XmlAttribute(name = "srcLockFlg")
        public boolean isSrcLockFlg() {
            return srcLockFlg;
        }

        /**
         * @param srcLockFlg
         *            セットする srcLockFlg
         */
        public void setSrcLockFlg(boolean srcLockFlg) {
            this.srcLockFlg = srcLockFlg;
        }

        /**
         * @return stereotype
         */
        @XmlAttribute
        public String getStereotype() {
            return stereotype;
        }

        /**
         * @param stereotype
         *            セットする stereotype
         */
        public void setStereotype(String stereotype) {
            this.stereotype = stereotype;
        }

        /**
         * @return superClass
         */
        @XmlAttribute
        public String getSuperClass() {
            return superClass;
        }

        /**
         * @param superClass
         *            セットする superClass
         */
        public void setSuperClass(String superClass) {
            this.superClass = superClass;
        }

        /**
         * @return superClassPackage
         */
        @XmlAttribute
        public String getSuperClassPackage() {
            return superClassPackage;
        }

        /**
         * @param superClassPackage
         *            セットする superClassPackage
         */
        public void setSuperClassPackage(String superClassPackage) {
            this.superClassPackage = superClassPackage;
        }

        /**
         * @return sys_tenantCode
         */
        @XmlAttribute
        public String getSys_tenantCode() {
            return sys_tenantCode;
        }

        /**
         * @param sys_tenantCode
         *            セットする sys_tenantCode
         */
        public void setSys_tenantCode(String sys_tenantCode) {
            this.sys_tenantCode = sys_tenantCode;
        }

        /**
         * @return tableReflectionFlg
         */
        @XmlAttribute(name = "tableReflectionFlg")
        public boolean isTableReflectionFlg() {
            return tableReflectionFlg;
        }

        /**
         * @param tableReflectionFlg
         *            セットする tableReflectionFlg
         */
        public void setTableReflectionFlg(boolean tableReflectionFlg) {
            this.tableReflectionFlg = tableReflectionFlg;
        }

        /**
         * @return updateCount
         */
        @XmlAttribute
        public String getUpdateCount() {
            return updateCount;
        }

        /**
         * @param updateCount
         *            セットする updateCount
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
         * @param updateDate
         *            セットする updateDate
         */
        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        /**
         * @return updatePerson
         */
        @XmlAttribute
        public String getUpdatePerson() {
            return updatePerson;
        }

        /**
         * @param updatePerson
         *            セットする updatePerson
         */
        public void setUpdatePerson(String updatePerson) {
            this.updatePerson = updatePerson;
        }

        /**
         * @return updateTime
         */
        @XmlAttribute
        public String getUpdateTime() {
            return updateTime;
        }

        /**
         * @param updateTime
         *            セットする updateTime
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
         * @param updatedBy
         *            セットする updatedBy
         */
        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        /**
         * @return updateTimestampMap
         */
        @XmlElement
        public TimestampMap getUpdateTimestampMap() {
            return updateTimestampMap;
        }

        /**
         * @param updateTimestampMap セットする updateTimestampMap
         */
        public void setUpdateTimestampMap(TimestampMap updateTimestampMap) {
            this.updateTimestampMap = updateTimestampMap;
        }

        /**
         * @return createTimestampMap
         */
        @XmlElement
        public TimestampMap getCreateTimestampMap() {
            return createTimestampMap;
        }

        /**
         * @param createTimestampMap セットする createTimestampMap
         */
        public void setCreateTimestampMap(TimestampMap createTimestampMap) {
            this.createTimestampMap = createTimestampMap;
        }

    }
}
