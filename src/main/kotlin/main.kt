import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import me.topilov.LolzApi
import java.io.File

val TOKEN = System.getenv("TOKEN")
val USER_ID = System.getenv("USER_ID").toInt()
val IMAGES_DIRECTORY = System.getenv("IMAGES_DIRECTORY")
val DELAY = System.getenv("DELAY").toLong()

fun main(): Unit = runBlocking {
    val api = LolzApi(TOKEN)
    val avatarRepository = AvatarRepository(api)
    val files = File(IMAGES_DIRECTORY).listFiles()

    while (true) {
        try {
            val file = files?.randomOrNull() ?: throw RuntimeException("folder is empty")
            println(file.name)
            val response = avatarRepository.uploadAvatar(USER_ID, file)
            println(response.message)
            delay(DELAY)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}