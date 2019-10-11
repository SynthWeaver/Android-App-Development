package nl.terwijn.rockpaperscissors

class Computer {
    public fun getAnswer():String {

        //get random 1 ~ 3
        val random = (1..3).random()

        when (random) {
            1 -> return Input.ROCK
            2 -> return Input.PAPER
            3 -> return Input.SCISSORS
            else -> { // Note the block
                print("x is neither 1 nor 2")
                return Input.ROCK
            }
        }
    }
}