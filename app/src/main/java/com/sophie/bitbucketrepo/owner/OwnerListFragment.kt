package com.sophie.bitbucketrepo.owner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sophie.bitbucketrepo.*
import com.sophie.bitbucketrepo.databinding.FragmentOwnerListBinding
import com.sophie.bitbucketrepo.json_shema.Root

class OwnerListFragment : Fragment() {

    private lateinit var ownerViewModelFactory: OwnerViewModelFactory
    private lateinit var ownerViewModel: OwnerViewModel

    private var data: Root? = null
    private lateinit var binding: FragmentOwnerListBinding
    private lateinit var adapter: OwnerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_owner_list, container, false)

        initializer()
        observers()
        clickListener()

        return binding.root
    }

    private fun clickListener() {
        binding.nextButton.setOnClickListener(View.OnClickListener {
            if (data != null) {
                ownerViewModel.nextOwnerList(data?.next!!)
            }

        })
    }

    private fun initializer() {
        val repository = OwnerRepository(getNetworkService())

        ownerViewModelFactory = OwnerViewModelFactory(repository)
        ownerViewModel = ViewModelProvider(this, ownerViewModelFactory).get(OwnerViewModel::class.java)

        ownerViewModel.refreshOwner()

        adapter = OwnerAdapter(this.requireContext())
        binding.ownerList.adapter = adapter
    }

    private fun observers() {
        ownerViewModel.value.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it.values!!
                data = it
            }
        })

        ownerViewModel.spinner.observe(viewLifecycleOwner, Observer {
            binding.spinner.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

}