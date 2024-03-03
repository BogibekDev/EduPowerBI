package uz.ubtuit.powerbi.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.ubtuit.powerbi.R
import uz.ubtuit.powerbi.adapter.AppsAdapter
import uz.ubtuit.powerbi.model.App

class MoreMicrosoftFragment : Fragment(R.layout.fragment_more_microsoft) {

    private lateinit var list: ArrayList<App>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        loadApps()
        val ivBack = view.findViewById<ImageView>(R.id.ivBack)
        val ivMicrosBanner = view.findViewById<ImageView>(R.id.ivMicroBanner)
        val icMicro = view.findViewById<ImageView>(R.id.icMicrosoft)
        val rvApps = view.findViewById<RecyclerView>(R.id.rvApps)
        val llAllApps=view.findViewById<LinearLayout>(R.id.llAllApps)
        val adapter = AppsAdapter(list)
        rvApps.adapter = adapter
        adapter.itemClick = { position ->
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${list[position].url}")))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${list[position].url}")))
            }

        }

        llAllApps.setOnClickListener{
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://dev?id=6720847872553662727")))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=6720847872553662727")))
            }
        }

        Glide.with(ivMicrosBanner)
            .load("https://play-lh.googleusercontent.com/1QKAMf_OwuL8ftJBmPAScxrpYvpjLllyPF22Xu6pJ84z465MUF0YvS4p0f4rLTlRyDc=w3840-h2160")
            .placeholder(R.drawable.img)
            .into(ivMicrosBanner)
        Glide.with(icMicro)
            .load("https://play-lh.googleusercontent.com/kHRf85euDvW-Kg7ThXK2vv-J-Yye9uxoo6GQvUcAwudNRz1sQvXubAl_m2bu6KJofA=s94")
            .into(icMicro)
        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun loadApps() {
        list = ArrayList()
        list.add(
            App(
                "Microsoft Word",
                "https://play-lh.googleusercontent.com/9kABykeGovHPy-dN19lRxxnCp8IZK3Pkl8qLFNxrEe-hhKVZeiyhTBEIRUt6t-vhxQ=w240-h480",
                "com.microsoft.office.word"
            )
        )
        list.add(
            App(
                "Microsoft Excel",
                "https://play-lh.googleusercontent.com/37EzETO6gZyKmCg2kBIFX1e9gkubxZrVa5fHJ6yOaa7VvEShHjKv2RdtwnZt9Sk258s=w240-h480",
                "com.microsoft.office.excel"
            )
        )

        list.add(
            App(
                "Microsoft PowerPoint",
                "https://play-lh.googleusercontent.com/6pTX4OILXTxazqad66oiVfG4x2KpYn4kIPgdzOe173tT0oHr2ThwpBhMyzzzxWq_r6M=w240-h480",
                "com.microsoft.office.powerpoint"
            )
        )
        list.add(
            App(
                "Microsoft Copilot",
                "https://play-lh.googleusercontent.com/nP2a6TMB-96l-qVzKuJ1I9rXsFATyw7pCM-ZVvnUuZqA3Q2kuPUitqOxG9Q8moSOijlz=w240-h480",
                "com.microsoft.copilot"
            )
        )
        list.add(
            App(
                "Microsoft 365 (Office)",
                "https://play-lh.googleusercontent.com/94uR6O49JmqiMMMsrfoDlWvWkqLf6rfE25zOH2BWnuzozzlfY1qnKaNzuQbUcupcpX9L=w240-h480",
                "com.microsoft.office.officehubrow"
            )
        )
        list.add(
            App(
                "Microsoft Outlook",
                "https://play-lh.googleusercontent.com/Zk9elS0eGXDr0L4W6-Ey7YwHbRNjkyezHC8iCc8rWp64lNIjlByS8TDF9qDSZbiEWY4=s64",
                "com.microsoft.office.outlook"
            )
        )
    }

}