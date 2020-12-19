package com.gitalive.isat.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.gitalive.isat.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView2.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                setTitle("本次更新")
                setMessage(R.string.update_message)
                setCancelable(true)
                show()
            }
        }
    }
}
