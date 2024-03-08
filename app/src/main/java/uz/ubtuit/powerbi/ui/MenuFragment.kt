package uz.ubtuit.powerbi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import uz.ubtuit.powerbi.R


class MenuFragment : Fragment(R.layout.fragment_menu){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        val ivMenu = view.findViewById<ImageView>(R.id.ivMenu)
        val drawerLayout = view.findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = view.findViewById<NavigationView>(R.id.navigationView)
        ivMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        navigationView.itemIconTintList = null
        //NavigationUI.setupWithNavController(navigationView, findNavController())
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.aboutProjectFragment -> {
                    findNavController().navigate(R.id.action_menuFragment_to_aboutProjectFragment)
                }

                R.id.moreMicrosoftFragment -> {
                    findNavController().navigate(R.id.action_menuFragment_to_moreMicrosoftFragment)
                }
            }
            return@setNavigationItemSelectedListener true

        }

        val bGraphics = view.findViewById<Button>(R.id.bGraphics)
        val bVideos = view.findViewById<Button>(R.id.bVideos)
        val bTests = view.findViewById<Button>(R.id.bTests)
        val bBooks = view.findViewById<Button>(R.id.bBooks)
        val bTasks = view.findViewById<Button>(R.id.bTasks)

        bGraphics.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_graphicsFragment)
        }
        bTests.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_testsFragment)
        }

        bVideos.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_videosFragment)
        }

        bBooks.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_booksFragment)
        }

        bTasks.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_tasksFragment)
        }
    }




}