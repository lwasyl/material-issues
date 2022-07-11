package com.example.navgraphapplication

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

private var COUNTER = 1

class SecondFragment : Fragment() {

    private val navController: NavController by lazy {
        findNavController()
            .apply {
                addOnDestinationChangedListener { _, destination, _ ->
                    context?.let {
                        Toast.makeText(it, "onDestinationChanged: $destination", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.label).text = getString(R.string.second_fragment_label, COUNTER++)

        view.findViewById<Button>(R.id.button2).setOnClickListener {
            navController.navigate(
                Uri.parse("https://www.example.com/second/"),
                NavOptions.Builder()
                    .setLaunchSingleTop(true)
                    .build(),
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.w("SecondFragment", "onDestroyView")
    }
}
