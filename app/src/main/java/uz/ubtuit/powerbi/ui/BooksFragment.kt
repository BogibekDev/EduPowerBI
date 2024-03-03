package uz.ubtuit.powerbi.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.ubtuit.powerbi.R
import uz.ubtuit.powerbi.adapter.BooksAdapter
import uz.ubtuit.powerbi.data.ApiClient
import uz.ubtuit.powerbi.model.Book
import uz.ubtuit.powerbi.utils.hide
import uz.ubtuit.powerbi.utils.show

class BooksFragment : Fragment(R.layout.fragment_books) {
    private lateinit var list: ArrayList<Book>
    private lateinit var adapter: BooksAdapter
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
        loadBooks()
        adapter = BooksAdapter(list)
        rvBooks = view.findViewById(R.id.rvBooks)
        rvBooks.adapter = adapter
        adapter.itemClick = { position ->

            findNavController()
                .navigate(
                    R.id.action_booksFragment_to_bookPdfFragment,
                    bundleOf(
                        "pdf" to
                                list[position].url
                    )
                )
        }

    }

    private fun loadBooks() {
        loading.show()
        list = ArrayList()
        ApiClient.apiService.getPDFs().enqueue(object : Callback<ArrayList<Book>> {
            override fun onResponse(
                call: Call<ArrayList<Book>>,
                response: Response<ArrayList<Book>>
            ) {
                if (response.isSuccessful) {
                    list = response.body()!!
                    adapter.submitList(list)
                }
                loading.hide()
                Log.d("@@@@", "onResponse: ${response.code()}")
            }

            override fun onFailure(call: Call<ArrayList<Book>>, t: Throwable) {
                loading.hide()
                Log.d("@@@@", "onFailure: ${t.message}")
            }
        })

        list = arrayListOf(
            Book(
                name = "1-dars",
                url = "https://freekidsbooks.org/wp-content/uploads/2024/01/2401-gorillas-go-to-the-beach-FKB.pdf"
            ),
            Book(
                name = "1-dars",
                url = "https://freekidsbooks.org/wp-content/uploads/2024/01/2401-gorillas-go-to-the-beach-FKB.pdf"
            ),
            Book(
                name = "1-dars",
                url = "https://freekidsbooks.org/wp-content/uploads/2024/01/2401-gorillas-go-to-the-beach-FKB.pdf"
            ),
            Book(
                name = "1-dars",
                url = "https://freekidsbooks.org/wp-content/uploads/2024/01/2401-gorillas-go-to-the-beach-FKB.pdf"
            ),
            Book(
                name = "1-dars",
                url = "https://freekidsbooks.org/wp-content/uploads/2024/01/2401-gorillas-go-to-the-beach-FKB.pdf"
            ),
            Book(
                name = "1-dars",
                url = "https://freekidsbooks.org/wp-content/uploads/2024/01/2401-gorillas-go-to-the-beach-FKB.pdf"
            ),
            Book(
                name = "1-dars",
                url = "https://freekidsbooks.org/wp-content/uploads/2024/01/2401-gorillas-go-to-the-beach-FKB.pdf"
            ),
            Book(
                name = "1-dars",
                url = "https://freekidsbooks.org/wp-content/uploads/2024/01/2401-gorillas-go-to-the-beach-FKB.pdf"
            ),
            Book(
                name = "1-dars",
                url = "https://freekidsbooks.org/wp-content/uploads/2024/01/2401-gorillas-go-to-the-beach-FKB.pdf"
            ),
            Book(
                name = "1-dars",
                url = "https://freekidsbooks.org/wp-content/uploads/2024/01/2401-gorillas-go-to-the-beach-FKB.pdf"
            ),
            Book(
                name = "1-dars",
                url = "https://freekidsbooks.org/wp-content/uploads/2024/01/2401-gorillas-go-to-the-beach-FKB.pdf"
            ),
            Book(
                name = "1-dars",
                url = "https://freekidsbooks.org/wp-content/uploads/2024/01/2401-gorillas-go-to-the-beach-FKB.pdf"
            ),


            )
    }
}