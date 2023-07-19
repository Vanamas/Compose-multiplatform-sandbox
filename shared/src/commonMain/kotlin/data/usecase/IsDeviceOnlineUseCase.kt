package data.usecase


/**
 * Checks if device is connected to internet.
 *
 * @author Martin Vana
 */
class IsDeviceOnlineUseCase {


    suspend operator fun invoke() = true
//    runCatching {
//        InetAddress.getByName(TEST_DOMAIN)
//    }.fold(
//        onSuccess = { !it.equals("") },
//        onFailure = { false }
//    )

    companion object {
        const val TEST_DOMAIN = "google.com"
    }
}