USE elective_system_v1;
CREATE TRIGGER so_after_update AFTER UPDATE ON so
FOR EACH ROW
BEGIN
IF NEW.so_ps_score is not null and NEW.so_ps_score < 0 THEN 
UPDATE so SET so_ps_score = 0 WHERE s_id = NEW.s_id and o_id = NEW.o_id;
END IF;
IF NEW.so_ks_score is not null and NEW.so_ks_score < 0 THEN
UPDATE so SET so_ks_score = 0 WHERE s_id = NEW.s_id and so.o_id = NEW.o_id;
END IF;
END;