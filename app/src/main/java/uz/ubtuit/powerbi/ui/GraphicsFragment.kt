package uz.ubtuit.powerbi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import uz.ubtuit.powerbi.R

class GraphicsFragment : Fragment(R.layout.fragment_graphics) {
    private lateinit var imageView: ImageView
    private var imageUrl = "https://ukclippingpath.com/wp-content/uploads/2022/01/Increase-Conversion-Rate-eCommerce-1.jpg"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        imageView = view.findViewById(R.id.ivGraph)
        val ivBack=view.findViewById<ImageView>(R.id.ivBack)
        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        Glide.with(imageView).load(imageUrl).into(imageView)

    }

}