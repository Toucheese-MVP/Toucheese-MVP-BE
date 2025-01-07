package com.example.toucheese_be.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -763177903L;

    public static final QUser user = new QUser("user");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final ListPath<com.example.toucheese_be.domain.order.entity.Order, com.example.toucheese_be.domain.order.entity.QOrder> orders = this.<com.example.toucheese_be.domain.order.entity.Order, com.example.toucheese_be.domain.order.entity.QOrder>createList("orders", com.example.toucheese_be.domain.order.entity.Order.class, com.example.toucheese_be.domain.order.entity.QOrder.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath profileImg = createString("profileImg");

    public final EnumPath<com.example.toucheese_be.domain.user.constant.Role> role = createEnum("role", com.example.toucheese_be.domain.user.constant.Role.class);

    public final StringPath socialId = createString("socialId");

    public final EnumPath<com.example.toucheese_be.domain.user.constant.SocialProvider> socialProvider = createEnum("socialProvider", com.example.toucheese_be.domain.user.constant.SocialProvider.class);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

