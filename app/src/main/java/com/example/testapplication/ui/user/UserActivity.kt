package com.example.testapplication.ui.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.testapplication.R
import com.example.testapplication.api.PlaceHolderUser
import com.example.testapplication.databinding.ActivityUserBinding
import com.example.testapplication.main.ViewModelProviderFactory
import com.example.testapplication.ui.post.PostActivity
import com.google.android.material.card.MaterialCardView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class UserActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: ActivityUserBinding
    private lateinit var adapter: UserListAdapter
    private val REPLY = "com.example.testapplication.USER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user)

        adapter = UserListAdapter()
        binding.contentUser.rvUser.adapter = adapter

        userViewModel = injectViewModel(viewModelFactory)
        userViewModel.getUsers()
        userViewModel.users.observe(this, observer)

    }

    private val observer: Observer<List<PlaceHolderUser>> =
            Observer { users -> adapter.setUsers(users) }

    private inline fun <reified T : ViewModel> injectViewModel(factory: ViewModelProvider.Factory): T {
        return ViewModelProviders.of(this, factory)[T::class.java]
    }

    fun openPostsOfUser(v: View){
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra(REPLY, (v as MaterialCardView).contentDescription)
        startActivity(intent)
    }
}