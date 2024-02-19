package iegcode.jpa.entity;

import java.time.LocalDateTime;

public interface UpdateAtAware {

    void setUpdateAt(LocalDateTime updateAt);
}
