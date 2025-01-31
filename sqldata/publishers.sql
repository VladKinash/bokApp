create table publishers
(
    publisher_id   int auto_increment
        primary key,
    publisher_name tinytext null,
    address        tinytext null,
    website        tinytext null
);

INSERT INTO bookapp.publishers (publisher_id, publisher_name, address, website) VALUES (1, 'Palaeos Press', '123 Dinosaur Dr, Fossil City, CA 90210', 'https://www.palaeospress.com');
INSERT INTO bookapp.publishers (publisher_id, publisher_name, address, website) VALUES (2, 'Open Source Publisher', '456 Digital Way, Cyberville, NY 10001', 'https://www.opensourcepublisher.org');
INSERT INTO bookapp.publishers (publisher_id, publisher_name, address, website) VALUES (3, 'Houghton Mifflin Harcourt', '3 Park Ave, New York, NY 10016', 'https://www.hmhco.com/');
INSERT INTO bookapp.publishers (publisher_id, publisher_name, address, website) VALUES (4, 'Subterranean Press', 'PO Box 190106, Burton, MI 48519', 'https://subterraneanpress.com/');
INSERT INTO bookapp.publishers (publisher_id, publisher_name, address, website) VALUES (5, 'Sphere', 'Carmelite House, 50 Victoria Embankment, London EC4Y 0DZ', 'https://www.littlebrown.co.uk/imprint/sphere/page/sphere-home/');
INSERT INTO bookapp.publishers (publisher_id, publisher_name, address, website) VALUES (6, 'Harper & Row', '195 Broadway, New York, NY 10007', 'https://www.harpercollins.com/');
