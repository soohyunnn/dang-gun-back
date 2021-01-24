package dang.gun.com.user

data class UserSignupDto(
        var id : Int,
        var email: String,
        var username: String,
        var password: String,
        var addressNumber: String,
        var detailAddress: String,
){

}