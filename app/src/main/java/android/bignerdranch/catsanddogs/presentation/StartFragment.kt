package android.bignerdranch.catsanddogs.presentation

import android.bignerdranch.catsanddogs.databinding.FragmentStartBinding
import android.bignerdranch.catsanddogs.presentation.adapters.AnimalAdapter
import android.bignerdranch.catsanddogs.presentation.viewmodel.AnimalViewModel
import android.bignerdranch.catsanddogs.presentation.viewmodel.DogsStateVM
import android.bignerdranch.catsanddogs.presentation.viewmodel.ViewModelFactory
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class StartFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: FragmentStartBinding
    private lateinit var animalViewModel: AnimalViewModel
    private var animalAdapter: AnimalAdapter? = null

    private val component by lazy {
        (requireActivity().application as AnimalApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animalViewModel = ViewModelProvider(this, viewModelFactory)[AnimalViewModel::class.java]
        animalViewModel.getDogs()
        observeData()
    }

    private fun observeData() {
        animalViewModel.getDogsResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DogsStateVM.GotDogs -> {
                    setupRecycler(result.list)
                }
                is DogsStateVM.MoreDogs -> {
                    result.more?.let {
                        animalAdapter?.addNewItems(it)
                    }
                }
                else -> {
                    DogsStateVM.MoreDogsError
                }
            }
        }
    }

    private fun setupRecycler(results: List<String>?) {
        animalAdapter = AnimalAdapter(dogsList = results ?: emptyList())
        binding.recyclerViewAnimals.adapter = animalAdapter

        val linearlayoutManager = LinearLayoutManager(activity?.baseContext)
        binding.recyclerViewAnimals.layoutManager = linearlayoutManager
        setMovieRecyclerListener(linearlayoutManager)
    }

    private fun setMovieRecyclerListener(linearlayoutManager: LinearLayoutManager) {
        binding.recyclerViewAnimals.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearlayoutManager.childCount
                val totalItemCount = linearlayoutManager.itemCount
                val pastVisibleItemCount = linearlayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItemCount >= totalItemCount
                    && pastVisibleItemCount >= 0
                ) {
                    animalViewModel.loadMore()
                }
            }
        })
    }
}
    

 






