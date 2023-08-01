package utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestDbutils {


    public static void main(String[] args) throws SQLException {


        DBUtils.createConnection();

//        List<List<Object>> resultSet = DBUtils.getQueryResultAsListOfLists("SELECT * from users");
//
//        for (List<Object> row : resultSet) {
//
//            System.out.println(row);
//
//        }
//
//        String o = (String) resultSet.get(0).get(1);
//        System.out.println(o);

        List<Map<String, Object>> result2 = DBUtils.getQueryResultListOfMaps("SELECT * from users");

        for (Map<String, Object> row : result2) {
            System.out.println(row);
        }

        Object username2 = result2.get(0).get("username");

        System.out.println(username2);

        String username = "SherlockHolmes";
        String id = "47";

        DBUtils.executeUpdate("UPDATE users SET username='"+username+"' WHERE id='"+id+"'");

        List<List<Object>> userupdated = DBUtils.getQueryResultAsListOfLists("SELECT * from users where id='"+id+"'");

        System.out.println(userupdated);

        List<String> columnNames = DBUtils.getColumnNames("SELECT * from users where id='" + id + "'");

        System.out.println(columnNames);

        DBUtils.close();


    }
}
