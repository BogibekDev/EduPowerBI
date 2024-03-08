package uz.ubtuit.powerbi.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.ubtuit.powerbi.R
import uz.ubtuit.powerbi.data.ApiClient
import uz.ubtuit.powerbi.model.Question
import uz.ubtuit.powerbi.utils.hide
import uz.ubtuit.powerbi.utils.show

class TestFragment : Fragment(R.layout.fragment_test) {
    private lateinit var tvQuestionNumber: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var rbAnswer1: RadioButton
    private lateinit var rbAnswer2: RadioButton
    private lateinit var rbAnswer3: RadioButton
    private lateinit var rbAnswer4: RadioButton
    private lateinit var radioGroup: RadioGroup
    private lateinit var bNext: FloatingActionButton
    private lateinit var questions: ArrayList<Question>
    private var count = 0
    private var correctAnswers = 0
    private var selected = ""
    private lateinit var loading: LottieAnimationView
    private var category = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
    }

    private fun initViews(view: View) {
        val ivBack = view.findViewById<ImageView>(R.id.ivBack)
        loading = view.findViewById(R.id.loading)
        tvQuestion = view.findViewById(R.id.tv_question)
        tvQuestionNumber = view.findViewById(R.id.tv_question_number)
        rbAnswer1 = view.findViewById(R.id.rb_answer1)
        rbAnswer2 = view.findViewById(R.id.rb_answer2)
        rbAnswer3 = view.findViewById(R.id.rb_answer3)
        rbAnswer4 = view.findViewById(R.id.rb_answer4)
        radioGroup = view.findViewById(R.id.rg_answers)
        bNext = view.findViewById(R.id.bNext)
        loadQuestions()

        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        bNext.setOnClickListener {
            selected = if (rbAnswer1.isChecked) {
                rbAnswer1.text.toString()
            } else if (rbAnswer2.isChecked) {
                rbAnswer2.text.toString()
            } else if (rbAnswer3.isChecked) {
                rbAnswer3.text.toString()
            } else if (rbAnswer4.isChecked) {
                rbAnswer4.text.toString()
            } else {
                ""
            }

            if (selected.isNotBlank()) {
                if (selected == questions[count].t_j) correctAnswers++
                count++
                if (count == questions.size) {

                    findNavController().navigate(
                        R.id.action_testFragment_to_testResultFragment,
                        bundleOf(
                            "totalQuestion" to questions.size,
                            "correctAnswers" to correctAnswers
                        )
                    )
                    correctAnswers = 0
                    count = 0
                } else setQuestion(count)
            } else {
                Toast.makeText(requireContext(), "Siz javob tanlamadingiz!", Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }

    private fun setQuestion(i: Int) {
        radioGroup.clearCheck()
        selected = ""
        tvQuestionNumber.text = "Question ${i + 1}/${questions.size}"
        tvQuestion.text = questions[i].test
        rbAnswer1.text = questions[i].a
        rbAnswer2.text = questions[i].b
        rbAnswer3.text = questions[i].c
        rbAnswer4.text = questions[i].d

    }

    private fun loadQuestions() {
        category = arguments?.getString("category") ?: ""
        questions = ArrayList()
        loading.show()
        ApiClient.apiService.getTests().enqueue(object : Callback<ArrayList<Question>> {
            override fun onResponse(
                call: Call<ArrayList<Question>>,
                response: Response<ArrayList<Question>>
            ) {
                if (response.isSuccessful) {
                    response.body()!!.forEach { question ->
                        if (category == question.toifalash.dars) questions.add(question)
                    }
                    setQuestion(count)
                }
                loading.hide()
                Log.d("@@@@", "onResponse: ${response.code()}")
            }

            override fun onFailure(call: Call<ArrayList<Question>>, t: Throwable) {
                loading.hide()
                Log.d("@@@@", "onFailure: ${t.message}")
            }
        })
    }

}