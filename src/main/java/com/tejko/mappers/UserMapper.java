package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.tejko.interfaces.mappers.UserMapperInterface;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.responses.PreferenceResponse;
import com.tejko.models.general.payload.responses.RoleResponse;
import com.tejko.models.general.payload.responses.UserResponse;

public class UserMapper implements UserMapperInterface {

    @Resource
    RoleMapper roleMapper;

    @Resource
    PreferenceMapper preferenceMapper;

    @Override
    public UserResponse toApiResponse(User user) {
        List<RoleResponse> roles = roleMapper.toApiResponseList(user.getRoles().stream().collect(Collectors.toList()));
        PreferenceResponse preference = preferenceMapper.toApiResponse(user.getPreference());
        return new UserResponse(
            user.getCreatedDate(), 
            user.getLastModifiedDate(), 
            user.getId(), 
            user.getUsername(), 
            roles, 
            preference
        );
    }

    @Override
    public List<UserResponse> toApiResponseList(List<User> userList) {
        return userList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
