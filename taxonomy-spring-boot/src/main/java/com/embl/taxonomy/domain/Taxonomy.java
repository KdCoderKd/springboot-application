package com.embl.taxonomy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "taxonomies")
@NamedQuery(name = "Taxonomy.findAll", query = "SELECT v FROM Taxonomy v")
public class Taxonomy {

    @Id
    @Column(unique = true, nullable = false, name = "taxonomy_id")
    private long Id;

    @Column(nullable = false, name = "taxonomy_common_name")
    private String commonName;

    @Column(nullable = false, name = "taxonomy_scientific_name")
    private String sciName;

}
