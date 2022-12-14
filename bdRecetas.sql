create database Recetas
go

use Recetas
go

create table Categorias (
    Id int primary key identity,
    Nombre varchar (500) not null,
)

create table Recetas (
    Id int primary key identity,
    Titulo varchar (500) not null,
    Descripcion varchar (500) not null,
    Imagen varbinary (max) not null,
    Duracion int not null,
    Personas int not null,
    Dificultad varchar (500) not null,
    Fecha date not null,
    Autor varchar (500) not null,
    Estado int not null,
    IdCategoria int not null,
    constraint FK_Recetas_Categorias foreign key (IdCategoria) references Categorias (Id) 
)

create table UnidadesMedida (
    Id int primary key identity,
    Nombre varchar (500) not null,
)

create table Ingredientes (
    Id int primary key identity,
    Nombre varchar (500) not null,
    Cantidad int not null,
    IdUdMedida int not null,
    IdReceta int not null,
    constraint FK_Ingredientes_Recetas foreign key (IdReceta) references Recetas(Id),
    constraint FK_Ingredientes_UnidadesMedida foreign key (IdUdMedida) references UnidadesMedida(Id)
)

create table Utensilios (
    Id int primary key identity,
    Nombre varchar (500) not null,
    IdReceta int not null,
    constraint FK_Utensilios_Recetas foreign key (IdReceta) references Recetas(Id)
)

create table Pasos (
    Id int primary key identity,
    Nombre varchar (500) not null,
    IdReceta int not null,
    constraint FK_Pasos_Recetas foreign key (IdReceta) references Recetas(Id)
)