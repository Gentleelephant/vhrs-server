package com.zhang.service;

import com.zhang.mapper.PoliticsstatusMapper;
import com.zhang.model.Politicsstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/4/25 16:55
 * @Version:1.0
 * @Description:
 */
@Service
public class PoliticsstatusService {

    @Autowired
    PoliticsstatusMapper politicsstatusMapper;

    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusMapper.getAllPoliticsstatus();
    }
}
