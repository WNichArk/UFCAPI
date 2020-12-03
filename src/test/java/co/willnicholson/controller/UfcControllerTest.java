package co.willnicholson.controller;

import co.willnicholson.DTOs.FightDTO;
import co.willnicholson.analysis.MatchupAnalysis;
import co.willnicholson.service.FightService;
import co.willnicholson.service.FighterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Note - A key part of UNIT testing is to restrict the scope to a minimum.
@RunWith(SpringRunner.class)
@WebMvcTest(value = UfcController.class)
public class UfcControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    FightService fightService;
    @MockBean
    FighterService fighterService;
    @MockBean
    MatchupAnalysis matchupAnalysis;
    @MockBean
    TwilioController twilioController;

    @Test
    public void getFightsByWinRound_ScenarioA() throws Exception{
        //Arrange
        List<FightDTO> testFightsList = new ArrayList<>();
        FightDTO fightA = new FightDTO();
        fightA.setFighter1("Will");
        fightA.setWinRound(2);
        testFightsList.add(fightA);
        FightDTO fightB = new FightDTO();
        fightB.setFighter1("Thomas");
        fightB.setWinRound(2);
        testFightsList.add(fightB);

        doReturn(testFightsList).when(fightService).getFightsByWinRound(2);

        List<FightDTO> expectedList = new ArrayList<>(); //Note don't skip this. Must be "different" from mock data reference in memory.
        FightDTO expectedA = new FightDTO();
        expectedA.setFighter1("Will");
        expectedA.setWinRound(2);
        expectedList.add(expectedA);
        FightDTO expectedB = new FightDTO();
        expectedB.setFighter1("Thomas");
        expectedB.setWinRound(2);
        expectedList.add(expectedB); //Note this list IN MEMORY is separate from the list being "returned" by service.

        String expectedResult = objectMapper.writeValueAsString(expectedList); //Note return list as string - this is the expected result

        //Act
        // Note now we need to build a mock http request - MockMvc.perform does this using its RequestBuilders
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/ufc/winround/2"))
                .andExpect(status().is(200))
                .andReturn();

        String actualResult = result.getResponse().getContentAsString(); //Note - this is the real result to test against our expectedResult that we built

        //Assert
        Assert.assertThat(actualResult, is(equalTo(expectedResult))); //Note - assert that the result == the expectedResult (not the same in memory)



        //Verify
        verify(fightService, times(1)).getFightsByWinRound(2);
        verifyNoMoreInteractions(ignoreStubs(fightService));

    }

    @Test
    public void getWinsByFighter_ScenarioA() throws Exception {
        //Arrange
        List<FightDTO> testList = new ArrayList<>();
        FightDTO fightA = new FightDTO();
        fightA.setFighter1("Will");
        fightA.setFighter2("Thomas");
        FightDTO fightB = new FightDTO();
        testList.add(fightA);
        fightB.setFighter1("Will");
        fightB.setFighter2("Thomas");
        testList.add(fightB);

        doReturn(testList).when(fightService).getWinsByFighter("Will");

        List<FightDTO> expectedList = new ArrayList<>();
        FightDTO expectedA = new FightDTO();
        expectedA.setFighter1("Will");
        expectedA.setFighter2("Thomas");
        expectedList.add(fightA);
        FightDTO expectedB = new FightDTO();
        expectedB.setFighter1("Will");
        expectedB.setFighter2("Thomas");
        expectedList.add(fightB);

        String expectedResult = objectMapper.writeValueAsString(expectedList);
        //Act

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/ufc/winsbyfighter/Will"))
                .andExpect(status().is(200))
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();

        //Assert

        Assert.assertThat(actualResult, is(equalTo(expectedResult)));

        //Verify
        verify(fightService, times(1)).getWinsByFighter("Will");
        verifyNoMoreInteractions(ignoreStubs(fightService));
    }

    @Test
    public void getLossesByFighter_ScenarioA() throws Exception{
        //Arrange
        List<FightDTO> testLossFights = new ArrayList<>();
        FightDTO fightOne = new FightDTO();
        fightOne.setFighter1("Will");
        fightOne.setFighter2("Thomas");
        testLossFights.add(fightOne);
        FightDTO fightTwo = new FightDTO();
        fightTwo.setFighter2("Thomas");
        fightTwo.setFighter1("Will");
        testLossFights.add(fightTwo);

        doReturn(testLossFights).when(fightService).getLossesByFighter("Thomas");

        List<FightDTO> expectedList = new ArrayList<>();
        FightDTO expectedA = new FightDTO();
        expectedA.setFighter1("Will");
        expectedA.setFighter2("Thomas");
        expectedList.add(expectedA);
        FightDTO expectedB = new FightDTO();
        expectedB.setFighter1("Will");
        expectedB.setFighter2("Thomas");
        expectedList.add(expectedB);

        String expectedResult = objectMapper.writeValueAsString(expectedList);

        //Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/ufc/lossesbyfighter/Thomas"))
                .andExpect(status().is(200))
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();

        //Assert
        Assert.assertThat(actualResult, is(equalTo(expectedResult)));

        //Verify

        verify(fightService, times(1)).getLossesByFighter("Thomas");
        verifyNoMoreInteractions(ignoreStubs(fightService));




    }
}