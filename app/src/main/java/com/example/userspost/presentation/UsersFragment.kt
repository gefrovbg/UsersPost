package com.example.userspost.presentation

//import com.example.userspost.factory.UserListViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userspost.R
import com.example.userspost.databinding.FragmentUsersBinding
import com.example.userspost.presentation.adapters.UsersListAdapter
import com.example.userspost.viewmodel.UsersListViewModel
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.fragment_users) {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
//    private lateinit var viewModel: UsersListViewModel
    private val viewModel: UsersListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        viewModel = ViewModelProvider(this, UserListViewModelFactory(requireContext(), this))[UsersListViewModel::class.java]

        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postponeEnterTransition()
        val parentView = view.parent as ViewGroup
        parentView.viewTreeObserver
            .addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    parentView.viewTreeObserver.removeOnPreDrawListener(this)
                    startPostponedEnterTransition()
                    return true
                }
            })
        super.onViewCreated(view, savedInstanceState)
        val adapter = UsersListAdapter{ user, textView, card, email->
//            val bundle = Bundle()
//            bundle.putString("kek", "${user.name}")
//            val action = UsersFragmentDirections.actionUsersFragmentToPostsFragment("keka")
            val extras = FragmentNavigatorExtras(
                textView to "name",
                card to "card",
                email to "email"
            )
            textView.findNavController().navigate(
                UsersFragmentDirections.actionUsersFragmentToPostsFragment(user.name, user.email, user),
                extras
            )
        }
        val recyclerUsersList = binding.usersListRecycler
//        recyclerUsersList.doOnPreDraw { startPostponedEnterTransition() }
        recyclerUsersList.adapter = adapter
        recyclerUsersList.layoutManager = LinearLayoutManager(requireContext())
        viewModel.usersList.observe(viewLifecycleOwner) {
            adapter.setUsers(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onChatListItemClick(view: ImageView, user: UserModel) {
//
//        val extras = FragmentNavigatorExtras(view to "image_big")
//        view.findNavController().navigate(
//            R.id.action_usersListFragment_to_usersPostsFragment,
//            null,
//            null,
//            extras
//        )
//
//    }

}