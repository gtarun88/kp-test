
CREATE TABLE PLAN_DESCRIPTIONS(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    plan_id VARCHAR(255) ,
    plan_name VARCHAR(255) ,
    coverage_type VARCHAR(255) ,
    estimated_premium VARCHAR(255) ,
    annual_deductible_individual VARCHAR(255) ,
    annual_deductible_family VARCHAR(255)
);


INSERT INTO PLAN_DESCRIPTIONS (id,plan_id, plan_name,coverage_type,estimated_premium,annual_deductible_individual,annual_deductible_family)
VALUES (1,'P001', 'Bronze6000/40%/HAS','Family','$ 962','$ 6000','$ 12000');

INSERT INTO PLAN_DESCRIPTIONS (id,plan_id, plan_name,coverage_type,estimated_premium,annual_deductible_individual,annual_deductible_family)
VALUES (2,'P002', 'Bronze5000/50','Family','$ 1017','$ 5000','$ 10000');

INSERT INTO PLAN_DESCRIPTIONS (id,plan_id, plan_name,coverage_type,estimated_premium,annual_deductible_individual,annual_deductible_family)
VALUES (3,'P003', 'Bronze4000/20','Family','$ 1045.67','$ 4000','$ 8000');

CREATE TABLE PLAN_COVERAGE(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    main_category VARCHAR(255) ,
    sub_category VARCHAR(255) ,
    plan_001 VARCHAR(255) ,
    plan_002 VARCHAR(255) ,
    plan_003 VARCHAR(255)
);

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (1,'Preventive Care', 'ROUTINE PHYSICAL EXAM','No Charge','No Charge','No Charge');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (2,'Preventive Care', 'MAMMOGRAMS','No Charge','No Charge','No Charge');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (3,'Outpatient Services', 'PRIMARY CARE OFFICE VISIT','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (4,'Outpatient Services', 'SPECIALTY CARE OFFICE VISIT','70% AFTER DEDUCTIBLE','80% AFTER DEDUCTIBLE','90% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (5,'Outpatient Services', 'X-RAYS','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (6,'Outpatient Services', 'LAB TESTS','80% AFTER DEDUCTIBLE','90% AFTER DEDUCTIBLE','100% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (7,'Outpatient Services', 'MRI, CT, PET','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (8,'Outpatient Services', 'OUTPATIENT SURGERY','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (9,'Outpatient Services', 'MENTAL HEALTH VISIT','60% AFTER DEDUCTIBLE','70% AFTER DEDUCTIBLE','80% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (10,'Inpatient Hospital Care', 'ROOM AND BOARD','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (11,'Inpatient Hospital Care', 'SURGERY','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (12,'Inpatient Hospital Care', 'ANESTHESIA','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (13,'Inpatient Hospital Care', 'X-RAYS','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (14,'Inpatient Hospital Care', 'LAB TESTS','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (15,'Inpatient Hospital Care', 'MEDICATIONS','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (16,'Inpatient Hospital Care', 'MENTAL HEALTH CARE','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (17,'Maternity', 'ROUTINE PRENATAL CARE VISIT','40% AFTER DEDUCTIBLE','40% AFTER DEDUCTIBLE','30% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (18,'Maternity', 'FIRST POSTPARTUM VISIT','40% AFTER DEDUCTIBLE','40% AFTER DEDUCTIBLE','30% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (19,'Maternity', 'DELIVERY AND INPATIENT WELL-BABY CARE','40% AFTER DEDUCTIBLE','40% AFTER DEDUCTIBLE','30% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (20,'Emergency And Urgent Care', 'EMERGENCY DEPARTMENT VISIT','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (21,'Emergency And Urgent Care', 'URGENT CARE VISIT','40% AFTER DEDUCTIBLE','$ 100','$ 120');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (22,'Emergency And Urgent Care', 'AMBULANCE SERVICES','40% AFTER DEDUCTIBLE','40% AFTER DEDUCTIBLE','30% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (23,'Prescription Drugs', 'GENERIC','60% AFTER DEDUCTIBLE','70% AFTER DEDUCTIBLE','80% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (24,'Prescription Drugs', 'PREFERRED BRAND','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE','70% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (25,'Prescription Drugs', 'NON-PREFERRED BRAND','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');

INSERT INTO PLAN_COVERAGE (id,main_category,sub_category,plan_001,plan_002,plan_003)
VALUES (26,'Prescription Drugs', 'SPECIALTY','40% AFTER DEDUCTIBLE','50% AFTER DEDUCTIBLE','60% AFTER DEDUCTIBLE');


CREATE TABLE POLICY_DATA(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    policy_id VARCHAR(255) ,
    policy_holder_id VARCHAR(255) ,
    first_name VARCHAR(255) ,
    last_name VARCHAR(255) ,
    plan_id VARCHAR(255) ,
    coverage_start_date VARCHAR(255) ,
    coverage_end_date VARCHAR(255) ,
    individual_accumulated_deductible_for_year VARCHAR(255) ,
    family_accumulated_deductible_for_year VARCHAR(255)
);

INSERT INTO POLICY_DATA (id,policy_id,policy_holder_id,first_name,last_name,plan_id,coverage_start_date,coverage_end_date,individual_accumulated_deductible_for_year,family_accumulated_deductible_for_year)
VALUES (1,'100001','1000011','Sam','Collins','P001','37987','','0.00','0.00');

INSERT INTO POLICY_DATA (id,policy_id,policy_holder_id,first_name,last_name,plan_id,coverage_start_date,coverage_end_date,individual_accumulated_deductible_for_year,family_accumulated_deductible_for_year)
VALUES (2,'100001','1000012','Jina','Collins','P001','37987','','0.00','0.00');

INSERT INTO POLICY_DATA (id,policy_id,policy_holder_id,first_name,last_name,plan_id,coverage_start_date,coverage_end_date,individual_accumulated_deductible_for_year,family_accumulated_deductible_for_year)
VALUES (3,'100001','1000013','Nancy','Collins','P001','40304','','0.00','0.00');

INSERT INTO POLICY_DATA (id,policy_id,policy_holder_id,first_name,last_name,plan_id,coverage_start_date,coverage_end_date,individual_accumulated_deductible_for_year,family_accumulated_deductible_for_year)
VALUES (4,'100001','1000014','Jack','Collins','P001','41463','','0.00','0.00');

INSERT INTO POLICY_DATA (id,policy_id,policy_holder_id,first_name,last_name,plan_id,coverage_start_date,coverage_end_date,individual_accumulated_deductible_for_year,family_accumulated_deductible_for_year)
VALUES (5,'100002','1000021','Daniel','Hayer','P002','40909','','4000.00','4000.00');

INSERT INTO POLICY_DATA (id,policy_id,policy_holder_id,first_name,last_name,plan_id,coverage_start_date,coverage_end_date,individual_accumulated_deductible_for_year,family_accumulated_deductible_for_year)
VALUES (6,'100003','1000031','Sally','Adams','P001','42370','','2500.00','2500.00');

INSERT INTO POLICY_DATA (id,policy_id,policy_holder_id,first_name,last_name,plan_id,coverage_start_date,coverage_end_date,individual_accumulated_deductible_for_year,family_accumulated_deductible_for_year)
VALUES (7,'100004','1000041','Jacke','Seegers','P003','39814','42521','5560.42','5560.42');

INSERT INTO POLICY_DATA (id,policy_id,policy_holder_id,first_name,last_name,plan_id,coverage_start_date,coverage_end_date,individual_accumulated_deductible_for_year,family_accumulated_deductible_for_year)
VALUES (8,'100005','1000051','Tom','Haskel','P001','40544','','6712.34','6712.34');

INSERT INTO POLICY_DATA (id,policy_id,policy_holder_id,first_name,last_name,plan_id,coverage_start_date,coverage_end_date,individual_accumulated_deductible_for_year,family_accumulated_deductible_for_year)
VALUES (9,'100006','1000061','San','Mildred','P001','42374','','3250.61','3250.61');

INSERT INTO POLICY_DATA (id,policy_id,policy_holder_id,first_name,last_name,plan_id,coverage_start_date,coverage_end_date,individual_accumulated_deductible_for_year,family_accumulated_deductible_for_year)
VALUES (10,'100007','1000071','Mack','Lee','P003','40883','42614','4460.82','4460.82');

