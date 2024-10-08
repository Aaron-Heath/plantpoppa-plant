package com.plantpoppa.plant.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.plantpoppa.plant.models.dto.UserPlantDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name="user_plant")
public class UserPlant {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private @Id int id;

    @Column(name="uuid")
    private String uuid = String.valueOf(UUID.randomUUID());

    @Column(name="nickname")
    private String nickname;

    @Column(name="snooze")
    private LocalDate snooze;

    @JsonManagedReference
    @OneToMany(mappedBy = "userPlant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Watering> waterings;

    @Formula("(SELECT MAX(w.watering_date) FROM watering w WHERE w.user_plant_id = id)")
    private LocalDate lastWatered;

    @ManyToOne
    @JoinColumn(name="plant_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Plant plant;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @Transient
    public LocalDate getNextWatering() {
        // if snooze return snooze
        if(snooze != null) {
            return snooze;
        }
        // if no last watered return today
        if (lastWatered == null) {
            return LocalDate.now();
        }
        // if no snooze return last watered + watering frequency
        LocalDate nextWatering = lastWatered.plusDays(plant.getWaterFrequency());
        return nextWatering;
    }

    public void removeWatering(Watering watering) {
        this.waterings.remove(watering);
        watering.setUserPlant(null);
    }


    public UserPlantDto toDto() {
        return new UserPlantDto.UserPlantDtoBuilder()
                .uuid(this.uuid)
                .nickname(this.nickname)
                .plant(this.plant)
                .snooze(this.snooze)
                .lastWatered(this.lastWatered)
                .build();
    }
}
