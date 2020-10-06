package com.embl.taxonomy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.embl.taxonomy.domain.Project;
import com.embl.taxonomy.repository.StudyRepository;

@Service
public class StudyServiceImpl implements StudyService {

    @Autowired
    StudyRepository studyRepository;

    @Override
    public List<Project> getStudiesByCommonName(String commonName) {
        return studyRepository.getStudiesByCommonName(commonName);
    }

    @Override
    public List<Project> getAllStudies() {
        return studyRepository.findAll();
    }

    @Override
    public Project getStudyById(String projId) {
        return studyRepository.getById(projId);
    }

}
