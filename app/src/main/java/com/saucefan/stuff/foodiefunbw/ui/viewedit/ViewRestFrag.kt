package com.saucefan.stuff.foodiefunbw.ui.viewedit

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant

import com.saucefan.stuff.foodiefunbw.R
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel
import timber.log.Timber


private const val ARG_ResaurantID = "param1"

/**
 a fragment to display an individual resaurant and give the user the
 opportunity to choose to nagigate to editing that restaurant

 */
class ViewRestFrag : Fragment() {
    private var chosenResaurantID: Int? = null
    private var chosenRestaurantObj:FoodieRestaurant? =null
    private var listener: ViewRestFragmentListener? = null
    private lateinit var viewModel: FoodieEntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(FoodieEntryViewModel::class.java)

        arguments?.let {
            chosenResaurantID = it.getInt(ARG_ResaurantID)
        }
        if (chosenResaurantID != null && chosenResaurantID != -1){
                viewModel.
        }
        else {
            Timber.i("chosenResaurantID = $chosenResaurantID")
            Toast.makeText(activity,"no such restaurant found -- chosenResaurantID = $chosenResaurantID",Toast.LENGTH_LONG).show()
            activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment",Toast.LENGTH_LONG).show()

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_rest, container, false)




    }

       fun onEditClick(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ViewRestFragmentListener) {
            listener = context
        } else {
            //we'll leave this here for now
            throw RuntimeException(context.toString() + " must implement ViewRestFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface ViewRestFragmentListener {
    // change this once it becomes more clean what, if anything needs to be communicated
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param restID ID of the resaurant the user needs to view
         * @return A new instance of fragment ViewRestFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(restID: Int) =
                ViewRestFrag().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ResaurantID, restID)

                    }
                }
    }
}
