#CREATE DATABASE excelcerts CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use excelcerts;

create table version (
	id 				int 			NOT NULL AUTO_INCREMENT,
    imported 		datetime		not null,
    importedByName	varchar(128)	null,
     PRIMARY KEY (id)
);

create table cert (
	  fkVersionId			int 		  not null
	, finished 				varchar(32)   null	# "Erledi-gung"
	, vb1 					varchar(255)  null
    , vb2 					varchar(32)   null
    , vb3 					varchar(32)   null
    , vb4 					varchar(32)   null
    , manufacturer 			varchar(255)  null	# "Name des          Herstellers"
    , manufacturerContact 	varchar(128)  null	# "Anprechperson beim Hersteller"
    , manufacturerLocation 	varchar(128)  null	# "Produktionsstandort des Herstellers"
    , productDescription 	varchar(1024) null	# "Produktbeschreibung (Norm/en)"
    , auditMonthLatest 		varchar(32)	  null	# "Auditmonat spätestens"
    , checkedBy 			varchar(128)  null	# "PÜ-Stelle"
    , auditor 				varchar(128)  null	# "Auditor"
    , checkOf 				varchar(32)	  null	# "Kontrolle der ..."
    , probeTaken 			varchar(32)   null	# "Probe-   nahme"
    , AoC 					varchar(32)   null	# "AoC"									--> laut Christ "Zahl"
    , n1  					varchar(32)   null
    , n2  					varchar(32)   null
    , n3  					varchar(32)   null
    , n4  					varchar(32)   null
    , n5  					varchar(32)   null
    , n6  					varchar(32)   null
    , n7  					varchar(32)   null
    , n8  					varchar(32)   null
    , n9  					varchar(32)   null
    , n10 					varchar(32)   null
    , certIssued 			date 		  null 	# "Ausstel-lung Zertifikat"
	, monitorContractCompany 				varchar(32)   null	# "ÜV                   Z mit Fa."   	--> Überwachungsvertrag mit Firma
    , monitorContractInspectionAuthority	varchar(32)   null	# "ÜV                     Z mit PÜ"  	--> Überwachungsvertrag mit Prüfstelle
    , certNumber 			varchar(32)   null	# "Zertifikatsnr."
    , BPVbranches			varchar(32)	  null	# "BPV-Fachgebiete" 					--> "Bauprodukteverordnung-Fachgebiete"
    , countryforInvoice 	varchar(64)   null	# "Land für Faktura"
    , industry				varchar(64)   null	# "Branche"
    , internalCostLocation	varchar(32)   null	# "interne KST"
    , report2018			varchar(32)   null	# "Bericht 2018"
    , report2017			varchar(32)   null	# "Bericht 2017"
    , FOREIGN KEY (fkVersionId) REFERENCES version(id)
);

