package ru.netology.nmedia

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb"
        )

        //обрабочик события при клике на root
        binding.root.setOnClickListener{
            System.out.println("Click root") //выводим в консоль текст
        }

        //обработчик событий при клике на avatar
        binding.avatar.setOnClickListener{
            System.out.println("Click avatar") //выводим в консоль текст
        }

        binding.content.text = post.content
        binding.published.text = post.published
        binding.author.text = post.author
        binding.shareCount.text = post.formatNumber(post.share)
        binding.viewCount.text = post.formatNumber(post.view)
        updateLike(binding, post)

        //событие при клике на лайк
        binding.like.setOnClickListener {
            post.likedByMe = !post.likedByMe
            if (post.likedByMe) post.liked++ else post.liked--
            binding.likesCount.text = post.formatNumber(post.liked)
            updateLike(binding, post)
        }

        //событие при клике на share
        binding.share.setOnClickListener{
            post.share++
            binding.shareCount.text = post.formatNumber(post.share)
        }
    }

    //обновление изображения сердечка у like
    private fun updateLike(binding: ActivityMainBinding, post: Post) {
        binding.like.setImageResource(
            if (post.likedByMe) {
                R.drawable.ic_favorite_24
            } else {
                R.drawable.ic_favorite_border_24
            }
        )
    }
}