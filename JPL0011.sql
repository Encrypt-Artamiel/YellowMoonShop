USE [master]
GO
/****** Object:  Database [YellowMoon]    Script Date: 18/10/2020 1:14:16 SA ******/
CREATE DATABASE [YellowMoon]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'YellowMoon', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\YellowMoon.mdf' , SIZE = 3136KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'YellowMoon_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\YellowMoon_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [YellowMoon] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [YellowMoon].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [YellowMoon] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [YellowMoon] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [YellowMoon] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [YellowMoon] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [YellowMoon] SET ARITHABORT OFF 
GO
ALTER DATABASE [YellowMoon] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [YellowMoon] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [YellowMoon] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [YellowMoon] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [YellowMoon] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [YellowMoon] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [YellowMoon] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [YellowMoon] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [YellowMoon] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [YellowMoon] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [YellowMoon] SET  ENABLE_BROKER 
GO
ALTER DATABASE [YellowMoon] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [YellowMoon] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [YellowMoon] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [YellowMoon] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [YellowMoon] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [YellowMoon] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [YellowMoon] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [YellowMoon] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [YellowMoon] SET  MULTI_USER 
GO
ALTER DATABASE [YellowMoon] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [YellowMoon] SET DB_CHAINING OFF 
GO
ALTER DATABASE [YellowMoon] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [YellowMoon] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [YellowMoon]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 18/10/2020 1:14:16 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Category](
	[categoryID] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 18/10/2020 1:14:16 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[detailID] [int] IDENTITY(1,1) NOT NULL,
	[orderID] [uniqueidentifier] NULL,
	[productID] [int] NULL,
	[quantity] [int] NULL,
	[priceTotal] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[detailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Orders]    Script Date: 18/10/2020 1:14:16 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Orders](
	[orderID] [uniqueidentifier] NOT NULL,
	[userID] [varchar](50) NULL,
	[total] [float] NULL,
	[orderDate] [datetime] NULL,
	[name] [varchar](100) NULL,
	[phone] [varchar](10) NULL,
	[address] [varchar](40) NULL,
	[paymentID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Payment]    Script Date: 18/10/2020 1:14:16 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Payment](
	[paymentID] [int] IDENTITY(1,1) NOT NULL,
	[paymentName] [varchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[paymentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Product]    Script Date: 18/10/2020 1:14:16 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Product](
	[productID] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
	[image] [varchar](100) NULL,
	[description] [varchar](100) NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[createDate] [date] NULL,
	[expirationDate] [date] NULL,
	[statusID] [int] NULL,
	[categoryID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Record]    Script Date: 18/10/2020 1:14:16 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Record](
	[recordID] [int] IDENTITY(1,1) NOT NULL,
	[userID] [varchar](50) NULL,
	[productID] [int] NULL,
	[dayUpdate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[recordID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Role]    Script Date: 18/10/2020 1:14:16 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Role](
	[roleID] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Status]    Script Date: 18/10/2020 1:14:16 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Status](
	[statusID] [int] IDENTITY(1,1) NOT NULL,
	[statusName] [varchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Users]    Script Date: 18/10/2020 1:14:16 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[userID] [varchar](50) NOT NULL,
	[password] [varchar](50) NULL,
	[name] [nvarchar](60) NULL,
	[phone] [varchar](10) NULL,
	[address] [varchar](40) NULL,
	[roleID] [int] NULL,
 CONSTRAINT [PK__Users__CB9A1CDF5E141141] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([categoryID], [name]) VALUES (1, N'LOTTERIA')
INSERT [dbo].[Category] ([categoryID], [name]) VALUES (2, N'ABC')
INSERT [dbo].[Category] ([categoryID], [name]) VALUES (3, N'KinhDo')
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (1, N'87a63039-cd3c-49a4-8d7d-f65354a48861', 1, 4, 400000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (2, N'87a63039-cd3c-49a4-8d7d-f65354a48861', 3, 1, 700000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (3, N'9bba028e-c6f5-42dc-bd3e-702b5a5c1843', 1, 3, 300000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (4, N'846cffe4-f4f4-416c-bd12-ed36504d7a14', 1, 6, 600000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (5, N'8808789a-8619-4852-a34a-ff6c46186cc7', 1, 4, 400000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (6, N'8808789a-8619-4852-a34a-ff6c46186cc7', 2, 2, 600000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (7, N'9a5e9f59-4e61-4507-9d25-c70a78ea478b', 1, 1, 100000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (8, N'9a5e9f59-4e61-4507-9d25-c70a78ea478b', 2, 1, 300000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (9, N'9a5e9f59-4e61-4507-9d25-c70a78ea478b', 3, 1, 700000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (10, N'e05af565-c74f-4252-a8e7-be814d187e3f', 4, 1, 230000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (11, N'1a1b7e38-f943-4d2a-9f8e-bfcabcd369c7', 4, 2, 460000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (12, N'57565f76-30fe-49ab-bead-482a7a1943b3', 3, 1, 700000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (13, N'57565f76-30fe-49ab-bead-482a7a1943b3', 4, 1, 230000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (14, N'529ecca6-9833-442f-b6f5-92b00f49f968', 2, 2, 600000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (15, N'91d48200-8b48-4b0c-b8e4-1f9b2e4d027a', 2, 1, 300000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (16, N'6f3dd1d2-2235-4971-ac24-a86f390a18d0', 2, 1, 300000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (17, N'6f3dd1d2-2235-4971-ac24-a86f390a18d0', 3, 1, 700000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (18, N'0eb86276-c7ce-4a48-8a3c-97a627f9b307', 2, 1, 300000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (19, N'0eb86276-c7ce-4a48-8a3c-97a627f9b307', 3, 1, 700000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (20, N'd7cf6fb9-44d9-40cc-930a-f4ee8664f6c2', 3, 2, 1400000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (21, N'f06aa896-2eb7-4e33-ab56-a1dd2539354d', 2, 1, 300000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (22, N'4f6c0bee-08bf-4af2-866d-87da7f10c61b', 2, 1, 300000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (23, N'4f6c0bee-08bf-4af2-866d-87da7f10c61b', 3, 1, 700000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (24, N'21a5e8ea-e080-439e-9603-7c46e23c6303', 1, 1, 100000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (25, N'4223009f-f628-44f7-a61c-830bb34bd17f', 4, 2, 460000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (26, N'8839a796-c1eb-4e94-ac5e-1ce4ab4e0516', 2, 1, 300000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (27, N'b7c88220-7a4f-49bd-ad45-747e218b0e67', 1, 1, 100000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (28, N'b7c88220-7a4f-49bd-ad45-747e218b0e67', 2, 1, 300000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (29, N'b7c88220-7a4f-49bd-ad45-747e218b0e67', 3, 1, 700000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (30, N'653f1b18-1e3d-448a-96de-198d36092bc5', 1, 1, 100000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (31, N'653f1b18-1e3d-448a-96de-198d36092bc5', 2, 1, 300000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (32, N'd441242c-e14c-4be2-912d-65f7d8a3bbda', 2, 2, 600000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (33, N'8681617e-137c-41cd-9c88-b1d91a103399', 2, 1, 300000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (34, N'378ba64c-21e6-4e5b-9df9-db234442b9d5', 1, 3, 300000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (35, N'378ba64c-21e6-4e5b-9df9-db234442b9d5', 2, 3, 900000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (36, N'378ba64c-21e6-4e5b-9df9-db234442b9d5', 3, 3, 2100000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (37, N'378ba64c-21e6-4e5b-9df9-db234442b9d5', 4, 1, 230000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (38, N'85eaabc5-4104-4a51-9b46-93da1ab333ca', 1, 1, 100000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (39, N'85eaabc5-4104-4a51-9b46-93da1ab333ca', 3, 1, 700000)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (40, N'85eaabc5-4104-4a51-9b46-93da1ab333ca', 21, 1, 65545)
INSERT [dbo].[OrderDetail] ([detailID], [orderID], [productID], [quantity], [priceTotal]) VALUES (41, N'85eaabc5-4104-4a51-9b46-93da1ab333ca', 6, 1, 150000)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'653f1b18-1e3d-448a-96de-198d36092bc5', NULL, 400000, CAST(0x0000AC5700000000 AS DateTime), N'asd', N'0797362169', N'asdasd', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'8839a796-c1eb-4e94-ac5e-1ce4ab4e0516', NULL, 300000, CAST(0x0000AC5700000000 AS DateTime), N'test', N'123', N'qweqwe', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'91d48200-8b48-4b0c-b8e4-1f9b2e4d027a', NULL, 300000, CAST(0x0000AC5700000000 AS DateTime), N'123', N'123', N'asd', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'c9390883-5799-45be-87d1-272edb50aea6', N'user', 1000000, CAST(0x0000AC5400000000 AS DateTime), N'user1', N'0123478569', N'Home', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'57565f76-30fe-49ab-bead-482a7a1943b3', NULL, 930000, CAST(0x0000AC5700000000 AS DateTime), N'me', N'123456789', N'12345', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'd441242c-e14c-4be2-912d-65f7d8a3bbda', NULL, 600000, CAST(0x0000AC5700000000 AS DateTime), N'asd', N'0112345678', N'hcm', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'9bba028e-c6f5-42dc-bd3e-702b5a5c1843', NULL, 300000, CAST(0x0000AC5700000000 AS DateTime), N'a', N'13', N'asd', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'b7c88220-7a4f-49bd-ad45-747e218b0e67', NULL, 1100000, CAST(0x0000AC5700000000 AS DateTime), N'tester', N'1213', N'zxc', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'21a5e8ea-e080-439e-9603-7c46e23c6303', NULL, 100000, CAST(0x0000AC5700000000 AS DateTime), N'a', N'123', N'aa', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'4223009f-f628-44f7-a61c-830bb34bd17f', NULL, 460000, CAST(0x0000AC5700000000 AS DateTime), N'a', N'1234567890', N'hcm', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'4f6c0bee-08bf-4af2-866d-87da7f10c61b', NULL, 1000000, CAST(0x0000AC5700000000 AS DateTime), N'aaa', N'111', N'qwe', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'529ecca6-9833-442f-b6f5-92b00f49f968', NULL, 600000, CAST(0x0000AC5700000000 AS DateTime), N'1', N'1', N'1', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'85eaabc5-4104-4a51-9b46-93da1ab333ca', N'longbnhse140368@fpt.edu.vn', 1015545, CAST(0x0000AC5700000000 AS DateTime), N'Bui Nguyen Hoang Long (K14 HCM)', N'1234567890', N'hjgsad', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'0eb86276-c7ce-4a48-8a3c-97a627f9b307', NULL, 1000000, CAST(0x0000AC5700000000 AS DateTime), N'qwe', N'121341', N'hn', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'f06aa896-2eb7-4e33-ab56-a1dd2539354d', NULL, 300000, CAST(0x0000AC5700000000 AS DateTime), N'1', N'1', N'1', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'6f3dd1d2-2235-4971-ac24-a86f390a18d0', NULL, 1000000, CAST(0x0000AC5700000000 AS DateTime), N'nganluong', N'09090909', N'hn', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'8681617e-137c-41cd-9c88-b1d91a103399', NULL, 300000, CAST(0x0000AC5700000000 AS DateTime), N'test again', N'0797362169', N'123hcm', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'e05af565-c74f-4252-a8e7-be814d187e3f', NULL, 230000, CAST(0x0000AC5700000000 AS DateTime), N'me', N'0123456789', N'2222', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'1a1b7e38-f943-4d2a-9f8e-bfcabcd369c7', NULL, 460000, CAST(0x0000AC5700000000 AS DateTime), N'q', N'1', N'123', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'9a5e9f59-4e61-4507-9d25-c70a78ea478b', NULL, 1100000, CAST(0x0000AC5700000000 AS DateTime), N'a', N'11', N'123', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'378ba64c-21e6-4e5b-9df9-db234442b9d5', N'longbnhse140368@fpt.edu.vn', 3530000, CAST(0x0000AC5700000000 AS DateTime), N'Bui Nguyen Hoang Long (K14 HCM)', N'01234567', N'124512fu', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'846cffe4-f4f4-416c-bd12-ed36504d7a14', NULL, 600000, CAST(0x0000AC5700000000 AS DateTime), N'me', N'1234567890', N'by me at home', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'd7cf6fb9-44d9-40cc-930a-f4ee8664f6c2', NULL, 1400000, CAST(0x0000AC5700000000 AS DateTime), N'3', N'3', N'qeqe', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'87a63039-cd3c-49a4-8d7d-f65354a48861', N'user', 1100000, CAST(0x0000AC5400000000 AS DateTime), N'user1', N'0123478569', N'Home', 1)
INSERT [dbo].[Orders] ([orderID], [userID], [total], [orderDate], [name], [phone], [address], [paymentID]) VALUES (N'8808789a-8619-4852-a34a-ff6c46186cc7', NULL, 1000000, CAST(0x0000AC5700000000 AS DateTime), N'ads', N'1478523690', N'hcm', 1)
SET IDENTITY_INSERT [dbo].[Payment] ON 

INSERT [dbo].[Payment] ([paymentID], [paymentName]) VALUES (1, N'On cash when deliveried')
SET IDENTITY_INSERT [dbo].[Payment] OFF
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (1, N'cake 1', N'C:\Users\DELL\Pictures\Saved Pictures\lotte1.jpg', N'none', 100000, 1216, CAST(0xA2410B00 AS Date), CAST(0x44420B00 AS Date), 1, 2)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (2, N'cake 2', N'C:\Users\DELL\Pictures\Saved Pictures\lotte2.jpg', N'better than 1', 300000, 981, CAST(0x5A410B00 AS Date), CAST(0x04420B00 AS Date), 1, 1)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (3, N'cake 3', N'C:\Users\DELL\Pictures\Saved Pictures\lotte3.jpg', N'aaa', 700000, 137, CAST(0x59410B00 AS Date), CAST(0xFC410B00 AS Date), 1, 1)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (4, N'cake 4', N'C:\Users\DELL\Pictures\Saved Pictures\lotte4.jpg', N'cake best', 230000, 993, CAST(0x1B410B00 AS Date), CAST(0x0B420B00 AS Date), 1, 2)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (5, N'cake 5', N'C:\Users\DELL\Pictures\Saved Pictures\lotte5.jpg', N'created now', 100000, 1000, CAST(0xA2410B00 AS Date), CAST(0xC0410B00 AS Date), 1, 2)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (6, N'cake 6', N'C:\Users\DELL\Pictures\Saved Pictures\lotte6.jpg', N'cake 6', 150000, 157, CAST(0xB1410B00 AS Date), CAST(0x0C420B00 AS Date), 1, 1)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (7, N'cake 7 ', N'C:\Users\DELL\Pictures\Saved Pictures\lotte7.jpg', N'7', 450000, 800, CAST(0xA2410B00 AS Date), CAST(0x2F420B00 AS Date), 1, 1)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (8, N'cake 9 ', N'C:\Users\DELL\Pictures\Saved Pictures\lotte4.jpg', N'zxc', 800000, 1500, CAST(0xA2410B00 AS Date), CAST(0xB6410B00 AS Date), 1, 1)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (9, N'cake 9 ', N'C:\Users\DELL\Pictures\Saved Pictures\yellowMoon.png', N'asd', 580000, 1500, CAST(0xA2410B00 AS Date), CAST(0xC0410B00 AS Date), 1, 1)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (10, N'cake 10 ', N'C:\Users\DELL\Pictures\Saved Pictures\lotte5.jpg', N'cvb', 120000, 158, CAST(0xA2410B00 AS Date), CAST(0xEF410B00 AS Date), 1, 1)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (11, N'cakae11', N'C:\Users\DELL\Pictures\Saved Pictures\lotte3.jpg', N'jkhg', 600000, 132, CAST(0xA2410B00 AS Date), CAST(0x13420B00 AS Date), 1, 2)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (12, N'cake123', N'C:\Users\DELL\Pictures\Saved Pictures\lotte7.jpg', N'hqjweg', 1234567, 12345, CAST(0xA2410B00 AS Date), CAST(0xC0410B00 AS Date), 1, 3)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (13, N'cake444', N'C:\Users\DELL\Pictures\Saved Pictures\lotte4.jpg', N'zxceq', 457899, 457, CAST(0xA2410B00 AS Date), CAST(0xFD410B00 AS Date), 1, 3)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (14, N'cake 78', N'C:\Users\DELL\Pictures\Saved Pictures\yellowMoon.png', N'cake', 444444, 1245, CAST(0x70410B00 AS Date), CAST(0xF0410B00 AS Date), 1, 1)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (15, N'cake 15', N'C:\Users\DELL\Pictures\Saved Pictures\lotte3.jpg', N'tqyet', 4782103, 7821, CAST(0xC0410B00 AS Date), CAST(0x13420B00 AS Date), 1, 3)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (16, N'cake 16 ', N'C:\Users\DELL\Pictures\Saved Pictures\lotte1.jpg', N'qwetgy', 456212, 5671, CAST(0xA2410B00 AS Date), CAST(0x53420B00 AS Date), 1, 1)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (17, N'cake 17 ', N'C:\Users\DELL\Pictures\Saved Pictures\lotte2.jpg', N'465', 452134, 123456, CAST(0xB0410B00 AS Date), CAST(0xC0410B00 AS Date), 1, 3)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (18, N'cake 19 ', N'C:\Users\DELL\Pictures\Saved Pictures\lotte4.jpg', N'qhjwkeh', 564210, 24324, CAST(0xBE410B00 AS Date), CAST(0x4A420B00 AS Date), 1, 1)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (19, N'cake 18 ', N'C:\Users\DELL\Pictures\Saved Pictures\lotte7.jpg', N'qweqwe', 132442, 454, CAST(0xB1410B00 AS Date), CAST(0x35420B00 AS Date), 1, 1)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (20, N'cake 20 ', N'C:\Users\DELL\Pictures\Saved Pictures\lotte3.jpg', N'4qweasd', 12132, 1231, CAST(0xA2410B00 AS Date), CAST(0x0C420B00 AS Date), 1, 1)
INSERT [dbo].[Product] ([productID], [name], [image], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [categoryID]) VALUES (21, N'cake 20 ', N'C:\Users\DELL\Pictures\Saved Pictures\lotte2.jpg', N'aejkqwh', 65545, 4563, CAST(0xF6410B00 AS Date), CAST(0x34420B00 AS Date), 1, 1)
SET IDENTITY_INSERT [dbo].[Product] OFF
SET IDENTITY_INSERT [dbo].[Record] ON 

INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (1, N'admin', 3, CAST(0xAF410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (2, N'admin', 3, CAST(0xAF410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (3, N'admin', 1, CAST(0xAF410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (4, N'admin', 1, CAST(0xAF410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (5, N'admin', 1, CAST(0xAF410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (6, N'admin', 1, CAST(0xAF410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (7, N'admin', 1, CAST(0xAF410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (8, N'admin', 1, CAST(0xAF410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (9, N'admin', 1, CAST(0xAF410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (10, N'admin', 1, CAST(0xAF410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (11, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (12, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (13, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (14, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (15, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (16, N'admin', 2, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (17, N'admin', 2, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (18, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (19, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (20, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (21, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (22, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (23, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (24, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (25, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (26, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (27, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (28, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (29, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (30, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (31, N'admin', 1, CAST(0xB2410B00 AS Date))
INSERT [dbo].[Record] ([recordID], [userID], [productID], [dayUpdate]) VALUES (32, N'admin', 1, CAST(0xB2410B00 AS Date))
SET IDENTITY_INSERT [dbo].[Record] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([roleID], [name]) VALUES (1, N'admin')
INSERT [dbo].[Role] ([roleID], [name]) VALUES (2, N'user')
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[Status] ON 

INSERT [dbo].[Status] ([statusID], [statusName]) VALUES (1, N'Active')
INSERT [dbo].[Status] ([statusID], [statusName]) VALUES (2, N'InActive')
SET IDENTITY_INSERT [dbo].[Status] OFF
INSERT [dbo].[Users] ([userID], [password], [name], [phone], [address], [roleID]) VALUES (N'admin', N'admin', N'admin1', N'0123456789', N'FU', 1)
INSERT [dbo].[Users] ([userID], [password], [name], [phone], [address], [roleID]) VALUES (N'bhoanglong@gmail.com', N'102842799343727389641', N'hoàng long bùi nguyễn', NULL, NULL, 2)
INSERT [dbo].[Users] ([userID], [password], [name], [phone], [address], [roleID]) VALUES (N'longbnhse140368@fpt.edu.vn', N'100531653636093052815', N'Bui Nguyen Hoang Long (K14 HCM)', NULL, NULL, 2)
INSERT [dbo].[Users] ([userID], [password], [name], [phone], [address], [roleID]) VALUES (N'user', N'user', N'user1', N'0123478569', N'Home', 2)
ALTER TABLE [dbo].[Orders] ADD  DEFAULT (newid()) FOR [orderID]
GO
ALTER TABLE [dbo].[Orders] ADD  DEFAULT ((1)) FOR [paymentID]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([orderID])
REFERENCES [dbo].[Orders] ([orderID])
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([productID])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([paymentID])
REFERENCES [dbo].[Payment] ([paymentID])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([userID])
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([categoryID])
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([statusID])
REFERENCES [dbo].[Status] ([statusID])
GO
ALTER TABLE [dbo].[Record]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([productID])
GO
ALTER TABLE [dbo].[Record]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([userID])
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Role1] FOREIGN KEY([roleID])
REFERENCES [dbo].[Role] ([roleID])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Role1]
GO
USE [master]
GO
ALTER DATABASE [YellowMoon] SET  READ_WRITE 
GO
