package uz.ubtuit.powerbi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import uz.ubtuit.powerbi.R


class TestResultFragment : Fragment(R.layout.fragment_test_result) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        val total=arguments?.getInt("totalQuestion") ?: 0
        val corrects=arguments?.getInt("correctAnswers") ?: 0
        val tvTotal =view.findViewById<TextView>(R.id.tvTotal)
        val tvCorrect =view.findViewById<TextView>(R.id.tvCorrects)
        val tvWrongs =view.findViewById<TextView>(R.id.tvWrongs)
        val ivBack = view.findViewById<ImageView>(R.id.ivBack)
        val bTry = view.findViewById<Button>(R.id.bTryAgain)
        val bMenu = view.findViewById<Button>(R.id.bMenu)


        tvTotal.text="$total"
        tvCorrect.text="$corrects"
        tvWrongs.text="${total-corrects}"


        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        bTry.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        bMenu.setOnClickListener {
            findNavController().navigate(R.id.action_testResultFragment_to_menuFragment)
        }

    }

}