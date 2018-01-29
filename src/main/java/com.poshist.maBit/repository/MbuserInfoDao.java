package com.poshist.maBit.repository;

import com.poshist.maBit.entity.MbSubUser;
import com.poshist.maBit.entity.MbUserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by poshist on 18-1-28.
 */
public interface MbuserInfoDao  extends CrudRepository<MbUserInfo, Long> {
}
