package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizapp.models.Question
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity() {
    private val TAG = "QuizQuestionsActivity"

    private var mCurrentQuestionPosition: Int = 0
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionNumber: Int = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        mQuestionList = Constants.getQuestions()
        setQuestion(0);


    }

    fun onTapOption(view: View) {
            when (view?.id) {
                R.id.tv_option_one -> {
                    selectOptionView(tv_option_one, 0)
                }
                R.id.tv_option_two -> {
                    selectOptionView(tv_option_two, 1)
                }
                R.id.tv_option_three -> {
                    selectOptionView(tv_option_three, 2)
                }
                R.id.tv_option_four -> {
                    selectOptionView(tv_option_four, 4)
                }
                R.id.btn_submit -> {
                    handleSubmit()
                }
        }


    }

    private fun handleSubmit(){
        if (mSelectedOptionNumber == 99) {
            mCurrentQuestionPosition++

            when {
                mCurrentQuestionPosition <= mQuestionList!!.size - 1 -> {
                    setQuestion(mCurrentQuestionPosition)
                }else -> {
                Toast.makeText(this,"You have successfully complete the Quiz",Toast.LENGTH_LONG).show()
            }
            }
        }else{
            val question = mQuestionList?.get(mCurrentQuestionPosition)
            if(question!!.correctAns != mSelectedOptionNumber){
                showAnswerView(mSelectedOptionNumber,R.drawable.wrong_option_border_bg)
            }
            showAnswerView(question!!.correctAns ,R.drawable.correct_option_border_bg)
            if(mCurrentQuestionPosition == mQuestionList!!.size-1){
                btn_submit.text = "FINISH"
            }else{
                btn_submit.text = "GO TO NEXT QUESTION"
            }
            mSelectedOptionNumber = 99
        }
    }


    private fun showAnswerView(answer: Int, drawableView: Int) {
        when (answer) {
            0 -> {
                tv_option_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            1 -> {
                tv_option_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                tv_option_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                tv_option_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    private fun setQuestion(index: Int) {
        mQuestionList?.let {
            updateView(it[index]);
        }
        makeAllOptionDefault()

    }

    private fun updateView(question: Question) {

        if(mCurrentQuestionPosition == mQuestionList!!.size-1){
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = "SUBMIT"
        }

        progress_bar.progress = mCurrentQuestionPosition + 1
        tv_progress.text = "${mCurrentQuestionPosition + 1}/${progress_bar.max}"
        tv_question.text = question.question
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
        iv_image.setImageResource(question.image)

    }


    private fun makeAllOptionDefault() {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectOptionView(tv: TextView, selectedOptionNumber: Int) {
        makeAllOptionDefault()
        mSelectedOptionNumber = selectedOptionNumber;
        tv.setTextColor(Color.parseColor("#363a43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)

    }
}