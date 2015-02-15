package com.robrua.orianna.type.api.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.api.RiotAPI;
import com.robrua.orianna.type.api.OriannaObject;
import com.robrua.orianna.type.api.common.Side;
import com.robrua.orianna.type.api.staticdata.Item;
import com.robrua.orianna.type.exception.MissingDataException;

public class Event extends OriannaObject<com.robrua.orianna.type.dto.match.Event> {
    private static final long serialVersionUID = -5805690031607393709L;
    private final Participant creator, participant, victim;
    private Item itemAfter, itemBefore, item;
    private final List<Participant> participants;
    private Position position;

    /**
     * @param data
     *            the underlying dto
     */
    public Event(final com.robrua.orianna.type.dto.match.Event data, final Map<Integer, Participant> participants) {
        super(data, com.robrua.orianna.type.dto.match.Event.class);
        this.participants = new ArrayList<>();
        for(final Integer ID : data.getAssistingParticipantIds()) {
            this.participants.add(participants.get(ID));
        }
        creator = participants.get(data.getCreatorId());
        participant = participants.get(data.getParticipantId());
        victim = participants.get(data.getVictimId());
    }

    /**
     * The ascended type of the event. Only present if relevant. Note that
     * CLEAR_ASCENDED refers to when a participants kills the ascended player.
     *
     * @return the ascended type of the event
     */
    public AscendedType getAscendedType() {
        try {
            return AscendedType.valueOf(super.getString(data.getAscendedType()));
        }
        catch(final IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * The assisting participant IDs of the event. Only present if relevant.
     *
     * @return the assisting participants
     */
    public List<Participant> getAssistingParticipants() {
        return Collections.unmodifiableList(participants);
    }

    /**
     * The building type of the event. Only present if relevant.
     *
     * @return the building type of the event
     */
    public BuildingType getBuildingType() {
        try {
            return BuildingType.valueOf(super.getString(data.getBuildingType()));
        }
        catch(final IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * The creator ID of the event. Only present if relevant.
     *
     * @return the creator ID of the event
     */
    public Participant getCreator() {
        return creator;
    }

    /**
     * Event type
     *
     * @return event type
     */
    public EventType getEventType() {
        try {
            return EventType.valueOf(super.getString(data.getEventType()));
        }
        catch(final IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * The ending item of the event. Only present if relevant.
     *
     * @return the ending item of the event
     */
    public Item getItem() {
        if(item != null) {
            return item;
        }

        final Integer i = data.getItemId();
        if(i == null) {
            throw new MissingDataException("Item ID is null.");
        }

        item = RiotAPI.getItemByID(i.longValue());
        return item;
    }

    /**
     * The ending item of the event. Only present if relevant.
     *
     * @return the ending item of the event
     */
    public Item getItemAfter() {
        if(itemAfter != null) {
            return itemAfter;
        }

        final Integer i = data.getItemAfter();
        if(i == null) {
            throw new MissingDataException("Item After ID is null.");
        }

        itemAfter = RiotAPI.getItemByID(i.longValue());
        return itemAfter;
    }

    /**
     * The ending item ID of the event. Only present if relevant.
     *
     * @return the ending item ID of the event
     */
    public long getItemAfterID() {
        return super.getInteger(data.getItemAfter());
    }

    /**
     * The ending item of the event. Only present if relevant.
     *
     * @return the ending item of the event
     */
    public Item getItemBefore() {
        if(itemBefore != null) {
            return itemBefore;
        }

        final Integer i = data.getItemBefore();
        if(i == null) {
            throw new MissingDataException("Item Before ID is null.");
        }

        itemBefore = RiotAPI.getItemByID(i.longValue());
        return itemBefore;
    }

    /**
     * The starting item ID of the event. Only present if relevant.
     *
     * @return the starting item ID of the event
     */
    public long getItemBeforeID() {
        return super.getInteger(data.getItemBefore());
    }

    /**
     * The item ID of the event. Only present if relevant.
     *
     * @return the item ID of the event
     */
    public long getItemID() {
        return super.getInteger(data.getItemId());
    }

    /**
     * The killer ID of the event. Only present if relevant. Killer ID 0
     * indicates a minion.
     *
     * @return the killer ID of the event
     */
    public int getKillerID() {
        return super.getInteger(data.getKillerId());
    }

    /**
     * The lane type of the event. Only present if relevant.
     *
     * @return the lane type of the event
     */
    public LaneType getLaneType() {
        try {
            return LaneType.valueOf(super.getString(data.getLaneType()));
        }
        catch(final IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * The level up type of the event. Only present if relevant.
     *
     * @return the level up type of the event
     */
    public LevelUpType getLevelUpType() {
        try {
            return LevelUpType.valueOf(super.getString(data.getLevelUpType()));
        }
        catch(final IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * The monster type of the event. Only present if relevant.
     *
     * @return the monster type of the event
     */
    public MonsterType getMonsterType() {
        try {
            return MonsterType.valueOf(super.getString(data.getMonsterType()));
        }
        catch(final IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * The participant of the event. Only present if relevant.
     *
     * @return the participant of the event
     */
    public Participant getParticipant() {
        return participant;
    }

    /**
     * The point captured in the event. Only present if relevant.
     *
     * @return the point captured in the event
     */
    public Point getPointCaptured() {
        try {
            return Point.valueOf(super.getString(data.getPointCaptured()));
        }
        catch(final IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * The position of the event. Only present if relevant.
     *
     * @return the position of the event
     */
    public Position getPosition() {
        if(position == null) {
            position = new Position(data.getPosition());
        }

        return position;
    }

    /**
     * The skill slot of the event. Only present if relevant.
     *
     * @return the skill slot of the event
     */
    public int getSkillSlot() {
        return super.getInteger(data.getSkillSlot());
    }

    /**
     * The team of the event. Only present if relevant.
     *
     * @return the team of the event
     */
    public Side getTeam() {
        return Side.forID(super.getInteger(data.getTeamId()));
    }

    /**
     * Represents how many milliseconds into the game the event occurred.
     *
     * @return how many milliseconds into the game the event occurred
     */
    public long getTimestamp() {
        return super.getLong(data.getTimestamp());
    }

    /**
     * The tower type of the event. Only present if relevant.
     *
     * @return the tower type of the event
     */
    public TowerType getTowerType() {
        try {
            return TowerType.valueOf(super.getString(data.getTowerType()));
        }
        catch(final IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * The victim of the event. Only present if relevant.
     *
     * @return the victim of the event
     */
    public Participant getVictim() {
        return victim;
    }

    /**
     * The ward type of the event. Only present if relevant.
     *
     * @return the ward type of the event
     */
    public WardType getWardType() {
        try {
            return WardType.valueOf(super.getString(data.getWardType()));
        }
        catch(final IllegalArgumentException e) {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Event";
    }
}
