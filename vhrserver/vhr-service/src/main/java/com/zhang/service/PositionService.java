package com.zhang.service;

import com.zhang.mapper.PositionMapper;
import com.zhang.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/4/8 13:57
 * @Version:1.0
 * @Description:
 */
@Service
public class PositionService {

    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPositions(){
        return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position){
        position.setEnabled(true);
        position.setCreateDate(new Date());
        return positionMapper.insertSelective(position);
    }

    public Integer updatePositions(Position position){
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deleteByPrimaryKey(Integer id){
        return positionMapper.deleteByPrimaryKey(id);
    }


    public Integer deletePositionsByIds(Integer[] ids){
        return positionMapper.deletePositionsByIds(ids);
    }
}
