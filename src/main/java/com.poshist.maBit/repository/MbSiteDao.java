package com.poshist.maBit.repository;

import com.poshist.maBit.entity.MbSite;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by poshist on 18-1-28.
 */
public interface MbSiteDao  extends CrudRepository<MbSite, Long> {
    MbSite findMbSiteByName(String name);
}
