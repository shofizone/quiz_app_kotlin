package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.models.Question
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity() {
    private  val TAG = "QuizQuestionsActivity"

    private var mCurrentQuestion:Int = 0
    private var mQuestionList :ArrayList<Question>? = null
    private var mSelectedOption:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        val mQuestionList = Constants.getQuestions()
        setQuestion(0);
    }

    private fun setQuestion(index:Int){
        updateView(mQuestionList!![index]);
    }
    private fun updateView(question:Question){
        progress_bar.progress = mCurrentQuestion +1
        tv_progress.text = "${mCurrentQuestion+1}/${progress_bar.max}"
        tv_question.text = question.question
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
        iv_image.setImageResource(question.image)

    }
}