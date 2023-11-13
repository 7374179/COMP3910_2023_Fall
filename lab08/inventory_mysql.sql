CREATE DATABASE inventory;

CREATE USER IF NOT EXISTS 'stock'@'localhost' IDENTIFIED BY 'check';
CREATE USER IF NOT EXISTS 'stock'@'%' IDENTIFIED BY 'check';
GRANT ALL ON inventory.* TO 'stock'@'localhost';
GRANT ALL ON inventory.* TO 'stock'@'%';

USE inventory;

DROP TABLE IF EXISTS Categories;
CREATE TABLE Categories(CategoryID int NOT NULL, CategoryName TINYTEXT, PRIMARY KEY(CategoryID));

INSERT INTO Categories VALUES(1,"Beverages");
INSERT INTO Categories VALUES(2,"Condiments");
INSERT INTO Categories VALUES(3,"Confections");
INSERT INTO Categories VALUES(4,"Dairy");
INSERT INTO Categories VALUES(5,"Grains/Cereals");
INSERT INTO Categories VALUES(6,"Meat/Poultry");
INSERT INTO Categories VALUES(7,"Produce");
INSERT INTO Categories VALUES(8,"Seafood");

DROP TABLE IF EXISTS Inventory;
CREATE TABLE Inventory(ProductID int NOT NULL, ProductName TINYTEXT, ProductDescription TINYTEXT, CategoryID int, Price float, ReorderLevel int, Discontinued tinyint, LeadTime TINYTEXT, Quantity int, PRIMARY KEY(ProductID), FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID));

INSERT INTO Inventory VALUES (2,"Tibetan Barley Beer","Unique flavor",1,4.0,10,0,"30 Days",100);
INSERT INTO Inventory VALUES (5,"Chef Anton's Gumbo Mix","Gumbo mix",8,55.0,10,0,"10 Days",3);
INSERT INTO Inventory VALUES (15,"Blue potato chips","nice texture",5,12.0,5,0,"5 Days",7);
INSERT INTO Inventory VALUES (17,"Wax lips","tasty treat",3,2.0,10,1,"10 Days",0);
INSERT INTO Inventory VALUES (19,"Pita pieces","",5,15.0,20,0,"1 Day",4);
INSERT INTO Inventory VALUES (20,"Tomato Jelly","yummy",2,1.0,100,0," 2 Days",20);
INSERT INTO Inventory VALUES (21,"Fishheads","",8,12.0,50,0,"10 Days",0);
INSERT INTO Inventory VALUES (24,"Jam Juice","",1,3.0,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (1,"Chili con Carne","Extra spicy",6,3.75,10,0,"5 Days",7);
INSERT INTO Inventory VALUES (3,"Energy Drink","For late night studying",1,4.0,10,0,"5 Days",5);
INSERT INTO Inventory VALUES (4,"Kona Coffee","",1,2.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (6,"Sushi Bar Tea","Non fermented",1,3.32,10,0,"4 Days",5);
INSERT INTO Inventory VALUES (7,"Sea Salt","Source of iodine",2,1.25,10,0,"1 Day",12);
INSERT INTO Inventory VALUES (8,"New Age Pepper","Hot, hot, hot",2,1.0,10,0,"3 Days",13);
INSERT INTO Inventory VALUES (9,"Gooey Buns","Sticky and sweet",3,3.37,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (10,"Apple strudel","Just right",3,2.56,10,0,"1 Day",3);
INSERT INTO Inventory VALUES (11,"Rice pudding","Good for sleeping",3,3.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (12,"Kheer","Sweet milk-rice confection",4,3.25,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (13,"Gulabjamun","Made with khoa",4,2.25,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (14,"Shrikhand","Fermented dairy dessert",4,3.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (16,"Rasogolla","King of Indian dairy sweets",4,3.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (18,"Dark chocolate balls","Made in Switzerland",3,3.50,10,0,"4 Days",5);
INSERT INTO Inventory VALUES (22,"Marzipan Confections","Edible art",3,2.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (23,"Matzah","Flatbread",5,3.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (25,"Naan","Not so flat bread",5,3.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (26,"Georgian Spice Bread","Fresh from Georgian marikets",5,3.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (27,"Lavash","Paper thin Armenian bread",5,4.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (28,"Corn Tortillas","Fresh Baked Today",5,5.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (29,"Polpette di Manzo","Italian meatballs",6,5.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (30,"Abbacchio al Forno","Roast Lamb",6,5.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (31,"Pollo alla Cacciatora","Hunters' Chicken",6,4.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (32,"Cotolette alla Calabrese","Beef cutlets, Calabrian style",6,5.50,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (33,"Celery","Freshly Picked Today",7,1.75,10,0,"1 Day",5);
INSERT INTO Inventory VALUES (34,"Corn","Fresh Today",7,5.50,10,0,"1 Day",5);

DROP TABLE IF EXISTS Suppliers;
CREATE TABLE Suppliers (SupplierID int NOT NULL, SupplierName TINYTEXT, ContactName TINYTEXT,ContactTitle TINYTEXT, Address TINYTEXT, City TINYTEXT, PostalCode TINYTEXT, StateOrProvince TINYTEXT, Country TINYTEXT, PhoneNumber TINYTEXT, FaxNumber TINYTEXT, PaymentTerms TINYTEXT, EmailAddress TINYTEXT, Notes TINYTEXT, PRIMARY KEY(SupplierID));

INSERT INTO Suppliers VALUES (1,"Exotic Liquids","Charlotte Cooper","Purchasing Manager","49 Gilbert St.","London","EC1 4SD","","UK","( 71) 555-2222","","Net 30 days","","");
INSERT INTO Suppliers VALUES (2,"New Orleans Cajun Delights","Shelley Burke","Order Administrator","P.O. Box 78934","New Orleans","70117-","","USA","(100) 555-4822","","Net 60 days","","");
INSERT INTO Suppliers VALUES (3,"Grandma Kelly's Homestead","Regina Murphy","Sales Representative","707 Oxford Rd.","Ann Arbor","48104-","","USA","(313) 555-5735","(313) 555-3349","Net 30 days","","");
INSERT INTO Suppliers VALUES (4,"Tokyo Traders","Yoshi Nagase","Marketing Manager","9-8 Sekimai Musashino-shi","Tokyo","100  -","","Japan","(03) 3555-5011","","Net 30 days","","");
INSERT INTO Suppliers VALUES (5,"Cooperativa de Quesos 'Las Cabras'","Antonio del Valle Saavedra ","Export Administrator","Calle del Rosal 4","Oviedo","33007-","","Spain","(98) 598 76 54","","Net 30 days","","");

DROP TABLE IF EXISTS ProductSuppliers;
CREATE TABLE ProductSuppliers (SupplierID int NOT NULL, ProductID int NOT NULL, Price float, PRIMARY KEY(SupplierID, ProductID), FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID), FOREIGN KEY (ProductID) REFERENCES Inventory(ProductID));

INSERT INTO ProductSuppliers VALUES (1,2,5.75);
INSERT INTO ProductSuppliers VALUES (4,2,7.0);
INSERT INTO ProductSuppliers VALUES (3,5,2.25);
INSERT INTO ProductSuppliers VALUES (1,15,7.75);
INSERT INTO ProductSuppliers VALUES (2,21,25.0);
INSERT INTO ProductSuppliers VALUES (4,17,1.0);
INSERT INTO ProductSuppliers VALUES (3,15,5.0);
INSERT INTO ProductSuppliers VALUES (5,19,5.0);
INSERT INTO ProductSuppliers VALUES (4,21,12.0);
INSERT INTO ProductSuppliers VALUES (1,24,3.0);
INSERT INTO ProductSuppliers VALUES (2,24,4.0);


