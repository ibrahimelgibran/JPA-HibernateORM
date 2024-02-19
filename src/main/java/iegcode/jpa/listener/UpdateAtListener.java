package iegcode.jpa.listener;

import iegcode.jpa.entity.UpdateAtAware;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class UpdateAtListener {

    @PrePersist
    @PreUpdate
    public void setLastUpdateAt(UpdateAtAware object){
        object.setUpdateAt(LocalDateTime.now());
    }
}
