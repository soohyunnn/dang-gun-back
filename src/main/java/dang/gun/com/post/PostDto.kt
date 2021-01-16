package dang.gun.com.post

import dang.gun.com.user.User
import lombok.NoArgsConstructor
import java.time.LocalDateTime

data class PostRequest(
        var id : Int,
        var title: String,
        var content: String,
        var price: Int,
        var userId: User
)



data class PostDto(
        var id: Int,
        var title: String?,
        var content: String?,
        var price: Int?,
        var userName: String?,
        var createAt: LocalDateTime?,
        var modifiedAt : LocalDateTime?,
) {
    constructor(post: Post) : this(
            id = post.id,
            title = post.title,
            content = post.content,
            price = post.price,
            userName = post.getUser().username,
            createAt = post.createdAt,
            modifiedAt = post.modifiedAt
    )

}