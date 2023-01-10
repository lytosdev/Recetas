if not exists (select name from master.dbo.sysdatabases where name='Recetas')
create database Recetas
go

use Recetas
go

if not exists (select * from sysobjects where name='Categorias' and xtype='U')
create table Categorias (
    Id int primary key identity,
    Nombre varchar (500) not null,
)

if not exists (select * from sysobjects where name='Recetas' and xtype='U')
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

if not exists (select * from sysobjects where name='UnidadesMedida' and xtype='U')
create table UnidadesMedida (
    Id int primary key identity,
    Nombre varchar (500) not null,
)

if not exists (select * from sysobjects where name='Ingredientes' and xtype='U')
create table Ingredientes (
    Id int primary key identity,
    Nombre varchar (500) not null,
    Cantidad int not null,
    IdUdMedida int not null,
    IdReceta int not null,
    constraint FK_Ingredientes_Recetas foreign key (IdReceta) references Recetas(Id),
    constraint FK_Ingredientes_UnidadesMedida foreign key (IdUdMedida) references UnidadesMedida(Id)
)

if not exists (select * from sysobjects where name='Utensilios' and xtype='U')
create table Utensilios (
    Id int primary key identity,
    Nombre varchar (500) not null,
    IdReceta int not null,
    constraint FK_Utensilios_Recetas foreign key (IdReceta) references Recetas(Id)
)

if not exists (select * from sysobjects where name='Pasos' and xtype='U')
create table Pasos (
    Id int primary key identity,
    Nombre varchar (500) not null,
    IdReceta int not null,
    constraint FK_Pasos_Recetas foreign key (IdReceta) references Recetas(Id)
)