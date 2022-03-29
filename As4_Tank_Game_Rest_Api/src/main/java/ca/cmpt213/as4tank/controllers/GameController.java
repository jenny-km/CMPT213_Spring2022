package ca.cmpt213.as4tank.controllers;

import ca.cmpt213.as4tank.model.CellState;
import ca.cmpt213.as4tank.model.Game;
import ca.cmpt213.as4tank.model.GameBoard;
import ca.cmpt213.as4tank.model.Tank;
import ca.cmpt213.as4tank.restapi.ApiBoardWrapper;
import ca.cmpt213.as4tank.restapi.ApiGameWrapper;
import ca.cmpt213.as4tank.restapi.ApiLocationWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * As4 - Tank Game Rest Api by Jennifer Kim
 *
 * Game Controller
 * - in charge of the Get/Posts and acts according to the methods
 *   based on their specified mappings
 */

@RestController
public class GameController {

    private List<Game> games = new ArrayList<>();
    boolean isCheatActivated = false;

    @GetMapping("api/about")
    public String getName(){
        return "Jennifer Kim";
    }

    @GetMapping("api/games")
    public List<ApiGameWrapper> getAllGames(){
        List<ApiGameWrapper> gameWrappers = new ArrayList<>();
        for (int i = 0; i < games.size(); i ++){
            gameWrappers.add(populateApiGameWrapper(i));
        }
        return gameWrappers;
    }

    @PostMapping("api/games")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiGameWrapper createNewGame(){
        isCheatActivated = false;
        games.add(new Game(5));
        int gameId = games.size() - 1;
        return populateApiGameWrapper(gameId);
    }

    @GetMapping("/api/games/{id}")
    public ApiGameWrapper getOneGame(@PathVariable("id") int gameId){
        if(gameId < games.size()){
            return populateApiGameWrapper(gameId);
        }
        throw new ResponseStatusException(NOT_FOUND, "GameId not Found.");
    }

    @GetMapping("api/games/{id}/board")
    public ApiBoardWrapper getBoardState(@PathVariable("id") int gameId){
        if(gameId < games.size()){
            return populateApiBoardWrapper(gameId);
        }
        throw new ResponseStatusException(NOT_FOUND, "GameId not Found.");
    }

    @PostMapping("api/games/{id}/moves")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void registerMoves(@PathVariable("id") int gameId, @RequestBody ApiLocationWrapper apiLocationWrapper){
        if(gameId < games.size()){
            int row = apiLocationWrapper.getRow();
            int col = apiLocationWrapper.getCol();
            boolean isValidRow = (row >= 0) && (row < 10);
            boolean isValidCol = (col >= 0) && (col < 10);

            if(isValidRow && isValidCol){
                CellState cellState = games.get(gameId).getGameBoard().getCellStatesArray()[row][col].makeHasBeenShot();
                games.get(gameId).getGameBoard().getCellStatesArray()[row][col] = cellState;
                games.get(gameId).fireTanks();
            }else{
                throw new ResponseStatusException(BAD_REQUEST, "Location is invalid.");
            }
        }else{
            throw new ResponseStatusException(NOT_FOUND, "GameId not Found.");
        }
    }

    @PostMapping("api/games/{id}/cheatstate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void showCheatState(@PathVariable("id") int gameId, @RequestBody String cheatState){
        if(gameId < games.size()){
            if(cheatState.equals("SHOW_ALL")){
                isCheatActivated = true;
            }else{
                throw new ResponseStatusException(BAD_REQUEST, "Not a valid String cheat");
            }
        }else{
            throw new ResponseStatusException(NOT_FOUND, "GameId not Found.");
        }

    }

    private ApiGameWrapper populateApiGameWrapper(int gameId){
        int id = gameId;
        boolean isGameWon = games.get(id).hasUserWon();
        boolean isGameLost = games.get(id).hasUserLost();
        int fortressHealth = games.get(id).getFortressHealth();
        List<Tank> tanks = games.get(id).getTanks();
        int numOfTanksAlive = 0;
        for(Tank tank : tanks){
            if(!tank.isDestroyed()){
                numOfTanksAlive++;
            }
        }
        int[] latestTankDamage = games.get(id).getLatestTankDamages();
        return new ApiGameWrapper(id, isGameWon, isGameLost, fortressHealth, numOfTanksAlive, latestTankDamage);
    }

    private ApiBoardWrapper populateApiBoardWrapper(int gameId){
        Game game = games.get(gameId);
        GameBoard board = game.getGameBoard();
        int boardWidth = board.getNumberRows();
        int boardHeight = board.getNumberCols();
        CellState[][] oldCellStates = board.getCellStatesArray();
        String[][] newCellStates = new String[boardWidth][boardHeight];
        for(int i = 0; i < newCellStates.length; i++) {
            for(int j = 0; j < newCellStates[i].length; j++) {
                if(isCheatActivated){
                    if(!oldCellStates[i][j].hasBeenShot() && oldCellStates[i][j].hasTank()){
                        newCellStates[i][j] = "tank";
                    }else if(!oldCellStates[i][j].hasBeenShot() && !oldCellStates[i][j].hasTank()){
                        newCellStates[i][j] = "field";
                    }
                }else{
                     if(oldCellStates[i][j].isHidden() ) {
                        newCellStates[i][j] = "fog";
                    }
                }
                if(oldCellStates[i][j].hasBeenShot() && oldCellStates[i][j].hasTank()){
                    newCellStates[i][j] = "hit";
                }else if(oldCellStates[i][j].hasBeenShot() && !oldCellStates[i][j].hasTank()){
                    newCellStates[i][j] = "miss";
                }
            }
        }
        return new ApiBoardWrapper(boardWidth, boardHeight, newCellStates);
    }
}
