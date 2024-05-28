package pl.bodzioch.damian.common;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.EventTypeSets;

import java.util.EnumSet;
import java.util.UUID;

public class UuidValueGeneration implements BeforeExecutionGenerator {

    private final EnumSet<EventType> eventTypes;

    public UuidValueGeneration(GeneratedUuidValue annotation) {
        this.eventTypes = EventTypeSets.fromArray(annotation.types());
    }

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1, EventType eventType) {
        return UUID.randomUUID();
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return this.eventTypes;
    }
}
