package com.example.toucheese_be.domain.studio.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudioDutyDate is a Querydsl query type for StudioDutyDate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudioDutyDate extends EntityPathBase<StudioDutyDate> {

    private static final long serialVersionUID = 1900185835L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudioDutyDate studioDutyDate = new QStudioDutyDate("studioDutyDate");

    public final TimePath<java.time.LocalTime> closeTime = createTime("closeTime", java.time.LocalTime.class);

    public final EnumPath<com.example.toucheese_be.domain.studio.entity.constant.DayType> day = createEnum("day", com.example.toucheese_be.domain.studio.entity.constant.DayType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final TimePath<java.time.LocalTime> openTime = createTime("openTime", java.time.LocalTime.class);

    public final QStudio studio;

    public final EnumPath<com.example.toucheese_be.domain.studio.entity.constant.DutyType> type = createEnum("type", com.example.toucheese_be.domain.studio.entity.constant.DutyType.class);

    public QStudioDutyDate(String variable) {
        this(StudioDutyDate.class, forVariable(variable), INITS);
    }

    public QStudioDutyDate(Path<? extends StudioDutyDate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudioDutyDate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudioDutyDate(PathMetadata metadata, PathInits inits) {
        this(StudioDutyDate.class, metadata, inits);
    }

    public QStudioDutyDate(Class<? extends StudioDutyDate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studio = inits.isInitialized("studio") ? new QStudio(forProperty("studio"), inits.get("studio")) : null;
    }

}

