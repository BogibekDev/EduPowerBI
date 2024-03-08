package uz.ubtuit.powerbi.ui


import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.OptIn
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import uz.ubtuit.powerbi.R

class VideoFragment : Fragment(R.layout.fragment_video) {
    private var player: ExoPlayer? = null
    private lateinit var videoPlayer: PlayerView
    private lateinit var tvDescription: TextView
    private var videoUrl = ""
    private var description = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        val ivBack = view.findViewById<ImageView>(R.id.ivBack)
        tvDescription = view.findViewById(R.id.tvDescription)

        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        videoPlayer = view.findViewById(R.id.playerView)
        videoUrl = arguments?.getString("videoUrl") ?: videoUrl
        description = arguments?.getString("description") ?: description
        tvDescription.text = description
        initPlayer()
    }

    @OptIn(UnstableApi::class)
    private fun initPlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        videoPlayer.player = player
        val mediaItem = MediaItem.Builder()
            .setUri(videoUrl)
            .setMimeType(MimeTypes.APPLICATION_MP4)
            .build()
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.play()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        releasePlayer()
    }


    private fun releasePlayer() {
        player?.apply {
            pause()
            release()
        }
        player = null
    }


}