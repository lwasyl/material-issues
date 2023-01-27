package com.example.navgraphapplication

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

class FirstFragment : Fragment() {

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button1).setOnClickListener {
            navController.navigate(Uri.parse("https://www.example.com/second/"))
        }

        snackbarExample(view)
    }

    private fun snackbarExample(view: View) {
        showSnackbar(view.findViewById(R.id.container), view.findViewById(R.id.anchorButton))

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            view.findViewById<Button>(R.id.anchorButton).isVisible = true
        }
    }

    private fun showSnackbar(container: View, bottomAnchor: View) {
        Snackbar.make(container, "text", Snackbar.LENGTH_INDEFINITE)
            .apply {
                anchorView = bottomAnchor
                isAnchorViewLayoutListenerEnabled = true

                show()
            }
    }
}
