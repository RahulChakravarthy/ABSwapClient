package tech.r7chakra.abswapclient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_post.*
import tech.r7chakra.abswapclient.activities.MainApplication
import tech.r7chakra.abswapclient.util.lazyAndroid
import tech.r7chakra.abswapclient.viewmodels.MainActivityViewModel
import tech.r7chakra.abswapclient.viewmodels.MainViewModelFactory
import javax.inject.Inject


class PostFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    val mainActivityViewModel : MainActivityViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(tech.r7chakra.abswapclient.R.layout.fragment_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MainApplication.mainApplicationComponent.inject(this)
        onUploadImage1Clicked()
        onUploadImage2Clicked()
        onUploadImagesClicked()
    }

    private fun onUploadImage1Clicked() {
        postImage1.setOnClickListener {
            mainActivityViewModel.onUploadImage1Clicked()
        }
        mainActivityViewModel.image1UriLiveData.observe(this, Observer {
            Picasso.with(requireActivity())
                .load(it.toString())
                .centerCrop()
                .fit()
                .into(postImage1)

            //Set post image 1 text to gone
            postImage1Text.visibility = View.GONE
        })
    }

    private fun onUploadImage2Clicked() {
        postImage2.setOnClickListener {
            mainActivityViewModel.onUploadImage2Clicked()
        }
        mainActivityViewModel.image2UriLiveData.observe(this, Observer {
            Picasso.with(requireContext())
                .load(it)
                .into(postImage2)
            postImage2Text.visibility = View.GONE
        })
    }

    private fun onUploadImagesClicked() {
        uploadImageButton.setOnClickListener {
            //Verify that there are 2 images to upload
            if (mainActivityViewModel.image1UriLiveData.value != null && mainActivityViewModel.image2UriLiveData.value != null) {
                mainActivityViewModel.uploadImages()
                //Show loading screen
                loadingLottie.visibility = View.VISIBLE
                loadingLottie.playAnimation()
            } else {
                Toast.makeText(requireContext(), "Please upload 2 photos", Toast.LENGTH_SHORT).show()
            }

        }
    }
}