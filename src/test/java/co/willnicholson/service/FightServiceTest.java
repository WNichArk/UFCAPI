package co.willnicholson.service;

import co.willnicholson.DTOs.FightDTO;
import co.willnicholson.DTOs.FightEntity;
import co.willnicholson.repository.FightRepository;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class FightServiceTest{

    @Mock
    private MapperFacade mapperFacade;

    @Mock
    private FightRepository testRepository;

    @InjectMocks
    private FightService systemUnderTest;




    @Test
    public void testGetFightsByWinRound_ScenarioA() throws Exception{
        //Arrange

        List<FightEntity> testList = new ArrayList<>();
        FightEntity fightA = new FightEntity();
        fightA.setFighter1("A");
        fightA.setWinRound(15);
        testList.add(fightA);
        FightEntity fightB = new FightEntity();
        fightB.setFighter1("B");
        fightB.setWinRound(15);
        testList.add(fightB);
        FightEntity fightC = new FightEntity();
        fightC.setFighter1("C");
        fightC.setWinRound(15);
        testList.add(fightC);

        doReturn(testList).when(testRepository).findAllByWinRound(15);

        List<FightDTO> testDtoList = new ArrayList<>();
        FightDTO dtoA = new FightDTO();
        dtoA.setFighter1("A");
        dtoA.setWinRound(15);
        testDtoList.add(dtoA);
        FightDTO dtoB = new FightDTO();
        dtoB.setFighter1("B");
        dtoB.setWinRound(15);
        testDtoList.add(dtoB);
        FightDTO dtoC = new FightDTO();
        dtoC.setFighter1("C");
        dtoC.setWinRound(15);
        testDtoList.add(dtoC);

        doReturn(testDtoList).when(mapperFacade).mapAsList(testList, FightDTO.class);


        //Act

        List<FightDTO> actual = systemUnderTest.getFightsByWinRound(15);


        //Assert

        Assert.assertThat(actual.size(), is(3));
        Assert.assertThat(actual.get(0).getFighter1(), is("A"));
        Assert.assertThat(actual.get(1).getWinRound(), is(15));


        //Verify
        verify(testRepository, times(1)).findAllByWinRound(15);
        verifyNoMoreInteractions(testRepository);
    }

    @Test
    public void testGetWinsByFighter() {
        //Arrange
        List<FightEntity> testEntityList = new ArrayList<>();
        FightEntity testA = new FightEntity();
        testA.setFighter1("Will");
        testEntityList.add(testA);
        FightEntity testB = new FightEntity();
        testB.setFighter1("Will");
        testEntityList.add(testB);
        FightEntity testC = new FightEntity();
        testC.setFighter1("Will");
        testEntityList.add(testC);

        doReturn(testEntityList).when(testRepository).findAllByFighter1Containing("Will");

        List<FightDTO> testDtoList = new ArrayList<>();
        FightDTO testDtoA = new FightDTO();
        testDtoA.setFighter1("Will");
        testDtoList.add(testDtoA);
        FightDTO testDtoB =new FightDTO();
        testDtoB.setFighter1("Will");
        testDtoList.add(testDtoB);
        FightDTO testDtoC = new FightDTO();
        testDtoC.setFighter1("Will");
        testDtoList.add(testDtoC);

        doReturn(testDtoList).when(mapperFacade).mapAsList(testEntityList, FightDTO.class);

        //Act
        List<FightDTO> actual = systemUnderTest.getWinsByFighter("Will");

        //Assert
        Assert.assertThat(actual.size(), is(3));
        Assert.assertThat(actual.get(0).getFighter1(), is("Will"));

        //Verify
        verify(testRepository, times(1)).findAllByFighter1Containing("Will");
        verifyNoMoreInteractions(testRepository);
    }

    @Test
    public void testGetLossesByFighter() {

        //Arrange
        List<FightEntity> testFightEntites = new ArrayList<>();
        FightEntity fightA = new FightEntity();
        fightA.setFighter1("Winner");
        fightA.setFighter2("Loser");
        fightA.setWinRound(15);
        testFightEntites.add(fightA);
        FightEntity fightB = new FightEntity();
        fightB.setFighter1("Winner");
        fightB.setFighter2("Loser");
        fightB.setWinRound(15);
        testFightEntites.add(fightB);
        FightEntity fightC = new FightEntity();
        fightC.setFighter1("Winner");
        fightC.setFighter2("Loser");
        fightC.setWinRound(15);
        testFightEntites.add(fightC);

        doReturn(testFightEntites ).when(testRepository).findAllByFighter2Containing("Loser");

        List<FightDTO> testFightDtos = new ArrayList<>();
        FightDTO dtoA = new FightDTO();
        dtoA.setFighter1("Winner");
        dtoA.setFighter2("Loser");
        dtoA.setWinRound(15);
        testFightDtos.add(dtoA);
        FightDTO dtoB = new FightDTO();
        dtoB.setFighter1("Winner");
        dtoB.setFighter2("Loser");
        dtoB.setWinRound(15);
        testFightDtos.add(dtoB);
        FightDTO dtoC = new FightDTO();
        dtoC.setFighter1("Winner");
        dtoC.setFighter2("Loser");
        dtoC.setWinRound(15);
        testFightDtos.add(dtoC);

        doReturn(testFightDtos).when(mapperFacade).mapAsList(testFightEntites, FightDTO.class);
        //Act

        List<FightDTO> actualResult = systemUnderTest.getLossesByFighter("Loser");

        //Assert

        Assert.assertThat(actualResult.size(), is(testFightEntites.size()));
        Assert.assertThat(actualResult.get(0).getFighter1(), is("Winner"));

        //Verify

        verify(testRepository, times(1)).findAllByFighter2Containing("Loser");
        verifyNoMoreInteractions(testRepository);

    }
    @Test
    public void testGetFightsByWinMethod() {
        //Arrange
        List<FightEntity> testFightEntites = new ArrayList<>();
        FightEntity fightA = new FightEntity();
        fightA.setFighter1("Winner");
        fightA.setFighter2("Loser");
        fightA.setWinMethod("Death");
        fightA.setWinRound(15);
        testFightEntites.add(fightA);
        FightEntity fightB = new FightEntity();
        fightB.setFighter1("Winner");
        fightB.setFighter2("Loser");
        fightB.setWinMethod("Death");
        fightB.setWinRound(15);
        testFightEntites.add(fightB);
        FightEntity fightC = new FightEntity();
        fightC.setFighter1("Winner");
        fightC.setFighter2("Loser");
        fightC.setWinMethod("Death");
        fightC.setWinRound(15);
        testFightEntites.add(fightC);

       doReturn(testFightEntites).when(testRepository).findAllByWinMethodContainingOrderByDate("Death");

        List<FightDTO> testFightDtos = new ArrayList<>();
        FightDTO dtoA = new FightDTO();
        dtoA.setFighter1("Winner");
        dtoA.setFighter2("Loser");
        dtoA.setWinMethod("Death");
        dtoA.setWinRound(15);
        testFightDtos.add(dtoA);
        FightDTO dtoB = new FightDTO();
        dtoB.setFighter1("Winner");
        dtoB.setFighter2("Loser");
        dtoB.setWinMethod("Death");
        dtoB.setWinRound(15);
        testFightDtos.add(dtoB);
        FightDTO dtoC = new FightDTO();
        dtoC.setFighter1("Winner");
        dtoC.setFighter2("Loser");
        dtoC.setWinMethod("Death");
        dtoC.setWinRound(15);
        testFightDtos.add(dtoC);

        doReturn(testFightDtos).when(mapperFacade).mapAsList(testFightEntites, FightDTO.class);

        //Act

        List<FightDTO> actualResult = systemUnderTest.getFightsByWinMethod("Death");

        //Assert

        Assert.assertThat(actualResult.size(), is(testFightEntites.size()));
        Assert.assertThat(actualResult.get(0).getFighter1(), is("Winner"));

        //Verify
        verify(testRepository, times(1)).findAllByWinMethodContainingOrderByDate("Death");
        verifyNoMoreInteractions(testRepository);
    }
    @Test
    public void testGetAllFights() {
        //Arrange
        List<FightEntity> testFightEntites = new ArrayList<>();
        FightEntity fightA = new FightEntity();
        fightA.setFighter1("Winner");
        fightA.setFighter2("Loser");
        fightA.setWinMethod("Death");
        fightA.setWinRound(15);
        testFightEntites.add(fightA);
        FightEntity fightB = new FightEntity();
        fightB.setFighter1("Winner");
        fightB.setFighter2("Loser");
        fightB.setWinMethod("Death");
        fightB.setWinRound(15);
        testFightEntites.add(fightB);
        FightEntity fightC = new FightEntity();
        fightC.setFighter1("Winner");
        fightC.setFighter2("Loser");
        fightC.setWinMethod("Death");
        fightC.setWinRound(15);
        testFightEntites.add(fightC);

        doReturn(testFightEntites).when(testRepository).findAllByOrderByDateAsc();

        List<FightDTO> testFightDtos = new ArrayList<>();
        FightDTO dtoA = new FightDTO();
        dtoA.setFighter1("Winner");
        dtoA.setFighter2("Loser");
        dtoA.setWinMethod("Death");
        dtoA.setWinRound(15);
        testFightDtos.add(dtoA);
        FightDTO dtoB = new FightDTO();
        dtoB.setFighter1("Winner");
        dtoB.setFighter2("Loser");
        dtoB.setWinMethod("Death");
        dtoB.setWinRound(15);
        testFightDtos.add(dtoB);
        FightDTO dtoC = new FightDTO();
        dtoC.setFighter1("Winner");
        dtoC.setFighter2("Loser");
        dtoC.setWinMethod("Death");
        dtoC.setWinRound(15);
        testFightDtos.add(dtoC);

        doReturn(testFightDtos).when(mapperFacade).mapAsList(testFightEntites, FightDTO.class);

        //Act

        List<FightDTO> actualResult = systemUnderTest.getAllFights();

        //Assert

        Assert.assertThat(actualResult.size(), is(testFightEntites.size()));
        Assert.assertThat(actualResult.get(0).getFighter1(), is("Winner"));
        Assert.assertThat(actualResult.get(0).getWinRound(), is(15));
        Assert.assertThat(actualResult.get(0).getWinMethod(), is("Death"));

        //Verify
        verify(testRepository, times(1)).findAllByOrderByDateAsc();
        verifyNoMoreInteractions(testRepository);
    }

    public void holdLists(){

        List<FightDTO> testFightDtos = new ArrayList<>();
        FightDTO dtoA = new FightDTO();
        dtoA.setFighter1("A");
        dtoA.setFighter2("A2");
        dtoA.setWinRound(15);
        testFightDtos.add(dtoA);
        FightDTO dtoB = new FightDTO();
        dtoB.setFighter1("B");
        dtoB.setFighter2("B2");
        dtoB.setWinRound(15);
        testFightDtos.add(dtoB);
        FightDTO dtoC = new FightDTO();
        dtoC.setFighter1("C");
        dtoC.setFighter2("C2");
        dtoC.setWinRound(15);
        testFightDtos.add(dtoC);


    }
}