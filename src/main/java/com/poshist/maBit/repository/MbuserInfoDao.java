package com.poshist.maBit.repository;

import com.poshist.maBit.entity.MbUserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by poshist on 18-1-28.
 */
public interface MbuserInfoDao  extends CrudRepository<MbUserInfo, Long> {
    List<MbUserInfo> findByRecTimeBetweenAndMbSubUser_SiteIdOrderByMbSubUserId(Date star, Date end,Long siteId);
}
