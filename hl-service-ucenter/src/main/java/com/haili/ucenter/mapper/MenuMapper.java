package com.haili.ucenter.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.haili.framework.domain.ucenter.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据指定的角色ID去菜单和角色关联表查出该角色所属菜单列表
     *
     * @param menuId
     * @return
     */
    @Select("select * from tb_menu " +
            " left join " +
            " tb_permission on tb_menu.id=tb_permission.menu_id " +
            " where tb_permission.role_id =#{roleId} ")
    List<Menu> getListByRoleId(String roleId);

    @Override
    @Select("select * from tb_menu ${ew.customSqlSegment} ")
    @Results(id = "menuMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "always_show", property = "alwaysShow"),
            @Result(column = "id", property = "roleList",
                    many = @Many(
                            select = "com.haili.ucenter.mapper.RoleMapper.getListByMenuId"
                    )
            )
    })
    IPage<Menu> selectPage(IPage<Menu> page, @Param(Constants.WRAPPER) Wrapper<Menu> queryWrapper);

    @Override
    @Select("select * from tb_menu ${ew.customSqlSegment} ")
    @ResultMap("menuMap")
    List<Menu> selectList(@Param(Constants.WRAPPER) Wrapper<Menu> queryWrapper);

}
