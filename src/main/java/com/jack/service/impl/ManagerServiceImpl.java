package com.jack.service.impl;

import com.jack.dao.ManagerMapper;
import com.jack.pojo.Manager;
import com.jack.service.ManagerService;

import java.util.HashMap;
import java.util.Map;

public class ManagerServiceImpl implements ManagerService {
    private ManagerMapper managerMapper;

    public void setManagerMapper(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    @Override
    public boolean login(Manager manager) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("username",manager.getUsername());
        parameters.put("password",manager.getPassword());
        Manager result = managerMapper.queryManager(parameters);
        if(result!=null){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public int getIdByName(String username) {
        return managerMapper.getManagerIdByName(username);
    }

    @Override
    public boolean changePwd(Manager manager) {
        try {
            int result = managerMapper.updateManager(manager);
            if(result!=0){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public Manager getManagerByIdPwd(int managerId, String password) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",managerId);
        map.put("password",password);
        return managerMapper.queryManager(map);
    }
}
