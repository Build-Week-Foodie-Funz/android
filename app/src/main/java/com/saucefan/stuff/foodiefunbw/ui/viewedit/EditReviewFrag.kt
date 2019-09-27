package com.saucefan.stuff.foodiefunbw.ui.viewedit

import android.app.AlertDialog
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
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry

import com.saucefan.stuff.foodiefunbw.R
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel
import kotlinx.android.synthetic.main.fragment_edit_rest.*
import kotlinx.android.synthetic.main.fragment_edit_review.*
import kotlinx.android.synthetic.main.fragment_edit_review.btn_delete
import kotlinx.android.synthetic.main.fragment_edit_review.btn_submit
import kotlinx.android.synthetic.main.fragment_view_review.ev_rating
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val ARG_ReviewID = "param1"
private const val ARG_RestName = "param2"
/**
edit review fragment
 should take an id,
 call any sanitation needed on the data,
 and then either create or updateReview
ID should be passed as 0 if is new object
 */
class EditReviewFrag : Fragment() {
    private var chosenReviewID: Int? = null
    private var chosenReviewObj: FoodieEntry? =null
    private var listener: EditReviewFragmentListener? = null
    private lateinit var viewModel: FoodieEntryViewModel
    private var newReviewRestName: String? = null
    private var isNewObject:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        viewModel = ViewModelProvider(this).get(FoodieEntryViewModel::class.java)

        arguments?.let {
            chosenReviewID = it.getInt(ARG_ReviewID)
            newReviewRestName = it.getString(ARG_RestName)
        }
        if (chosenReviewID==0) {
            isNewObject = true
        }
        else if (chosenReviewID != null && chosenReviewID as Int >= -1){
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
            Timber.i("chosenRevoewID = $chosenReviewID")
            Toast.makeText(activity,"no such review found -- chosenResaurantID = $chosenReviewID", Toast.LENGTH_LONG).show()
            activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment", Toast.LENGTH_LONG).show()

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_review, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //if we are making a new review, make a an object to eventually be
        //also change the text of the the buttons or whatever other housekeeping is needed
        if (isNewObject) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM--yyyy")
            val formatted = current.format(formatter)
            var finalObj =FoodieEntry()
            finalObj.restName =newReviewRestName
            Timber.i("new review name set to $newReviewRestName")
            finalObj.cuisineType ="left blank"
            finalObj.itemPrice = 0.00f
            finalObj.menuItemName ="menu item name left blank"
            finalObj.rating ="5/5"
            finalObj.shortReview = "review body left blank"
            btn_submit.text="Create"
            btn_delete.text="Cancel"

            ev_rating.text="enter rating out of 5"
            ev_cuisine_type.hint="enter type of cuisine"
            ev_item_name.hint="enter name of item ordered"
            ev_item_price.hint="enter item price"
            ev_review.hint="enter an short review"

            //finally, set onclick behavior of buttons

            btn_submit.setOnClickListener {
                Timber.i("attempting to insert $finalObj")

                //insert the restaurant using viewModel
                viewModel.insertReview(finalObj)


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
                Toast.makeText(view.context,"Review not created", Toast.LENGTH_SHORT).show()

                //for the sake of symetry, we'll set isNewObject to false
                !isNewObject
                //trigger back press to close fragment
                activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment", Toast.LENGTH_LONG).show()

            }


        }


        //if we successfully have a restaurant, fill in the views
        else if (chosenReviewObj != null && !isNewObject) {
            //set the obj as a local val to avoid a whole lot of useless null checking, well, redundant null checking
            // as well as to allow us to make a meaningful diff of the old object and the new object changed by the user
            val finalObj = chosenReviewObj as FoodieEntry
            //if the restaurant has photos in its array, set the first one and then we will likely have to make ImageView Dynamically, and glide them in for the rest

            //glide the first image in here, should be little more than code like:
            //val imgString = finalObj.restPhotos
            /*    Glide.with(this)
                        .load(imgString)
                        .into(imgViewHeader)*/
            btn_submit.text="Update Review"
            btn_delete.text="Delete Review Permanently"

            ev_cuisine_type.hint=finalObj.cuisineType
            ev_item_name.hint = finalObj.menuItemName
            ev_item_price.hint=finalObj.itemPrice.toString()
            ev_rating.hint=finalObj.rating
            ev_review.hint=finalObj.shortReview
            //finally set behavior of buttons
            btn_submit.setOnClickListener {
                // we make a new obj where by we take in data from the ev forms and alter the preexisting finalObj
                var objtoupdate = sanitizeReviewData(finalObj)

                // great,

                Timber.i("attempting to update $finalObj")

                //update the restaurant using viewModel
                viewModel.updateReview(objtoupdate)

                //pop a toast to let user know review has been updated, hopefully livedata should handle the updated rest in such a way as to make it immediately obvious to the user
                Toast.makeText(view.context,"Your review for ${finalObj.restName} updated successfully!", Toast.LENGTH_SHORT).show()

                //trigger back press to close fragment
                activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment", Toast.LENGTH_LONG).show()

            }

            btn_delete.setOnClickListener {
                //on delete press, we'd like to confirm with the user
                val builder = AlertDialog.Builder(view.context)
                builder.setTitle("Delete review permanently?")
                builder.setMessage("You are about to delete  your review of ${finalObj.menuItemName}. Do you really want to proceed?")
                builder.setCancelable(false)
                builder.setPositiveButton("YES"){dialog, which ->
                    //they've confirmed the delete
                    Toast.makeText(view.context,"You've choosen to delete ${finalObj.menuItemName}",Toast.LENGTH_SHORT).show()
                    viewModel.deleteReview(finalObj)
                    //and we leave the fragment
                    activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment", Toast.LENGTH_LONG).show()

                }

                builder.setNegativeButton("cancel"){dialog,which ->
                    //cancel delete code here
                    Toast.makeText(view.context,"Delete Canceled",Toast.LENGTH_SHORT).show()
                    activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment", Toast.LENGTH_LONG).show()
                }
                builder.show()
            }


        }
        //else let us know and exit the fragment
        else {
            Timber.i("failed at onViewCreated -- obj null -- chosenResaurantID = $chosenReviewID")
            activity?.onBackPressed() ?:  Toast.makeText(activity,"no activity found to trigger on back pressed and close fragment", Toast.LENGTH_LONG).show()

        }


    }
    fun sanitizeReviewData(foodieEntry: FoodieEntry):FoodieEntry {
        //take in some rest data,
        //analyze it for issues
        //spit it back out
        if (ev_rating.text.toString() != "") {
            foodieEntry.rating = ev_rating.text.toString()
        }
        if (ev_cuisine_type.text.toString() != "") {
            foodieEntry.cuisineType = ev_cuisine_type.text.toString()
        }
        if (ev_review.text.toString() != "") {
            foodieEntry.shortReview = ev_review.text.toString()
        }
        if (ev_item_price.text.toString() != "") {
            foodieEntry.itemPrice = ev_item_price.text.toString().toFloat()
        }
        if (ev_item_name.text.toString() != "") {
            foodieEntry.menuItemName = ev_item_name.text.toString()
        }

        return foodieEntry
    }

    fun onEditClick(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EditReviewFragmentListener) {
            listener = context
        } else {

            //  throw RuntimeException(context.toString() + " must implement EditRestFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface EditReviewFragmentListener {
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
        fun newInstance(reviewID: Int, restName:String?) =
                EditReviewFrag().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ReviewID, reviewID)
                        putString(ARG_RestName, restName)
                    }
                }
    }
}
