package com.jack.service;

import com.jack.pojo.Manager;

public interface ManagerService {
    public boolean login(Manager manager);
    public int getIdByName(String username);
    public boolean changePwd(Manager manager);
    public Manager getManagerByIdPwd(int managerId,String password);
}
