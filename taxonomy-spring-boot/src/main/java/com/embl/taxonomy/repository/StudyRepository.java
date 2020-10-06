package com.embl.taxonomy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.embl.taxonomy.domain.Project;

@Repository
public interface StudyRepository extends JpaRepository<Project, String> {

    @Query(value = "SELECT * FROM projects WHERE TAXONOMY_ID in "
            + "(SELECT TAXONOMY_ID FROM taxonomies WHERE TAXONOMY_COMMON_NAME LIKE :commonName)", nativeQuery = true)
    public List<Project> getStudiesByCommonName(@Param("commonName") String commonName);

    @Query(value = "SELECT * FROM projects WHERE project_id = :projId", nativeQuery = true)
    public Project getById(@Param("projId") String projId);

}
