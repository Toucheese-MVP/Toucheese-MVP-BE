-- Concept 테이블에 컨셉 데이터 삽입

INSERT INTO concept (name, concept_img)
VALUES
    ('VIBRANT', 'vibrant_image.jpg'),            -- 생동감 있는 실물 느낌
    ('FLASHY_GLOSSY', 'flashy_glossy_image.jpg'), -- 플래쉬 터진 느낌, 화려한 느낌
    ('BLACK_BLUE_ACTOR', 'black_blue_actor.jpg'), -- 블랙/블루 배우 느낌
    ('WATERCOLOUR', 'watercolour_image.jpg'),    -- 수채화 그림체 느낌
    ('NATURAL_PHOTO', 'natural_photo.jpg');      -- 내추럴 화보 느낌


-- Studio 테이블에 스튜디오 데이터 삽입
INSERT INTO studio (name, profile_img, avg_asterion, address, concept_id)
VALUES
    -- 생동감있는 실물느낌
    ('시현하다-강남 오리지널', 'vibrant_profile.jpg', 4.5, '서울특별시 강남구 테헤란로 19길 53-6', 1),
    ('시현하다-성수 플래그십', 'vibrant_profile.jpg', 4.5, '서울특별시 성동구 성수이로 144 이엠타워 1층', 1),
    ('시현하다-홍대 스페이스', 'vibrant_profile.jpg', 4.5, '서울특별시 마포구 독막로2길37 EK빌딩 1층 ', 1),
    ('시현하다-북촌 하우스', 'vibrant_profile.jpg', 4.5, '서울 종로구 계동길 80', 1),
    -- 수체화그림체 느낌
    ('산호맨숀', 'flashy_profile.jpg', 4.2, '서울 종로구 청계천로 159 860호,751호,662호', 4),
    ('크림포토', 'flashy_profile.jpg', 4.2, '서울 양천구 오목로50길 5 B1', 4),
    ('오해피데이스튜디오', 'flashy_profile.jpg', 4.2, '강남구 신사동 566-10 4F', 4),
    ('프롬선스튜디오', 'flashy_profile.jpg', 4.2, '서울 강남구 강남대로 522 4층(와인샵과 약국골목 정금당 옆 계단입구 4층)', 4),
    -- 플래시 터진 느낌
    ('아워유스', 'vivid_profile.jpg', 4.8, '서울시 마포구 와우산로 64 전원빌딩 4층', 2),
    ('허쉬스튜디오', 'vivid_profile.jpg', 4.8, '서울 강남구 강남대로120길 76 2층', 2),
    ('레코디드(홍대)', 'vivid_profile.jpg', 4.8, '서울 마포구 동교로 212-28 지하1층', 2),
    ('레코디드(강남)', 'vivid_profile.jpg', 4.8, '서울 강남구 봉은사로2길 24 3층 301호', 2);

-- Item 테이블에 아이템 데이터 삽입
INSERT INTO item (name, description, price, image, studio_id)
VALUES
    ('프로필사진', '시현하다-강남 오리지널의 프로필사진', 84000, 'vibrant_item.jpg', 1),
    ('프로필사진', '시현하다 성수 플래그십의 프로필사진', 84000, 'flashy_item.jpg', 2),
    ('프로필사진', '시현하다-홍대 스페이스의 프로필사진', 84000, 'vibrant_item.jpg', 3),
    ('프로필사진', '시현하다 북촌 하우스의 프로필사진', 84000, 'flashy_item.jpg', 4),
    ('프로필촬영', '산호맨숀의 프로필촬영', 300000, 'flashy_item.jpg', 5),
    ('프로필', '크림포토의 프로필', 70000, 'flashy_item.jpg', 6),
    ('증명사진', '오해피데이스튜디오의 증명사진', 75000, 'flashy_item.jpg', 7),
    ('프로필촬영', '프롬선의 프로필촬영', 120000, 'flashy_item.jpg', 8),
    ('프로필촬영', '아워유스의 프로필촬영', 99000, 'flashy_item.jpg', 9),
    ('프로필촬영', '허쉬스튜디오의 프로필촬영', 120000, 'flashy_item.jpg', 10),
    ('증명사진', '레코디드(홍대)의 증명사진', 50000, 'flashy_item.jpg', 11),
    ('증명사진', '레코디드(건대)의 증명사진', 55000, 'flashy_item.jpg', 12);


-- Portfolio 테이블에 포트폴리오 데이터 삽입
INSERT INTO portfolio (image_url, studio_id)
VALUES
    ('vibrant_portfolio1.jpg', 1),
    ('flashy_portfolio1.jpg', 2),
    ('vivid_portfolio1.jpg', 3),
    ('watercolour_portfolio1.jpg', 4);
