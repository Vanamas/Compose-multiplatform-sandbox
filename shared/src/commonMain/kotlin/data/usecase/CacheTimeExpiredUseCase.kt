package data.usecase

import kotlinx.datetime.LocalDate


/**
 * Checks if device is connected to internet.
 *
 * @author Martin Vana
 */
class CacheTimeExpiredUseCase {

    companion object {
        // Cache time in milliseconds (2 minutes)
        private const val CACHE_TIME = 2 * 60 * 1000
    }

    suspend operator fun invoke(lastUpdate: LocalDate?) =
        lastUpdate == null //|| LocalDate. .time > lastUpdate.time + CACHE_TIME

}