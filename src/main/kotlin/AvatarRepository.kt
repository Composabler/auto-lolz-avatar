import com.fasterxml.jackson.databind.JsonNode
import me.topilov.LolzApi
import me.topilov.data.Result
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.*

class AvatarRepository(
    private val api: LolzApi,
) {

    suspend fun uploadAvatar(userId: Int, file: File): Result {
        val part = MultipartBody.Part.createFormData("avatar", file.name, file.asRequestBody("image/*".toMediaTypeOrNull()))
        return api.uploadAvatar(userId, part)
    }
}