
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tb_carb_mall_carousel`;
CREATE TABLE `tb_carb_mall_admin_user`  (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `login_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆名称',
  `login_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆密码',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员显示昵称',
  `locked` tinyint(4) NULL DEFAULT 0 COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


INSERT INTO `tb_carb_mall_admin_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'LK', 0);
INSERT INTO `tb_carb_mall_admin_user` VALUES (2, 'carb-admin1', 'e10adc3949ba59abbe56e057f20f883e', '01', 0);
INSERT INTO `tb_carb_mall_admin_user` VALUES (3, 'carb-admin2', 'e10adc3949ba59abbe56e057f20f883e', '02', 0);


DROP TABLE IF EXISTS `tb_carb_mall_carousel`;
CREATE TABLE `tb_carb_mall_carousel`  (
  `carousel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '首页轮播图主键id',
  `carousel_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '轮播图',
  `redirect_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '\'##\'' COMMENT '点击后的跳转地址(默认不跳转)',
  `carousel_rank` int(11) NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user` int(11) NOT NULL DEFAULT 0 COMMENT '修改者id',
  PRIMARY KEY (`carousel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


INSERT INTO `tb_carb_mall_carousel` VALUES
(1, 'https://w.wallhaven.cc/full/xl/wallhaven-xlp72v.jpg', 'https://www.ferrari.com/', 100, 0, '2024-6-29 00:00:00', 0, '2024-6-29 00:00:00', 0),
(2, 'https://www.astonmartin.com/-/media/110-timeline-jan-2023/110_001a_a741268_rgb_3840x2160_16-9.jpg?mw', 'https://www.astonmartin.com/en/',80 , 0, '2024-6-29 00:00:00', 0, '2024-6-29 00:00:00', 0),
(3, '/cars-img/toyota corolla.jpg', 'https://www.lamborghini.com/cn-en',90, 0, '2024-6-29 00:00:00', 0, '2024-6-29 00:00:00', 0),
(4, 'https://www.bugatti.com/media/31qj0jrw/sur-mesure-desktop.jpg?width=1920&height=800&rnd=133343618828', 'https://www.bugatti.com/', 101, 0, '2024-6-29 00:00:00', 0, '2024-6-29 00:00:00', 0),
(5, 'https://i.pinimg.com/736x/e7/46/2a/e7462aab61352dc2954b4559bad13f5b.jpg', 'https://www.lamborghini.com/cn-en', 13, 0, '2024-6-29 00:00:00', 0, '2024-6-29 00:00:00', 0);



CREATE TABLE `tb_carb_mall_cars_category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `category_level` tinyint(4) NOT NULL DEFAULT 0 COMMENT '分类级别(1-一级分类 2-二级分类 3-三级分类)',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父分类id',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `category_rank` int(11) NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user` int(11) NOT NULL DEFAULT 0 COMMENT '修改者id',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=DYNAMIC;

-- 一级分类
INSERT INTO `tb_carb_mall_cars_category` VALUES (1, 1, 0, 'Compact cars', 0, 0, '2024-06-24 18:47:38', 0, '2024-06-24 18:47:38', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (2, 1, 0, 'Mid-size cars', 0, 0, '2024-06-24 18:47:38', 0, '2024-06-24 18:47:38', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (3, 1, 0, 'Small SUVs', 0, 0, '2024-06-24 18:47:49', 0, '2024-06-24 18:47:49', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (4, 1, 0, 'Large SUVs', 0, 0, '2024-06-24 18:47:49', 0, '2024-06-24 18:47:49', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (5, 1, 0, 'Light Trucks', 0, 0, '2024-06-24 18:47:58', 0, '2024-06-24 18:47:58', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (6, 1, 0, 'Heavy Trucks', 0, 0, '2024-06-24 18:47:58', 0, '2024-06-24 18:47:58', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (7, 1, 0, 'Luxury cars', 0, 0, '2024-06-24 18:48:00', 0, '2024-06-24 18:48:00', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (8, 1, 0, 'Sports cars', 0, 0, '2024-06-24 18:48:00', 0, '2024-06-24 18:48:00', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (9, 1, 0, 'Supercars', 0, 0, '2024-06-24 18:48:00', 0, '2024-06-24 18:48:00', 0);

-- 二级分类: 汽车品牌
-- Compact cars brands
INSERT INTO `tb_carb_mall_cars_category` VALUES (10, 2, 1, 'Toyota', 0, 0, '2024-06-24 18:48:38', 0, '2024-06-24 18:48:38', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (11, 2, 1, 'Honda', 0, 0, '2024-06-24 18:48:49', 0, '2024-06-24 18:48:49', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (12, 2, 1, 'Ford', 0, 0, '2024-06-24 18:48:58', 0, '2024-06-24 18:48:58', 0);

-- Mid-size cars brands
INSERT INTO `tb_carb_mall_cars_category` VALUES (13, 2, 2, 'Toyota', 0, 0, '2024-06-24 18:48:38', 0, '2024-06-24 18:48:38', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (14, 2, 2, 'Honda', 0, 0, '2024-06-24 18:48:49', 0, '2024-06-24 18:48:49', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (15, 2, 2, 'Ford', 0, 0, '2024-06-24 18:48:58', 0, '2024-06-24 18:48:58', 0);

-- Small SUVs brands
INSERT INTO `tb_carb_mall_cars_category` VALUES (16, 2, 3, 'BMW', 0, 0, '2024-06-24 18:49:06', 0, '2024-06-24 18:49:06', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (17, 2, 3, 'Mercedes-Benz', 0, 0, '2024-06-24 18:49:12', 0, '2024-06-24 18:49:12', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (18, 2, 3, 'Audi', 0, 0, '2024-06-24 18:49:26', 0, '2024-06-24 18:49:26', 0);

-- Large SUVs brands
INSERT INTO `tb_carb_mall_cars_category` VALUES (19, 2, 4, 'BMW', 0, 0, '2024-06-24 18:49:06', 0, '2024-06-24 18:49:06', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (20, 2, 4, 'Mercedes-Benz', 0, 0, '2024-06-24 18:49:12', 0, '2024-06-24 18:49:12', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (21, 2, 4, 'Audi', 0, 0, '2024-06-24 18:49:26', 0, '2024-06-24 18:49:26', 0);

-- Light Trucks brands
INSERT INTO `tb_carb_mall_cars_category` VALUES (22, 2, 5, 'Ford', 0, 0, '2024-06-24 18:49:40', 0, '2024-06-24 18:49:40', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (23, 2, 5, 'Dodge', 0, 0, '2024-06-24 18:49:50', 0, '2024-06-24 18:49:50', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (24, 2, 5, 'Toyota', 0, 0, '2024-06-24 18:49:58', 0, '2024-06-24 18:49:58', 0);

-- Heavy Trucks brands
INSERT INTO `tb_carb_mall_cars_category` VALUES (25, 2, 6, 'Ford', 0, 0, '2024-06-24 18:49:40', 0, '2024-06-24 18:49:40', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (26, 2, 6, 'Dodge', 0, 0, '2024-06-24 18:49:50', 0, '2024-06-24 18:49:50', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (27, 2, 6, 'Toyota', 0, 0, '2024-06-24 18:49:58', 0, '2024-06-24 18:49:58', 0);

-- Luxury cars brands
INSERT INTO `tb_carb_mall_cars_category` VALUES (28, 2, 7, 'Ferrari', 0, 0, '2024-06-24 18:50:06', 0, '2024-06-24 18:50:06', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (29, 2, 7, 'Lamborghini', 0, 0, '2024-06-24 18:50:14', 0, '2024-06-24 18:50:14', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (30, 2, 7, 'McLaren', 0, 0, '2024-06-24 18:50:22', 0, '2024-06-24 18:50:22', 0);

-- Sports cars brands
INSERT INTO `tb_carb_mall_cars_category` VALUES (31, 2, 8, 'Ferrari', 0, 0, '2024-06-24 18:50:06', 0, '2024-06-24 18:50:06', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (32, 2, 8, 'Lamborghini', 0, 0, '2024-06-24 18:50:14', 0, '2024-06-24 18:50:14', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (33, 2, 8, 'McLaren', 0, 0, '2024-06-24 18:50:22', 0, '2024-06-24 18:50:22', 0);

-- Supercars brands
INSERT INTO `tb_carb_mall_cars_category` VALUES (34, 2, 9, 'Ferrari', 0, 0, '2024-06-24 18:50:06', 0, '2024-06-24 18:50:06', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (35, 2, 9, 'Lamborghini', 0, 0, '2024-06-24 18:50:14', 0, '2024-06-24 18:50:14', 0);
INSERT INTO `tb_carb_mall_cars_category` VALUES (36, 2, 9, 'McLaren', 0, 0, '2024-06-24 18:50:22', 0, '2024-06-24 18:50:22', 0);

-- 三级分类: 具体型号
-- Toyota (Compact cars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (37, 3, 10, 'Toyota Corolla', 0, 0, '2024-06-24 18:51:49', 0, '2024-06-24 18:51:49', 0);

-- Honda (Compact cars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (38, 3, 11, 'Honda Civic', 0, 0, '2024-06-24 18:52:14', 0, '2024-06-24 18:52:14', 0);

-- Ford (Compact cars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (39, 3, 12, 'Ford Focus', 0, 0, '2024-06-24 18:52:38', 0, '2024-06-24 18:52:38', 0);

-- Toyota (Mid-size cars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (40, 3, 13, 'Toyota Camry', 0, 0, '2024-06-24 18:51:58', 0, '2024-06-24 18:51:58', 0);

-- Honda (Mid-size cars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (41, 3, 14, 'Honda Accord', 0, 0, '2024-06-24 18:52:22', 0, '2024-06-24 18:52:22', 0);

-- Ford (Mid-size cars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (42, 3, 15, 'Ford Fusion', 0, 0, '2024-06-24 18:52:46', 0, '2024-06-24 18:52:46', 0);

-- BMW (Small SUVs)
INSERT INTO `tb_carb_mall_cars_category` VALUES (43, 3, 16, 'BMW X1', 0, 0, '2024-06-24 18:52:54', 0, '2024-06-24 18:52:54', 0);

-- Mercedes-Benz (Small SUVs)
INSERT INTO `tb_carb_mall_cars_category` VALUES (44, 3, 17, 'Mercedes-Benz GLA', 0, 0, '2024-06-24 18:53:18', 0, '2024-06-24 18:53:18', 0);

-- Audi (Small SUVs)
INSERT INTO `tb_carb_mall_cars_category` VALUES (45, 3, 18, 'Audi Q3', 0, 0, '2024-06-24 18:53:42', 0, '2024-06-24 18:53:42', 0);

-- BMW (Large SUVs)
INSERT INTO `tb_carb_mall_cars_category` VALUES (46, 3, 19, 'BMW X5', 0, 0, '2024-06-24 18:53:10', 0, '2024-06-24 18:53:10', 0);

-- Mercedes-Benz (Large SUVs)
INSERT INTO `tb_carb_mall_cars_category` VALUES (47, 3, 20, 'Mercedes-Benz GLS', 0, 0, '2024-06-24 18:53:34', 0, '2024-06-24 18:53:34', 0);

-- Audi (Large SUVs)
INSERT INTO `tb_carb_mall_cars_category` VALUES (48, 3, 21, 'Audi Q7', 0, 0, '2024-06-24 18:53:58', 0, '2024-06-24 18:53:58', 0);

-- Ford (Light Trucks)
INSERT INTO `tb_carb_mall_cars_category` VALUES (49, 3, 22, 'Ford F-150', 0, 0, '2024-06-24 18:54:06', 0, '2024-06-24 18:54:06', 0);

-- Dodge (Light Trucks)
INSERT INTO `tb_carb_mall_cars_category` VALUES (50, 3, 23, 'Dodge Ram 1500', 0, 0, '2024-06-24 18:54:22', 0, '2024-06-24 18:54:22', 0);

-- Toyota (Light Trucks)
INSERT INTO `tb_carb_mall_cars_category` VALUES (51, 3, 24, 'Toyota Tacoma', 0, 0, '2024-06-24 18:54:46', 0, '2024-06-24 18:54:46', 0);

-- Ford (Heavy Trucks)
INSERT INTO `tb_carb_mall_cars_category` VALUES (52, 3, 25, 'Ford Super Duty', 0, 0, '2024-06-24 18:54:14', 0, '2024-06-24 18:54:14', 0);

-- Dodge (Heavy Trucks)
INSERT INTO `tb_carb_mall_cars_category` VALUES (53, 3, 26, 'Dodge Ram 2500', 0, 0, '2024-06-24 18:54:30', 0, '2024-06-24 18:54:30', 0);

-- Toyota (Heavy Trucks)
INSERT INTO `tb_carb_mall_cars_category` VALUES (54, 3, 27, 'Toyota Tundra', 0, 0, '2024-06-24 18:54:38', 0, '2024-06-24 18:54:38', 0);

-- Ferrari (Luxury cars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (55, 3, 28, 'Ferrari 488', 0, 0, '2024-06-24 18:55:30', 0, '2024-06-24 18:55:30', 0);

-- Lamborghini (Luxury cars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (56, 3, 29, 'Lamborghini Huracan', 0, 0, '2024-06-24 18:55:38', 0, '2024-06-24 18:55:38', 0);

-- McLaren (Luxury cars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (57, 3, 30, 'McLaren 720S', 0, 0, '2024-06-24 18:55:46', 0, '2024-06-24 18:55:46', 0);

-- Ferrari (Sports cars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (58, 3, 31, 'Ferrari Portofino', 0, 0, '2024-06-24 18:55:54', 0, '2024-06-24 18:55:54', 0);

-- Lamborghini (Sports cars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (59, 3, 32, 'Lamborghini Aventador', 0, 0, '2024-06-24 18:56:02', 0, '2024-06-24 18:56:02', 0);

-- McLaren (Sports cars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (60, 3, 33, 'McLaren P1', 0, 0, '2024-06-24 18:56:10', 0, '2024-06-24 18:56:10', 0);

-- Ferrari (Supercars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (61, 3, 34, 'Ferrari LaFerrari', 0, 0, '2024-06-24 18:56:18', 0, '2024-06-24 18:56:18', 0);

-- Lamborghini (Supercars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (62, 3, 35, 'Lamborghini Veneno', 0, 0, '2024-06-24 18:56:26', 0, '2024-06-24 18:56:26', 0);

-- McLaren (Supercars)
INSERT INTO `tb_carb_mall_cars_category` VALUES (63, 3, 36, 'McLaren Senna', 0, 0, '2024-06-24 18:56:34', 0, '2024-06-24 18:56:34', 0);
--

DROP TABLE IF EXISTS `tb_carb_mall_cars_info`;
CREATE TABLE `tb_carb_mall_cars_info`  (
  `cars_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品表主键id',
  `cars_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名',
  `cars_intro` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品简介',
  `cars_category_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联分类id',
  `cars_cover_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/admin/dist/img/no-img.png' COMMENT '商品主图',
  `cars_carousel` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/admin/dist/img/no-img.png' COMMENT '商品轮播图',
  `cars_detail_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品详情',
  `original_price` int(11) NOT NULL DEFAULT 1 COMMENT '商品价格',
  `selling_price` int(11) NOT NULL DEFAULT 1 COMMENT '商品实际售价',
  `stock_num` int(11) NOT NULL DEFAULT 0 COMMENT '商品库存数量',
  `tag` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品标签',
  `cars_sell_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '商品上架状态 0-上架 1-下架',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '添加者主键id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商品添加时间',
  `update_user` int(11) NOT NULL DEFAULT 0 COMMENT '修改者主键id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商品修改时间',
  PRIMARY KEY (`cars_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- ----------------------------
-- Compact cars
INSERT INTO `tb_carb_mall_cars_info` (cars_name, cars_intro, cars_category_id, cars_cover_img, cars_carousel, cars_detail_content, original_price, selling_price, stock_num, tag, cars_sell_status, create_user)
VALUES
('Toyota Corolla', 'Toyota Compact Car', 37, '/images/game1.png', '/images/toyota_corolla_1.jpg,/images/toyota_corolla_2.jpg', 'The Toyota Corolla is a compact car.', 20000, 18000, 50, 'Compact', 0,1),
('Honda Civic', 'Honda Compact Car', 38, '/images/honda_civic.jpg', '/images/honda_civic_1.jpg,/images/honda_civic_2.jpg', 'The Honda Civic is a compact car.', 22000, 20000, 50, 'Compact', 0, 1),
('Ford Focus', 'Ford Compact Car', 39, '/images/ford_focus.jpg', '/images/ford_focus_1.jpg,/images/ford_focus_2.jpg', 'The Ford Focus is a compact car.', 21000, 19000, 50, 'Compact', 0, 1);

-- Mid-size cars
INSERT INTO `tb_carb_mall_cars_info` (cars_name, cars_intro, cars_category_id, cars_cover_img, cars_carousel, cars_detail_content, original_price, selling_price, stock_num, tag, cars_sell_status, create_user)
VALUES
('Toyota Camry', 'long long detail', 40, '/images/game1.png', '/images/game1.png,/images/toyota_camry_2.jpg', 'The Toyota Camry is a mid-size car.', 25000, 23000, 50, 'Mid-size', 0, 1),
('Honda Accord', 'Honda Mid-size Car', 41, '/images/honda_accord.jpg', '/images/honda_accord_1.jpg,/images/honda_accord_2.jpg', 'The Honda Accord is a mid-size car.', 26000, 24000, 50, 'Mid-size', 0, 1),
('Ford Fusion', 'Ford Mid-size Car', 42, '/images/ford_fusion.jpg', '/images/ford_fusion_1.jpg,/images/ford_fusion_2.jpg', 'The Ford Fusion is a mid-size car.', 27000, 25000, 50, 'Mid-size', 0, 1);

-- Small SUVs
INSERT INTO `tb_carb_mall_cars_info` (cars_name, cars_intro, cars_category_id, cars_cover_img, cars_carousel, cars_detail_content, original_price, selling_price, stock_num, tag, cars_sell_status, create_user)
VALUES
('BMW X1', 'BMW Small SUV', 43, '/images/bmw_x1.jpg', '/images/bmw_x1_1.jpg,/images/bmw_x1_2.jpg', 'The BMW X1 is a small SUV.', 35000, 33000, 50, 'SUV', 0, 1),
('Mercedes-Benz GLA', 'Mercedes-Benz Small SUV', 44, '/images/mercedes_benz_gla.jpg', '/images/mercedes_benz_gla_1.jpg,/images/mercedes_benz_gla_2.jpg', 'The Mercedes-Benz GLA is a small SUV.', 37000, 35000, 50, 'SUV', 0, 1),
('Audi Q3', 'Audi Small SUV', 45, '/images/audi_q3.jpg', '/images/audi_q3_1.jpg,/images/audi_q3_2.jpg', 'The Audi Q3 is a small SUV.', 36000, 34000, 50, 'SUV', 0, 1);

-- Large SUVs
INSERT INTO `tb_carb_mall_cars_info` (cars_name, cars_intro, cars_category_id, cars_cover_img, cars_carousel, cars_detail_content, original_price, selling_price, stock_num, tag, cars_sell_status, create_user)
VALUES
('BMW X5', 'BMW Large SUV', 46, '/images/bmw_x5.jpg', '/images/bmw_x5_1.jpg,/images/bmw_x5_2.jpg', 'The BMW X5 is a large SUV.', 60000, 58000, 50, 'SUV', 0, 1),
('Mercedes-Benz GLS', 'Mercedes-Benz Large SUV', 47, '/images/mercedes_benz_gls.jpg', '/images/mercedes_benz_gls_1.jpg,/images/mercedes_benz_gls_2.jpg', 'The Mercedes-Benz GLS is a large SUV.', 65000, 63000, 50, 'SUV', 0, 1),
('Audi Q7', 'Audi Large SUV', 48, '/images/audi_q7.jpg', '/images/audi_q7_1.jpg,/images/audi_q7_2.jpg', 'The Audi Q7 is a large SUV.', 64000, 62000, 50, 'SUV', 0, 1);

-- Light Trucks
INSERT INTO `tb_carb_mall_cars_info` (cars_name, cars_intro, cars_category_id, cars_cover_img, cars_carousel, cars_detail_content, original_price, selling_price, stock_num, tag, cars_sell_status, create_user)
VALUES
('Ford F-150', 'Ford Light Truck', 49, '/images/ford_f150.jpg', '/images/ford_f150_1.jpg,/images/ford_f150_2.jpg', 'The Ford F-150 is a light truck.', 30000, 28000, 50, 'Truck', 0, 1),
('Dodge Ram 1500', 'Dodge Light Truck', 50, '/images/dodge_ram_1500.jpg', '/images/dodge_ram_1500_1.jpg,/images/dodge_ram_1500_2.jpg', 'The Dodge Ram 1500 is a light truck.', 32000, 30000, 50, 'Truck', 0, 1),
('Toyota Tacoma', 'Toyota Light Truck', 51, '/images/toyota_tacoma.jpg', '/images/toyota_tacoma_1.jpg,/images/toyota_tacoma_2.jpg', 'The Toyota Tacoma is a light truck.', 31000, 29000, 50, 'Truck', 0, 1);

-- Heavy Trucks
INSERT INTO `tb_carb_mall_cars_info` (cars_name, cars_intro, cars_category_id, cars_cover_img, cars_carousel, cars_detail_content, original_price, selling_price, stock_num, tag, cars_sell_status, create_user)
VALUES
('Ford Super Duty', 'Ford Heavy Truck', 52, '/images/ford_super_duty.jpg', '/images/ford_super_duty_1.jpg,/images/ford_super_duty_2.jpg', 'The Ford Super Duty is a heavy truck.', 50000, 48000, 50, 'Truck', 0, 1),
('Dodge Ram 2500', 'Dodge Heavy Truck', 53, '/images/dodge_ram_2500.jpg', '/images/dodge_ram_2500_1.jpg,/images/dodge_ram_2500_2.jpg', 'The Dodge Ram 2500 is a heavy truck.', 52000, 50000, 50, 'Truck', 0, 1),
('Toyota Tundra', 'Toyota Heavy Truck', 54, '/images/toyota_tundra.jpg', '/images/toyota_tundra_1.jpg,/images/toyota_tundra_2.jpg', 'The Toyota Tundra is a heavy truck.', 51000, 49000, 50, 'Truck', 0, 1);

-- Luxury cars
INSERT INTO `tb_carb_mall_cars_info` (cars_name, cars_intro, cars_category_id, cars_cover_img, cars_carousel, cars_detail_content, original_price, selling_price, stock_num, tag, cars_sell_status, create_user)
VALUES
('Ferrari 488', 'Ferrari Luxury Car', 55, '/images/ferrari_488.jpg', '/images/ferrari_488_1.jpg,/images/ferrari_488_2.jpg', 'The Ferrari 488 is a luxury car.', 250000, 240000, 10, 'Luxury', 0, 1),
('Lamborghini Huracan', 'Lamborghini Luxury Car', 56, '/images/lamborghini_huracan.jpg', '/images/lamborghini_huracan_1.jpg,/images/lamborghini_huracan_2.jpg', 'The Lamborghini Huracan is a luxury car.', 260000, 250000, 10, 'Luxury', 0, 1),
('McLaren 720S', 'McLaren Luxury Car', 57, '/images/mclaren_720s.jpg', '/images/mclaren_720s_1.jpg,/images/mclaren_720s_2.jpg', 'The McLaren 720S is a luxury car.', 270000, 260000, 10, 'Luxury', 0, 1);
-- Sports cars

INSERT INTO `tb_carb_mall_cars_info` (cars_name, cars_intro, cars_category_id, cars_cover_img, cars_carousel, cars_detail_content, original_price, selling_price, stock_num, tag, cars_sell_status, create_user)
VALUES
('Ferrari Portofino', 'Ferrari Sports Car', 58, '/images/ferrari_portofino.jpg', '/images/ferrari_portofino_1.jpg,/images/ferrari_portofino_2.jpg', 'The Ferrari Portofino is a sports car.', 220000, 210000, 10, 'Sports', 0, 1),
('Lamborghini Aventador', 'Lamborghini Sports Car', 59, '/images/lamborghini_aventador.jpg', '/images/lamborghini_aventador_1.jpg,/images/lamborghini_aventador_2.jpg', 'The Lamborghini Aventador is a sports car.', 230000, 220000, 10, 'Sports', 0, 1),
('McLaren P1', 'McLaren Sports Car', 60, '/images/mclaren_p1.jpg', '/images/mclaren_p1_1.jpg,/images/mclaren_p1_2.jpg', 'The McLaren P1 is a sports car.', 240000, 230000, 10, 'Sports', 0, 1);

-- Supercars
INSERT INTO `tb_carb_mall_cars_info` (cars_name, cars_intro, cars_category_id, cars_cover_img, cars_carousel, cars_detail_content, original_price, selling_price, stock_num, tag, cars_sell_status, create_user)

VALUES
('Ferrari LaFerrari', 'Ferrari Supercar', 61, '/images/ferrari_laferrari.jpg', '/images/ferrari_laferrari_1.jpg,/images/ferrari_laferrari_2.jpg', 'The Ferrari LaFerrari is a supercar.', 350000, 340000, 5, 'Supercar', 0, 1),
('Lamborghini Veneno', 'Lamborghini Supercar', 62, '/images/lamborghini_veneno.jpg', '/images/lamborghini_veneno_1.jpg,/images/lamborghini_veneno_2.jpg', 'The Lamborghini Veneno is a supercar.', 360000, 350000, 5, 'Supercar', 0, 1),
('McLaren Senna', 'McLaren Supercar', 63, '/cars-img/toyota corolla.jpg', '/images/mclaren_senna_1.jpg,/images/mclaren_senna_2.jpg','<p>商品介绍加载中...</p>', 370000, 360000, 5, 'Supercar', 0, 1);


-- ----------------------------

-- ----------------------------
DROP TABLE IF EXISTS `tb_carb_mall_index_config`;
CREATE TABLE `tb_carb_mall_index_config`  (
  `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '首页配置项主键id',
  `config_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '显示字符(配置搜索时不可为空，其他可为空)',
  `config_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '1-搜索框热搜 2-搜索下拉框热搜 3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐',
  `cars_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '商品id 默认为0',
  `redirect_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '##' COMMENT '点击后的跳转地址(默认不跳转)',
  `config_rank` int(11) NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '修改者id',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- ----------------------------
-- Inserting data into tb_carb_mall_index_config table
INSERT INTO `tb_carb_mall_index_config` (config_name, config_type, cars_id, redirect_url, config_rank, is_deleted, create_time, create_user, update_time, update_user) VALUES
('HOT SEARCH McLaren Senna', 1, 10925, '/images/toyota_camry.jpg', 10, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('HOT SEARCH Lamborghini Veneno', 2, 10924, '/images/toyota_camry.jpg', 10, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
-- 热销商品
('HOT Ferrari LaFerrari', 3, 10923, '/images/toyota_camry.jpg', 10, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('HOT McLaren P1', 3, 10922, '/images/toyota_camry.jpg', 10, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('HOT Lamborghini Aventador', 3, 10921, '/images/toyota_camry.jpg', 10, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('HOT Ferrari Portofino', 3, 10920, '/images/toyota_camry.jpg', 10, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('HOT McLaren 720S', 3, 10919, '/images/toyota_camry.jpg', 10, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
-- 新品上线
('NEW Toyota Corolla', 4, 10896, '/images/toyota_camry.jpg', 40, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('NEW Honda Civic', 4, 10897, '/images/toyota_camry.jpg', 40, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('NEW Ford Focus', 4, 10898, '/images/toyota_camry.jpg', 40, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('NEW Toyota Camry', 4, 10003, '/images/game1.png', 40, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('NEW Honda Accord', 4, 10900, '/images/toyota_camry.jpg', 40, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
-- 为你推荐
('RECOMMEND BMW X5', 5, 10905, '/images/bmw_x1.jpg', 70, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('RECOMMEND Audi Q3', 5, 10904, '/images/bmw_x1.jpg', 70, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('RECOMMEND Mercedes-Benz GLA', 5, 10903, '/images/bmw_x1.jpg', 70, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('RECOMMEND BMW X1', 5, 10902, '/images/bmw_x1.jpg', 70, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('RECOMMEND Mercedes-Benz GLS', 5, 10906, '/images/bmw_x1.jpg', 70, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('RECOMMEND Audi Q7', 5, 10907, '/images/bmw_x1.jpg', 70, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('RECOMMEND Ford F-150', 5, 10908, '/images/bmw_x1.jpg', 70, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('RECOMMEND Dodge Ram 1500', 5, 10909, '/images/bmw_x1.jpg', 70, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('RECOMMEND Toyota Tacoma', 5, 10910, '/images/bmw_x1.jpg', 70, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1),
('RECOMMEND Ford Super Duty', 5, 10911, '/images/bmw_x1.jpg', 70, 0, '2024-06-24 18:57:00', 1, '2024-06-24 18:57:00', 1);


DROP TABLE IF EXISTS `tb_carb_mall_order`;
CREATE TABLE `tb_carb_mall_order`  (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单表主键id',
  `order_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户主键id',
  `total_price` int(11) NOT NULL DEFAULT 1 COMMENT '订单总价',
  `pay_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '支付状态:0.未支付,1.支付成功,-1:支付失败',
  `pay_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0.无 1.支付宝支付 2.微信支付',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `order_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭',
  `extra_info` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '订单body',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人手机号',
  `user_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人收货地址',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `tb_carb_mall_order_item`;
CREATE TABLE `tb_carb_mall_order_item`  (
  `order_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单关联购物项主键id',
  `order_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '订单主键id',
  `cars_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联商品id',
  `cars_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '下单时商品的名称(订单快照)',
  `cars_cover_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '下单时商品的主图(订单快照)',
  `selling_price` int(11) NOT NULL DEFAULT 1 COMMENT '下单时商品的价格(订单快照)',
  `cars_count` int(11) NOT NULL DEFAULT 1 COMMENT '数量(订单快照)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`order_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
INSERT INTO `tb_carb_mall_order_item` VALUES (1, 1, 10905, 'Ferrari 488', '/cars-img/64768a8d-0664-4b29-88c9-2626578ffbd1.jpg', 240000, 2, '2024-06-24 22:53:07');


-- ----------------------------
DROP TABLE IF EXISTS `tb_carb_mall_shopping_cart_item`;
CREATE TABLE `tb_carb_mall_shopping_cart_item`  (
  `cart_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物项主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `cars_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联商品id',
  `cars_count` int(11) NOT NULL DEFAULT 1 COMMENT '数量(最大为5)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
  PRIMARY KEY (`cart_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
DROP TABLE IF EXISTS `tb_carb_mall_user`;
CREATE TABLE `tb_carb_mall_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称',
  `login_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '登陆名称(默认为手机号)',
  `password_md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'MD5加密后的密码',
  `introduce_sign` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '个性签名',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货地址',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '注销标识字段(0-正常 1-已注销)',
  `locked_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '锁定标识字段(0-未锁定 1-已锁定)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


INSERT INTO `tb_carb_mall_user` VALUES (1, 'LK', '13700002703', 'e10adc3949ba59abbe56e057f20f883e', 'ababababab', 'cd', 0, 0, '2024-06-22 08:44:57');
INSERT INTO `tb_carb_mall_user` VALUES (2, 'Wong', '0176787767', '96e79218965eb72c92a549dd5a330112', 'hello world', 'cd', 0, 0, '2024-07-07 11:57:36');