/**
 *
 */
package jp.co.xwave.sc.tool;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author hirai
 *
 */
public class XmlParser {

    /**
     *
     * @return
     */
    public static Response convert(InputStream in) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Response.class);
        Response response = (Response) context.createUnmarshaller().unmarshal(in);
        return response;
    }

    @XmlRootElement
    public static class Response extends ToStringObject {

        boolean isError;

        Body body;

        @XmlAttribute
        public boolean isError() {
            return isError;
        }

        public void setError(boolean isError) {
            this.isError = isError;
        }

        @XmlElement
        public Body getBody() {
            return body;
        }

        public void setBody(Body body) {
            this.body = body;
        }

    }

    public static class Body extends ToStringObject {

        Obj0List _obj0List;

        @XmlElement
        public Obj0List get_obj0List() {
            return _obj0List;
        }

        public void set_obj0List(Obj0List _obj0List) {
            this._obj0List = _obj0List;
        }

    }

    public static class Obj0List extends ToStringObject {

        List<Obj0> _obj0List = new ArrayList<>();

        @XmlElement(name = "_obj0")
        public List<Obj0> get_obj0List() {
            return _obj0List;
        }

        public void set_obj0List(List<Obj0> _obj0List) {
            this._obj0List = _obj0List;
        }

    }

    @XmlType(name = "_obj0")
    public static class Obj0 extends ToStringObject {

        String command;
        String lineNo;
        String resultStr;
        String sqlStmt;
        String status;
        String tableName;

        DataList dataList;
        ColumnList columnList;

        @XmlAttribute
        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        @XmlAttribute
        public String getLineNo() {
            return lineNo;
        }

        public void setLineNo(String lineNo) {
            this.lineNo = lineNo;
        }

        @XmlAttribute
        public String getResultStr() {
            return resultStr;
        }

        public void setResultStr(String resultStr) {
            this.resultStr = resultStr;
        }

        @XmlAttribute
        public String getSqlStmt() {
            return sqlStmt;
        }

        public void setSqlStmt(String sqlStmt) {
            this.sqlStmt = sqlStmt;
        }

        @XmlAttribute
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @XmlAttribute
        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        @XmlElement
        public DataList getDataList() {
            return dataList;
        }

        public void setDataList(DataList dataList) {
            this.dataList = dataList;
        }

        @XmlElement
        public ColumnList getColumnList() {
            return columnList;
        }

        public void setColumnList(ColumnList columnList) {
            this.columnList = columnList;
        }

    }

    @XmlType(name = "dataList")
    public static class DataList extends ToStringObject {

        List<Data> dataList;

        @XmlElement(name = "data")
        public List<Data> getDataList() {
            return dataList;
        }

        public void setDataList(List<Data> dataList) {
            this.dataList = dataList;
        }

    }

    @XmlType(name = "data")
    public static class Data extends ToStringObject {

        private Map<QName, Object> attributes;

        @XmlAnyAttribute
        public Map<QName, Object> getAttributes() {
            return attributes;
        }

        public void setAttributes(Map<QName, Object> attributes) {
            this.attributes = attributes;
        }

    }

    @XmlType(name = "columnList")
    public static class ColumnList extends ToStringObject {

        List<Column> columnList;

        @XmlElement(name = "column")
        public List<Column> getColumnList() {
            return columnList;
        }

        public void setColumnList(List<Column> columnList) {
            this.columnList = columnList;
        }

    }

    @XmlType(name = "column")
    public static class Column extends ToStringObject {
        String columnName;
        String columnPhyName;

        @XmlAttribute
        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        @XmlAttribute
        public String getColumnPhyName() {
            return columnPhyName;
        }

        public void setColumnPhyName(String columnPhyName) {
            this.columnPhyName = columnPhyName;
        }

    }

    public static class ToStringObject {
        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this,
                    ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }
}
