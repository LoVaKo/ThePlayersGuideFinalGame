package Main;

public enum GameMode {
    PLAYER_VS_COMPUTER {
        @Override
        public String toString() {
            return "Player vs. Computer";
        }
    },
    PLAYER_VS_PLAYER {
        @Override
        public String toString() {
            return "Player vs. Player";
        }
    },
    COMPUTER_VS_COMPUTER {
        @Override
        public String toString() {
            return "Computer vs. Computer";
        }
    }

}
