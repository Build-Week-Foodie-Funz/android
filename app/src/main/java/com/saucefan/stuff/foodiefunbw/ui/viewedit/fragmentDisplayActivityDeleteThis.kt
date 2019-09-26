package com.saucefan.stuff.foodiefunbw.ui.viewedit

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.saucefan.stuff.foodiefunbw.*
import kotlinx.android.synthetic.main.activity_prefs.*

class fragmentDisplayActivityDeleteThis : AppCompatActivity(),ViewRestFrag.ViewRestFragmentListener, ViewReviewFrag.ViewReviewFragmentListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_display_delete_this)
        btn_rest.setOnClickListener {
            editRestFrag(R.id.fragment_container_view_tag,2,this)
        }
        btn_rev.setOnClickListener {
            editReviewFragment(R.id.fragment_container_view_tag,2,this)
        }
    }


}
