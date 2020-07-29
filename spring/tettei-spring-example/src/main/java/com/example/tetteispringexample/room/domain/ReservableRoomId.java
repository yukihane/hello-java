package com.example.tetteispringexample.room.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservableRoomId implements Serializable {
    private Integer roomId;
    private LocalDate reservedDate;
}
