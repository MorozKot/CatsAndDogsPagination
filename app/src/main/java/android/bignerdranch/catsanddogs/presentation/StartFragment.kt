package android.bignerdranch.catsanddogs.presentation

import android.bignerdranch.catsanddogs.databinding.FragmentStartBinding
import android.bignerdranch.catsanddogs.presentation.adapters.AnimalAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private lateinit var animalAdapter: AnimalAdapter
    private lateinit var animalViewModel: AnimalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearlayoutManager = LinearLayoutManager(activity?.baseContext)
        binding.recyclerViewAnimals.layoutManager = linearlayoutManager
        setMovieRecyclerListener(linearlayoutManager)

        animalAdapter = AnimalAdapter()

        binding.recyclerViewAnimals.adapter = animalAdapter

        animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)

        animalViewModel.getCatModel()

        animalViewModel.getDogModel()

        animalViewModel.dogList.observe(viewLifecycleOwner) {
            animalAdapter.setDogList(it)
        }

        animalViewModel.catsList.observe(viewLifecycleOwner) {
            animalAdapter.setCatsList(it)
        }
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
                    animalViewModel.getCatModel()

                    animalViewModel.getDogModel()
                }
            }
        })
    }
}
    

 





