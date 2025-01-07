-- Automatic delete mail after 30 days left from trash 
CREATE EVENT AutomaticDelete
ON SCHEDULE EVERY 1 MINUTE
DO
	DELETE FROM Trash
	WHERE NOW() >= DATE_ADD(trashed_on, INTERVAL 30 DAY);
    
SHOW VARIABLES LIKE 'event_scheduler';

SHOW EVENTS;