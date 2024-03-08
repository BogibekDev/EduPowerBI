package uz.ubtuit.powerbi.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
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
import uz.ubtuit.powerbi.adapter.VideosAdapter
import uz.ubtuit.powerbi.data.ApiClient
import uz.ubtuit.powerbi.model.Video
import uz.ubtuit.powerbi.utils.hide
import uz.ubtuit.powerbi.utils.show


class VideosFragment : Fragment(R.layout.fragment_videos) {
    private lateinit var list: ArrayList<Video>
    private lateinit var adapter: VideosAdapter
    private lateinit var rvVideos: RecyclerView
    private lateinit var loading: LottieAnimationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        val ivBack = view.findViewById<ImageView>(R.id.ivBack)
        loading = view.findViewById(R.id.loading)
        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        loadVideos()
        adapter = VideosAdapter(list)
        rvVideos = view.findViewById(R.id.rvVideos)
        rvVideos.adapter = adapter

        adapter.itemClick = { position ->
            findNavController().navigate(
                R.id.action_videosFragment_to_videoFragment,
                bundleOf(
                    "videoUrl" to list[position].file,
                    "description" to list[position].description,

                    )
            )
        }

    }

    private fun loadVideos() {
        loading.show()
        list = ArrayList()
        ApiClient.apiService.getVideos().enqueue(object : Callback<ArrayList<Video>> {
            override fun onResponse(
                call: Call<ArrayList<Video>>,
                response: Response<ArrayList<Video>>
            ) {
                if (response.isSuccessful) {
                    list = response.body()!!
                    adapter.submitList(list)
                }
                loading.hide()
            }

            override fun onFailure(call: Call<ArrayList<Video>>, t: Throwable) {
                loading.hide()
            }
        })
    }
}