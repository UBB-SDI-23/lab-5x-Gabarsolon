package com.smartphones.Model;

import java.util.Set;

public class DisplaySmartphonesDTO {
    private Long displayId;
    private Set<Long> smartphonesIds;

    public DisplaySmartphonesDTO(Long displayId, Set<Long> smartphonesIds) {
        this.displayId = displayId;
        this.smartphonesIds = smartphonesIds;
    }

    public Long getDisplayId() {
        return displayId;
    }

    public Set<Long> getSmartphonesIds() {
        return smartphonesIds;
    }

    public void setDisplayId(Long displayId) {
        this.displayId = displayId;
    }

    public void setSmartphonesIds(Set<Long> smartphonesIds) {
        this.smartphonesIds = smartphonesIds;
    }
}
