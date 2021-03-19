package dev.nasim.daos;

import dev.nasim.entities.Manager;
import dev.nasim.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ManagerDaoPostgres implements ManagerDao{
    @Override
    public Manager getManagerById(int managerId) {
        try (Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from manager where manager_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, managerId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Manager manager = new Manager();
            manager.setManagerId(rs.getInt("manager_id"));
            manager.setFirstName(rs.getString("first_name"));
            manager.setLastName(rs.getString("last_name"));
            manager.setUsername(rs.getString("username"));
            manager.setPswrd(rs.getString("pswrd"));
            return manager;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Manager> getAllManagers() {
        Set<Manager> managers = new HashSet<Manager>();
        try (Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from manager";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // while book exists, create a new book and store the info
            // into the new book
            while(rs.next()) {
                Manager manager = new Manager();
                manager.setManagerId(rs.getInt("manager_id"));
                manager.setFirstName(rs.getString("first_name"));
                manager.setLastName(rs.getString("last_name"));
                manager.setUsername(rs.getString("username"));
                manager.setPswrd(rs.getString("pswrd"));
                managers.add(manager);
            }
            return managers;
        } catch (SQLException e){
            e.printStackTrace();
            return managers;
        }
    }
}
