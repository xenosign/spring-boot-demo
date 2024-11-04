-- 기존 테이블이 있다면 삭제 (순서 주의)
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

-- 사용자 테이블 생성
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(100),
                       email VARCHAR(100),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       status VARCHAR(10)
);

-- 상품 테이블 생성
CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100),
                          price DECIMAL(10,2),
                          stock INT
);

-- 주문 테이블 생성
CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT,
                        order_amount DECIMAL(10,2),
                        order_date TIMESTAMP,
                        status VARCHAR(20),
                        FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 주문상품 테이블 생성
CREATE TABLE order_items (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             order_id BIGINT,
                             product_id BIGINT,
                             quantity INT,
                             price DECIMAL(10,2),
                             FOREIGN KEY (order_id) REFERENCES orders(id),
                             FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 1. 먼저 사용자 데이터 생성 (1000명)
INSERT INTO users (username, email, status)
SELECT
    CONCAT('user', n),
    CONCAT('user', n, '@example.com'),
    CASE WHEN n % 10 = 0 THEN 'INACTIVE' ELSE 'ACTIVE' END
FROM (
         SELECT (@row_number:=@row_number + 1) as n
         FROM (select 0 union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) t1,
              (select 0 union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) t2,
              (select 0 union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) t3,
              (SELECT @row_number:=0) t4
             LIMIT 1000
     ) numbers;

-- 2. 상품 데이터 생성 (100개)
INSERT INTO products (name, price, stock)
SELECT
    CONCAT('Product ', n),
    ROUND(RAND() * 900000 + 100000, 2),
    FLOOR(RAND() * 1000)
FROM (
         SELECT (@row_number:=@row_number + 1) as n
         FROM (select 0 union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) t1,
              (select 0 union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) t2,
              (SELECT @row_number:=0) t3
             LIMIT 100
     ) numbers;

-- 외래키 제약조건 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 테이블 초기화 (이미 생성된 데이터가 있는 경우를 위해)
TRUNCATE TABLE order_items;
TRUNCATE TABLE orders;

-- 외래키 제약조건 다시 활성화
SET FOREIGN_KEY_CHECKS = 1;

-- 임시 숫자 테이블 생성
DROP TEMPORARY TABLE IF EXISTS numbers;
CREATE TEMPORARY TABLE numbers (
    n INT AUTO_INCREMENT PRIMARY KEY
);

-- 5000개의 숫자를 생성하는 방법 수정
INSERT INTO numbers (n)
SELECT
    ((a.N * 1000) + (b.N * 100) + (c.N * 10) + d.N + 1) as num
FROM
    (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) a,
    (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b,
    (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) c,
    (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) d
WHERE
    ((a.N * 1000) + (b.N * 100) + (c.N * 10) + d.N + 1) <= 5000
ORDER BY 1;

-- 3. 주문 데이터 생성 (5000건)
INSERT INTO orders (user_id, order_amount, order_date, status)
SELECT
    1 + FLOOR(RAND() * (SELECT COUNT(*) FROM users)),
    ROUND(RAND() * 1000000 + 10000, 2),
    DATE_SUB(CURRENT_TIMESTAMP, INTERVAL FLOOR(RAND() * 365) DAY),
    CASE
        WHEN RAND() < 0.7 THEN 'COMPLETED'
        WHEN RAND() < 0.8 THEN 'PENDING'
        WHEN RAND() < 0.9 THEN 'CANCELLED'
        ELSE 'REFUNDED'
        END
FROM numbers;

-- 4. 주문상품 데이터 생성 (각 주문당 1-3개의 아이템)
INSERT INTO order_items (order_id, product_id, quantity, price)
SELECT
    o.id,
    1 + FLOOR(RAND() * (SELECT COUNT(*) FROM products)),
    FLOOR(RAND() * 5) + 1,
    ROUND(RAND() * 100000 + 10000, 2)
FROM orders o
         CROSS JOIN (
    SELECT 1 AS num UNION ALL SELECT 2 UNION ALL SELECT 3
) numbers_small;

-- 임시 테이블 삭제
DROP TEMPORARY TABLE numbers;

-- 데이터 검증 쿼리들

-- 1. 전체 테이블 레코드 수 확인
SELECT
    'users' as table_name,
    COUNT(*) as record_count
FROM users
UNION ALL
SELECT 'products', COUNT(*) FROM products
UNION ALL
SELECT 'orders', COUNT(*) FROM orders
UNION ALL
SELECT 'order_items', COUNT(*) FROM order_items;

-- 2. 주문 상태별 통계
SELECT
    status,
    COUNT(*) as count,
    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM orders), 2) as percentage
FROM orders
GROUP BY status;

-- 3. 샘플 데이터 확인
SELECT * FROM orders LIMIT 5;
SELECT * FROM order_items LIMIT 5;