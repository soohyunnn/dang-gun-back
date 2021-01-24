package dang.gun.com.post

import java.time.LocalDateTime

data class PostCreateRequest(
        var title: String,
        var content: String,
        var price: Int,
        var userId: Int,
) {

}

data class PostUpdateRequest(
        var postId: Int,
        var title: String,
        var content: String,
        var price: Int,
) {

}

data class PostDeleteRequest(
        var postId: Int,
        var userId: Int,
) {

}

data class PostDto(
        var id: Int,
        var title: String?,
        var content: String?,
        var price: Int?,
        var viewCnt: Int?,
        var likeCnt: Int?,
        var userName: String?,
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
            userName = post.getUser().name,
            detailaddress = post.getUser().detailAddress,
            createAt = post.createdAt,
            modifiedAt = post.modifiedAt
    )
}

interface PostListDto {
    fun getId(): Int;
    fun getTitle(): String;
    fun getPrice(): Int;
    fun getDetailaddress(): String;
    fun getPath(): String;
    fun getFilename(): String;
}
