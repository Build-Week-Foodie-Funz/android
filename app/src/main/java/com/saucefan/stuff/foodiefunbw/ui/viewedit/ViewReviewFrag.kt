package com.saucefan.stuff.foodiefunbw.ui.viewedit

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.R
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber


private const val ARG_ReviewID = "param1"

/**
a fragment to display an individual resaurant and give the user the
opportunity to choose to nagigate to editing that restaurant

 */
class ViewReviewFrag : Fragment() {
    private var chosenReviewID: Int? = null
    private var chosenReviewObj:FoodieEntry? =null
    private var listener: ViewRestFragmentListener? = null
    private lateinit var viewModel: FoodieEntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodieEntryViewModel::class.java)

        arguments?.let {
            chosenReviewID = it.getInt(ARG_ReviewID)
        }
        if (chosenReviewID != null && chosenReviewID != -1){
            // I believe run blocking is the correct choice here for the following reasons:
            //1. this should be a very simple query, with a single answer and no ambiguity
            //2. we can be pretty confident that the user will never be the victim of a "bad" query
            //as they should only ever arrive at this part of the program redirected from another object with a
            //reasonable ID -- this code will not run if the ID is null or invalid due to being in afor loop
            //3. user presumedly wants the details to this object and can not proceed in the app until they can be provided
            //
            // that all being said, I would love to find a better way to do this later if time allows.
            // and if nothing else we can pass the object itself to the fragment, I suppose we may be best served just doing that
            runBlocking {
            chosenReviewObj=   viewModel.getReviewsByID(chosenReviewID as Int)

            }
                Timber.i("rest obj set as ${chosenReviewObj.toString()}")

        }
        else {
            Timber.i("chosenResaurantID = $chosenReviewID")
            Toast.makeText(activity,"no such restaurant found -- chosenResaurantID = $chosenReviewID",Toast.LENGTH_LONG).show()
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
        fun newInstance(reviewID: Int) =
                ViewRestFrag().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ReviewID, reviewID)

                    }
                }
    }
}
