-- Concept 테이블에 컨셉 데이터 삽입
INSERT INTO concept (name, concept_img)
VALUES
    ('VIBRANT', 'vibrant_image.jpg'),
    ('FLASHY_GLOSSY', 'flashy_glossy_image.jpg'),
    ('VIVID', 'vivid_image.jpg'),
    ('WATERCOLOUR', 'watercolour_image.jpg');

-- Studio 테이블에 스튜디오 데이터 삽입
INSERT INTO studio (name, profile_img, avg_asterion, address, concept_id)
VALUES
    ('시현하다-강남 오리지널', 'vibrant_profile.jpg', 4.5, '서울특별시 강남구 테헤란로 19길 53-6', 1),
    ('플래시 스튜디오', 'flashy_profile.jpg', 4.2, '서울특별시 강남구 테헤란로 10길 45-1', 2),
    ('선명 스튜디오', 'vivid_profile.jpg', 4.8, '서울특별시 강남구 테헤란로 20길 12-3', 3),
    ('수채화 스튜디오', 'watercolour_profile.jpg', 4.3, '서울특별시 강남구 테헤란로 30길 7-8', 4);

-- Item 테이블에 아이템 데이터 삽입
INSERT INTO item (name, description, price, image, studio_id)
VALUES
    ('상품1', 'Vibrant 스타일의 아이템', 100000, 'vibrant_item.jpg', 1),
    ('상품2', 'Flashy Glossy 스타일의 아이템', 150000, 'flashy_item.jpg', 2),
    ('상품3', 'Vivid 스타일의 아이템', 200000, 'vivid_item.jpg', 3),
    ('상품4', 'Watercolour 스타일의 아이템', 120000, 'watercolour_item.jpg', 4);

-- Portfolio 테이블에 포트폴리오 데이터 삽입
INSERT INTO portfolio (image_url, studio_id)
VALUES
    ('vibrant_portfolio1.jpg', 1),
    ('flashy_portfolio1.jpg', 2),
    ('vivid_portfolio1.jpg', 3),
    ('watercolour_portfolio1.jpg', 4);
