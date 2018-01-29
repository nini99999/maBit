package com.poshist.maBit.repository;

import com.poshist.maBit.entity.MbSubUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by poshist on 18-1-28.
 */
public interface MbSubUserDao   extends CrudRepository<MbSubUser, Long> {
    MbSubUser findMbSubUserByNameAndSiteId(String name,Long ID);
}
