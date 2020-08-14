package com.zhang.service;

import com.zhang.mapper.NationMapper;
import com.zhang.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/4/25 16:51
 * @Version:1.0
 * @Description:
 */
@Service
public class NationService {

    @Autowired
    NationMapper nationMapper;

    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
