package com.example.toucheese_be.domain.studio.repository;

import com.example.toucheese_be.domain.item.entity.QItem;
import com.example.toucheese_be.domain.studio.dto.StudioDto;
import com.example.toucheese_be.domain.studio.dto.StudioSearchFilterDto;
import com.example.toucheese_be.domain.studio.entity.QPortfolio;
import com.example.toucheese_be.domain.studio.entity.QStudio;
import com.example.toucheese_be.domain.studio.entity.Studio;
import com.example.toucheese_be.domain.studio.entity.constant.Popularity;
import com.example.toucheese_be.domain.studio.entity.constant.PriceFilter;
import com.example.toucheese_be.domain.studio.entity.constant.Region;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudioRepositoryImpl implements StudioRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QStudio studio = QStudio.studio;
    private final QItem item = QItem.item;

    @Override
    public Page<StudioDto> getStudioListWithPages(
            Long conceptId,
            StudioSearchFilterDto dto,
            Pageable pageable
    ) {
        // 기본 조건 : 컨셉 ID 로 필터링
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(studio.concept.id.eq(conceptId));

        if (dto != null) {
            // 지역 필터
            if (dto.getRegion() != null && dto.getRegion() != Region.ALL) {
                builder.and(studio.address.containsIgnoreCase(dto.getRegion().getDescription()));
            }
            // 인기 필터
            if (dto.getPopularity() != null && dto.getPopularity() != Popularity.ALL) {
                builder.and(studio.popularity.goe(dto.getPopularity().getMinRating()));
            }

            Integer minPrice = dto.getPriceFilter().getMinPrice(); // 최소 가격
            Integer maxPrice = dto.getPriceFilter().getMaxPrice(); // 최대 가격

            if (dto.getPriceFilter() == PriceFilter.ABOVE_20) {
                // 20만 원 이상의 상품이 존재하는 경우
                builder.and(
                        JPAExpressions.selectOne()
                                .from(item)
                                .where(item.studio.eq(studio)
                                        .and(item.price.goe(minPrice))) // 20만 원 이상의 조건
                                .exists()
                );
            } else {
                // 일반적인 최소/최대 가격 필터
                if (minPrice != null) {
                    builder.and(
                            JPAExpressions.select(item.price.min())
                                    .from(item)
                                    .where(item.studio.eq(studio))
                                    .goe(minPrice)
                    );
                }
                if (maxPrice != null) {
                    builder.and(
                            JPAExpressions.select(item.price.min())
                                    .from(item)
                                    .where(item.studio.eq(studio))
                                    .loe(maxPrice)
                    );
                }
            }
        }


        // 페이징 처리
        List<Studio> results = jpaQueryFactory.selectFrom(studio)
                .leftJoin(studio.portfolios, QPortfolio.portfolio).fetchJoin()
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 총 개수 계산
        long total = jpaQueryFactory.selectFrom(studio)
                .where(builder)
                .fetchCount();

        // 결과를 DTO로 변환
        List<StudioDto> studioDtos = results.stream()
                .map(StudioDto::fromEntity)
                .collect(Collectors.toList());

        return new PageImpl<>(studioDtos, pageable, total);
    }
}
