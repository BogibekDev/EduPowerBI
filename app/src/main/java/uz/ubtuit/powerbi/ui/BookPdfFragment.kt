package uz.ubtuit.powerbi.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.github.barteksc.pdfviewer.PDFView
import uz.ubtuit.powerbi.R
import uz.ubtuit.powerbi.utils.hide
import uz.ubtuit.powerbi.utils.show
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection


class BookPdfFragment : Fragment(R.layout.fragment_book_pdf) {

    private lateinit var pdfView: PDFView
    private lateinit var loading: LottieAnimationView
    private var pdfUrl = "https://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        val ivBack=view.findViewById<ImageView>(R.id.ivBack)
        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        pdfView = view.findViewById(R.id.pdfView)
        loading = view.findViewById(R.id.loading)
        pdfUrl = arguments?.getString("pdf") ?: pdfUrl
        loadPdf(pdfUrl)
    }


    private fun loadPdf(pdf: String) {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
            try {
                loading.show()
                var inputStream: InputStream? = null
                val url = URL(pdf)

                val urlConnection =
                    if (pdf.startsWith("https")) url.openConnection() as HttpsURLConnection
                    else url.openConnection() as HttpURLConnection

                if (urlConnection.responseCode == 200) {
                    inputStream = BufferedInputStream(urlConnection.inputStream)
                    handler.post {
                        pdfView.fromStream(inputStream).onLoad {  loading.hide() }.load()
                    }
                } else {
                    loadPdf(pdf)
                }


            } catch (e: Exception) {
                Log.d("error", "loadPdf: ${e.message}")
                handler.post {
                    loading.hide()
                    Toast.makeText(
                        requireContext(),
                        "Internet bilan muammo bor!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


}