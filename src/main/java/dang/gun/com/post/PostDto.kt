package dang.gun.com.post

import java.time.LocalDateTime

data class PostCreateRequest(
        var title: String,
        var content: String,
        var price: Int,
        var userEmail: String,
) {

}

data class PostUpdateRequest(
        var postId: Int,
        var title: String,
        var content: String,
        var price: Int,
        var userId: Int,
) {

}

data class PostDeleteRequest(
        var postId: Int,
        var userEmail: String,
) {

}

data class PostDto(
        var id: Int,
        var title: String?,
        var content: String?,
        var price: Int?,
        var viewCnt: Int?,
        var likeCnt: Int?,
        var userEmail : String?,
        var detailaddress: String?,
        var createAt: LocalDateTime?,
        var modifiedAt: LocalDateTime?,
) {
    constructor(post: Post) : this(
            id = post.id,
            title = post.title,
            content = post.content,
            price = post.price,
            viewCnt = post.viewCnt,
            likeCnt = post.likeCnt,
            userEmail = post.getUser().email,
            detailaddress = post.getUser().detailAddress,
            createAt = post.createdAt,
            modifiedAt = post.modifiedAt
    )
}

interface PostAllDto {
    fun getId(): Int;
    fun getTitle(): String;
    fun getPrice(): Int;
    fun getDetailaddress(): String;
    fun getPath(): String;
    fun getFilename(): String;
}
