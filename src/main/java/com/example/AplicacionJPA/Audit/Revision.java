package com.example.AplicacionJPA.Audit;

import com.example.AplicacionJPA.Config.CustomRevisionListener;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;


import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Revision_Info")
@RevisionEntity(CustomRevisionListener.class)
@Data
public class Revision implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revisio_seq")
    @SequenceGenerator(name = "revision_seq", sequenceName = "rbac.seq_revison_id")
    @RevisionNumber
    private Long id;

    @Column(name = "Revisio_Date")
    @Temporal(TemporalType.TIMESTAMP)
    @RevisionTimestamp
    private Date date;
}
