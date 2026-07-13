CREATE DATABASE AirlineDB;
GO

USE AirlineDB;
GO

CREATE SCHEMA airline;
GO

CREATE TABLE airline.AirplaneType (
    TypeName VARCHAR(50)    NOT NULL,
    MaxSeats INT            NOT NULL,
    Company VARCHAR(100)    NOT NULL,
    PRIMARY KEY (TypeName)
);

CREATE TABLE airline.Airport (
    Airport_Code VARCHAR(10) NOT NULL,
    City VARCHAR(100)       NOT NULL,
    StateName VARCHAR(100)  NOT NULL,
    Name VARCHAR(150)       NOT NULL,
    PRIMARY KEY (Airport_Code)
);

CREATE TABLE airline.Flight (
    Number INT              NOT NULL,
    Airline VARCHAR(100)    NOT NULL,
    Weekdays VARCHAR(50)    NOT NULL,
    PRIMARY KEY (Number)
);

CREATE TABLE airline.Airplane (
    AirplaneID INT          NOT NULL,
    TotalNSeats INT         NOT NULL,
    TypeName VARCHAR(50)    NOT NULL,
    PRIMARY KEY (AirplaneID),
    FOREIGN KEY (TypeName) REFERENCES airline.AirplaneType(TypeName)
);

CREATE TABLE airline.CanLand (
    TypeName VARCHAR(50)    NOT NULL,
    AirportCode VARCHAR(10) NOT NULL,
    PRIMARY KEY (TypeName, AirportCode),
    FOREIGN KEY (TypeName) REFERENCES airline.AirplaneType(TypeName),
    FOREIGN KEY (AirportCode) REFERENCES airline.Airport(Airport_Code)
);

CREATE TABLE airline.FlightLeg (
    FlightNumber INT        NOT NULL,
    LegNo INT               NOT NULL,
    ScheduledDepTime TIME   NOT NULL,
    ScheduledArrTime TIME   NOT NULL,
    DepAirportCode VARCHAR(10) NOT NULL,
    ArrAirportCode VARCHAR(10) NOT NULL,
    PRIMARY KEY (FlightNumber, LegNo),
    FOREIGN KEY (FlightNumber) REFERENCES airline.Flight(Number),
    FOREIGN KEY (DepAirportCode) REFERENCES airline.Airport(Airport_Code),
    FOREIGN KEY (ArrAirportCode) REFERENCES airline.Airport(Airport_Code)
);

CREATE TABLE airline.Fare (
    Code VARCHAR(20)        NOT NULL,
    FlightNumber INT        NOT NULL,
    Amount DECIMAL(10,1)    NOT NULL,
    Restrictions VARCHAR(20),
    PRIMARY KEY (Code, FlightNumber),
    FOREIGN KEY (FlightNumber) REFERENCES airline.Flight(Number)
);

CREATE TABLE airline.LegInstance (
    FlightNumber INT        NOT NULL,
    LegNo INT               NOT NULL,
    Date DATE               NOT NULL,
    NoAvailSeats INT        NOT NULL,
    DepTime TIME            NOT NULL,
    ArrTime TIME            NOT NULL,
    PRIMARY KEY (FlightNumber, LegNo, Date),
    FOREIGN KEY (FlightNumber, LegNo) REFERENCES airline.FlightLeg(FlightNumber, LegNo)
);

CREATE TABLE airline.Seat (
    SeatNo VARCHAR(10)      NOT NULL,
    FlightNumber INT        NOT NULL,
    LegNo INT               NOT NULL,
    Date DATE               NOT NULL,
    CustomerName VARCHAR(100) NOT NULL,
    Cphone VARCHAR(20),
    PRIMARY KEY (SeatNo, FlightNumber, LegNo, Date),
    FOREIGN KEY (FlightNumber, LegNo, Date) REFERENCES airline.LegInstance(FlightNumber, LegNo, Date)
);