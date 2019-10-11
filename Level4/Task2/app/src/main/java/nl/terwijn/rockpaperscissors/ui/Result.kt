package nl.terwijn.rockpaperscissors.ui

class Result{
    companion object {
        val WIN = "You win!"
        val DRAW = "Draw"
        val LOSE = "Computer wins!"
    }

    public fun getWinner(playerInput : String, computerInput : String): String {
        if(playerInput == computerInput){
            return DRAW
        }

        if(playerInput == Input.ROCK && computerInput == Input.SCISSORS){
           return WIN
        }else if(playerInput == Input.PAPER && computerInput == Input.ROCK){
            return WIN
        }else if(playerInput == Input.SCISSORS && computerInput == Input.PAPER){
            return WIN
        }

        return LOSE
    }
}