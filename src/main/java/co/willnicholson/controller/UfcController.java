package co.willnicholson.controller;


import co.willnicholson.analysis.MatchupAnalysis;
import co.willnicholson.service.FightService;
import co.willnicholson.service.FighterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@RestController
@Api(value = "Ufc Endpoints")
@RequestMapping("/ufc")
public class UfcController {
    private FightService fightService;
    private FighterService fighterService;
    private MatchupAnalysis matchupAnalysis;


    @Autowired
    public UfcController(FightService s, FighterService f, MatchupAnalysis m){
        this.fightService = s;
        this.fighterService = f;
        this.matchupAnalysis = m;
    }

    //Fight-specific endpoints
    @Operation(summary = "Gets fights ending in given round.")
    @GetMapping(value = "/winround/{winRound}", produces = "application/json")
    public ResponseEntity getFightsByWinRound(@PathVariable int winRound){
        return ResponseEntity.status(HttpStatus.OK).body(fightService.getFightsByWinRound(winRound));
    }
    @Operation(summary = "Gets wins by fighter using name search.")
    @GetMapping(value = "/winsbyfighter/{name}", produces = "application/json")
    public ResponseEntity getWinsByFighter(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(fightService.getWinsByFighter(name));
    }
    @Operation(summary = "Gets losses by fighter using name search.")
    @GetMapping(value= "/lossesbyfighter/{name}", produces = "application/json")
    public ResponseEntity getLossesByFighter(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(fightService.getLossesByFighter(name));
    }
    @Operation(summary = "Gets fights by win method. Ex. KO, TKO, Submission, Decision, No Contest")
    @GetMapping(value = "/winmethod/{winmethod}", produces = "application/json")
    public ResponseEntity getWinsByFightMethod(@PathVariable String winmethod){
        
        return ResponseEntity.status(HttpStatus.OK).body(fightService.getFightsByWinMethod(winmethod));
    }
    @Operation(summary = "Gets all fights. Time it.")
    @GetMapping (value = "/all", produces = "application/json")
    public ResponseEntity getAllFights(){
        return ResponseEntity.status(HttpStatus.OK).body(fightService.getAllFights());
    }
    @Operation(summary = "Gets list of upcoming fights from database")
    @GetMapping (value = "/upcoming", produces = "application/json")
    public ResponseEntity getUpcomingFights(){
        return ResponseEntity.status(HttpStatus.OK).body(fightService.getUpcomingFights());
    }
    @Operation(summary = "Gets list of upcoming fights, returned with CSS/HTML.")
    @GetMapping (value = "/upcomingpretty", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity getUpcomingFightsPretty(){
        return ResponseEntity.status(HttpStatus.OK).body(fightService.getUpcomingFightsPretty());
    }

    //FIGHTER-SPECIFIC ENDPOINTS
    @Operation(summary = "Get all info on fighter by name")
    @GetMapping(value="/fighter/{name}", produces = "application/json")
    public ResponseEntity getFighterByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(fighterService.getFighterByName(name));
    }

    //CALCULATED RETURN ENDPOINTS
    @Operation(summary = "Looks through previous opponents of two fighters for mutual matchups. Determines advantage based on fight record.")
    @GetMapping(value="/matchup", produces = "text/plain")
    public ResponseEntity getHistoricalWeights(@RequestParam String fighter1, @RequestParam String fighter2){
        return ResponseEntity.status(HttpStatus.OK).body(matchupAnalysis.getHistoricalWeight(fighter1, fighter2));
    }
    @Operation(summary = "Get win ration by fighter using name search.")
    @GetMapping(value = "/winratio/{name}", produces = "text/plain")
    public ResponseEntity getWinRatio(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body("Weighted win Ratio for " + name + " is: " + matchupAnalysis.getWinRatio(name));
    }




//    //Twilio Test Endpoints
//      @ApiIgnore
//    @GetMapping(value = "/phone/", produces = "text/plain")
//    public ResponseEntity testPhone(@RequestParam String toNumber, @RequestParam String fromNumber, @RequestParam String body){
//        twilioController.sendIt(toNumber, fromNumber, body);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//      @ApiIgnore
//    @GetMapping(value = "/getIt")
//    public ResponseEntity testPhone(){
//        twilioController.periodicReceive();
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

}
