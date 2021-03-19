package dev.nasim.services;

import dev.nasim.daos.ManagerDao;
import dev.nasim.entities.Manager;

import java.util.Set;

public class ManagerServiceImpl implements ManagerService{

    private ManagerDao mdao;

    public ManagerServiceImpl(ManagerDao mdao){
        this.mdao = mdao;
    }


    @Override
    public Set<Manager> getAllManagers() {
        return mdao.getAllManagers();
    }

    @Override
    public Manager getManagerById(int managerId) {
        return mdao.getManagerById(managerId);
    }

    @Override
    public Manager getManagerByUsernameAndPswrd(String username, String pswrd) {
        Manager m = null;
        Set<Manager> managers = this.getAllManagers();
        for (Manager temp : managers){
            if (temp.getUsername().equals(username) && temp.getPswrd().equals(pswrd)){
                m = temp;
            }
        }
        return m;
    }
}
