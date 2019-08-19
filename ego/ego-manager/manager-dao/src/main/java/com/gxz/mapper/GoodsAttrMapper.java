package com.gxz.mapper;

import com.gxz.dao.IDao;
import com.gxz.domain.GoodsAttr;
import com.gxz.domain.GoodsAttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsAttrMapper extends IDao<GoodsAttr> {
    long countByExample(GoodsAttrExample example);

    int deleteByExample(GoodsAttrExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAttr record);

    int insertSelective(GoodsAttr record);

    List<GoodsAttr> selectByExampleWithBLOBs(GoodsAttrExample example);

    List<GoodsAttr> selectByExample(GoodsAttrExample example);

    GoodsAttr selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsAttr record, @Param("example") GoodsAttrExample example);

    int updateByExampleWithBLOBs(@Param("record") GoodsAttr record, @Param("example") GoodsAttrExample example);

    int updateByExample(@Param("record") GoodsAttr record, @Param("example") GoodsAttrExample example);

    int updateByPrimaryKeySelective(GoodsAttr record);

    int updateByPrimaryKeyWithBLOBs(GoodsAttr record);

    int updateByPrimaryKey(GoodsAttr record);
}