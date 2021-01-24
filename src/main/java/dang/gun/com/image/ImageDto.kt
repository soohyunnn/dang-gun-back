package dang.gun.com.image

data class ImageDto(
        var id: Int,
        var name : String,
        var path: String
){
    constructor(image: Image) : this(
            id = image.id,
            name = image.filename,
            path = image.path
    )
}