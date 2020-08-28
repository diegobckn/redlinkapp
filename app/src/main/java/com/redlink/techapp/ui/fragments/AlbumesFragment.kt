package com.redlink.techapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.redlink.techapp.R
import com.redlink.techapp.api.RepoMain
import com.redlink.techapp.api.Request
import com.redlink.techapp.domain.Resource
import com.redlink.techapp.domain.model.Album
import com.redlink.techapp.ui.adapters.AlbumAdapter
import com.redlink.techapp.ui.vm.Factory
import com.redlink.techapp.ui.vm.Main
import kotlinx.android.synthetic.main.fragment_albumes.*

class AlbumesFragment : Fragment() , AlbumAdapter.OnAlbumClickListener{
    private val viewModel by viewModels<Main>{
        Factory(RepoMain(Resource()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleView()
        initObservers()
        initSearchView()
    }

    private fun initObservers(){
        viewModel.fetchAlbumList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Request.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is Request.Success -> {
                    progress_bar.visibility = View.GONE
                    recycle_view_albumes.adapter =
                        AlbumAdapter(
                            requireContext(),
                            result.data,
                            this
                        )
                }
                is Request.Failure -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(requireContext(),"Error al traer datos: ${result.exception}",
                        Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initSearchView(){
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.setTitle(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
    }

    private fun initRecycleView(){
        recycle_view_albumes.layoutManager = LinearLayoutManager(requireContext())
        recycle_view_albumes.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albumes, container, false)
    }

    override fun onAlbumClick(album: Album) {
        val bundle = Bundle()
        bundle.putParcelable("album",album)
        findNavController().navigate(R.id.action_albumesFragment_to_photosFragment,bundle)
    }
}