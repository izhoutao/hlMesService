package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.basic.QcDefect;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-04-02
 */
public interface QcDefectMapper extends BaseMapper<QcDefect> {

    @Select("SELECT qd.*, count( qd.defect_code ) AS occurrence " +
            "FROM tb_qc_defect qd " +
            "join tb_ipqc ipqc on qd.ipqc_id = ipqc.id where ipqc.date >= #{beginDate} and ipqc.date <= #{endDate} " +
            "GROUP BY qd.defect_code ORDER BY count( qd.defect_code ) DESC LIMIT 0,#{num}")
    List<QcDefect> getTopDefects(LocalDate beginDate , LocalDate endDate , Integer num);

}
