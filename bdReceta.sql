if not exists (select name from master.dbo.sysdatabases where name='Recetas')
create database Recetas
go

use Recetas 

if not exists (select * from sysobjects where name='Categorias' and xtype='U')
create table Categorias (
    IdCategoria int primary key,
    NombreCategoria varchar (50) not null,
)

if not exists (select * from sysobjects where name='Recetas' and xtype='U')
create table Recetas (
    IdReceta int primary key,
    NombreReceta varchar (100) not null,
    Descripcion varchar (100) not null,
    Imagen binary not null,
    Duracion int not null,
    Personas int not null,
    Estado int not null,
    Dificultad varchar (100) not null,
    Fecha date not null,
    Autor varchar (100) not null,
    IdCategoria int not null,
    add constraint FK_Recetas_Categorias foreign key (IdCategoria) references Categoria(IdCategoria) 
)

if not exists (select * from sysobjects where name='UnidadesMedida' and xtype='U')
create table UnidadesMedida (
    IdUdMedida int primary key,
    NombreUdMedida varchar (50) not null,
)

if not exists (select * from sysobjects where name='Ingredientes' and xtype='U')
create table Ingredientes (
    IdIngrediente int primary key,
    NombreIngrediente varchar (59) not null,
    Cantidad int not null,
    IdReceta int not null,
    IdUdMedida int not null,
    add constraint FK_Ingredientes_Recetas foreign key (IdReceta) references Recetas(IdReceta)
    add constraint FK_Ingredientes_UnidadesMedida foreign key (IdUdMedida) references UnidadesMedida(IdUdMedida)
)

if not exists (select * from sysobjects where name='Utensilios' and xtype='U')
create table Utensilios (
    IdUtensilio int primary key,
    NombreUtensilio varchar (59) not null,
    IdReceta int not null,
    add constraint FK_Utensilios_Recetas foreign key (IdReceta) references Recetas(IdReceta)
)

if not exists (select * from sysobjects where name='Pasos' and xtype='U')
create table Pasos (
    IdPaso int primary key,
    NombrePaso varchar (59) not null,
    IdReceta int not null,
    add constraint FK_Pasos_Recetas foreign key (IdReceta) references Recetas(IdReceta)
)