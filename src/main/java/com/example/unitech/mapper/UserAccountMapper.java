package com.example.unitech.mapper;

import com.example.unitech.dto.request.UserAccountCreateRequestDTO;
import com.example.unitech.dto.response.UserAccountViewerResponseDTO;
import com.example.unitech.entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserAccountMapper {
//    @Mapping(target = "user", ignore = true)
    UserAccount userAccountCreateDtoToUserAccount(UserAccountCreateRequestDTO createRequestDTO);

    List<UserAccountViewerResponseDTO> userAccountsToUserAccountViewerResponseDTOs(List<UserAccount> allByUserIdAndAccountStatus);
}
