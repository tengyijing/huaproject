package cn.huacao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.huacao.pojo.TbUser;
import cn.huacao.pojo.TbUserExample;

public interface TbUserMapper {
    int countByExample(TbUserExample example);

    int deleteByExample(TbUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    List<TbUser> selectByExample(TbUserExample example);

    TbUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbUser record, @Param("example") TbUserExample example);

    int updateByExample(@Param("record") TbUser record, @Param("example") TbUserExample example);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

	int updateUserStatus(@Param("userId")Integer userId,@Param("status") int status);

	List<TbUser> fundUserList(@Param("start")int start,@Param("rows") int rows);

	List<TbUser> selectUserByNameAndPwd(@Param("username")String username,@Param("pwd") String pwd);
}