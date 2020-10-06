package com.embl.taxonomy.service;

import java.util.List;

import com.embl.taxonomy.domain.Project;

public interface StudyService {

    public List<Project> getAllStudies();

    public Project getStudyById(String id);

    public List<Project> getStudiesByCommonName(String commonName);

}
