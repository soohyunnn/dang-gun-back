package dang.gun.com.image

import dang.gun.com.post.Post

data class ImageRequest(
        var imgName: String,
        var imgPath: String,
        var imgType: String,
        var postId : Post
) {


}