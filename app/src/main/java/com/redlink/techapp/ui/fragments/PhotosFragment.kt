package com.redlink.techapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.redlink.techapp.R
import com.redlink.techapp.api.RepoMain
import com.redlink.techapp.api.Request
import com.redlink.techapp.domain.Resource
import com.redlink.techapp.domain.model.Album
import com.redlink.techapp.ui.adapters.PhotoAdapter
import com.redlink.techapp.ui.vm.Factory
import com.redlink.techapp.ui.vm.Main
import kotlinx.android.synthetic.main.fragment_photos.*

class PhotosFragment : Fragment() {

    private lateinit var album: Album

    private val viewModel by viewModels<Main>{
        Factory(RepoMain(Resource()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            album = it.getParcelable("album")!!
            viewModel.setAlbumId(album.id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleView()
        initObservers()
    }

    private fun initRecycleView(){
        recycle_view_photos.layoutManager = LinearLayoutManager(requireContext())
        recycle_view_photos.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )
    }

    private fun initObservers(){
        viewModel.fetchPhotoList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Request.Loading -> {
                    progress_bar_photo.visibility = View.VISIBLE
                }
                is Request.Success -> {
                    progress_bar_photo.visibility = View.GONE
                    recycle_view_photos.adapter =
                        PhotoAdapter(
                            requireContext(),
                            result.data
                        )
                }
                is Request.Failure -> {
                    progress_bar_photo.visibility = View.GONE
                    Toast.makeText(requireContext(),"Error al traer datos: ${result.exception}",
                        Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}