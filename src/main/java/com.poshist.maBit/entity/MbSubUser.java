package com.poshist.maBit.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * Created by poshist on 18-1-28.
 */
@Entity
@Table(name = "MB_SUB_USER")
@EntityListeners(AuditingEntityListener.class)
public class MbSubUser extends AbstractPersistable<Long> implements Cloneable {
    private Long siteId;
    private String name;



    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
