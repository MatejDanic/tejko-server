package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tejko.interfaces.mappers.UserMapperInterface;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.responses.PreferenceResponse;
import com.tejko.models.general.payload.responses.RoleResponse;
import com.tejko.models.general.payload.responses.UserResponse;

@Component
public class UserMapper implements UserMapperInterface {

    @Resource
    RoleMapper roleMapper;

    @Resource
    PreferenceMapper preferenceMapper;

    @Override
    public UserResponse toRestResponse(User user) {
        if (user == null) return null;
        List<RoleResponse> roles = roleMapper.toRestResponseList(user.getRoles().stream().collect(Collectors.toList()));
        PreferenceResponse preference = preferenceMapper.toRestResponse(user.getPreference());
        return new UserResponse(
            user.getId(), 
            user.getCreatedDate(), 
            user.getLastModifiedDate(), 
            user.getUsername(), 
            roles, 
            preference
        );
    }

    @Override
    public List<UserResponse> toRestResponseList(List<User> userList) {
        return userList.stream().map(this::toRestResponse).collect(Collectors.toList());
    }

}
