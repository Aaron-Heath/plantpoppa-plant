package com.plantpoppa.plant.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class JournalRequestDto {
    private String entityUuid;
    private int entityId;
    private LocalDate entryDate;

    public JournalRequestDto(String entityUuid, int entityId, LocalDate entryDate) {
        this.entityUuid = entityUuid;
        this.entityId = entityId;
        this.entryDate = entryDate;
    }

    public JournalRequestDto() {
    }

    public String getEntityUuid() {
        return entityUuid;
    }

    public void setEntityUuid(String entityUuid) {
        this.entityUuid = entityUuid;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
}
