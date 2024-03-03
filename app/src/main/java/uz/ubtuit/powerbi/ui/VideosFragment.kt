package uz.ubtuit.powerbi.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import uz.ubtuit.powerbi.R
import uz.ubtuit.powerbi.adapter.VideosAdapter
import uz.ubtuit.powerbi.model.Video


class VideosFragment : Fragment(R.layout.fragment_videos) {
    private lateinit var list: List<Video>
    private lateinit var adapter: VideosAdapter
    private lateinit var rvVideos: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        initViews(view)
    }

    private fun initViews(view: View) {
        val ivBack=view.findViewById<ImageView>(R.id.ivBack)
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
                    "videoUrl" to list[position].url
                )
            )
        }

    }

    private fun loadVideos() {
        list = listOf(
            Video(
                "1-dars",
                "https://storage.googleapis.com/downloads.webmproject.org/av1/exoplayer/bbb-av1-480p.mp4"
            ),
            Video(
                "2-dars",
                "https://storage.googleapis.com/downloads.webmproject.org/av1/exoplayer/bbb-av1-480p.mp4"
            ),
            Video(
                "3-dars",
                "https://storage.googleapis.com/downloads.webmproject.org/av1/exoplayer/bbb-av1-480p.mp4"
            ),
            Video(
                "4-dars",
                "https://storage.googleapis.com/downloads.webmproject.org/av1/exoplayer/bbb-av1-480p.mp4"
            ),
            Video(
                "5-dars",
                "https://storage.googleapis.com/downloads.webmproject.org/av1/exoplayer/bbb-av1-480p.mp4"
            ),
            Video(
                "6-dars",
                "https://storage.googleapis.com/downloads.webmproject.org/av1/exoplayer/bbb-av1-480p.mp4"
            ),
            Video(
                "7-dars",
                "https://storage.googleapis.com/downloads.webmproject.org/av1/exoplayer/bbb-av1-480p.mp4"
            )
        )
    }
}