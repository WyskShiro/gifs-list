package will.shiro.giphy.gifs.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import will.shiro.giphy.R
import will.shiro.giphy.databinding.FragmentGifHomeBinding
import will.shiro.giphy.gifs.home.models.UIGifHome
import will.shiro.giphy.gifs.home.models.UIGifHomeModel

@AndroidEntryPoint
class GifHomeFragment : Fragment() {
    private val viewModel: GifHomeViewModel by viewModels()
    private val searchAdapter by lazy {
        GifHomeAdapter(::onSearchGifClick)
    }
    private var _binding: FragmentGifHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifHomeBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addOnBackPressed()
        setUpUI()
        setUpObservers()
        viewModel.setUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    customBackListener()
                }
            })
    }

    private fun setUpUI() {
        binding.searchEditText.addTextChangedListener {
            viewModel.handleSearchText(it?.toString() ?: "")
        }
        binding.gifsSearchListView.gifsSearchRecyclerView.apply {
            adapter = searchAdapter
            layoutManager = GridLayoutManager(
                requireContext(),
                3
            )
        }
        binding.backImageButton.setOnClickListener { customBackListener() }
    }

    private fun customBackListener() {
        if (binding.searchEditText.text.isNullOrEmpty()) {
            requireActivity().finish()
        } else {
            binding.backImageButton.isVisible = false
            binding.searchEditText.setText("")
            viewModel.getRandomGif()
        }
    }

    private fun setUpObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect(::handleState)
                }
                launch { viewModel.sideEffect.collect(::handleSideEffect) }
            }
        }
    }

    private fun handleState(state: UIGifHome.State) = binding.apply {
        val gif = state.gif
        val url = gif?.url
        val showRandom = state.searchText.isEmpty()
        if (showRandom) {
            Glide.with(requireContext()).asGif().load(url).fitCenter()
                .override(
                    Target.SIZE_ORIGINAL,
                    Target.SIZE_ORIGINAL
                ).into(
                    gifDetailsView.randomGifImageView
                )
            gifDetailsView.titleTextView.text = gif?.title
            gifDetailsView.linkTextView.text = gif?.link
            gifDetailsView.ratingTextView.text = gif?.rating
        } else {
            backImageButton.isVisible = state.searchText.isNotEmpty()
        }
        searchAdapter.submitList(state.searchGifs)
        gifsSearchListView.root.isVisible = !showRandom
        gifDetailsView.root.isVisible = showRandom
        labelTextView.setText(
            if (showRandom) {
                R.string.random_gif
            } else {
                R.string.search_gif
            }
        )
    }

    private fun handleSideEffect(sideEffect: UIGifHome.SideEffect) {
        when (sideEffect) {
            UIGifHome.SideEffect.Initial -> return
            is UIGifHome.SideEffect.Loading -> binding.loadingProgressBar.root.isVisible =
                sideEffect.isLoading
        }
    }

    private fun onSearchGifClick(uiGifHomeModel: UIGifHomeModel) {
        findNavController().navigate(GifHomeFragmentDirections.homeToDetails(uiGifHomeModel))
    }
}