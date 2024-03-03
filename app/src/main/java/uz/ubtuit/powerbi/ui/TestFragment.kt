package uz.ubtuit.powerbi.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.ubtuit.powerbi.R
import uz.ubtuit.powerbi.model.Question

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadQuestions()
        initViews(view)
    }

    private fun initViews(view: View) {
        val ivBack = view.findViewById<ImageView>(R.id.ivBack)
        tvQuestion = view.findViewById(R.id.tv_question)
        tvQuestionNumber = view.findViewById(R.id.tv_question_number)
        rbAnswer1 = view.findViewById(R.id.rb_answer1)
        rbAnswer2 = view.findViewById(R.id.rb_answer2)
        rbAnswer3 = view.findViewById(R.id.rb_answer3)
        rbAnswer4 = view.findViewById(R.id.rb_answer4)
        radioGroup = view.findViewById(R.id.rg_answers)
        bNext = view.findViewById(R.id.bNext)
        setQuestion(count)
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
                if (selected == questions[count].correctAnswer) correctAnswers++
                count++
                if (count == questions.size) {

                    findNavController().navigate(
                        R.id.action_testFragment_to_testResultFragment,
                        bundleOf(
                            "totalQuestion" to questions.size,
                            "correctAnswers" to correctAnswers
                        )
                    )
                    correctAnswers=0
                    count=0
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
        tvQuestion.text = questions[i].question
        rbAnswer1.text = questions[i].variant1
        rbAnswer2.text = questions[i].variant2
        rbAnswer3.text = questions[i].variant3
        rbAnswer4.text = questions[i].variant4

    }

    private fun loadQuestions() {
        questions = ArrayList()
        questions.add(Question("Savol1", "javob1", "javob2", "javob3", "javob4", "javob1"))
        questions.add(Question("Savol2", "javob1", "javob2", "javob3", "javob4", "javob2"))
        questions.add(Question("Savol3", "javob1", "javob2", "javob3", "javob4", "javob2"))
        questions.add(Question("Savol4", "javob1", "javob2", "javob3", "javob4", "javob2"))
        questions.add(Question("Savol5", "javob1", "javob2", "javob3", "javob4", "javob2"))
        questions.add(Question("Savol6", "javob1", "javob2", "javob3", "javob4", "javob2"))
    }

}