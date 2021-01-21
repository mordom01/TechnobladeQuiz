package com.example.quiz

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var quiz : Quiz

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // construct a quiz object
        // get the first question and set the text fields & buttons to match
        // to test, call a few of the functions in the Quiz class and see if they do what you expect them to do.
        quiz = Quiz(jsonToList())//**********************************************************************************************make questions list global / maybe mutable?
        setUp()

        /*text_main_Question.setText(quiz.getCurrentQuestion().question)
        button_main_leftChoice.setText(quiz.getCurrentQuestion().answers[0])
        button_main_rightChoice.setText(quiz.getCurrentQuestion().answers[1])
        text_main_score.setText(quiz.getScore().toString())
        button_main_leftChoice.setOnClickListener {
            if (quiz.checkAnswer(quiz.getCurrentQuestion().answers[0])){
                quiz.updateScore()
            }
        }
        button_main_rightChoice.setOnClickListener {
            if (quiz.checkAnswer(quiz.getCurrentQuestion().answers[1])){
                quiz.updateScore()
            }
        }
        if(quiz.hasNextQuestion()){
            quiz.getNextQuestion()
            text_main_Question.setText(quiz.getCurrentQuestion().question)
            button_main_leftChoice.setText(quiz.getCurrentQuestion().answers[0])
            button_main_rightChoice.setText(quiz.getCurrentQuestion().answers[1])
            text_main_score.setText(quiz.getScore().toString())
        }*/

        // in the button listeners, when the user clicks on something,
        // it passes the info on to the Quiz class. The Quiz class decides if
        // it's right or wrong and updates its score.
        // We check if there are more questions
        // If there are, we'll ask for another question & set that up
        // otherwise, we're done and go to the final score screen
    }
    fun jsonToList() : List<Question>{
        val inputStream = resources.openRawResource(R.raw.questions)
        val inputString = inputStream.bufferedReader().use{
            it.readText()
        }

        //parsing the string into our custom objects using Gson
        val gson = Gson()
        //use the parsing between collection, list, or array section of:
        //https://medium.com/@hissain.khan/parsing-with-google-gson-library-in-android-kotlin-7920e26f5520
        val jsonDataType = object : TypeToken<List<Question>>() { }.type
        val questions = gson.fromJson<List<Question>>(inputString, jsonDataType)
        return questions
    }
    //setUp function fills in each textbox with the appropriate
    fun setUp(){
        if(quiz.hasNextQuestion()){
            text_main_Question.setText(quiz.getCurrentQuestion().question)
            button_main_leftChoice.setText(quiz.getCurrentQuestion().answers[0])
            button_main_rightChoice.setText(quiz.getCurrentQuestion().answers[1])
            text_main_score.setText(quiz.getScore().toString())
            imageView.visibility=View.VISIBLE
        }
        else{
            text_main_score.visibility = View.INVISIBLE
            button_main_rightChoice.visibility = View.INVISIBLE
            button_main_leftChoice.visibility = View.INVISIBLE
            imageView.visibility=View.VISIBLE
            text_main_Question.setText("Final Score: " + quiz.getScore() + "/14")
        }
    }

    fun onLeftClick(view: View){
        if (quiz.checkAnswer(quiz.getCurrentQuestion().answers[0])){
            quiz.updateScore()
        }
        if (quiz.hasNextQuestion()){quiz.getNextQuestion()}
        setUp()
    }
    fun onRightClick(view: View){
        if (quiz.checkAnswer(quiz.getCurrentQuestion().answers[1])){
            quiz.updateScore()
        }
        if (quiz.hasNextQuestion()){quiz.getNextQuestion()}
        setUp()
    }
}