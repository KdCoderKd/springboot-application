package com.embl.taxonomy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "projects")
@NamedQuery(name = "Project.findAll", query = "SELECT v FROM Project v")
public class Project {

    @Id
    @Column(unique = true, nullable = false, name = "project_id")
    private String id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, length = 4000, name = "description")
    private String description;

    @Column(nullable = false, name = "source_type")
    private String srcType;

    @Column(nullable = false, name = "study_type")
    private String studyType;

    @Column(nullable = true, name = "eva_center_name")
    private String evaCenterName;

    @Column(nullable = false, name = "center_name")
    private String centerName;

    @ManyToOne
    @JoinColumn(name = "taxonomy_id", referencedColumnName = "taxonomy_id")
    private Taxonomy taxonomy;
}
