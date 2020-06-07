package common.tools;

import common.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryToArrayList {

//    public static <T> ArrayList<T> ResultSetToArrayList(ResultSet rs) throws SQLException {
//        ArrayList<T> ArrayListToSent = new ArrayList<T>();
//        while (rs.next()) {
//            ArrayListToSent.add(new Employee(rs.getString(1), rs.getString(2),
//                    rs.getString(3), rs.getString(4), rs.getString(5),
//                    rs.getString(6)));
//        }
//        return ArrayListToSent;
//    }



    public static ArrayList<User> ResultSetToUsersArrayList(ResultSet rs) throws SQLException{
        ArrayList<User> arrayListToSent = new ArrayList<User>();
        while (rs.next()){
            User userToAdd = new User(rs.getInt(1),rs.getInt(2),rs.getString(5),rs.getString(6),rs.getString(7));
            arrayListToSent.add(userToAdd);
        }
        return arrayListToSent;
    }

    public static Float RsultSetToFloat(ResultSet rs) throws SQLException {
        System.out.println(rs.getString(1));
        Float num = rs.getFloat(1);
        return num;
    }
}
