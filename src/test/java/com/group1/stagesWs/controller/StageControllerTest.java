package com.group1.stagesWs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group1.stagesWs.enums.CVStatus;
import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.model.Whitelist;
import com.group1.stagesWs.service.CVService;
import com.group1.stagesWs.service.EmailService;
import com.group1.stagesWs.service.StageService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(StageController.class)
public class StageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StageService stageService;

    @MockBean
    private CVService cvService;

    @MockBean
    private EmailService emailService;

    private static ObjectMapper mapper;

    @BeforeAll
    static void initializeObjectMapper() {
        mapper = new ObjectMapper().findAndRegisterModules();
    }

    @Test
    void testGetAllOffres() throws Exception {
        //Arrange
        List<Offre> expected = List.of(getOffre(), getOffre(), getOffre());
        when(stageService.getAllOffres()).thenReturn(expected);

        //Act
        MvcResult result = mockMvc.perform(get("/stage/offres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(expected))).andReturn();

        //Assert
        var actualOffres = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualOffres.size()).isEqualTo(expected.size());
    }

    @Test
    void testGetEtudiantOffres() throws Exception {
        //Arrange
        Etudiant etudiant = getEtudiant();
        List<Offre> expected = List.of(getOffre(), getOffre(), getOffre());

        when(stageService.getEtudiantOffres(any(String.class))).thenReturn(expected);
        String url = "/stage/offres/etudiant/" + etudiant.getCourriel();
        //Act
        MvcResult result = mockMvc.perform(get(url)).andReturn();

        //Assert
        var actualOffres = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualOffres.size()).isEqualTo(expected.size());
    }

    @Test
    void testSaveOffre() throws Exception {
        //Arrange
        Offre expected = getOffre();
        when(stageService.saveOffre(expected)).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(post("/stage/offre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(expected))).andReturn();

        //Assert
        var actualOffre = mapper.readValue(result.getResponse().getContentAsString(), Offre.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualOffre).isEqualTo(expected);
    }


    @Test
    void testSaveWhitelist() throws Exception {
        //Arrange
        Whitelist expected = new Whitelist();
        when(stageService.saveWhitelist(any(Whitelist.class))).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(post("/stage/whitelist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(expected))).andReturn();

        //Assert
        var actualWhitelist = mapper.readValue(result.getResponse().getContentAsString(), Whitelist.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualWhitelist).isEqualTo(expected);
    }


    @Test
    void testAcceptCV() throws Exception {
        //Arrange
        CV expected = new CV();
        expected.setStatus(CVStatus.ACCEPTED);
        when(stageService.acceptCV(any())).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(post("/stage/cv/accept")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(expected))).andReturn();

        //Assert
        var actual = mapper.readValue(result.getResponse().getContentAsString(), CV.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actual.getStatus()).isEqualTo(CVStatus.ACCEPTED);
    }

    @Test
    void testAcceptCVFail() throws Exception {
        //Arrange
        when(stageService.acceptCV(any())).thenReturn(Optional.empty());

        //Act
        MvcResult result = mockMvc.perform(post("/stage/cv/accept")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new CV()))).andReturn();

        //Assert
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void testRejectCV() throws Exception {
        //Arrange
        CV expected = new CV();
        expected.setStatus(CVStatus.REJECTED);
        when(stageService.rejectCV(any())).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(post("/stage/cv/reject")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(expected))).andReturn();

        //Assert
        var actual = mapper.readValue(result.getResponse().getContentAsString(), CV.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actual.getStatus()).isEqualTo(CVStatus.REJECTED);
    }

    @Test
    void testRejectCVFail() throws Exception {
        //Arrange
        when(stageService.rejectCV(any())).thenReturn(Optional.empty());

        //Act
        MvcResult result = mockMvc.perform(post("/stage/cv/reject")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new CV()))).andReturn();

        //Assert
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void testGetAllCVs() throws Exception {
        //Arrange
        List<CV> expected = List.of(new CV(), new CV(), new CV());
        when(stageService.getAllCVs()).thenReturn(expected);

        //Act
        MvcResult result = mockMvc.perform(get("/stage/cv")).andReturn();

        //Assert
        var actual = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actual.size()).isEqualTo(3);
    }

    @Test
    void testGetCV() throws Exception {
        //Arrange
        CV expected = new CV();
        expected.setId(1);
        when(stageService.getCV(1)).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(get("/stage/cv/" + expected.getId())).andReturn();

        //Assert
        var actual = mapper.readValue(result.getResponse().getContentAsString(), CV.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actual).isEqualTo(expected);
    }

    private Offre getOffre() {
        return new Offre(
                "Developpeur Java",
                "Developpeur Java sur un projet de banque",
                "Banque NCA",
                false,
                "1345 Boul Leger Saint-Jean",
                "2022-1-05",
                "2022-4-05",
                13,
                "9:00 a 5:00",
                40,
                22);
    }

    private Etudiant getEtudiant() {
        return new Etudiant(
                "Pascal",
                "Bourgoin",
                "test@test.com",
                "password",
                "123456789",
                "technique",
                "addy 123",
                "123456",
                true,
                true);
    }
}
