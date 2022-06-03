package com.example.userspost.presentation

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userspost.R
import com.example.userspost.databinding.FragmentPostsBinding
import com.example.userspost.presentation.adapters.PostsListAdapter
import com.example.userspost.viewmodel.PostsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostsFragment : Fragment(R.layout.fragment_posts) {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var postsListViewModelFactory: PostsListViewModel.PostsListViewModelFactory

    val args: PostsFragmentArgs by navArgs()

    private val viewModel: PostsListViewModel by viewModels{
        PostsListViewModel.provideFactory(
//            getAllPostsRepository, addAllPostsToLocalDB, getAllCachedPostsOfUserRepository,
            postsListViewModelFactory, this, arguments, args.currentUser)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation


        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = args.currentUser
        binding.name.text = user.name
        binding.email.text = user.email
        binding.phone.text = user.phone
        binding.username.text = user.username
        binding.website.text = user.website
        binding.address.text = "${user.address.zipcode};  ${user.address.city}, ${user.address.street}, ${user.address.suite}; Geo: ${user.address.geo.lat}, ${user.address.geo.lng}"
        binding.company.text = "${user.company.name}\n${user.company.catchPhrase}\n${user.company.bs}"

        val adapter = PostsListAdapter()
        val recyclerPostsList = binding.recyclerView
        recyclerPostsList.adapter = adapter
        recyclerPostsList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.postsList.observe(viewLifecycleOwner){
            adapter.setPosts(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}