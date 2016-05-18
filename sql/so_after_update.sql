DELIMITER //
CREATE TRIGGER so_before_update BEFORE UPDATE ON so
FOR EACH ROW
BEGIN
     IF NEW.so_ps_score < 0 THEN
         SET NEW.so_ps_score = 0;
     ELSEIF NEW.so_ps_score > 100 THEN
         SET NEW.so_ps_score = 100;
     END IF;
     IF NEW.so_ks_score < 0 THEN
         SET NEW.so_ks_score = 0;
     ELSEIF NEW.so_ks_score > 100 THEN
         SET NEW.so_ks_score = 100;
     END IF;
END;
//
DELIMITER ;