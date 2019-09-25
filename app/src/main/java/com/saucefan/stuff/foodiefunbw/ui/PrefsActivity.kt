package com.saucefan.stuff.foodiefunbw.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.saucefan.stuff.foodiefunbw.R
import com.saucefan.stuff.foodiefunbw.ui.viewedit.ViewRestFrag
import kotlinx.android.synthetic.main.activity_prefs.*

class PrefsActivity : AppCompatActivity(), ViewRestFrag.ViewRestFragmentListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prefs)

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
