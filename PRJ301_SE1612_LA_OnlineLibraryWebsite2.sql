USE [master]
GO
/****** Object:  Database [PRJ301_SE1612_LA_OnlineLibraryWebsite2]    Script Date: 3/17/2022 12:18:16 AM ******/
CREATE DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PRJ301_SE1612_LA_OnlineLibraryWebsite2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\PRJ301_SE1612_LA_OnlineLibraryWebsite2.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'PRJ301_SE1612_LA_OnlineLibraryWebsite2_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\PRJ301_SE1612_LA_OnlineLibraryWebsite2_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PRJ301_SE1612_LA_OnlineLibraryWebsite2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET ARITHABORT OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET  ENABLE_BROKER 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET  MULTI_USER 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET DELAYED_DURABILITY = DISABLED 
GO
USE [PRJ301_SE1612_LA_OnlineLibraryWebsite2]
GO
/****** Object:  Table [dbo].[Author]    Script Date: 3/17/2022 12:18:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Author](
	[AuthorID] [varchar](20) NOT NULL,
	[AuthorName] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[AuthorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Book]    Script Date: 3/17/2022 12:18:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Book](
	[BookID] [varchar](10) NOT NULL,
	[BookName] [varchar](50) NOT NULL,
	[BookURL] [varchar](20) NOT NULL,
	[PublisherID] [varchar](20) NOT NULL,
	[PublishingYear] [decimal](4, 0) NOT NULL,
	[Price] [float] NOT NULL,
	[Quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[BookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[BookAuthor]    Script Date: 3/17/2022 12:18:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BookAuthor](
	[AuthorID] [varchar](20) NOT NULL,
	[BookID] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[AuthorID] ASC,
	[BookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CallCard]    Script Date: 3/17/2022 12:18:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CallCard](
	[CallCardID] [varchar](20) NOT NULL,
	[UserID] [varchar](20) NOT NULL,
	[BorrowDate] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CallCardID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CallCardDetail]    Script Date: 3/17/2022 12:18:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CallCardDetail](
	[CallCardID] [varchar](20) NOT NULL,
	[BookID] [varchar](10) NOT NULL,
	[Status] [decimal](1, 0) NOT NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Member]    Script Date: 3/17/2022 12:18:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Member](
	[MemberID] [varchar](20) NOT NULL,
	[MemberPassword] [varchar](20) NOT NULL,
	[FullName] [varchar](30) NOT NULL,
	[Address] [varchar](50) NOT NULL,
	[Phone] [varchar](10) NOT NULL,
	[Role] [decimal](1, 0) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Publisher]    Script Date: 3/17/2022 12:18:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Publisher](
	[PublisherID] [varchar](20) NOT NULL,
	[PublisherName] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[PublisherID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ReturnCard]    Script Date: 3/17/2022 12:18:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ReturnCard](
	[ReturnCardID] [varchar](20) NOT NULL,
	[UserID] [varchar](20) NOT NULL,
	[ReturnDate] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ReturnCardID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ReturnCardDetail]    Script Date: 3/17/2022 12:18:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ReturnCardDetail](
	[ReturnCardID] [varchar](20) NOT NULL,
	[BookID] [varchar](10) NOT NULL,
	[CitePirce] [float] NULL,
	[Late] [int] NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A123', N'Sir Alex')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A124', N'Mada Nighi')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A125', N'Nick')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A126', N'Jack')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A127', N'Maliga Afghana')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A128', N'Kimo Naha')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A129', N'Elanga')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A130', N'Phil Jones')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A131', N'Fred')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A132', N'Juan Mata')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A133', N'Marcus Rashford')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A134', N'Mason Greenwood')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A135', N'Jesse Lingard')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A136', N'Raphael Varane')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A137', N'Jadon Sancho')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A138', N'Sir Alex')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A139', N'Warren Buffet')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A140', N'Harry Maguire')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A141', N'Diogo Dalot')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A142', N'Luke Shaw')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A143', N'Nick Maha')
GO
INSERT [dbo].[Author] ([AuthorID], [AuthorName]) VALUES (N'A144', N'Jack Kata')
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B123', N'Mario Puzo', N'12.jpg', N'Pb123', CAST(2020 AS Decimal(4, 0)), 72100, 14)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B124', N'Blockchain', N'13.jpg', N'Pb123', CAST(2021 AS Decimal(4, 0)), 66000, 10)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B125', N'Gatsby', N'14.jpg', N'Pb123', CAST(2018 AS Decimal(4, 0)), 49200, 15)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B126', N'Forrest Gump', N'15.jpg', N'Pb123', CAST(2017 AS Decimal(4, 0)), 81000, 15)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B127', N'JEFFREY ARCHER', N'16.jpg', N'Pb123', CAST(2026 AS Decimal(4, 0)), 100000, 15)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B128', N'Ajahn Chah', N'17.jpg', N'Pb123', CAST(2018 AS Decimal(4, 0)), 50000, 5)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B129', N'The Silence of the Lambs', N'18.jpg', N'Pb124', CAST(2020 AS Decimal(4, 0)), 70000, 20)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B130', N'To Kill a Mockingbird', N'19.jpg', N'Pb124', CAST(2020 AS Decimal(4, 0)), 74000, 20)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B132', N'Tribe of mentor', N'20.jpg', N'Pb124', CAST(2020 AS Decimal(4, 0)), 74000, 20)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B133', N'Why Digital Transformations Fail', N'21.jpg', N'Pb124', CAST(2020 AS Decimal(4, 0)), 90000, 20)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B134', N'Swipe To Unlock', N'22.jpg', N'Pb124', CAST(2020 AS Decimal(4, 0)), 21000, 20)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B135', N'Keanu Reeves ', N'23.jpg', N'Pb124', CAST(2020 AS Decimal(4, 0)), 84000, 20)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B136', N'Think And Grow Rich', N'24.jpg', N'Pb124', CAST(2020 AS Decimal(4, 0)), 74000, 10)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B137', N'The Science Of Cooking', N'25.jpg', N'Pb125', CAST(2020 AS Decimal(4, 0)), 90000, 8)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B138', N'Sapiens', N'26.jpg', N'Pb126', CAST(2020 AS Decimal(4, 0)), 51000, 9)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B139', N'Warren Buffet', N'27.jpg', N'Pb124', CAST(2020 AS Decimal(4, 0)), 39000, 15)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B140', N'Many Lives - Many Times', N'28.jpg', N'Pb124', CAST(2021 AS Decimal(4, 0)), 32000, 5)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B141', N'Being In Love', N'29.jpg', N'Pb124', CAST(2020 AS Decimal(4, 0)), 50000, 15)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B142', N'Storytelling With Data', N'30.jpg', N'Pb124', CAST(2016 AS Decimal(4, 0)), 35000, 5)
GO
INSERT [dbo].[Book] ([BookID], [BookName], [BookURL], [PublisherID], [PublishingYear], [Price], [Quantity]) VALUES (N'B143', N'Homo Deus', N'31.jpg', N'Pb124', CAST(2020 AS Decimal(4, 0)), 50000, 10)
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A123', N'B125')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A124', N'B126')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A125', N'B127')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A126', N'B128')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A127', N'B129')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A128', N'B130')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A130', N'B132')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A131', N'B133')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A132', N'B134')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A133', N'B135')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A134', N'B136')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A135', N'B137')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A136', N'B138')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A137', N'B124')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A137', N'B139')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A138', N'B123')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A138', N'B140')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A139', N'B141')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A140', N'B142')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A141', N'B125')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A141', N'B143')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A142', N'B126')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A143', N'B127')
GO
INSERT [dbo].[BookAuthor] ([AuthorID], [BookID]) VALUES (N'A144', N'B128')
GO
INSERT [dbo].[CallCard] ([CallCardID], [UserID], [BorrowDate]) VALUES (N'3112204455', N'member', CAST(N'2022-03-16' AS Date))
GO
INSERT [dbo].[CallCardDetail] ([CallCardID], [BookID], [Status]) VALUES (N'3112204455', N'B123', CAST(0 AS Decimal(1, 0)))
GO
INSERT [dbo].[Member] ([MemberID], [MemberPassword], [FullName], [Address], [Phone], [Role]) VALUES (N'member', N'123', N'Nguyen Ho Tien Dat', N'Bien Hoa - Dong Nai', N'0123456789', CAST(0 AS Decimal(1, 0)))
GO
INSERT [dbo].[Member] ([MemberID], [MemberPassword], [FullName], [Address], [Phone], [Role]) VALUES (N'vip', N'123', N'Nguyen Ho Tien Dat', N'Bien Hoa - Dong Nai', N'0123456780', CAST(1 AS Decimal(1, 0)))
GO
INSERT [dbo].[Publisher] ([PublisherID], [PublisherName]) VALUES (N'Pb123', N'NXB Ha Noi')
GO
INSERT [dbo].[Publisher] ([PublisherID], [PublisherName]) VALUES (N'Pb124', N'NXB TP HCM')
GO
INSERT [dbo].[Publisher] ([PublisherID], [PublisherName]) VALUES (N'Pb125', N'NXB Kim Dong')
GO
INSERT [dbo].[Publisher] ([PublisherID], [PublisherName]) VALUES (N'Pb126', N'NXB Can Tho')
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Member__5C7E359ED9AA8FED]    Script Date: 3/17/2022 12:18:16 AM ******/
ALTER TABLE [dbo].[Member] ADD UNIQUE NONCLUSTERED 
(
	[Phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Book]  WITH CHECK ADD FOREIGN KEY([PublisherID])
REFERENCES [dbo].[Publisher] ([PublisherID])
GO
ALTER TABLE [dbo].[BookAuthor]  WITH CHECK ADD FOREIGN KEY([AuthorID])
REFERENCES [dbo].[Author] ([AuthorID])
GO
ALTER TABLE [dbo].[BookAuthor]  WITH CHECK ADD FOREIGN KEY([BookID])
REFERENCES [dbo].[Book] ([BookID])
GO
ALTER TABLE [dbo].[CallCard]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[Member] ([MemberID])
GO
ALTER TABLE [dbo].[CallCardDetail]  WITH CHECK ADD FOREIGN KEY([BookID])
REFERENCES [dbo].[Book] ([BookID])
GO
ALTER TABLE [dbo].[CallCardDetail]  WITH CHECK ADD FOREIGN KEY([CallCardID])
REFERENCES [dbo].[CallCard] ([CallCardID])
GO
ALTER TABLE [dbo].[ReturnCard]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[Member] ([MemberID])
GO
ALTER TABLE [dbo].[ReturnCardDetail]  WITH CHECK ADD FOREIGN KEY([BookID])
REFERENCES [dbo].[Book] ([BookID])
GO
ALTER TABLE [dbo].[ReturnCardDetail]  WITH CHECK ADD FOREIGN KEY([ReturnCardID])
REFERENCES [dbo].[ReturnCard] ([ReturnCardID])
GO
USE [master]
GO
ALTER DATABASE [PRJ301_SE1612_LA_OnlineLibraryWebsite2] SET  READ_WRITE 
GO
