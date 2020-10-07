package com.embl.taxonamy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.embl.taxonomy.TaxonamyApplication;
import com.embl.taxonomy.controller.StudyController;

@SpringBootTest(classes = TaxonamyApplication.class)
class TaxonamyApplicationTest {

    @Autowired
    private StudyController studyController;

    @Test
    void contextLoads() {
        assertTrue(studyController != null);
    }

}
