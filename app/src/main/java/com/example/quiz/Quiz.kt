package com.example.quiz

var index: Int = 0
var score : Int = 0

class Quiz(questions: List<Question>) {
    val question : List<Question> = questions

    fun getCurrentQuestion(): Question{
        return question[index]
    }

    fun updateScore(){
        score++
    }

    fun getScore() : Int{
        return score
    }

    fun getIndex() : Int{
        return index
    }

    fun increaseIndex(){
        index++
    }

    /**
     * @return whether or not another question exists in the list of questions
     */
    fun hasNextQuestion(): Boolean {
        return question.lastIndex != index
    }

    /**
     * Need to have another question to exist
     */
    fun getNextQuestion(): Question?{
        if(hasNextQuestion()){
            index++
            return question[index]
        }

        else{
            return null
        }
    }

    fun checkAnswer(choice : String): Boolean{
        return getCurrentQuestion().correctAnswer == choice
    }
}