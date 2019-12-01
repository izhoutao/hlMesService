package com.haili.ucenter.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.haili.framework.domain.ucenter.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

//    List<Role> findRoleList();


    /**
     * 根据指定的用户ID去角色和用户关联表查出该用户所属角色列表
     *
     * @param userId
     * @return
     */
    @Select("select * from tb_role " +
            " left join " +
            " tb_user_role on tb_role.id=tb_user_role.role_id " +
            " where tb_user_role.user_id =#{userId} ")
    List<Role> getListByUserId(String userId);


    /**
     * 根据指定的用户ID去角色和用户关联表查出该用户所属角色列表
     *
     * @param userId
     * @return
     */
    @Select("select * from tb_role " +
            " left join " +
            " tb_permission on tb_role.id=tb_permission.role_id " +
            " where tb_permission.menu_id =#{menuId} ")
    List<Role> getListByMenuId(String menuId);

    @Select("select * from tb_role ${ew.customSqlSegment} ")
    @Results(id = "roleMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "id", property = "menuList",
                    many = @Many(
                            select = "com.haili.ucenter.mapper.MenuMapper.getListByRoleId"
                    )
            )
    })
    IPage<Role> selectPagePreLoad(IPage<Role> page, @Param(Constants.WRAPPER) Wrapper<Role> queryWrapper);

    @Select("select * from tb_role ${ew.customSqlSegment} ")
    @ResultMap("roleMap")
    List<Role> selectListPreLoad(@Param(Constants.WRAPPER) Wrapper<Role> queryWrapper);

}
