package com.poshist.maBit.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by poshist on 18-1-28.
 */
@Entity
@Table(name = "MB_USER_INFO")
@EntityListeners(AuditingEntityListener.class)
public class MbUserInfo extends AbstractPersistable<Long> implements Cloneable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private MbSubUser mbSubUser;
    private Double prv;
    private Double bch;
    private Double btc;
    private Date recTime;

    public MbSubUser getMbSubUser() {
        return mbSubUser;
    }

    public void setMbSubUser(MbSubUser mbSubUser) {
        this.mbSubUser = mbSubUser;
    }

    public Double getPrv() {
        return prv;
    }

    public void setPrv(Double prv) {
        this.prv = prv;
    }

    public Double getBch() {
        return bch;
    }

    public void setBch(Double bch) {
        this.bch = bch;
    }

    public Double getBtc() {
        return btc;
    }

    public void setBtc(Double btc) {
        this.btc = btc;
    }

    public Date getRecTime() {
        return recTime;
    }

    public void setRecTime(Date recTime) {
        this.recTime = recTime;
    }
}
