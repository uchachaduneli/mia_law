/*
 Navicat Premium Data Transfer

 Source Server         : mia_law
 Source Server Type    : PostgreSQL
 Source Server Version : 90519
 Source Host           : 185.51.101.11:5432
 Source Catalog        : lawyers
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90519
 File Encoding         : 65001

 Date: 11/03/2020 20:51:38
*/


-- ----------------------------
-- Sequence structure for board_board_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."board_board_id_seq";
CREATE SEQUENCE "public"."board_board_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for case_case_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."case_case_id_seq";
CREATE SEQUENCE "public"."case_case_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for case_doc_doc_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."case_doc_doc_id_seq";
CREATE SEQUENCE "public"."case_doc_doc_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for court_court_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."court_court_id_seq";
CREATE SEQUENCE "public"."court_court_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for court_instance_instance_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."court_instance_instance_id_seq";
CREATE SEQUENCE "public"."court_instance_instance_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for end_result_end_result_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."end_result_end_result_id_seq";
CREATE SEQUENCE "public"."end_result_end_result_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for judge_judge_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."judge_judge_id_seq";
CREATE SEQUENCE "public"."judge_judge_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for litigation_subject_litigation_subject_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."litigation_subject_litigation_subject_id_seq";
CREATE SEQUENCE "public"."litigation_subject_litigation_subject_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for status_status_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."status_status_id_seq";
CREATE SEQUENCE "public"."status_status_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for user_status_status_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."user_status_status_id_seq";
CREATE SEQUENCE "public"."user_status_status_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for user_type_type_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."user_type_type_id_seq";
CREATE SEQUENCE "public"."user_type_type_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for user_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."user_user_id_seq";
CREATE SEQUENCE "public"."user_user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for board
-- ----------------------------
DROP TABLE IF EXISTS "public"."board";
CREATE TABLE "public"."board" (
  "board_id" int4 NOT NULL DEFAULT nextval('board_board_id_seq'::regclass),
  "name" varchar(300) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON TABLE "public"."board" IS 'კოლეგია';

-- ----------------------------
-- Records of board
-- ----------------------------
INSERT INTO "public"."board" VALUES (2, 'სამოქალაქო');
INSERT INTO "public"."board" VALUES (3, 'ადმინისტრაციული');

-- ----------------------------
-- Table structure for case
-- ----------------------------
DROP TABLE IF EXISTS "public"."case";
CREATE TABLE "public"."case" (
  "case_id" int4 NOT NULL DEFAULT nextval('case_case_id_seq'::regclass),
  "name" varchar(500) COLLATE "pg_catalog"."default" NOT NULL,
  "number" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "judge_id" int4 NOT NULL,
  "case_start_date" date,
  "litigation_subject_id" int4 NOT NULL,
  "litigation_description" varchar(1000) COLLATE "pg_catalog"."default",
  "end_result_id" int4,
  "case_end_date" date,
  "note" varchar(1000) COLLATE "pg_catalog"."default",
  "court_id" int4 NOT NULL,
  "status_id" int4 NOT NULL DEFAULT 1,
  "court_instance_id" int4,
  "add_user_id" int4 NOT NULL,
  "litigation_price" float8,
  "group_id" varchar COLLATE "pg_catalog"."default",
  "board_id" int4,
  "third_persons" varchar(1000) COLLATE "pg_catalog"."default",
  "ministry_status" int4,
  "expire_date" date,
  "resolution" varchar COLLATE "pg_catalog"."default",
  "expire_note" varchar COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of case
-- ----------------------------
INSERT INTO "public"."case" VALUES (484, 'ლეილა გაბუნია (გ.ჩუთლაშვილი)', '3ბ/2543-17', 164, '2017-06-26', 14, 'პოლიციის შესახებ კანონის 50-ე მუხლის 1 პრიმა პუნქტით კომპენსაციის დანიშვნა', 7, NULL, NULL, 2, 2, 1, 6, 100000, '180216062044244', 3, NULL, 1, NULL, NULL, NULL);
INSERT INTO "public"."case" VALUES (485, 'ალეკო', '654654', 53, '2020-03-03', 2, '6565', 7, '2020-03-05', NULL, 1, 2, 1, 110, 1, '200310033525325', 2, NULL, 1, NULL, NULL, NULL);
INSERT INTO "public"."case" VALUES (486, 'ოტარი', '1/44', 56, '2020-02-25', 1, '123', 7, '2020-02-26', NULL, 1, 2, 1, 102, 123, '200310035922322', 2, NULL, 1, NULL, '', NULL);

-- ----------------------------
-- Table structure for case_doc
-- ----------------------------
DROP TABLE IF EXISTS "public"."case_doc";
CREATE TABLE "public"."case_doc" (
  "doc_id" int4 NOT NULL DEFAULT nextval('case_doc_doc_id_seq'::regclass),
  "case_id" int4 NOT NULL,
  "name" varchar COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Table structure for court
-- ----------------------------
DROP TABLE IF EXISTS "public"."court";
CREATE TABLE "public"."court" (
  "court_id" int4 NOT NULL DEFAULT nextval('court_court_id_seq'::regclass),
  "name" varchar(200) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of court
-- ----------------------------
INSERT INTO "public"."court" VALUES (1, 'თბილისის საქალაქო სასამართლო');
INSERT INTO "public"."court" VALUES (2, 'თბილისის სააპელაციო სასამართლო');
INSERT INTO "public"."court" VALUES (4, 'ბათუმის საქალაქო სასამართლო');
INSERT INTO "public"."court" VALUES (3, 'საქართველოს უზენაესი სასამართლო');
INSERT INTO "public"."court" VALUES (5, 'ქუთაისის საქალაქო სასამართლო');
INSERT INTO "public"."court" VALUES (6, 'ზუგდიდის რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (7, 'რუსთავის საქალაქო სასამართლო');
INSERT INTO "public"."court" VALUES (8, 'ფოთის საქალაქო სასამართლო');
INSERT INTO "public"."court" VALUES (9, 'ქუთაისის სააპელაციო სასამართლო');
INSERT INTO "public"."court" VALUES (10, 'ზესთაფონის რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (11, 'სენაკის რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (12, 'ახალციხის რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (13, 'გორის რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (14, 'თელავის რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (15, 'სიღნაღის რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (16, 'თეთრიწყაროს რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (17, 'ბოლნისის რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (18, 'ხონის მაგისტრატი სასამართლო');
INSERT INTO "public"."court" VALUES (19, 'სამტრედიის რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (20, 'ქარელის მაგისტრატი სასამართლო');
INSERT INTO "public"."court" VALUES (21, 'ოზურგეთის რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (22, 'საჩხერის რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (23, 'მცხეთის რაიონული სასამართლო');
INSERT INTO "public"."court" VALUES (24, 'მარტვილის მაგისტრატი სასამართლო');
INSERT INTO "public"."court" VALUES (25, 'ახმეტის მაგისტრატი სასამართლო');
INSERT INTO "public"."court" VALUES (26, 'დმანისის მაგისტრატი სასამართლო');
INSERT INTO "public"."court" VALUES (27, 'ონის მაგისტრატი სასამართლო');
INSERT INTO "public"."court" VALUES (28, 'ონის მაგისტრატი სასამართლო');
INSERT INTO "public"."court" VALUES (29, 'დმანისის მაგისტრატი სასამართლო');

-- ----------------------------
-- Table structure for court_instance
-- ----------------------------
DROP TABLE IF EXISTS "public"."court_instance";
CREATE TABLE "public"."court_instance" (
  "instance_id" int4 NOT NULL DEFAULT nextval('court_instance_instance_id_seq'::regclass),
  "name" varchar(500) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of court_instance
-- ----------------------------
INSERT INTO "public"."court_instance" VALUES (1, 'პირველი ინსტანცია');
INSERT INTO "public"."court_instance" VALUES (2, 'მეორე ინსტანცია');
INSERT INTO "public"."court_instance" VALUES (3, 'მესამე ინსტანცია');

-- ----------------------------
-- Table structure for end_result
-- ----------------------------
DROP TABLE IF EXISTS "public"."end_result";
CREATE TABLE "public"."end_result" (
  "end_result_id" int4 NOT NULL DEFAULT nextval('end_result_end_result_id_seq'::regclass),
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of end_result
-- ----------------------------
INSERT INTO "public"."end_result" VALUES (1, 'სარჩელი დაკმაყოფილდა');
INSERT INTO "public"."end_result" VALUES (2, 'სარჩელი არ დაკმაყოფილდა');
INSERT INTO "public"."end_result" VALUES (3, 'სარჩელი დაკმაყოფილდა ნაწილობრივ');
INSERT INTO "public"."end_result" VALUES (4, 'სააპალციო საჩივარი დაკმაყოფილდა');
INSERT INTO "public"."end_result" VALUES (5, 'სააპელაციო საჩივარი არ დაკმაყოფილდა');
INSERT INTO "public"."end_result" VALUES (6, 'სააპალციო საჩივარი დაკმაყოფილდა ნაწილობრივ');
INSERT INTO "public"."end_result" VALUES (7, 'მიმდინარე');
INSERT INTO "public"."end_result" VALUES (8, 'საქმე დასრულდა მორიგებით');
INSERT INTO "public"."end_result" VALUES (9, 'განუხილველად დატოვება');
INSERT INTO "public"."end_result" VALUES (10, 'მოპასუხეთა წრიდან ამორიცხვა');
INSERT INTO "public"."end_result" VALUES (11, 'შეწყდა საქმის წარმოება');

-- ----------------------------
-- Table structure for judge
-- ----------------------------
DROP TABLE IF EXISTS "public"."judge";
CREATE TABLE "public"."judge" (
  "judge_id" int4 NOT NULL DEFAULT nextval('judge_judge_id_seq'::regclass),
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "assistant" varchar(100) COLLATE "pg_catalog"."default",
  "assistant_phone" varchar(50) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of judge
-- ----------------------------
INSERT INTO "public"."judge" VALUES (53, 'ვასილ მშვენიერაძე', 'ხატია ჯანანაშვილი', '1464');
INSERT INTO "public"."judge" VALUES (54, 'გიორგი მიქაუტაძე', 'ნინო ჯიქია', '1293');
INSERT INTO "public"."judge" VALUES (55, 'ნათია ტოგონიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (56, 'ლაშა თავართქილაძე', 'სოფიკო წურწუმია:', '1383');
INSERT INTO "public"."judge" VALUES (57, 'ივანე აღნიაშვილი', 'მარიამ ნიაური', '1418');
INSERT INTO "public"."judge" VALUES (58, 'თამარ ხაჟომია', 'სალომე ბერია', '1381');
INSERT INTO "public"."judge" VALUES (59, 'ივანე ღლონტი', 'ნათია ჯაფარიძე', '8803');
INSERT INTO "public"."judge" VALUES (60, 'ლეილა გოგიშვილი', 'იამზე შიშნიაშვილი', '1311');
INSERT INTO "public"."judge" VALUES (61, 'ნანა დარასელია', 'ეკატერინე მინდიაშვილი', '1392');
INSERT INTO "public"."judge" VALUES (62, 'ინგა კვაჭანტირაძე', 'სალომე ხერხაძე', '1380');
INSERT INTO "public"."judge" VALUES (63, 'ნინო ყანჩაველი', 'ლელა გოგოჭური', '1333');
INSERT INTO "public"."judge" VALUES (64, 'ხატია არდაზიშვილი', 'სალომე წიკლაური', '8802');
INSERT INTO "public"."judge" VALUES (65, 'ნათია ბუსკაძე', 'გიული ზოიძე', '1494');
INSERT INTO "public"."judge" VALUES (66, 'ნინო ბუაჩიძე', 'თამუნა გელაშვილი', '1227');
INSERT INTO "public"."judge" VALUES (67, 'ეკატერინე ჯინჭველაშვილი', 'ეკატერინე კვირკველია', '1434');
INSERT INTO "public"."judge" VALUES (68, 'ნანა ჭიჭილეიშვილი', 'ნინო სახელაშვილი', '1306');
INSERT INTO "public"."judge" VALUES (69, 'ნინო ჭალიძე', 'ია ბერიძე', '1305');
INSERT INTO "public"."judge" VALUES (70, 'მერი გულუაშვილი', 'სოფიო აბესაძე', '1308');
INSERT INTO "public"."judge" VALUES (71, 'ლეილა მამულაშვილი', 'მარიამ ჩუბინიძე', '1321');
INSERT INTO "public"."judge" VALUES (72, 'თამარ მეშველიანი', 'იანა ციცქიშვილი', '8801');
INSERT INTO "public"."judge" VALUES (73, 'ნონა ზარქუა', 'კობა წაქაძე', NULL);
INSERT INTO "public"."judge" VALUES (74, 'დიანა ფარქოსაძე', 'გვანცა მარჯანიშვილი', '1304');
INSERT INTO "public"."judge" VALUES (75, 'ანა ჩხეტია', 'ნატო მიქელაშვილი', '1219');
INSERT INTO "public"."judge" VALUES (76, 'დავით წერეთელი', 'მიხეილ მემარნიშვილი', '1327');
INSERT INTO "public"."judge" VALUES (77, 'თამარ ოქროპირიძე', 'ლიანა ბაკურაძე', '8815');
INSERT INTO "public"."judge" VALUES (78, 'ნანა აფციაური', 'რუსუდან იკაშვილი', '1432');
INSERT INTO "public"."judge" VALUES (79, 'ქეთევან მინაშვილი', 'თამარ ხარებაშვილი', '1419');
INSERT INTO "public"."judge" VALUES (80, 'სერგო მეთოფიშვილი', 'ირაკლი ხუსკივაძე:', '1384');
INSERT INTO "public"."judge" VALUES (81, 'გენადი მაკარიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (82, 'ხათუნა ჯინორია', 'ელისო ხუროძე', NULL);
INSERT INTO "public"."judge" VALUES (83, 'ივერი აბაშიძე', 'ნინო ხუხუნაიშვილი', '8827');
INSERT INTO "public"."judge" VALUES (84, 'თინა ვაშაყმაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (85, 'მაია გიგაური', 'მახარაძე ნინო', NULL);
INSERT INTO "public"."judge" VALUES (86, 'ირინა ზარქუა', 'ნინო ცირეკიძე', '7810');
INSERT INTO "public"."judge" VALUES (87, 'ლია ავალიშვილი', 'ანნა ყიზილაშვილი', '1362');
INSERT INTO "public"."judge" VALUES (88, 'თინათინ ეცადაშვილი', 'თამარ ლომსაძე', '1236');
INSERT INTO "public"."judge" VALUES (89, 'გიორგი მაისურაძე', 'ხათუნა შარანგია', '7803');
INSERT INTO "public"."judge" VALUES (90, 'ლილი ტყემალაძე', 'ნინო მეტრეველი', '1263');
INSERT INTO "public"."judge" VALUES (91, 'ლელა ცანავა', 'სოფიკო ქურსუა', '7817');
INSERT INTO "public"."judge" VALUES (92, 'ზაალ მარუაშვილი', 'ზვიად ცეკვავა', '1258');
INSERT INTO "public"."judge" VALUES (93, 'ნინო მამულაშვილი', 'მარიამ ქიტოშვილი', '1256');
INSERT INTO "public"."judge" VALUES (94, 'ვერა დობორჯგინიძე', 'ნუცა შონია', '8816');
INSERT INTO "public"."judge" VALUES (95, 'თეა ბერაია', 'შორენა კონჯარია', '1331');
INSERT INTO "public"."judge" VALUES (96, 'ცისანა სირბილაძე', 'თამარ გავაშელიშვილი', '1235');
INSERT INTO "public"."judge" VALUES (97, 'ქეთევან მამაცაშვილი', 'ანნა ლომიტაშვილი', '1267');
INSERT INTO "public"."judge" VALUES (98, 'ეკატერინე ბიწაძე', 'ანი მეგრელი', '7742');
INSERT INTO "public"."judge" VALUES (99, 'ეკა ზარნაძე', 'მაია შეყრილაძე', '7816');
INSERT INTO "public"."judge" VALUES (100, 'ზაზა მარტიაშვილი', 'ნათია გობეჯიშვილი', '1271');
INSERT INTO "public"."judge" VALUES (101, 'მაია სვიანაძე', 'ნინო ჩაკვეტაძე', '1257');
INSERT INTO "public"."judge" VALUES (102, 'თამარ ლაკერბაია', 'სალომე ჯაბიძე', '1221');
INSERT INTO "public"."judge" VALUES (103, 'ვლადიმერ კაკაბაძე', 'ეკატერინე მაღრაძე', '1322');
INSERT INTO "public"."judge" VALUES (1, 'მისტერ მოსამართლე', 'ასისტენტ ასისტენტაძე', '');
INSERT INTO "public"."judge" VALUES (104, 'შორენა ჯანხოთელი', 'შორენა ხუცურაული', '7751');
INSERT INTO "public"."judge" VALUES (105, 'ელისო ტუკვაძე', 'ეკა აბჟანდაძე', '1246');
INSERT INTO "public"."judge" VALUES (106, 'თამარ ჩიხლაძე', 'ნანა ტაბატაძე', '1279');
INSERT INTO "public"."judge" VALUES (107, 'ლაშა ქოჩიაშვილი', 'ნინო ენუქიძე', '1252');
INSERT INTO "public"."judge" VALUES (108, 'მაკა ჭედია', 'ეკატერინე კახნიაშვილი', '1266');
INSERT INTO "public"."judge" VALUES (109, 'ასმათ კოხრეიძე', 'მარიკა ჩეჩელაშვილი', '1265');
INSERT INTO "public"."judge" VALUES (110, 'შორენა წიქარიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (111, 'ნათია შიოშვილი', 'გურანდა ჟღენტი', '1262');
INSERT INTO "public"."judge" VALUES (112, 'სოსო ღურწკაია', 'ქეთევან ლოთიშვილი', '1249');
INSERT INTO "public"."judge" VALUES (113, 'დალი აბჟანდაძე', 'ხატია ქვრივიშვილი', '7815');
INSERT INTO "public"."judge" VALUES (114, 'თამარ ჭუნიაშვილი', NULL, '1244');
INSERT INTO "public"."judge" VALUES (115, 'ქეთევან კუჭავა', 'ნათია გველესიანი:', '1240');
INSERT INTO "public"."judge" VALUES (116, 'თამარ ბურჯანაძე', 'გიორგი თარიძე', '1280');
INSERT INTO "public"."judge" VALUES (117, 'გიორგი გოგიჩაშვილი', 'ლიკა ბოკერია', '1452');
INSERT INTO "public"."judge" VALUES (118, 'ტარიელ ტაბატაძე', 'თამარ დალაქიშვილი', '1248');
INSERT INTO "public"."judge" VALUES (119, 'ანა ჩოგოვაძე', 'მიხეილ კუხიანიძე', '1264');
INSERT INTO "public"."judge" VALUES (120, 'ხათუნა კაკაბაძე', 'თეა შუშიაშვილი', '1268');
INSERT INTO "public"."judge" VALUES (121, 'მადი ჩანტლაძე', 'თამთა შაგულაშვილი', '1273');
INSERT INTO "public"."judge" VALUES (122, 'ირაკლი კოპალიანი', 'ვალენტინა კიკნაძე', '1243');
INSERT INTO "public"."judge" VALUES (123, 'ზოია კვარაცხელია', 'ხათუნა მამარდაშვილი', '7806');
INSERT INTO "public"."judge" VALUES (124, 'ლევან მიქაბერიძე', 'სალომე ფოფხაძე', '7746');
INSERT INTO "public"."judge" VALUES (125, 'მიხეილ ჩინჩალაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (126, 'მზია ლომთათიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (127, 'მანუჩარ კაპანაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (128, 'ნათია ბარბაქაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (129, 'მაია თეთრაული', NULL, NULL);
INSERT INTO "public"."judge" VALUES (130, 'შორენა ყაველაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (131, 'მურმან ისაევი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (132, 'კახაბერ მაჭავარიანი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (133, 'მარინა ხოლოაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (134, 'რევაზ ნადარაია', NULL, NULL);
INSERT INTO "public"."judge" VALUES (135, 'გერონტი კახეთელიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (136, 'ლევან თევზაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (137, 'მაია სულხანიშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (138, 'ვანო წიკლაური', NULL, NULL);
INSERT INTO "public"."judge" VALUES (139, 'გოგიტა თოთოსაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (140, 'თეა სოხაშვილი-ნიკოლაიშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (141, 'ირაკლი ბონდარენკო', NULL, NULL);
INSERT INTO "public"."judge" VALUES (142, 'ოთარ სიჭინავა', NULL, NULL);
INSERT INTO "public"."judge" VALUES (143, 'ნათია გუჯაბიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (144, 'გელა ქირია', NULL, NULL);
INSERT INTO "public"."judge" VALUES (145, 'თამარ ზამბახიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (146, 'ეკატერინე ცისკარიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (147, 'ლევან გვარამია', NULL, NULL);
INSERT INTO "public"."judge" VALUES (148, 'ბესარიონ ტაბაღუა', NULL, NULL);
INSERT INTO "public"."judge" VALUES (149, 'ხათუნა არევაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (150, 'გოდერძი გიორგიშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (151, 'ანა გოგიშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (152, 'ამირან ძაბუნიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (153, 'ქეთევან მესხიშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (154, 'ნატალია ნაზღაიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (155, 'თამარ ალანია', NULL, NULL);
INSERT INTO "public"."judge" VALUES (156, 'მერაბ გაბინაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (157, 'დიანა ბერეკაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (158, 'მარიამ ცისკაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (159, 'ილონა თოდუა', NULL, NULL);
INSERT INTO "public"."judge" VALUES (160, 'გიორგი გოგიაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (161, 'თეა ძიმისტარაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (162, 'ლევან მურუსიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (163, 'ირაკლი შენგელია', NULL, NULL);
INSERT INTO "public"."judge" VALUES (164, 'მერაბ ლომიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (165, 'გიორგი ტყავაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (166, 'შოთა გეწაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (167, 'მანანა ჩოხელი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (168, 'ნინო ქადაგიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (169, 'ნათია ქუთათელაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (170, 'ქეთევან დუგლაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (171, 'მირანდა ერემაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (172, 'გელა ბადრიაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (173, 'სპარტაკ პავლიაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (174, 'გიორგი მიროტაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (175, 'გიორგი გოგინაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (176, 'მანუჩარ ცაცუა', NULL, NULL);
INSERT INTO "public"."judge" VALUES (177, 'ირაკლი აბშილავა', NULL, NULL);
INSERT INTO "public"."judge" VALUES (178, 'ირაკლი შავაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (179, 'დარეჯან კვარაცხელია', NULL, NULL);
INSERT INTO "public"."judge" VALUES (181, 'ნინო ონიანი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (180, 'ეკატერინე ყანჩელი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (182, 'დავით ახალბედაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (183, 'თეიმურაზ სიხარულიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (184, 'ნატა თედეშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (185, 'ლაბაზარ დუოშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (186, 'ჯუმბერ ბეჟანიძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (187, 'გოჩა ჯეირანაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (188, 'მაია შოშიაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (189, 'ნათია მერაბიშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (190, 'დავით პაპუაშვილი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (191, 'თამარ ჩიხლაძე', NULL, NULL);
INSERT INTO "public"."judge" VALUES (192, 'თამარ ონიანი', NULL, NULL);
INSERT INTO "public"."judge" VALUES (193, 'თეიმურაზ სიხარულიძე', NULL, NULL);

-- ----------------------------
-- Table structure for litigation_subject
-- ----------------------------
DROP TABLE IF EXISTS "public"."litigation_subject";
CREATE TABLE "public"."litigation_subject" (
  "litigation_subject_id" int4 NOT NULL DEFAULT nextval('litigation_subject_litigation_subject_id_seq'::regclass),
  "name" varchar(200) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of litigation_subject
-- ----------------------------
INSERT INTO "public"."litigation_subject" VALUES (1, 'აქტის ბათილად ცნობა');
INSERT INTO "public"."litigation_subject" VALUES (2, 'ზიანის ანაზღაურება');
INSERT INTO "public"."litigation_subject" VALUES (3, 'დევნილი');
INSERT INTO "public"."litigation_subject" VALUES (4, 'ზედდება');
INSERT INTO "public"."litigation_subject" VALUES (5, 'სხვა');
INSERT INTO "public"."litigation_subject" VALUES (6, 'ინფორმაციის გაცემა');
INSERT INTO "public"."litigation_subject" VALUES (8, 'პირგასამტეხლოს დაკისრება');
INSERT INTO "public"."litigation_subject" VALUES (9, 'ზედმეტად ჩარიცული ხელფასის დაბრუნება');
INSERT INTO "public"."litigation_subject" VALUES (10, 'სახელფასო დავალიანების ანაზღაურება');
INSERT INTO "public"."litigation_subject" VALUES (11, 'შსს-სთვის მიყენებული ქონებრივი ზიანის ანაზღაურება');
INSERT INTO "public"."litigation_subject" VALUES (12, 'აღიარებითი სარჩელი');
INSERT INTO "public"."litigation_subject" VALUES (13, 'წელთა ნამსახურეობის დაანგარიშება (შრომის სტაჟის დადგენა)');
INSERT INTO "public"."litigation_subject" VALUES (14, 'კომპენსაცია');
INSERT INTO "public"."litigation_subject" VALUES (15, 'ქმედების განხორციელება');
INSERT INTO "public"."litigation_subject" VALUES (16, 'არარად აღიარება');
INSERT INTO "public"."litigation_subject" VALUES (17, 'უფლებამონაცვლედ ცნობა');
INSERT INTO "public"."litigation_subject" VALUES (7, 'დათხოვნის შესახებ ბრძანების ბათილად/არარად ცნობა');
INSERT INTO "public"."litigation_subject" VALUES (18, 'ხელშეკრულებიდან გამომდინარე ზიანის ანაზღაურება');
INSERT INTO "public"."litigation_subject" VALUES (19, 'ხელშეკრულებიდან გამომდინარე სხვა დავები');
INSERT INTO "public"."litigation_subject" VALUES (20, 'უკანონო პატიმრობის გამო ზიანის ანაზღაურება');
INSERT INTO "public"."litigation_subject" VALUES (21, 'ზიანის ანაზღაურება (ოჯახური ძალადობა)');
INSERT INTO "public"."litigation_subject" VALUES (22, 'საზღვრის კვეთა');
INSERT INTO "public"."litigation_subject" VALUES (23, 'ქონების ყადაღისგან გათავისუფლება');

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS "public"."status";
CREATE TABLE "public"."status" (
  "status_id" int4 NOT NULL DEFAULT nextval('status_status_id_seq'::regclass),
  "name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO "public"."status" VALUES (1, 'დასრულებული ');
INSERT INTO "public"."status" VALUES (2, 'მიმდინარე');
INSERT INTO "public"."status" VALUES (3, 'შეჩერებული');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "public"."user";
CREATE TABLE "public"."user" (
  "user_id" int4 NOT NULL DEFAULT nextval('user_user_id_seq'::regclass),
  "name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "username" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "role" varchar(200) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO "public"."user" VALUES (12, 'მაია წიწილაშვილი', 'მაია წიწილაშვილი', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (6, 'ალეკო ნაჭყებია', 'სასამართლო', 'JUSTICE_ADMIN,JUSTICE_OPERATOR,JUSTICE_SIP_ADMIN,JUSTICE_SIP_OPERATOR');
INSERT INTO "public"."user" VALUES (36, 'გიორგი ჩუთლაშვილი', 'გიორგი ჩუთლაშვილი', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (18, 'დავით ტეფნაძე', 'დავით ტეფნაძე', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (19, 'თორნიკე ფუტკარაძე', 'თორნიკე ფუტკარაძე', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (37, 'გვანცა ხარებავა', 'გვანცა ხარებავა', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (27, 'მარიამ კერესელიძე', 'მარიამ კერესელიძე', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (34, 'ოთარ ჯახველაძე', 'ოთარ ჯახველაძე', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (95, '', 'nachyebiaa', 'JUSTICE_ADMIN,JUSTICE_OPERATOR,JUSTICE_SIP_ADMIN,JUSTICE_SIP_OPERATOR');
INSERT INTO "public"."user" VALUES (101, '', 'tsitsilashvilim', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (102, '', 'ojakhveladze', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (104, '', 'tfutkaradze', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (108, '', 'gchutlashvili', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (109, '', 'dtefnadze', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (110, '', 'mkereselidze', 'JUSTICE_OPERATOR');
INSERT INTO "public"."user" VALUES (111, '', 'bdochviri', 'JUSTICE_ADMIN,JUSTICE_OPERATOR,JUSTICE_SIP_ADMIN,JUSTICE_SIP_OPERATOR');

-- ----------------------------
-- Table structure for user_status
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_status";
CREATE TABLE "public"."user_status" (
  "status_id" int4 NOT NULL DEFAULT nextval('user_status_status_id_seq'::regclass),
  "name" varchar(50) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of user_status
-- ----------------------------
INSERT INTO "public"."user_status" VALUES (2, 'პასიური');
INSERT INTO "public"."user_status" VALUES (1, 'აქტიური');

-- ----------------------------
-- Table structure for user_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_type";
CREATE TABLE "public"."user_type" (
  "type_id" int4 NOT NULL DEFAULT nextval('user_type_type_id_seq'::regclass),
  "name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of user_type
-- ----------------------------
INSERT INTO "public"."user_type" VALUES (1, 'JUSTICE_ADMIN');
INSERT INTO "public"."user_type" VALUES (2, 'JUSTICE_OPERATOR');
INSERT INTO "public"."user_type" VALUES (3, 'JUSTICE_SIP_ADMIN');
INSERT INTO "public"."user_type" VALUES (4, 'JUSTICE_SIP_OPERATOR');

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."board_board_id_seq"
OWNED BY "public"."board"."board_id";
SELECT setval('"public"."board_board_id_seq"', 5, true);
ALTER SEQUENCE "public"."case_case_id_seq"
OWNED BY "public"."case"."case_id";
SELECT setval('"public"."case_case_id_seq"', 487, true);
ALTER SEQUENCE "public"."case_doc_doc_id_seq"
OWNED BY "public"."case_doc"."doc_id";
SELECT setval('"public"."case_doc_doc_id_seq"', 1302, true);
ALTER SEQUENCE "public"."court_court_id_seq"
OWNED BY "public"."court"."court_id";
SELECT setval('"public"."court_court_id_seq"', 30, true);
ALTER SEQUENCE "public"."court_instance_instance_id_seq"
OWNED BY "public"."court_instance"."instance_id";
SELECT setval('"public"."court_instance_instance_id_seq"', 4, true);
ALTER SEQUENCE "public"."end_result_end_result_id_seq"
OWNED BY "public"."end_result"."end_result_id";
SELECT setval('"public"."end_result_end_result_id_seq"', 12, true);
ALTER SEQUENCE "public"."judge_judge_id_seq"
OWNED BY "public"."judge"."judge_id";
SELECT setval('"public"."judge_judge_id_seq"', 194, true);
ALTER SEQUENCE "public"."litigation_subject_litigation_subject_id_seq"
OWNED BY "public"."litigation_subject"."litigation_subject_id";
SELECT setval('"public"."litigation_subject_litigation_subject_id_seq"', 24, true);
ALTER SEQUENCE "public"."status_status_id_seq"
OWNED BY "public"."status"."status_id";
SELECT setval('"public"."status_status_id_seq"', 4, true);
ALTER SEQUENCE "public"."user_status_status_id_seq"
OWNED BY "public"."user_status"."status_id";
SELECT setval('"public"."user_status_status_id_seq"', 3, true);
ALTER SEQUENCE "public"."user_type_type_id_seq"
OWNED BY "public"."user_type"."type_id";
SELECT setval('"public"."user_type_type_id_seq"', 3, true);
ALTER SEQUENCE "public"."user_user_id_seq"
OWNED BY "public"."user"."user_id";
SELECT setval('"public"."user_user_id_seq"', 117, true);

-- ----------------------------
-- Primary Key structure for table board
-- ----------------------------
ALTER TABLE "public"."board" ADD CONSTRAINT "board_pkey" PRIMARY KEY ("board_id");

-- ----------------------------
-- Primary Key structure for table case
-- ----------------------------
ALTER TABLE "public"."case" ADD CONSTRAINT "case_pkey" PRIMARY KEY ("case_id");

-- ----------------------------
-- Primary Key structure for table case_doc
-- ----------------------------
ALTER TABLE "public"."case_doc" ADD CONSTRAINT "case_doc_pkey" PRIMARY KEY ("doc_id");

-- ----------------------------
-- Primary Key structure for table court
-- ----------------------------
ALTER TABLE "public"."court" ADD CONSTRAINT "court_pkey" PRIMARY KEY ("court_id");

-- ----------------------------
-- Primary Key structure for table court_instance
-- ----------------------------
ALTER TABLE "public"."court_instance" ADD CONSTRAINT "court_instance_pkey1" PRIMARY KEY ("instance_id");

-- ----------------------------
-- Primary Key structure for table end_result
-- ----------------------------
ALTER TABLE "public"."end_result" ADD CONSTRAINT "end_result_pkey" PRIMARY KEY ("end_result_id");

-- ----------------------------
-- Primary Key structure for table judge
-- ----------------------------
ALTER TABLE "public"."judge" ADD CONSTRAINT "judge_pkey" PRIMARY KEY ("judge_id");

-- ----------------------------
-- Primary Key structure for table litigation_subject
-- ----------------------------
ALTER TABLE "public"."litigation_subject" ADD CONSTRAINT "litigation_subject_pkey" PRIMARY KEY ("litigation_subject_id");

-- ----------------------------
-- Primary Key structure for table status
-- ----------------------------
ALTER TABLE "public"."status" ADD CONSTRAINT "status_pkey" PRIMARY KEY ("status_id");

-- ----------------------------
-- Uniques structure for table user
-- ----------------------------
ALTER TABLE "public"."user" ADD CONSTRAINT "UNIQ_USRNM" UNIQUE ("username");

-- ----------------------------
-- Primary Key structure for table user
-- ----------------------------
ALTER TABLE "public"."user" ADD CONSTRAINT "user_pkey" PRIMARY KEY ("user_id");

-- ----------------------------
-- Primary Key structure for table user_status
-- ----------------------------
ALTER TABLE "public"."user_status" ADD CONSTRAINT "user_status_pkey" PRIMARY KEY ("status_id");

-- ----------------------------
-- Primary Key structure for table user_type
-- ----------------------------
ALTER TABLE "public"."user_type" ADD CONSTRAINT "user_type_pkey" PRIMARY KEY ("type_id");

-- ----------------------------
-- Foreign Keys structure for table case
-- ----------------------------
ALTER TABLE "public"."case" ADD CONSTRAINT "fk_case_to_court" FOREIGN KEY ("court_id") REFERENCES "public"."court" ("court_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."case" ADD CONSTRAINT "fk_case_to_end_result" FOREIGN KEY ("end_result_id") REFERENCES "public"."end_result" ("end_result_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."case" ADD CONSTRAINT "fk_case_to_judge" FOREIGN KEY ("judge_id") REFERENCES "public"."judge" ("judge_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."case" ADD CONSTRAINT "fk_litigation_subject_id" FOREIGN KEY ("litigation_subject_id") REFERENCES "public"."litigation_subject" ("litigation_subject_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
