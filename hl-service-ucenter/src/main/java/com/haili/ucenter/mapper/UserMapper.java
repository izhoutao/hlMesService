package com.haili.ucenter.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.haili.framework.domain.ucenter.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getUserByUserName(String username);

    @Select("select * from tb_user ${ew.customSqlSegment} ")
    @Results(id = "userMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staff_id", property = "staffId"),
            @Result(column = "dept_id", property = "deptId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "id", property = "roleList",
                    many = @Many(
                            select = "com.haili.ucenter.mapper.RoleMapper.getListByUserId"
                    )
            )
    })
    IPage<User> selectPagePreload(IPage<User> page, @Param(Constants.WRAPPER) Wrapper<User> queryWrapper);

    @Select("select * from tb_user ${ew.customSqlSegment} ")
    @ResultMap("userMap")
    List<User> selectListPreload(@Param(Constants.WRAPPER) Wrapper<User> queryWrapper);

}
