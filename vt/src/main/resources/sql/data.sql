INSERT INTO vessel (name, imo, length) VALUES ('PUELO', 9306172, 304);
INSERT INTO vessel (name, imo, length) VALUES ('ESSEN EXPRESS', 9501370, 366);
INSERT INTO port (port_id) VALUES (2);
INSERT INTO port_vessel (id, port_id, imo, time_started, time_finished) VALUES (123, 2, 9306172, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO port_vessel (id, port_id, imo, time_started, time_finished) VALUES (124, 2, 9306172, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO port_vessel (id, port_id, imo, time_started, time_finished) VALUES (125, 2, 9501370, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO port_vessel (id, port_id, imo, time_started, time_finished) VALUES (126, 2, 9501370, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO port_vessel (id, port_id, imo, time_started, time_finished) VALUES (127, 2, 9501370, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());