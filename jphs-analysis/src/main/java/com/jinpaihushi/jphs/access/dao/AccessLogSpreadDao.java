package com.jinpaihushi.jphs.access.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.access.model.AccessLogSpread;

@Repository("accessLogSpreadDao")
public interface AccessLogSpreadDao extends BaseDao<AccessLogSpread> {

    List<AccessLogSpread> getAmountOfAccessByDay(@Param("days") String days);

    List<AccessLogSpread> getAmountOfAccessByWeek(@Param("startTime") String startTime,
            @Param("endTime") String endTime);

    List<AccessLogSpread> getAmountOfAccessByMonth(@Param("month") String month);

    List<AccessLogSpread> getAmountOfAccessByYear(@Param("year") String year);
}
