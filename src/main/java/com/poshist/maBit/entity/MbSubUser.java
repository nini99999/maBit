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
    private String btcDesc;
    private String bchDesc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String  status;


    public String getBtcDesc() {
        return btcDesc;
    }

    public void setBtcDesc(String btcDesc) {
        this.btcDesc = btcDesc;
    }

    public String getBchDesc() {
        return bchDesc;
    }

    public void setBchDesc(String bchDesc) {
        this.bchDesc = bchDesc;
    }

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
