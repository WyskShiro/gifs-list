package will.shiro.giphy.gifs.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import will.shiro.giphy.databinding.ItemListGifHomeBinding
import will.shiro.giphy.gifs.home.models.UIGifHomeModel

class GifHomeAdapter(
    private val onGifClick: (UIGifHomeModel) -> Unit
) :
    ListAdapter<UIGifHomeModel, GifHomeAdapter.GifHomeViewHolder>(GifHomeDiffUtil()) {

    inner class GifHomeViewHolder(
        private val binding: ItemListGifHomeBinding
    ) : ViewHolder(binding.root) {

        fun bind(gif: UIGifHomeModel) {
            Glide.with(binding.root.context).asGif().load(gif.url).fitCenter()
                .override(
                    Target.SIZE_ORIGINAL,
                    Target.SIZE_ORIGINAL
                ).into(
                    binding.randomGifImageView
                )
            binding.root.setOnClickListener {
                onGifClick(gif)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifHomeViewHolder {
        return GifHomeViewHolder(
            ItemListGifHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GifHomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class GifHomeDiffUtil : DiffUtil.ItemCallback<UIGifHomeModel>() {
    override fun areItemsTheSame(oldItem: UIGifHomeModel, newItem: UIGifHomeModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UIGifHomeModel, newItem: UIGifHomeModel): Boolean {
        return oldItem.url == newItem.url
    }
}