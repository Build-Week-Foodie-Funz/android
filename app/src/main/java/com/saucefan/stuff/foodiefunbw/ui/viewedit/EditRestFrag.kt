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
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.R
import com.saucefan.stuff.foodiefunbw.User
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel
import kotlinx.android.synthetic.main.fragment_edit_rest.*
import kotlinx.android.synthetic.main.fragment_view_rest.*
import kotlinx.android.synthetic.main.fragment_view_rest.ev_rating
import kotlinx.android.synthetic.main.fragment_view_rest.ev_rest_hours
import kotlinx.android.synthetic.main.fragment_view_rest.ev_rest_location
import kotlinx.android.synthetic.main.fragment_view_rest.ev_rest_name
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val ARG_ResaurantID = "param1"

/**
edit review fragment
should take an id,
call any sanitation needed on the data,
and then either create or updateReview

 ID should be passed as 0 if is new object
 */
class EditRestFrag : Fragment() {
    private var chosenRestaurantID: Int? = null
    private var chosenRestaurantObj: FoodieRestaurant? =null
    private var listener: EditRestFragmentListener? = null
    private lateinit var viewModel: FoodieEntryViewModel
    private var isNewObject:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodieEntryViewModel::class.java)

        arguments?.let {
            chosenRestaurantID = it.getInt(ARG_ResaurantID)
        }
        if (chosenRestaurantID != null && chosenRestaurantID != -1) {
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
                chosenRestaurantObj = viewModel.getRestByID(chosenRestaurantID as Int)
            }
            Timber.i("rest obj set as ${chosenRestaurantObj.toString()}")
        }
        else if (chosenRestaurantID==0) {
            isNewObject=true
        }
            else {

            Timber.i("chosenRestaurantID = $chosenRestaurantID")
            Toast.makeText(activity,"no such restaurant found -- chosenRestaurantID = $chosenRestaurantID", Toast.LENGTH_LONG).show()
            activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment", Toast.LENGTH_LONG).show()

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_rest, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //if we are making a new restaurant, make a an object to eventually be
        //also change the text of the the buttons or whatever other housekeeping is needed
        if (isNewObject) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM--yyyy")
            val formatted = current.format(formatter)
            var finalObj =FoodieRestaurant()
            finalObj.restRating ="5 of 5"
            finalObj.restHours
            finalObj.restLocation
            finalObj.restName
            finalObj.restId
            finalObj.recentVisit=formatted
            finalObj.user= User("id","location","username","--","email",null)

            btn_submit.text="Create"
            btn_delete.text="Cancel"

            ev_rating.text="enter rating out of 5"
            ev_rest_hours.text = "enter hours of operation"
            ev_rest_location.text="enter address or location"
            ev_rest_name.text="enter restaurant name"

            //finally, set onclick behavior of buttons

            btn_submit.setOnClickListener {
                Timber.i("attempting to insert ${finalObj.toString()}")

                //insert the restaurant using viewModel
                viewModel.insertRestaurant(finalObj)


                //pop a toast to let user know rest has been created, hopefully livedata should handle the new rest in such a way as to make it immediately obvious to the user
                Toast.makeText(view.context,"${finalObj.restName} created successfully! add a review!", Toast.LENGTH_SHORT).show()

                //for the sake of symetry, we'll set isNewObject to false
                !isNewObject
                //trigger back press to close fragment
                activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment", Toast.LENGTH_LONG).show()

            }

            btn_delete.setOnClickListener {
                // we shouldn't have to do much of anything besides simply ditch the fragment
                //pop a toast to let use know no rest was created
                 Toast.makeText(view.context,"Restaurant not created", Toast.LENGTH_SHORT).show()

                //for the sake of symetry, we'll set isNewObject to false
                !isNewObject
                //trigger back press to close fragment
                activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment", Toast.LENGTH_LONG).show()

            }


        }
        //if we successfully have a restaurant and it is not a new object, fill in the views
        else if (chosenRestaurantObj != null && !isNewObject) {
            //set the obj as a local val to avoid a whole lot of useless null checking, well, redundant null checking
            // as well as to allow us to make a meaningful diff of the old object and the new object changed by the user
            var finalObj = chosenRestaurantObj as FoodieRestaurant
            //if the restaurant has photos in its array, set the first one and then we will likely have to make ImageView Dynamically, and glide them in for the rest
            if (!finalObj.restPhotos.isNullOrEmpty()) {
                //glide the first image in here, should be little more than code like:
                //val imgString = finalObj.restPhotos!![0]
                /*    Glide.with(this)
                            .load(imgString)
                            .into(imgViewHeader)*/
            }
            //set buttons and make editviews the previous objects values
            btn_submit.text="Update Restaurant"
            btn_delete.text="Delete Restaurant Permanently"

            ev_rating.text=finalObj.restRating
            ev_rest_hours.text = finalObj.restHours
            ev_rest_location.text=finalObj.restLocation
            ev_rest_name.text=finalObj.restName

            //finally set behavior of buttons
            btn_submit.setOnClickListener {
                Timber.i("attempting to insert ${finalObj.toString()}")

                //insert the restaurant using viewModel
                viewModel.insertRestaurant(finalObj)


                //pop a toast to let user know rest has been created, hopefully livedata should handle the new rest in such a way as to make it immediately obvious to the user
                Toast.makeText(view.context,"${finalObj.restName} created successfully! add a review!", Toast.LENGTH_SHORT).show()

                //for the sake of symetry, we'll set isNewObject to false
                !isNewObject
                //trigger back press to close fragment
                activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment", Toast.LENGTH_LONG).show()

            }

            btn_delete.setOnClickListener {
                // we shouldn't have to do much of anything besides simply ditch the fragment
                //pop a toast to let use know no rest was created
                Toast.makeText(view.context,"Restaurant not created", Toast.LENGTH_SHORT).show()

                //for the sake of symetry, we'll set isNewObject to false
                !isNewObject
                //trigger back press to close fragment
                activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment", Toast.LENGTH_LONG).show()

            }

        }
        //else let us know and exit the fragment
        else {
            Timber.i("failed at onViewCreated -- obj null and not a new object -- how did we get here?-- chosenRestaurantID = $chosenRestaurantID")
            //this code effectively presses the back button hence closing the fragment, there is some potential issues I could imagine here, esspecially with search view,
            //but it should work in general
            activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment", Toast.LENGTH_LONG).show()
        }

        private fun sanitizeRestaurantData(foodieRestaurant: FoodieRestaurant):FoodieRestaurant {
            //take in some rest data,
            //analyze it for issues
            //spit it back out
            return sanitizeRestaurantData()

        }




    }

    fun onEditClick(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EditRestFragmentListener) {
            listener = context
        } else {

            //  throw RuntimeException(context.toString() + " must implement EditRestFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface EditRestFragmentListener {
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
                EditRestFrag().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ResaurantID, restID)

                    }
                }
    }
}
