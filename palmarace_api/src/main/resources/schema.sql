DROP SCHEMA IF EXISTS palmarace_db;
CREATE SCHEMA palmarace_db;
USE palmarace_db;

CREATE TABLE COUNTRY (
    country_code CHAR(2) PRIMARY KEY,
    continent VARCHAR(13) NOT NULL,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE PROFILE (
    profile_code CHAR(3) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE PLACE (
    place_id INT PRIMARY KEY AUTO_INCREMENT,
    country_code CHAR(2) NOT NULL,
    region VARCHAR(100) NOT NULL,
	city VARCHAR(100) NOT NULL,
	CONSTRAINT FK_PLACE_COUNTRY FOREIGN KEY (country_code) REFERENCES COUNTRY(country_code)
);

CREATE INDEX IDX_PLACE_COUNTRY_CODE ON PLACE (country_code);

CREATE TABLE IMAGE_TYPE (
    image_type_code CHAR(3) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IMAGE (
    image_id INT PRIMARY KEY,
    image_type_code VARCHAR(3) NOT NULL,
	CONSTRAINT FK_IMAGE_IMAGE_TYPE FOREIGN KEY (image_type_code) REFERENCES IMAGE_TYPE(image_type_code)
);

CREATE INDEX IDX_IMAGE_IMAGE_TYPE_CODE ON IMAGE (image_type_code);

CREATE TABLE GPX_TRK (
    gpx_trk_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
	gpx_version VARCHAR(10) NOT NULL,
	creator VARCHAR(255) NOT NULL
);

CREATE TABLE GPX_TRKPT (
    gpx_trkpt_id INT PRIMARY KEY AUTO_INCREMENT,
    gpx_trk_id INT NOT NULL,
    position INT NOT NULL,
	lat DECIMAL(8,6) NOT NULL,
	lon DECIMAL(8,6) NOT NULL,
	ele DECIMAL (8,6) NOT NULL,
	CONSTRAINT FK_GPX_TRKPT_GPX_TRK FOREIGN KEY (gpx_trk_id) REFERENCES GPX_TRK(gpx_trk_id)
);

CREATE INDEX IDX_GPX_TRKPT_GPX_TRK_ID ON GPX_TRKPT (gpx_trk_id);

CREATE TABLE SPORT (
    sport_code CHAR(3) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE EVENT (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    event_date DATE NOT NULL,
	place_id INT NOT NULL,
	sport_code CHAR(3) NOT NULL,
	gpx_trk_id INT,
	logo INT,
	distance DECIMAL(7,3) NOT NULL,
	total_participants INT,
	total_finishers INT,
	parent_event_id INT,
	CONSTRAINT FK_EVENT_PLACE FOREIGN KEY (place_id) REFERENCES PLACE(place_id),
	CONSTRAINT FK_EVENT_SPORT FOREIGN KEY (sport_code) REFERENCES SPORT(sport_code),
	CONSTRAINT FK_EVENT_GPX_TRK FOREIGN KEY (gpx_trk_id) REFERENCES GPX_TRK(gpx_trk_id),
	CONSTRAINT FK_EVENT_IMAGE FOREIGN KEY (logo) REFERENCES IMAGE(image_id),
	CONSTRAINT FK_EVENT_EVENT FOREIGN KEY (parent_event_id) REFERENCES EVENT(event_id)
);

CREATE INDEX IDX_EVENT_PLACE_ID ON EVENT (place_id);
CREATE INDEX IDX_EVENT_SPORT_CODE ON EVENT (sport_code);
CREATE INDEX IDX_EVENT_GPX_TRK_ID ON EVENT (gpx_trk_id);
CREATE INDEX IDX_EVENT_EVENT_DATE ON EVENT (event_date);

CREATE TABLE ATHLETE (
    athlete_id INT PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
	date_birth DATE NOT NULL,
	email VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	profile_picture INT,
	country_code CHAR(2) NOT NULL,
	bio TEXT,
	creation_date DATETIME NOT NULL,
	profile_code CHAR(3) NOT NULL,
	CONSTRAINT FK_ATHLETE_IMAGE FOREIGN KEY (profile_picture) REFERENCES IMAGE(image_id),
	CONSTRAINT FK_ATHLETE_COUNTRY FOREIGN KEY (country_code) REFERENCES COUNTRY(country_code),
	CONSTRAINT FK_ATHLETE_PROFILE FOREIGN KEY (profile_code) REFERENCES PROFILE(profile_code)
);

CREATE INDEX IDX_ATHLETE_COUNTRY_CODE ON ATHLETE (country_code);
CREATE INDEX IDX_ATHLETE_PROFILE_CODE ON ATHLETE (profile_code);

CREATE TABLE CONNECTION (
    connection_id INT PRIMARY KEY AUTO_INCREMENT,
    athlete_follower INT NOT NULL,
    athlete_followed INT NOT NULL,
	creation_date DATETIME NOT NULL,
	CONSTRAINT FK_CONNECTION_ATHLETE_FOLLOWER FOREIGN KEY (athlete_follower) REFERENCES ATHLETE(athlete_id),
	CONSTRAINT FK_CONNECTION_ATHLETE_FOLLOWED FOREIGN KEY (athlete_followed) REFERENCES ATHLETE(athlete_id)
);

CREATE INDEX IDX_CONNECTION_ATHLETE ON CONNECTION (athlete_follower, athlete_followed);

CREATE TABLE TAG (
    tag_code CHAR(3) PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE ATHLETE_EVENT (
    athlete_event_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT NOT NULL,
    finisher BOOLEAN NOT NULL DEFAULT false,
	finish_time TIME,
	overall_rank INT,
	bib_number INT,
	athlete_id INT NOT NULL,
	CONSTRAINT FK_ATHLETE_EVENT_EVENT FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
	CONSTRAINT FK_ATHLETE_EVENT_ATHLETE FOREIGN KEY (athlete_id) REFERENCES ATHLETE(athlete_id)
);

CREATE INDEX IDX_ATHLETE_EVENT_EVENT_ID ON ATHLETE_EVENT (event_id);
CREATE INDEX IDX_ATHLETE_EVENT_ATHLETE_ID ON ATHLETE_EVENT (athlete_id);
CREATE INDEX IDX_ATHLETE_EVENT_FINISH_TIME ON ATHLETE_EVENT (finish_time);


CREATE TABLE ATHLETE_EVENT_PHOTO (
    athlete_event_photo_id INT PRIMARY KEY,
	athlete_event_id INT NOT NULL,
    image_id INT NOT NULL,
	CONSTRAINT FK_ATHLETE_EVENT_PHOTO_IMAGE FOREIGN KEY (image_id) REFERENCES IMAGE(image_id),
	CONSTRAINT FK_ATHLETE_EVENT_PHOTO_ATHLETE_EVENT FOREIGN KEY (athlete_event_id) REFERENCES ATHLETE_EVENT(athlete_event_id)
);

CREATE INDEX IDX_ATHLETE_EVENT_PHOTO_IMAGE_ID ON ATHLETE_EVENT_PHOTO (image_id);
CREATE INDEX IDX_ATHLETE_EVENT_PHOTO_ATHLETE_EVENT_ID ON ATHLETE_EVENT_PHOTO (athlete_event_id);

CREATE TABLE ATHLETE_EVENT_PHOTO_TAG (
    athlete_event_photo_tag_id INT PRIMARY KEY AUTO_INCREMENT,
	tag_code CHAR(3) NOT NULL,
    athlete_event_photo_id INT NOT NULL,
	CONSTRAINT FK_ATHLETE_EVENT_PHOTO_TAG_TAG FOREIGN KEY (tag_code) REFERENCES TAG(tag_code),
	CONSTRAINT FK_ATHLETE_EVENT_PHOTO_TAG_ATHLETE_EVENT_PHOTO FOREIGN KEY (athlete_event_photo_id) REFERENCES ATHLETE_EVENT_PHOTO(athlete_event_photo_id)
);

CREATE INDEX IDX_ATHLETE_EVENT_PHOTO_TAG_TAG_CODE ON ATHLETE_EVENT_PHOTO_TAG (tag_code);
CREATE INDEX IDX_ATHLETE_EVENT_PHOTO_TAG_ATHLETE_EVENT_PHOTO_ID ON ATHLETE_EVENT_PHOTO_TAG (athlete_event_photo_id);

CREATE TABLE KUDO (
    kudo_id INT PRIMARY KEY AUTO_INCREMENT,
    athlete_event_id INT NOT NULL,
    author INT NOT NULL,
	creation_date DATETIME NOT NULL,
	CONSTRAINT FK_KUDO_ATHLETE_EVENT FOREIGN KEY (athlete_event_id) REFERENCES ATHLETE_EVENT(athlete_event_id),
	CONSTRAINT FK_KUDO_ATHLETE FOREIGN KEY (author) REFERENCES ATHLETE(athlete_id)
);

CREATE INDEX IDX_KUDO_ATHLETE_EVENT_ID ON KUDO (athlete_event_id);
CREATE INDEX IDX_KUDO_AUTHOR ON KUDO (author);

CREATE TABLE COMMENT (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    athlete_event_id INT NOT NULL,
    author INT NOT NULL,
	content TEXT NOT NULL,
	creation_date DATETIME NOT NULL,
	CONSTRAINT FK_COMMENT_ATHLETE_EVENT FOREIGN KEY (athlete_event_id) REFERENCES ATHLETE_EVENT(athlete_event_id),
	CONSTRAINT FK_COMMENT_ATHLETE FOREIGN KEY (author) REFERENCES ATHLETE(athlete_id)
);

CREATE INDEX IDX_COMMENT_ATHLETE_EVENT_ID ON COMMENT (athlete_event_id);
CREATE INDEX IDX_COMMENT_AUTHOR ON COMMENT (author);

INSERT INTO COUNTRY (country_code, continent, name) VALUES
	('AF', 'Asia', 'Afghanistan'),
	('AL', 'Europe', 'Albania'),
	('DZ', 'Africa', 'Algeria'),
	('AS', 'Oceania', 'American Samoa'),
	('AD', 'Europe', 'Andorra'),
	('AO', 'Africa', 'Angola'),
	('AI', 'North America', 'Anguilla'),
	('AQ', 'Antarctica', 'Antarctica'),
	('AG', 'North America', 'Antigua and Barbuda'),
	('AR', 'South America', 'Argentina'),
	('AM', 'Asia', 'Armenia'),
	('AW', 'North America', 'Aruba'),
	('AU', 'Oceania', 'Australia'),
	('AT', 'Europe', 'Austria'),
	('AZ', 'Asia', 'Azerbaijan'),
	('BS', 'North America', 'Bahamas'),
	('BH', 'Asia', 'Bahrain'),
	('BD', 'Asia', 'Bangladesh'),
	('BB', 'North America', 'Barbados'),
	('BY', 'Europe', 'Belarus'),
	('BE', 'Europe', 'Belgium'),
	('BZ', 'North America', 'Belize'),
	('BJ', 'Africa', 'Benin'),
	('BM', 'North America', 'Bermuda'),
	('BT', 'Asia', 'Bhutan'),
	('BO', 'South America', 'Bolivia'),
	('BQ', 'North America', 'Bonaire, Sint Eustatius and Saba'),
	('BA', 'Europe', 'Bosnia and Herzegovina'),
	('BW', 'Africa', 'Botswana'),
	('BV', 'Antarctica', 'Bouvet Island'),
	('BR', 'South America', 'Brazil'),
	('IO', 'Asia', 'British Indian Ocean Territory'),
	('BN', 'Asia', 'Brunei Darussalam'),
	('BG', 'Europe', 'Bulgaria'),
	('BF', 'Africa', 'Burkina Faso'),
	('BI', 'Africa', 'Burundi'),
	('CV', 'Africa', 'Cabo Verde'),
	('KH', 'Asia', 'Cambodia'),
	('CM', 'Africa', 'Cameroon'),
	('CA', 'North America', 'Canada'),
	('KY', 'North America', 'Cayman Islands'),
	('CF', 'Africa', 'Central African Republic'),
	('TD', 'Africa', 'Chad'),
	('CL', 'South America', 'Chile'),
	('CN', 'Asia', 'China'),
	('CX', 'Oceania', 'Christmas Island'),
	('CC', 'Oceania', 'Cocos (Keeling) Islands'),
	('CO', 'South America', 'Colombia'),
	('KM', 'Africa', 'Comoros'),
	('CG', 'Africa', 'Congo'),
	('CD', 'Africa', 'Congo, Democratic Republic of the'),
	('CK', 'Oceania', 'Cook Islands'),
	('CR', 'North America', 'Costa Rica'),
	('HR', 'Europe', 'Croatia'),
	('CU', 'North America', 'Cuba'),
	('CW', 'North America', 'Curaçao'),
	('CY', 'Asia', 'Cyprus'),
	('CZ', 'Europe', 'Czechia'),
	('DK', 'Europe', 'Denmark'),
	('DJ', 'Africa', 'Djibouti'),
	('DM', 'North America', 'Dominica'),
	('DO', 'North America', 'Dominican Republic'),
	('EC', 'South America', 'Ecuador'),
	('EG', 'Africa', 'Egypt'),
	('SV', 'North America', 'El Salvador'),
	('GQ', 'Africa', 'Equatorial Guinea'),
	('ER', 'Africa', 'Eritrea'),
	('EE', 'Europe', 'Estonia'),
	('SZ', 'Africa', 'Eswatini'),
	('ET', 'Africa', 'Ethiopia'),
	('FK', 'South America', 'Falkland Islands'),
	('FO', 'Europe', 'Faroe Islands'),
	('FJ', 'Oceania', 'Fiji'),
	('FI', 'Europe', 'Finland'),
	('FR', 'Europe', 'France'),
	('GF', 'South America', 'French Guiana'),
	('PF', 'Oceania', 'French Polynesia'),
	('TF', 'Antarctica', 'French Southern Territories'),
	('GA', 'Africa', 'Gabon'),
	('GM', 'Africa', 'Gambia'),
	('GE', 'Asia', 'Georgia'),
	('DE', 'Europe', 'Germany'),
	('GH', 'Africa', 'Ghana'),
	('GI', 'Europe', 'Gibraltar'),
	('GR', 'Europe', 'Greece'),
	('GL', 'North America', 'Greenland'),
	('GD', 'North America', 'Grenada'),
	('GP', 'North America', 'Guadeloupe'),
	('GU', 'Oceania', 'Guam'),
	('GT', 'North America', 'Guatemala'),
	('GG', 'Europe', 'Guernsey'),
	('GN', 'Africa', 'Guinea'),
	('GW', 'Africa', 'Guinea-Bissau'),
	('GY', 'South America', 'Guyana'),
	('HT', 'North America', 'Haiti'),
	('HM', 'Antarctica', 'Heard Island and McDonald Islands'),
	('VA', 'Europe', 'Holy See'),
	('HN', 'North America', 'Honduras'),
	('HK', 'Asia', 'Hong Kong'),
	('HU', 'Europe', 'Hungary'),
	('IS', 'Europe', 'Iceland'),
	('IN', 'Asia', 'India'),
	('ID', 'Asia', 'Indonesia'),
	('IR', 'Asia', 'Iran'),
	('IQ', 'Asia', 'Iraq'),
	('IE', 'Europe', 'Ireland'),
	('IM', 'Europe', 'Isle of Man'),
	('IL', 'Asia', 'Israel'),
	('IT', 'Europe', 'Italy'),
	('JM', 'North America', 'Jamaica'),
	('JP', 'Asia', 'Japan'),
	('JE', 'Europe', 'Jersey'),
	('JO', 'Asia', 'Jordan'),
	('KZ', 'Asia', 'Kazakhstan'),
	('KE', 'Africa', 'Kenya'),
	('KI', 'Oceania', 'Kiribati'),
	('KP', 'Asia', 'North Korea'),
	('KR', 'Asia', 'South Korea'),
	('KW', 'Asia', 'Kuwait'),
	('KG', 'Asia', 'Kyrgyzstan'),
	('LA', 'Asia', 'Lao'),
	('LV', 'Europe', 'Latvia'),
	('LB', 'Asia', 'Lebanon'),
	('LS', 'Africa', 'Lesotho'),
	('LR', 'Africa', 'Liberia'),
	('LY', 'Africa', 'Libya'),
	('LI', 'Europe', 'Liechtenstein'),
	('LT', 'Europe', 'Lithuania'),
	('LU', 'Europe', 'Luxembourg'),
	('MO', 'Asia', 'Macao'),
	('MG', 'Africa', 'Madagascar'),
	('MW', 'Africa', 'Malawi'),
	('MY', 'Asia', 'Malaysia'),
	('MV', 'Asia', 'Maldives'),
	('ML', 'Africa', 'Mali'),
	('MT', 'Europe', 'Malta'),
	('MH', 'Oceania', 'Marshall Islands'),
	('MQ', 'North America', 'Martinique'),
	('MR', 'Africa', 'Mauritania'),
	('MU', 'Africa', 'Mauritius'),
	('YT', 'Africa', 'Mayotte'),
	('MX', 'North America', 'Mexico'),
	('FM', 'Oceania', 'Micronesia'),
	('MD', 'Europe', 'Moldova'),
	('MC', 'Europe', 'Monaco'),
	('MN', 'Asia', 'Mongolia'),
	('ME', 'Europe', 'Montenegro'),
	('MS', 'North America', 'Montserrat'),
	('MA', 'Africa', 'Morocco'),
	('MZ', 'Africa', 'Mozambique'),
	('MM', 'Asia', 'Myanmar'),
	('NA', 'Africa', 'Namibia'),
	('NR', 'Oceania', 'Nauru'),
	('NP', 'Asia', 'Nepal'),
	('NL', 'Europe', 'Netherlands'),
	('NC', 'Oceania', 'New Caledonia'),
	('NZ', 'Oceania', 'New Zealand'),
	('NI', 'North America', 'Nicaragua'),
	('NE', 'Africa', 'Niger'),
	('NG', 'Africa', 'Nigeria'),
	('NU', 'Oceania', 'Niue'),
	('NF', 'Oceania', 'Norfolk Island'),
	('MK', 'Europe', 'North Macedonia'),
	('MP', 'Oceania', 'Northern Mariana Islands'),
	('NO', 'Europe', 'Norway'),
	('OM', 'Asia', 'Oman'),
	('PK', 'Asia', 'Pakistan'),
	('PW', 'Oceania', 'Palau'),
	('PS', 'Asia', 'Palestine'),
	('PA', 'North America', 'Panama'),
	('PG', 'Oceania', 'Papua New Guinea'),
	('PY', 'South America', 'Paraguay'),
	('PE', 'South America', 'Peru'),
	('PH', 'Asia', 'Philippines'),
	('PN', 'Oceania', 'Pitcairn'),
	('PL', 'Europe', 'Poland'),
	('PT', 'Europe', 'Portugal'),
	('PR', 'North America', 'Puerto Rico'),
	('QA', 'Asia', 'Qatar'),
	('RE', 'Africa', 'Réunion'),
	('RO', 'Europe', 'Romania'),
	('RU', 'Europe', 'Russia'),
	('RW', 'Africa', 'Rwanda'),
	('BL', 'North America', 'Saint Barthélemy'),
	('SH', 'Africa', 'Saint Helena'),
	('KN', 'North America', 'Saint Kitts and Nevis'),
	('LC', 'North America', 'Saint Lucia'),
	('MF', 'North America', 'Saint Martin'),
	('PM', 'North America', 'Saint Pierre and Miquelon'),
	('VC', 'North America', 'Saint Vincent and the Grenadines'),
	('WS', 'Oceania', 'Samoa'),
	('SM', 'Europe', 'San Marino'),
	('ST', 'Africa', 'Sao Tome and Principe'),
	('SA', 'Asia', 'Saudi Arabia'),
	('SN', 'Africa', 'Senegal'),
	('RS', 'Europe', 'Serbia'),
	('SC', 'Africa', 'Seychelles'),
	('SL', 'Africa', 'Sierra Leone'),
	('SG', 'Asia', 'Singapore'),
	('SX', 'North America', 'Sint Maarten'),
	('SK', 'Europe', 'Slovakia'),
	('SI', 'Europe', 'Slovenia'),
	('SB', 'Oceania', 'Solomon Islands'),
	('SO', 'Africa', 'Somalia'),
	('ZA', 'Africa', 'South Africa'),
	('GS', 'Antarctica', 'South Georgia and the South Sandwich Islands'),
	('SS', 'Africa', 'South Sudan'),
	('ES', 'Europe', 'Spain'),
	('LK', 'Asia', 'Sri Lanka'),
	('SD', 'Africa', 'Sudan'),
	('SR', 'South America', 'Suriname'),
	('SE', 'Europe', 'Sweden'),
	('CH', 'Europe', 'Switzerland'),
	('SY', 'Asia', 'Syrian Arab Republic'),
	('TW', 'Asia', 'Taiwan'),
	('TJ', 'Asia', 'Tajikistan'),
	('TZ', 'Africa', 'Tanzania'),
	('TH', 'Asia', 'Thailand'),
	('TL', 'Asia', 'Timor-Leste'),
	('TG', 'Africa', 'Togo'),
	('TK', 'Oceania', 'Tokelau'),
	('TO', 'Oceania', 'Tonga'),
	('TT', 'North America', 'Trinidad and Tobago'),
	('TN', 'Africa', 'Tunisia'),
	('TR', 'Asia', 'Turkey'),
	('TM', 'Asia', 'Turkmenistan'),
	('TC', 'North America', 'Turks and Caicos Islands'),
	('TV', 'Oceania', 'Tuvalu'),
	('UG', 'Africa', 'Uganda'),
	('UA', 'Europe', 'Ukraine'),
	('AE', 'Asia', 'United Arab Emirates'),
	('GB', 'Europe', 'United Kingdom'),
	('US', 'North America', 'United States'),
	('UM', 'Oceania', 'United States Minor Outlying Islands'),
	('UY', 'South America', 'Uruguay'),
	('UZ', 'Asia', 'Uzbekistan'),
	('VU', 'Oceania', 'Vanuatu'),
	('VE', 'South America', 'Venezuela'),
	('VN', 'Asia', 'Vietnam'),
	('VG', 'North America', 'Virgin Islands (British)'),
	('VI', 'North America', 'Virgin Islands (U.S.)'),
	('WF', 'Oceania', 'Wallis and Futuna'),
	('EH', 'Africa', 'Western Sahara'),
	('YE', 'Asia', 'Yemen'),
	('ZM', 'Africa', 'Zambia'),
	('ZW', 'Africa', 'Zimbabwe');

INSERT INTO SPORT (sport_code, name) VALUES
	('RDR', 'Road running'),
	('TLR', 'Trail running'),
	('CCR', 'Cross-country running'),
	('DUA', 'Duathlon'),
	('CDU', 'Cross duathlon'),
	('TRI', 'Triathlon'),
	('CTR', 'Cross triathlon'),
	('RDC', 'Road cycling'),
	('GVC', 'Gravel cycling'),
	('MTB', 'Mountain biking');
	
INSERT INTO IMAGE_TYPE (image_type_code, name) VALUES
	('PPC', 'Profile picture'),
	('EVL', 'Event logo'),
	('AEP', 'Athlete event photo');
	
INSERT INTO TAG (tag_code, name) VALUES
	('MDL', 'Medal'),
	('SHT', 'Shirt'),
	('IRC', 'In race'),
	('WIF', 'With friends'),
	('FSH', 'Photo finish');
	
INSERT INTO PROFILE (profile_code, name) VALUES
	('USR', 'User'),
	('ADM', 'Admin');