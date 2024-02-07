package main;

import gamecharacters.Party;

import static gamecharacters.heroes.Hero.setupHeroParty;
import static gamecharacters.monsters.Monster.setupMonsterParty;

public class Game {
    private final Party heroParty = gamecharacters.heroes.Hero.getHeroParty();
    private final Party monsterParty = gamecharacters.monsters.Monster.getMonsterParty();
    public boolean gameOver = false;
    private int numOfBattles = 0;


    // Constructor
    public Game() {
    }

    public static void main(String[] args) {
        // Setting GameMode
        Game game = new Game();
        setupHeroParty();
        game.execute();

    }

    public void execute() {
        int numOfHeroes = heroParty.getCharacters().size();

        while (!gameOver) {
            // Setup monster party
            setupMonsterParty(numOfHeroes, this);

            // Run battle
            Battle Battle = new Battle(this);
            Battle.execute();

            // End game if heroes were defeated in the last round OR boss has been defeated
            if (numOfBattles == 3 && monsterParty.isEmpty()) {
                System.out.println();
                System.out.println("All enemies have finally been defeated! The heroes win.");
                gameOver = true;
            }
            if (gameOver) break;

        }
    }

    // Getters
    public Party getHeroParty() {
        return heroParty;
    }

    public Party getMonsterParty() {
        return monsterParty;
    }


    // Main Game mechanics
    public void setNumOfBattles(int numberToAdd) {
        this.numOfBattles += numberToAdd;
    }


}
