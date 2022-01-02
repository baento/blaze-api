package fr.blaze.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@Entity
public class Component extends Resource {

    private String name;

    @ManyToOne
    private Component parent;

    @OneToMany(mappedBy = "parent")
    private List<Component> children;

    private int maxVolumeFI;

    private int maxVolumeFC;

    private int maxVolumeFA;

    private int maxVolumeOther;

    private int reference;

    private int weightedReference;
}
