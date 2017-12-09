package com.cloud.sms.docker.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.*;
import java.sql.*;
import java.util.Map;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author liangwj9
 * @version 1.0
 * @since 1.0
 */
public class BlobToObjectTypeHandler extends BaseTypeHandler<Map<String,Object>> {


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Map<String, Object> parameter, JdbcType jdbcType) throws SQLException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (null != parameter) {
            try {
                ObjectOutputStream out = new ObjectOutputStream(baos);
                out.writeObject(parameter);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        preparedStatement.setBytes(i, (baos == null) ? new byte[0] : baos.toByteArray());
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Object obj = null;

        Blob blobLocator = resultSet.getBlob(s);
        obj = parseBlobToObject(blobLocator,obj);
        return (Map)obj;
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Object obj = null;

        Blob blobLocator = resultSet.getBlob(i);
        obj = parseBlobToObject(blobLocator,obj);
        return (Map)obj;
    }

    @Override
    public Map<String, Object> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Object obj = null;

        Blob blobLocator = callableStatement.getBlob(i);
        obj = parseBlobToObject(blobLocator,obj);
        return (Map)obj;
    }


    public Object parseBlobToObject(Blob blob , Object obj) throws SQLException {
        if (blob != null && blob.length() != 0) {
            InputStream binaryInput = blob.getBinaryStream();

            if (null != binaryInput) {
                if (binaryInput instanceof ByteArrayInputStream
                        && ((ByteArrayInputStream) binaryInput).available() == 0) {
                    //do nothing
                } else {
                    ObjectInputStream in = null;
                    try {
                        in = new ObjectInputStream(binaryInput);
                        obj = in.readObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if(in != null){
                                in.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
        return obj;
    }
}
