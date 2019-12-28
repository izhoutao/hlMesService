package com.haili.ucenter.mapper;

import com.haili.framework.domain.system.XSequenceSequence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootTest
@RunWith(SpringRunner.class)
public class XSequenceSequenceMapperTest {
    @Autowired
    XSequenceSequenceMapper xSequenceSequenceMapper;

    @Test
    public void updateById() {
        LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
        XSequenceSequence xSequenceSequence = new XSequenceSequence();
        xSequenceSequence.setId(3L);
        xSequenceSequence.setGmtModified(now);
        xSequenceSequence.setValue(0L);
        xSequenceSequenceMapper.updateById(xSequenceSequence);
    }
}