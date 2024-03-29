package com.saucefan.stuff.foodiefunbw.ui.viewedit

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.R
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel
import kotlinx.android.synthetic.main.fragment_view_rest.*
import kotlinx.coroutines.runBlocking
import timber.log.Timber


private const val ARG_ResaurantID = "param1"

/**
 a fragment to display an individual restaurant and give the user the
 opportunity to choose to navigate to editing that restaurant

 */
class ViewRestFrag : Fragment() {
    private var chosenResaurantID: Int? = null
    private var chosenRestaurantObj:FoodieRestaurant? =null
    private var listener: ViewRestFragmentListener? = null
    private lateinit var viewModel: FoodieEntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
         viewModel = ViewModelProvider(this).get(FoodieEntryViewModel::class.java)

        arguments?.let {
            chosenResaurantID = it.getInt(ARG_ResaurantID)
        }
        if (chosenResaurantID != null && chosenResaurantID as Int >= -1) {
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
            chosenRestaurantObj = viewModel.getRestByID(chosenResaurantID as Int)
        }
            Timber.i("rest obj set as ${chosenRestaurantObj.toString()}")
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //if we successfully have a restaurant, fill in the views
        if (chosenRestaurantObj != null) {
            //set the obj as a local val to avoid a whole lot of useless null checking, well, redundant null checking
            val finalObj = chosenRestaurantObj as FoodieRestaurant
            //if the restaurant has photos in its array, set the first one and then we will likely have to make ImageView Dynamically, and glide them in for the rest
            if (!finalObj.restPhotos.isNullOrEmpty()) {
                //glide the first image in here, should be little more than code like:
                //val imgString = finalObj.restPhotos!![0]
            /*    Glide.with(this)
                        .load(imgString)
                        .into(imgViewHeader)*/

            }
            ev_rating.text=finalObj.restRating
            ev_rest_hours.text = finalObj.restHours
            ev_rest_location.text=finalObj.restLocation
            ev_rest_name.text=finalObj.restName

            }
        //else let us know and exit the fragment
        else {
            Timber.i("failed at onViewCreated -- obj null -- chosenResaurantID = $chosenResaurantID")
            activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment",Toast.LENGTH_LONG).show()

        }


    }

    fun onEditClick(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ViewRestFragmentListener) {
            listener = context
        } else {

          //  throw RuntimeException(context.toString() + " must implement EditRestFragmentListener")
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

        @JvmStatic
        fun newInstance(restID: Int) =
                ViewRestFrag().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ResaurantID, restID)

                    }
                }
    }
}
