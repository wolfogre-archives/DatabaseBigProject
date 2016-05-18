DELIMITER //  
CREATE PROCEDURE create_term(IN term_year int, IN term_summer char(1))  
BEGIN
	SET @month_start = 0;
	SET @month_end = 0;
	IF(term_summer = 'C') THEN
		INSERT INTO d (d_term, d_begin, d_end) VALUES (CONCAT(term_year, '-', term_summer), DATE(CONCAT(term_year, '_', 3, '_', 1)), DATE(CONCAT(term_year, '_', 6, '_', 30))); 
	END IF;
	IF(term_summer = 'X') THEN
		INSERT INTO d (d_term, d_begin, d_end) VALUES (CONCAT(term_year, '-', term_summer), DATE(CONCAT(term_year, '_', 7, '_', 1)), DATE(CONCAT(term_year, '_', 7, '_', 31))); 
	END IF;
	IF(term_summer = 'Q') THEN
		INSERT INTO d (d_term, d_begin, d_end) VALUES (CONCAT(term_year, '-', term_summer), DATE(CONCAT(term_year, '_', 9, '_', 1)), DATE(CONCAT(term_year, '_', 11, '_', 30))); 
	END IF;
	IF(term_summer = 'D') THEN
		INSERT INTO d (d_term, d_begin, d_end) VALUES (CONCAT(term_year, '-', term_summer), DATE(CONCAT(term_year, '_', 12, '_', 1)), DATE(CONCAT(term_year + 1, '_', 2, '_', 28))); 
	END IF;
END;   
//  
DELIMITER ; 