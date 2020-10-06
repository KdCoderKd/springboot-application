package com.embl.taxonamy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.embl.taxonomy.controller.StudyController;
import com.embl.taxonomy.domain.Project;
import com.embl.taxonomy.domain.Taxonomy;
import com.embl.taxonomy.service.StudyService;

@ExtendWith(MockitoExtension.class)
public class StudyControllerStandaloneTest {

    private MockMvc mockMvc;

    @Mock
    private StudyService studyService;

    @InjectMocks
    private StudyController studyController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(studyController).build();
    }

    @Test
    public void getAllStudiesTest() {

        given(studyService.getAllStudies()).willReturn(populateProjects());

        try {
            final MvcResult mvcResult = mockMvc.perform(get("/api/v1/studies").accept(MediaType.APPLICATION_JSON)).andReturn();
            assertEquals(200, mvcResult.getResponse().getStatus());
        } catch (final Exception e) {
            fail();
        }
    }

    @Test
    public void getStudyByIdTest() {

        given(studyService.getStudyById("PRJEB4395")).willReturn(getProjectById());

        try {
            final MvcResult mvcResult = mockMvc.perform(get("/api/v1/studies/PRJEB4395").accept(MediaType.APPLICATION_JSON)).andReturn();
            assertEquals(200, mvcResult.getResponse().getStatus());
        } catch (final Exception e) {
            fail();
        }
    }

    @Test
    public void getStudiesByCommonNameTest() {

        given(studyService.getStudiesByCommonName("Rice")).willReturn(populateRiceProjects());

        try {
            final MvcResult mvcResult = mockMvc.perform(get("/api/v1/studies/filter?common-name=Rice").accept(MediaType.APPLICATION_JSON))
                    .andReturn();
            assertEquals(200, mvcResult.getResponse().getStatus());
        } catch (final Exception e) {
            fail();
        }
    }

    @Test
    public void getStudyById404Test() {

        given(studyService.getStudyById("PRJEB4300")).willReturn(null);

        try {
            final MvcResult mvcResult = mockMvc.perform(get("/api/v1/studies/PRJEB4300").accept(MediaType.APPLICATION_JSON)).andReturn();
            assertEquals(404, mvcResult.getResponse().getStatus());
        } catch (final Exception e) {
            fail();
        }
    }

    @Test
    public void getStudyById400Test() {

        try {
            final MvcResult mvcResult = mockMvc.perform(get("/api/v1/studies/filter?common-name=").accept(MediaType.APPLICATION_JSON)).andReturn();
            assertEquals(400, mvcResult.getResponse().getStatus());
        } catch (final Exception e) {
            fail();
        }
    }

    private Project getProjectById() {
        return new Project("PRJEB4395", "Whole genome re-sequencing in tomato",
                "Whole genome re-sequencing in tomato reveals variation associated with introgression and breeding events. Here we describe the whole-genome resequencing of eight tomato samples chosen to represent a large range of intra-specific variability and the identification and annotation of novel polymorphisms.Comparison of the sequences with the reference genome yielded more than 4 million single nucleotide polymorphisms (SNPs) and almost 128,000 InDels were detected.This study represents the first whole genome resequencing experiment in cultivated tomato.",
                "Germline", "Control Set", null, "Institut National de la Recherche Agronomique - France",
                new Taxonomy(4081, "Tomato", "Solanum lycopersicum"));
    }

    private List<Project> populateProjects() {
        final List<Project> projects = new ArrayList<Project>();
        projects.add(new Project("PRJEB13083", "Highly evolvable malaria vectors: The genomes of 16 Anopheles mosquitoes. Anopheles minimus samples.",
                "Anopheles minimus subset of project sequencing 16 anopheline vector genomes to complement and facilitate comparative analysis with the three other sequenced anophelines, Anopheles gambiae PEST, M and S forms.",
                "Germline", "Control Set", null, "Broad Institute", new Taxonomy(112268, "Mosquito", "Anopheles minimus")));
        projects.add(new Project("PRJEB13089",
                "Highly evolvable malaria vectors: The genomes of 16 Anopheles mosquitoes. Anopheles culicifacies samples.",
                "Anopheles culicifacies subset of project sequencing 16 anopheline vector genomes to complement and facilitate comparative analysis with the three other sequenced anophelines, Anopheles gambiae PEST, M and S forms.",
                "Germline", "Control Set", null, "Broad Institute", new Taxonomy(139723, "Mosquito", "Anopheles culicifacies")));

        projects.add(new Project("PRJEB13088",
                "Highly evolvable malaria vectors: The genomes of 16 Anopheles mosquitoes. Anopheles epiroticus samples.",
                "Anopheles epiroticus subset of project sequencing 16 anopheline vector genomes to complement and facilitate comparative analysis with the three other sequenced anophelines, Anopheles gambiae PEST, M and S forms.",
                "Germline", "Control Set", null, "Broad Institute", new Taxonomy(199890, "Mosquito", "Anopheles epiroticus")));

        projects.add(new Project("PRJEB13084", "Highly evolvable malaria vectors: The genomes of 16 Anopheles mosquitoes. Anopheles merus samples.",
                "Anopheles merus subset of project sequencing 16 anopheline vector genomes to complement and facilitate comparative analysis with the three other sequenced anophelines, Anopheles gambiae PEST, M and S forms.",
                "Germline", "Control Set", null, "Broad Institute", new Taxonomy(30066, "Mosquito", "Anopheles merus")));

        projects.add(new Project("PRJEB13080",
                "Highly evolvable malaria vectors: The genomes of 16 Anopheles mosquitoes. Anopheles stephensi samples.",
                "Anopheles stephensi subset of project sequencing 16 anopheline vector genomes to complement and facilitate comparative analysis with the three other sequenced anophelines, Anopheles gambiae PEST, M and S forms.",
                "Germline", "Control Set", null, "Broad Institute", new Taxonomy(30069, "Mosquito", "Anopheles stephensi")));

        projects.add(new Project("PRJEB12769", "Diversity of Taiwan rice varieties", "To study breeding and domestication trace of Taiwan",
                "Germline", "Control Set", null, "IPMB, Academia Sinica", new Taxonomy(4530, "Rice", "Oryza sativa")));

        projects.add(new Project("PRJEB10964",
                "Whole genome sequencing of elite rice cultivars as a comprehensive information resource for marker assisted selection",
                "Whole genome sequencing and bioinformatic analysis of 104 rice cultivars including 54 elite lines from North America and Latin America",
                "Germline", "Control Set", null, "International Center for Tropical Agriculture (CIAT)", new Taxonomy(4530, "Rice", "Oryza sativa")));

        projects.add(new Project("PRJEB12770", "Study the somatic variant due to tissue culture and transformation",
                "For inspecting the level of the somatic variation from tissue culture and transformation, there were eight treatments applied on rice. The variety is Tainung67 (TNG67), which widely used in genomic study in Taiwan and with a large poplation T-DNA insertional lines, the Taiwan Rice Insertional Mutagenesis (TRIM).",
                "Germline", "Control Set", null, "IPMB, Academia Sinica", new Taxonomy(4530, "Rice", "Oryza sativa")));

        projects.add(new Project("PRJEB13517",
                "Transcriptome characterization and gene expression analyses of salt-tolerant and salt-sensitive varieties of Pokkali Oryza sativa following exposure to salinity stress",
                "Sequence reads from Pokkali, a salt-sensitive variety, were aligned to the reference Japonica genome to the identify single nucleotide polymorphisms",
                "Germline", "Control Set", null, "Oregon State University", new Taxonomy(4530, "Rice", "Oryza sativa")));

        projects.add(new Project("PRJEB13516",
                "Transcriptome characterization and gene expression analyses of salt-tolerant and salt-sensitive varieties of IR29 Oryza sativa following exposure to salinity stress",
                "Sequence reads from IR29, a salt-sensitive variety, were aligned to the reference Japonica genome to the identify single nucleotide polymorphisms",
                "Germline", "Control Set", null, "Oregon State University", new Taxonomy(4530, "Rice", "Oryza sativa")));

        projects.add(new Project("PRJEB14385",
                "Genomic diversity and introgression in O. sativa reveal the impact of domestication and breeding on the rice genome",
                "In this study, we utilize a novel 1,536 SNP panel genotyped across 395 diverse accessions of O. sativa to study genome-wide patterns of polymorphism, to characterize population structure, and to infer the introgression history of domesticated Asian rice. ",
                "Germline", "Control Set", null, "Cornell University", new Taxonomy(4530, "Rice", "Oryza sativa")));

        projects.add(new Project("PRJEB14378", "Genomewide SNP variation reveals relationships among landraces and modern varieties of rice",
                "We initiated the OryzaSNP project (www.OryzaSNP.org) to discover genetic variation within 20 rice varieties and landraces. Most varieties belong to the 2 main groups, indica and japonica, including tropical and temperate japonica, whereas others represent the aus, deep water, and aromatic rice groups. Here, we describe the discovery of high-quality, nonredundant SNPs distributed across the entire genomes of the OryzaSNPset.",
                "Germline", "Control Set", null, "International Rice Research Institute", new Taxonomy(4530, "Rice", "Oryza sativa")));

        projects.add(new Project("PRJEB13618", "The 3,000 rice genomes project",
                "The 3000 Rice Genome Project is an international effort to sequence the genomes of 3,024 rice varieties from 89 countries. The project contains the variant calls of 3000 samples against the Nipponbare reference genome sequence. This data is available for anyone to use under the terms of the Toronto Statement.",
                "Germline", "Control Set", null, "International Rice Research Institute (IRRI)", new Taxonomy(4530, "Rice", "Oryza sativa")));

        projects.add(new Project("PRJEB4395", "Whole genome re-sequencing in tomato",
                "Whole genome re-sequencing in tomato reveals variation associated with introgression and breeding events. Here we describe the whole-genome resequencing of eight tomato samples chosen to represent a large range of intra-specific variability and the identification and annotation of novel polymorphisms.Comparison of the sequences with the reference genome yielded more than 4 million single nucleotide polymorphisms (SNPs) and almost 128,000 InDels were detected.This study represents the first whole genome resequencing experiment in cultivated tomato.",
                "Germline", "Control Set", null, "Institut National de la Recherche Agronomique - France",
                new Taxonomy(4081, "Tomato", "Solanum lycopersicum")));
        return projects;
    }

    private List<Project> populateRiceProjects() {
        final List<Project> riceProjects = new ArrayList<Project>();
        riceProjects.add(new Project("PRJEB12769", "Diversity of Taiwan rice varieties", "To study breeding and domestication trace of Taiwan",
                "Germline", "Control Set", null, "IPMB, Academia Sinica", new Taxonomy(4530, "Rice", "Oryza sativa")));

        riceProjects.add(new Project("PRJEB10964",
                "Whole genome sequencing of elite rice cultivars as a comprehensive information resource for marker assisted selection",
                "Whole genome sequencing and bioinformatic analysis of 104 rice cultivars including 54 elite lines from North America and Latin America",
                "Germline", "Control Set", null, "International Center for Tropical Agriculture (CIAT)", new Taxonomy(4530, "Rice", "Oryza sativa")));

        riceProjects.add(new Project("PRJEB12770", "Study the somatic variant due to tissue culture and transformation",
                "For inspecting the level of the somatic variation from tissue culture and transformation, there were eight treatments applied on rice. The variety is Tainung67 (TNG67), which widely used in genomic study in Taiwan and with a large poplation T-DNA insertional lines, the Taiwan Rice Insertional Mutagenesis (TRIM).",
                "Germline", "Control Set", null, "IPMB, Academia Sinica", new Taxonomy(4530, "Rice", "Oryza sativa")));

        riceProjects.add(new Project("PRJEB13517",
                "Transcriptome characterization and gene expression analyses of salt-tolerant and salt-sensitive varieties of Pokkali Oryza sativa following exposure to salinity stress",
                "Sequence reads from Pokkali, a salt-sensitive variety, were aligned to the reference Japonica genome to the identify single nucleotide polymorphisms",
                "Germline", "Control Set", null, "Oregon State University", new Taxonomy(4530, "Rice", "Oryza sativa")));

        riceProjects.add(new Project("PRJEB13516",
                "Transcriptome characterization and gene expression analyses of salt-tolerant and salt-sensitive varieties of IR29 Oryza sativa following exposure to salinity stress",
                "Sequence reads from IR29, a salt-sensitive variety, were aligned to the reference Japonica genome to the identify single nucleotide polymorphisms",
                "Germline", "Control Set", null, "Oregon State University", new Taxonomy(4530, "Rice", "Oryza sativa")));

        riceProjects.add(new Project("PRJEB14385",
                "Genomic diversity and introgression in O. sativa reveal the impact of domestication and breeding on the rice genome",
                "In this study, we utilize a novel 1,536 SNP panel genotyped across 395 diverse accessions of O. sativa to study genome-wide patterns of polymorphism, to characterize population structure, and to infer the introgression history of domesticated Asian rice. ",
                "Germline", "Control Set", null, "Cornell University", new Taxonomy(4530, "Rice", "Oryza sativa")));

        riceProjects.add(new Project("PRJEB14378", "Genomewide SNP variation reveals relationships among landraces and modern varieties of rice",
                "We initiated the OryzaSNP project (www.OryzaSNP.org) to discover genetic variation within 20 rice varieties and landraces. Most varieties belong to the 2 main groups, indica and japonica, including tropical and temperate japonica, whereas others represent the aus, deep water, and aromatic rice groups. Here, we describe the discovery of high-quality, nonredundant SNPs distributed across the entire genomes of the OryzaSNPset.",
                "Germline", "Control Set", null, "International Rice Research Institute", new Taxonomy(4530, "Rice", "Oryza sativa")));

        riceProjects.add(new Project("PRJEB13618", "The 3,000 rice genomes project",
                "The 3000 Rice Genome Project is an international effort to sequence the genomes of 3,024 rice varieties from 89 countries. The project contains the variant calls of 3000 samples against the Nipponbare reference genome sequence. This data is available for anyone to use under the terms of the Toronto Statement.",
                "Germline", "Control Set", null, "International Rice Research Institute (IRRI)", new Taxonomy(4530, "Rice", "Oryza sativa")));
        return riceProjects;
    }
}