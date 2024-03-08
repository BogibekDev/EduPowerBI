package uz.ubtuit.powerbi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.ubtuit.powerbi.R
import uz.ubtuit.powerbi.adapter.BooksAdapter
import uz.ubtuit.powerbi.adapter.TestsAdapter
import uz.ubtuit.powerbi.data.ApiClient
import uz.ubtuit.powerbi.model.Book
import uz.ubtuit.powerbi.model.Question
import uz.ubtuit.powerbi.utils.hide
import uz.ubtuit.powerbi.utils.show


class TestsFragment : Fragment(R.layout.fragment_tests) {
    private lateinit var list: ArrayList<Question>
    private lateinit var adapter: TestsAdapter
    private lateinit var rvBooks: RecyclerView
    private lateinit var loading: LottieAnimationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        loading = view.findViewById(R.id.loading)
        val ivBack = view.findViewById<ImageView>(R.id.ivBack)
        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        loadTests()
        adapter = TestsAdapter(list)
        rvBooks = view.findViewById(R.id.rvTest)
        rvBooks.adapter = adapter
        adapter.itemClick = { position ->

            findNavController()
                .navigate(
                    R.id.action_testsFragment_to_testFragment,
                    bundleOf(
                        "category" to list[position].toifalash.dars
                    )
                )
        }

    }

    private fun loadTests() {
        loading.show()
        list = ArrayList()
        ApiClient.apiService.getTests().enqueue(object : Callback<ArrayList<Question>> {
            override fun onResponse(
                call: Call<ArrayList<Question>>,
                response: Response<ArrayList<Question>>
            ) {
                if (response.isSuccessful) {
                    val categories = ArrayList<String>()
                    response.body()!!.forEach {
                        if (!categories.contains(it.toifalash.dars)) {
                            categories.add(it.toifalash.dars)
                            list.add(it)
                        }
                    }

                    adapter.submitList(list)
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