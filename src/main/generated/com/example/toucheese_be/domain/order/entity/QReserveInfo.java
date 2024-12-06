package com.example.toucheese_be.domain.order.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.toucheese_be.domain.order.entity.constant.OrderStatus;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReserveInfo is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReserveInfo extends EntityPathBase<Order> {

    private static final long serialVersionUID = -437091921L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReserveInfo reserveInfo = new QReserveInfo("reserveInfo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.toucheese_be.domain.item.entity.QItem item;

    public final EnumPath<OrderStatus> nowReserveInfo = createEnum("nowReserveInfo", OrderStatus.class);

    public final com.example.toucheese_be.domain.item.entity.QOption option;

    public final DateTimePath<java.time.LocalDateTime> reserveDateTime = createDateTime("reserveDateTime", java.time.LocalDateTime.class);

    public final com.example.toucheese_be.domain.studio.entity.QStudio studio;

    public final com.example.toucheese_be.domain.user.entity.QUser user;

    public QReserveInfo(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QReserveInfo(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReserveInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReserveInfo(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QReserveInfo(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new com.example.toucheese_be.domain.item.entity.QItem(forProperty("item"), inits.get("item")) : null;
        this.option = inits.isInitialized("option") ? new com.example.toucheese_be.domain.item.entity.QOption(forProperty("option")) : null;
        this.studio = inits.isInitialized("studio") ? new com.example.toucheese_be.domain.studio.entity.QStudio(forProperty("studio"), inits.get("studio")) : null;
        this.user = inits.isInitialized("user") ? new com.example.toucheese_be.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

