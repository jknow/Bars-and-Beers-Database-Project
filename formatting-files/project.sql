create table Bars(
BarID int not null,
primary key (BarID),
BarName varchar(255),
BarType varchar(255),
Address varchar(255),
BarPhoneNumber varchar(13),
Kitchen boolean,
OpeningYear int,
CityState varchar(255)
)engine = InnoDB;

create table Sells(
BarID int,
foreign key (BarID) references Bars(BarID),
Beer varchar(255),
foreign key (Beer) references Beers(BeerName),
Price float
)engine = InnoDB;

create table BarStatistics(
BarID int,
foreign key (BarID) references Bars(BarID),
CustomerRating double,
Size int,
ExpenseRating int,
NumberofStaff int,
RevenuePerYear double,
AgeRange varchar(5),
Demographic varchar(255)
)engine = InnoDB;

create table Hours(
/*2400 hour times*/
BarID int,
foreign key (BarID) references Bars(BarID),
MonOpen varchar(6),
MonClose varchar(6),
TuesOpen varchar(6),
TuesClose varchar(6),
WedOpen varchar(6),
WedClose varchar(6),
ThursOpen varchar(6),
ThursClose varchar(6),
FriOpen varchar(6),
FriClose varchar(6),
SatOpen varchar(6),
SatClose varchar(6),
SunOpen varchar(6),
SunClose varchar(6)
)engine = InnoDB;

create table Staff(
PersonID int not null,
primary key (PersonID),
FirstName varchar(255),
LastName varchar(255),
Age int,
Sex char,
PhoneNumber varchar(255),
BarID int,
foreign key (BarID) references Bars(BarID),
Job varchar(255),
SkillRating int,
Wage double,
Address varchar(255)
)engine = InnoDB;

create table Beers(
BeerName varchar(255),
primary key (BeerName),
BeerType varchar(255),
AlcoholPercentage int,
QualityRating double,
Origin varchar(255),
Size int
)engine = InnoDB;