package com.embl.taxonomy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.embl.taxonomy.domain.Project;
import com.embl.taxonomy.service.StudyService;

/**
 * Rest Controller
 */
@RestController
@RequestMapping("/api/v1")
public class StudyController {

    @Autowired
    StudyService studyService;

    private static final Logger logger = LoggerFactory.getLogger(StudyController.class);

    /**
     * Get all studies
     */
    @GetMapping("/studies")
    public ResponseEntity<List<Project>> getAllStudies() {

        final List<Project> projects = studyService.getAllStudies();

        if (projects == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("No. of studies: {}", projects.size());

        return ResponseEntity.ok(projects);
    }

    /**
     * Get a study by id
     */
    @GetMapping("/studies/{id}")
    public ResponseEntity<Project> getStudy(@PathVariable(value = "id") String projId) {

        final Project project = studyService.getStudyById(projId);

        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Study details for the id {}: {}", projId, project.toString());

        return ResponseEntity.ok(project);
    }

    /**
     * Filter studies by taxonomy common name.
     */

    @GetMapping("/studies/filter")
    public ResponseEntity<List<Project>> getStudiesByCommonName(@RequestParam(value = "common-name") String commonName) {

        if (commonName == null || commonName.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        final List<Project> projects = studyService.getStudiesByCommonName(commonName);

        if (projects == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("No. of studies: {}", projects.size());

        return ResponseEntity.ok(projects);

    }
}
