 --
-- PostgreSQL database dump
--

-- Dumped from database version 11.1 (Debian 11.1-1.pgdg90+1)
-- Dumped by pg_dump version 11.1 (Debian 11.1-1.pgdg90+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;
SET default_tablespace = '';
SET default_with_oids = false;

--Setup database
DROP DATABASE IF EXISTS rtjvm;
CREATE DATABASE rtjvm;
\c rtjvm;

\echo 'LOADING database'
--  Sample employee database for PostgreSQL
--  See changelog table for details
--  Created from MySQL Employee Sample Database (http://dev.mysql.com/doc/employee/en/index.html)
--  Created by Vraj Mohan
--  DISCLAIMER
--  To the best of our knowledge, this data is fabricated, and
--  it does not correspond to real people.
--  Any similarity to existing people is purely coincidental.

CREATE TYPE gender AS ENUM('M', 'F');

CREATE TABLE employees (
    emp_no      INT             NOT NULL,
    birth_date  DATE            NOT NULL,
    first_name  VARCHAR(14)     NOT NULL,
    last_name   VARCHAR(16)     NOT NULL,
    gender      gender          NULL,
    hire_date   DATE            NOT NULL,
    PRIMARY KEY (emp_no)
);

CREATE TABLE devices (
    device_id     CHAR(4)         NOT NULL,
    device_name   VARCHAR(40)     NOT NULL,
    PRIMARY KEY (device_id)
);



COPY public.devices (device_id, device_name) FROM stdin;
d001	Iphone
d002	Zyxel
d003	TestOne
d004	One2One
d005	Samsung_s8
d006	Samsung_s9
d007	Samsung_s10
d008	Nokia
d009	Sony
\.




--
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: docker
--

COPY public.employees (emp_no, birth_date, first_name, last_name, gender, hire_date) FROM stdin;
10010	1963-06-01	Duangkaew	Piveteau	F	1989-08-24
10020	1952-12-24	Mayuko	Warwick	M	1991-01-26
10030	1958-07-14	Elvis	Demeyer	M	1994-02-17
10040	1959-09-13	Weiyi	Meriste	F	1993-02-14
10050	1958-05-21	Yinghua	Dredge	M	1990-12-25
10060	1961-10-15	Breannda	Billingsley	M	1987-11-02
10070	1955-08-20	Reuven	Garigliano	M	1985-10-14
10080	1957-12-03	Premal	Baek	M	1985-11-19
10090	1961-05-30	Kendra	Hofting	M	1986-03-14
10100	1953-04-21	Hironobu	Haraldson	F	1987-09-21
10110	1957-03-07	Xuejia	Ullian	F	1986-08-22
10120	1960-03-26	Armond	Fairtlough	F	1996-07-06
10130	1955-04-27	Nishit	Casperson	M	1988-06-21
10140	1957-03-11	Yucel	Auria	F	1991-03-14
10150	1955-01-29	Zhenbing	Perng	F	1986-11-16
10160	1953-10-18	Debatosh	Khasidashvili	M	1989-01-30
10170	1960-10-03	Kasturi	Jenevein	F	1986-01-02
10180	1956-01-29	Shaw	Wendorf	M	1986-02-25
10190	1964-12-11	Arve	Fairtlough	F	1986-06-23
10200	1961-12-31	Vidya	Awdeh	M	1985-10-16
10210	1958-01-24	Yuping	Alpin	M	1994-05-10
10220	1958-05-25	Kish	Fasbender	F	1992-06-25
10230	1955-09-11	Clyde	Vernadat	M	1996-06-16
10240	1952-04-01	Remko	Maccarone	M	1998-10-06
10250	1958-08-12	Serap	Etalle	M	1992-08-30
10260	1961-07-14	Alper	Suomi	F	1991-04-13
10270	1963-01-30	Bedir	Hartvigsen	F	1990-04-26
10280	1964-10-29	Stabislas	Delgrange	M	1988-03-18
10290	1957-05-29	Yongmao	Pleszkun	M	1991-09-18
10300	1960-07-12	Tadahiko	Ulupinar	F	1991-05-17
10310	1964-08-29	Aksel	Alencar	M	1990-10-24
10320	1956-06-22	Uinam	Stasinski	F	1988-11-03
10330	1957-02-02	Kasturi	Bellmore	M	1985-06-12
10340	1952-08-20	Djelloul	Laventhal	M	1987-06-05
10350	1954-11-14	Kristen	Kavvadias	F	1990-08-19
10360	1960-11-04	Irene	Munck	M	1996-09-04
10370	1957-08-03	Clyde	Fandrianto	M	1992-04-04
10380	1956-05-25	Alejandro	Kamble	F	1985-03-19
10390	1960-03-07	Wanqing	Bratten	M	1989-05-17
10400	1961-12-16	Ortrud	Nitto	M	1993-10-17
10410	1965-01-19	Takahito	Gecsei	F	1993-10-16
10420	1963-03-03	Kaijung	Riesenhuber	M	1988-01-17
10430	1958-06-30	Ferdinand	Chenney	M	1990-11-16
10440	1959-04-10	Akeel	Narahara	F	1994-03-31
10450	1959-11-05	Khun	Harbusch	M	1990-01-17
10460	1963-02-19	Shushma	VanScheik	M	1994-07-01
10470	1956-04-12	Peternela	Iwayama	M	1988-06-01
10480	1965-01-25	Make	Baba	F	1988-05-18
10490	1961-09-28	Huiqun	Vuskovic	F	1992-10-07
10500	1957-04-29	Vojin	Narwekar	M	1996-12-16
10510	1960-02-13	Keung	Slobodova	F	1990-04-20
10520	1964-03-06	Jenwei	Ecklund	M	1994-03-27
10530	1956-07-19	Boriana	Coors	F	1987-09-23
10540	1961-02-24	Martins	Barriga	M	1994-05-03
10550	1959-08-03	Manton	Leuchs	M	1985-02-03
10560	1960-05-01	Yinlin	Esteva	F	1988-09-28
10570	1958-10-30	Alassane	Morrin	M	1985-05-05
10580	1956-05-28	Christoper	Rehfuss	M	1987-09-11
10590	1963-10-01	Yongqiao	Kalloufi	M	1986-04-09
10600	1963-08-17	Emran	Rettelbach	F	1985-12-22
10610	1955-06-24	Irena	Roccetti	F	1996-04-11
10620	1959-11-23	Marit	Veeraraghavan	M	1990-11-10
10630	1954-08-10	Bezalel	Katzenelson	F	1993-06-26
10640	1958-11-09	Anneke	Meszaros	M	1992-12-04
10650	1959-04-09	Hongzhu	Chepyzhov	M	1992-10-04
10660	1964-01-03	Jouko	Kolinko	M	1988-08-12
10670	1962-04-23	Shunichi	McAffer	M	1988-03-23
10680	1962-10-11	Kish	Honglei	M	1986-06-20
10690	1962-09-06	Matt	Jumpertz	F	1989-08-22
10700	1962-11-30	Angel	Mondadori	M	1987-04-02
10710	1962-12-25	Sorina	Kermarrec	M	1992-01-10
10720	1956-06-17	Odinaldo	Kruskal	M	1985-08-01
10730	1962-07-22	Mohit	Janetzko	M	1988-11-24
10740	1956-04-19	Suzette	Sadowski	M	1995-08-15
10750	1953-06-14	Roddy	Demeyer	F	1991-08-04
10760	1963-05-27	Phillip	Dratva	M	1994-06-15
10770	1956-06-18	Bedir	Perry	F	1987-12-25
10780	1955-09-12	Goncalo	Staudhammer	M	1989-11-06
10790	1962-04-01	Yurij	Schonegge	M	1990-07-03
10800	1961-12-15	Yuchang	Matzke	M	1993-05-20
10810	1957-10-23	Lijie	Lunn	F	1994-09-07
10820	1956-11-12	Baocai	Pena	F	1986-02-23
10830	1954-08-17	Fen	Fiebach	M	1991-08-24
10840	1961-04-26	Mohammad	Koshino	M	1993-06-04
10850	1963-01-01	Florina	Schapire	F	1990-04-22
10860	1961-11-05	Gino	Werthner	M	1992-03-11
10870	1956-07-23	Shahid	Leppanen	F	1988-01-13
10880	1962-02-11	Weiye	Nitsche	M	1986-04-20
10890	1963-04-13	Ramzi	Furudate	M	1988-04-21
10900	1955-07-18	Ferdinand	Prenel	F	1995-09-07
10910	1958-11-11	Arco	Jervis	M	1989-07-13
10920	1955-04-08	Ronghao	Narahara	M	1991-07-09
10930	1954-01-13	Arch	Minakawa	M	1990-08-09
10940	1953-01-07	Aleksander	Fioravanti	F	1994-12-02
10950	1958-12-07	Sushant	Staylopatis	M	1996-06-08
10960	1959-10-24	Billur	Braunschweig	M	1988-03-14
10970	1962-08-24	Gen	Tiemann	F	1994-09-16
10980	1963-11-10	Filipp	Munawer	M	1989-01-04
10990	1954-10-22	Shin	Foote	M	1988-07-06
11000	1960-09-12	Alain	Bonifati	M	1988-08-20
11010	1963-03-20	Jaques	Narwekar	F	1986-12-18
11020	1957-03-18	Weiwu	Encarnacion	M	1988-03-25
11030	1956-06-12	Kwan	Hellwagner	F	1988-05-09
11040	1961-11-07	Cristinel	Denna	F	1988-01-22
11050	1952-09-23	Elvis	Katiyar	M	1986-05-06
11060	1953-06-30	Genevieve	Pokrovskii	F	1991-05-03
11070	1952-06-30	Adib	Raney	M	1985-07-24
11080	1956-12-27	Sanjai	Puppe	M	1988-07-04
11090	1959-07-10	Ebbe	Hasenauer	M	1986-07-25
11100	1955-10-14	Berna	Cochrane	M	1986-10-13
11110	1955-12-11	Valeska	Birjandi	F	1987-11-14
11120	1961-12-15	Masato	Curless	F	1988-01-22
11130	1953-01-17	Xinyu	Menhoudj	M	1993-06-07
11140	1957-04-30	Elgin	Gihr	M	1993-07-04
11150	1956-05-13	Geraldo	Garigliano	F	1989-05-01
11160	1956-08-05	Anwar	Nivat	F	1987-05-07
11170	1952-05-05	Minghong	Cannard	M	1989-12-29
11180	1952-11-07	Mitsuyuki	Bojadziev	F	1986-06-11
11190	1960-09-26	Saeko	Mellouli	M	1992-03-06
11200	1964-06-05	Renny	Junet	F	1988-06-28
11210	1957-09-27	Kamakshi	Decaestecker	M	1986-02-27
11220	1962-06-25	Sahrah	Figueira	F	1989-11-24
11230	1956-12-28	Yakichi	Tzvieli	M	1990-12-06
\.