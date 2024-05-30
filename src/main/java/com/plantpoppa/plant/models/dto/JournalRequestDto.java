package com.plantpoppa.plant.models.dto;

public class JournalRequestDto {
    private String entityUuid;
    private int entityId;

    public JournalRequestDto(String userPlantUuid, int entityId) {
        this.entityUuid = userPlantUuid;
        this.entityId = entityId;
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
}
