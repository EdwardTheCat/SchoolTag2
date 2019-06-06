package com.ynov.schooltag.tag

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ynov.schooltag.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tag_fragment.*
import org.jetbrains.anko.toast

class TagFragment : Fragment() {

    companion object {

        fun newInstance(): TagFragment {
            return TagFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tag_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tag_btn.setOnClickListener {
            Toast.makeText(activity,"Send nfc",Toast.LENGTH_LONG).show()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LinearLayoutManager(context)
    }
}