//package will.shiro.giphy.gifs.details
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
//import androidx.navigation.fragment.navArgs
//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
//import dagger.hilt.android.AndroidEntryPoint
//import will.shiro.giphy.databinding.FragmentGifDetailsBinding
//
//@AndroidEntryPoint
//class GifDetailsFragment : Fragment() {
//    private var _binding: FragmentGifDetailsBinding? = null
//    private val binding get() = _binding!!
//    private val args: GifDetailsFragmentArgs by navArgs()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentGifDetailsBinding.inflate(
//            inflater,
//            container,
//            false
//        )
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        setUpUI()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    private fun setUpUI() = binding.apply {
//        val gif = args.gif
//        val url = gif.url
//        Glide.with(requireContext()).asGif().load(url).fitCenter()
//            .override(
//                SIZE_ORIGINAL,
//                SIZE_ORIGINAL
//            ).into(
//                gifDetailsView.randomGifImageView
//            )     // Override the size to match the original dimensions
//        gifTitleTextView.text = gif.title
//        gifDetailsView.titleTextView.text = gif.title
//        gifDetailsView.linkTextView.text = gif.link
//        gifDetailsView.ratingTextView.text = gif.rating
//
//        backImageButton.setOnClickListener {
//            findNavController().popBackStack()
//        }
//    }
//}