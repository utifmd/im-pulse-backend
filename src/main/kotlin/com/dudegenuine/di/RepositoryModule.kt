package com.dudegenuine.di

import com.dudegenuine.app.mapper.*
import com.dudegenuine.app.mapper.contract.*
import com.dudegenuine.app.repository.*
import com.dudegenuine.app.repository.contract.*
import com.dudegenuine.app.repository.dependency.HashDependency
import com.dudegenuine.app.repository.dependency.IHashDependency
import com.dudegenuine.app.repository.dependency.ITokenDependency
import com.dudegenuine.app.repository.dependency.TokenDependency
import org.koin.dsl.module

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/

val repositoryModule = module {
    single<IAuthRepository>{ AuthRepository(get(), get(), get(), get()) }
    single<IUserRepository>{ UserRepository(get()) }
    single<IProfileRepository>{ ProfileRepository(get()) }
    single<IFileRepository>{ FileRepository(get()) }
    single<IRoleRepository>{ RoleRepository(get()) }
    single<IImageRepository>{ ImageRepository(get()) }
    single<IDeviceRepository>{ DeviceRepository(get()) }
    single<IContactRepository>{ ContactRepository(get()) }
    single<IMessageRepository>{ MessageRepository(get()) }
    single<IConversationRepository>{ ConversationRepository(get(), get()) }
    single<IParticipantRepository>{ ParticipantRepository() }
    single<IBlacklistRepository>{ BlacklistRepository(get()) }
    single<IVerifierRepository>{ VerifierRepository(get()) }
}

val mapperModule = module {
    single<IAuthMapper>{ AuthMapper() }
    single<IUserMapper>{ UserMapper() }
    single<IProfileMapper>{ ProfileMapper() }
    single<IFileMapper>{ FileMapper() }
    single<IRoleMapper>{ RoleMapper() }
    single<IImageMapper>{ ImageMapper() }
    single<IDeviceMapper>{ DeviceMapper() }
    single<IContactMapper>{ ContactMapper() }
    single<IMessageMapper>{ MessageMapper() }
    single<IConversationMapper>{ ConversationMapper(get()) }
    single<IParticipantMapper>{ ParticipantMapper(get()) }
    single<IBlacklistMapper>{ BlacklistMapper(get()) }
    single<IVerifierMapper>{ VerifierMapper() }
}

val dependencyModule = module {
    single<IHashDependency>{ HashDependency() }
    single<ITokenDependency>{ TokenDependency() }
}