CREATE DATABASE excelcerts CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use excelcerts;

create table cert (
	finished 				date null			# "Erledi-gung"
	, vb1 					date null
    , vb2 					date null
    , vb3 					date null
    , vb4 					date null
    , manufacturer 			varchar(255)		# "Name des          Herstellers"
    , manufacturerContact 	varchar(128)		# "Anprechperson beim Hersteller"
    , manufacturerLocation 	varchar(128)		# "Produktionsstandort des Herstellers"
    , productDescription 	varchar(255)		# "Produktbeschreibung (Norm/en)"
    , auditMonthLatest 		date				# "Auditmonat spätestens"
    , checkedBy 			varchar(128)		# "PÜ-Stelle"
    , auditor 				varchar(128)		# "Auditor"
    , checkOf 				varchar(32)			# "Kontrolle der ..."
    , probeTaken 			char(1)				# "Probe-   nahme"
    , AoC 					int					# "AoC"									--> laut Christ "Zahl"
    , n1  					varchar(32) null
    , n2  					varchar(32) null
    , n3  					varchar(32) null
    , n4  					varchar(32) null
    , n5  					varchar(32) null
    , n6  					varchar(32) null
    , n7  					varchar(32) null
    , n8  					varchar(32) null
    , n9  					varchar(32) null
    , n10 					varchar(32) null
    , certIssued 			date null 			# "Ausstel-lung Zertifikat"
	, fack1 				varchar(32) null	# "ÜV                   Z mit Fa."   	--> Überwachungsvertrag mit Firma
    , fack2 				varchar(32) null	# "ÜV                     Z mit PÜ"  	--> Überwachungsvertrag mit Prüfstelle
    , certNumber 			varchar(32) null	# "Zertifikatsnr."
    , BPVbranches			date null			# "BPV-Fachgebiete" 					--> "Bauprodukteverordnung-Fachgebiete"
    , countryforInvoice 	varchar(64) null	# "Land für Faktura"
    , industry				varchar(64) null	# "Branche"
    , internalCostLocation	varchar(32) null	# "interne KST"
    , report2018			varchar(32) null	# "Bericht 2018"
    , report2017			varchar(32) null	# "Bericht 2017"
);

