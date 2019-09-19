package cn.huacao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.huacao.pojo.TbItem;
import cn.huacao.pojo.TbItemExample;

public interface TbItemMapper {
    int countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItemExample example);

    TbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);

	int updateItemStatus(@Param("itemId") Long itemId ,@Param("status") byte status);
}