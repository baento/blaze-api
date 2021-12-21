package fr.blaze.calendar.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class Resource {
    @Id
    private Integer id;
    
    @NaturalId
    private String handle;
    
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @Temporal(TemporalType.DATE)
    private Date deletionDate;

    public boolean isDeleted() {
        return deletionDate != null && deletionDate.before(new Date());
    }
}
