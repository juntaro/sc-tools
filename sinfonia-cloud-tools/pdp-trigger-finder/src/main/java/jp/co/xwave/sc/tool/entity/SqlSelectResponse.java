/**
 *
 */
package jp.co.xwave.sc.tool.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

/**
 * @author hirai
 * TODO クラス名をスキーマにあわせずにわかり易い名前にする予定。マッピングはアノテーションで。
 */
@XmlRootElement(name = "response")
public class SqlSelectResponse extends ToStringObject {

    /** */
    private boolean isError;

    /** */
    private Body body;

    /**
     *
     * @return
     */
    @XmlAttribute
    public boolean isError() {
        return isError;
    }

    /**
     *
     * @param isError
     */
    public void setError(boolean isError) {
        this.isError = isError;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public Body getBody() {
        return body;
    }

    /**
     *
     * @param body
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     *
     * @author hirai
     *
     */
    public static class Body extends ToStringObject {

        /** */
        private Obj0List _obj0List;

        /**
         *
         * @return
         */
        @XmlElement
        public Obj0List get_obj0List() {
            return _obj0List;
        }

        /**
         *
         * @param _obj0List
         */
        public void set_obj0List(Obj0List _obj0List) {
            this._obj0List = _obj0List;
        }

    }

    /**
     *
     * @author hirai
     */
    public static class Obj0List extends ToStringObject {

        /** */
        private List<Obj0> _obj0List = new ArrayList<>();

        @XmlElement(name = "_obj0")
        public List<Obj0> get_obj0List() {
            return _obj0List;
        }

        public void set_obj0List(List<Obj0> _obj0List) {
            this._obj0List = _obj0List;
        }

    }

    /**
     *
     * @author hirai
     *
     */
    public static class Obj0 extends ToStringObject {
        /** */
        private String command;
        /** */
        private String lineNo;
        /** */
        private String resultStr;
        /** */
        private String sqlStmt;
        /** */
        private String status;
        /** */
        private String tableName;
        /** */
        private DataList dataList;
        /** */
        private ColumnList columnList;

        /**
         *
         * @return
         */
        @XmlAttribute
        public String getCommand() {
            return command;
        }

        /**
         *
         * @param command
         */
        public void setCommand(String command) {
            this.command = command;
        }

        /**
         *
         * @return
         */
        @XmlAttribute
        public String getLineNo() {
            return lineNo;
        }

        /**
         *
         * @param lineNo
         */
        public void setLineNo(String lineNo) {
            this.lineNo = lineNo;
        }

        /**
         *
         * @return
         */
        @XmlAttribute
        public String getResultStr() {
            return resultStr;
        }

        /**
         *
         * @param resultStr
         */
        public void setResultStr(String resultStr) {
            this.resultStr = resultStr;
        }

        /**
         *
         * @return
         */
        @XmlAttribute
        public String getSqlStmt() {
            return sqlStmt;
        }

        /**
         *
         * @param sqlStmt
         */
        public void setSqlStmt(String sqlStmt) {
            this.sqlStmt = sqlStmt;
        }

        /**
         *
         * @return
         */
        @XmlAttribute
        public String getStatus() {
            return status;
        }

        /**
         *
         * @param status
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         *
         * @return
         */
        @XmlAttribute
        public String getTableName() {
            return tableName;
        }

        /**
         *
         * @param tableName
         */
        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        /**
         *
         * @return
         */
        @XmlElement
        public DataList getDataList() {
            return dataList;
        }

        /**
         *
         * @param dataList
         */
        public void setDataList(DataList dataList) {
            this.dataList = dataList;
        }

        /**
         *
         * @return
         */
        @XmlElement
        public ColumnList getColumnList() {
            return columnList;
        }

        /**
         *
         * @param columnList
         */
        public void setColumnList(ColumnList columnList) {
            this.columnList = columnList;
        }

    }

    /**
     *
     * @author hirai
     *
     */
    public static class DataList extends ToStringObject {

        /** */
        private List<Data> dataList;

        /**
         *
         * @return
         */
        @XmlElement(name = "data")
        public List<Data> getDataList() {
            return dataList;
        }

        /**
         *
         * @param dataList
         */
        public void setDataList(List<Data> dataList) {
            this.dataList = dataList;
        }

    }

    /**
     *
     * @author hirai
     *
     */
    public static class Data extends ToStringObject {
        /** */
        private Map<QName, Object> attributes;

        /**
         *
         * @return
         */
        @XmlAnyAttribute
        public Map<QName, Object> getAttributes() {
            return attributes;
        }

        /**
         *
         * @param attributes
         */
        public void setAttributes(Map<QName, Object> attributes) {
            this.attributes = attributes;
        }

    }

    /**
     *
     * @author hirai
     *
     */
    public static class ColumnList extends ToStringObject {

        /** */
        private List<Column> columnList;

        /**
         *
         * @return
         */
        @XmlElement(name = "column")
        public List<Column> getColumnList() {
            return columnList;
        }

        /**
         *
         * @param columnList
         */
        public void setColumnList(List<Column> columnList) {
            this.columnList = columnList;
        }

    }

    /**
     *
     * @author hirai
     */
    public static class Column extends ToStringObject {
        /** */
        private String columnName;
        /** */
        private String columnPhyName;

        /**
         *
         * @return
         */
        @XmlAttribute
        public String getColumnName() {
            return columnName;
        }

        /**
         *
         * @param columnName
         */
        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        /**
         *
         * @return
         */
        @XmlAttribute
        public String getColumnPhyName() {
            return columnPhyName;
        }

        /**
         *
         * @param columnPhyName
         */
        public void setColumnPhyName(String columnPhyName) {
            this.columnPhyName = columnPhyName;
        }

    }
}
