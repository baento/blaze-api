package fr.blaze.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class Resource {
    @Id
    @GeneratedValue
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

    @JsonIgnore
    public boolean isDeleted() {
        return deletionDate != null && deletionDate.before(new Date());
    }
}
