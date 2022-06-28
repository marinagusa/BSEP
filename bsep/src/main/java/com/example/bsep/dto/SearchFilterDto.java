package com.example.bsep.dto;

import com.example.bsep.model.Role;

public class SearchFilterDto {

    private String search;

    private Role roleFilter;

    private boolean enabledFilter;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Role getRoleFilter() {
        return roleFilter;
    }

    public void setRoleFilter(Role roleFilter) {
        this.roleFilter = roleFilter;
    }

    public boolean isEnabledFilter() {
        return enabledFilter;
    }

    public void setEnabledFilter(boolean enabledFilter) {
        this.enabledFilter = enabledFilter;
    }
}
