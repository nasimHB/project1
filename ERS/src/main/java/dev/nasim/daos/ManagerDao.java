package dev.nasim.daos;

import dev.nasim.entities.Manager;

import java.util.Set;


public interface ManagerDao {

    public Manager getManagerById(int managerId);
    public Set<Manager> getAllManagers();
}
