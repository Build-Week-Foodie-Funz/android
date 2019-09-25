package com.saucefan.stuff.foodiefunbw.ui.viewedit

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.saucefan.stuff.foodiefunbw.R
import kotlinx.android.synthetic.main.activity_prefs.*

class fragmentDisplayActivityDeleteThis : AppCompatActivity(),ViewRestFrag.ViewRestFragmentListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_display_delete_this)
        btn_rest.setOnClickListener {
            val testRestFrag = ViewRestFrag.newInstance(1)
            supportFragmentManager
                    .beginTransaction()
                    // 2
                    .replace(R.id.fragment_container_view_tag, testRestFrag, "restFragTestFrag")
                    // 3
                    .addToBackStack("1") //presumedly re'd name it the id or perhaps "rest$Id"
                    .commit()
        }

    }
}
