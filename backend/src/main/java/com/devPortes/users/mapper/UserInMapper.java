package com.devPortes.users.mapper;

import com.devPortes.users.dto.auth.EditProfileRequestDto;
import com.devPortes.users.dto.auth.NewUserRequestDto;
import com.devPortes.users.dto.auth.NewUserResponseDto;
import com.devPortes.users.dto.client.EditClientRequestDto;
import com.devPortes.users.dto.client.ListUsersResponseDto;
import com.devPortes.users.entities.ClientEntity;
import com.devPortes.users.model.Client;
import com.devPortes.users.model.IAuthenticated;

public class UserInMapper {

    public static Client toModel(NewUserRequestDto dto, String passwordHash){
        return Client.create(
                dto.name(),
                dto.identityDocument(),
                dto.phoneNumber(),
                dto.email(),
                passwordHash,
                null
        );

    }
    public static Client toEditModel( Client saveClient,EditClientRequestDto dto, String urlPicture){
        return Client.edit(
                urlPicture,
                dto.name(),
                saveClient.getIdentityDocument(),
                dto.phoneNumber(),
                saveClient.getEmail(),
                saveClient.getPasswordHash(),
                saveClient.getClassification(),
                dto.reservationAmount(),
                saveClient.getRole(),
                saveClient.isState()

        );
    }
    public static NewUserResponseDto toNewUserDto (Client user, String token){
        return new NewUserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUrlPicture(),
                token
        );
    }

    public static NewUserResponseDto toDtoProfile(IAuthenticated user){
        return new NewUserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user instanceof Client client ? client.getUrlPicture() : null,
                ""
        );
    }
    public static Client toEditProfileModel(Client existing, EditProfileRequestDto dto) {
        return Client.edit(
                existing.getUrlPicture(),
                dto.name(),
                dto.identityDocument(),
                dto.phoneNumber(),
                dto.email(),
                existing.getPasswordHash(),
                existing.getClassification(),
                existing.getReservationAmount(),
                existing.getRole(),
                existing.isState()
        );
    }

    public static ListUsersResponseDto toUserDto(Client entity) {
        return new ListUsersResponseDto(
                entity.getId(),
                entity.getUrlPicture(),
                entity.getName(),
                entity.getEmail(),
                entity.getIdentityDocument(),
                entity.getPhoneNumber(),
                entity.getRole().name(),
                entity.getClassification() != null ? entity.getClassification().name() : null,
                entity.isState()
        );
    }
}
