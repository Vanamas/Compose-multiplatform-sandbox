package di

/**
 * Koin dependency injection
 *
 * @author Martin Vana
 */
import Platform
import data.repository.PostRepositoryImpl
import data.source.remote.PostsApiSource
import data.usecase.CacheTimeExpiredUseCase
import data.usecase.IsDeviceOnlineUseCase
import domain.repository.PostRepository
import domain.usecase.GetPostUseCase
import domain.usecase.GetPostsUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import presentation.screen.home.HomeViewModel
import presentation.screen.post.PostViewModel
import presentation.screen.post.PostsViewModel

private val client = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            useAlternativeNames = false
        })
    }
    defaultRequest {
        url("https://jsonplaceholder.typicode.com/")
    }
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

fun appModule() = listOf(
    commonModule,
    sourceModule,
    repositoriesModule,
    useCasesModule,
    platformModule,
    viewModelModule
)

val viewModelModule = module {
    factoryOf(::HomeViewModel)
    factoryOf(::PostsViewModel)
    factoryOf(::PostViewModel)
}

val platformModule = module {
    singleOf(::Platform)
}

val commonModule = module {
    single { createJson() }
    single { client }
}

val useCasesModule = module {
    singleOf(::IsDeviceOnlineUseCase)
    singleOf(::CacheTimeExpiredUseCase)
    singleOf(::GetPostsUseCase)
    singleOf(::GetPostUseCase)
}

val repositoriesModule = module {
    singleOf(::PostRepositoryImpl) { bind<PostRepository>() }
}

val sourceModule = module {
    singleOf(::PostsApiSource)
}