insert into cursussen(naam) values('testGroep');
insert into groepscursussen(id, van, tot)
  values((select id from cursussen where naam='testGroep'),'2018-01-01','2018-12-31');
insert into cursussen(naam) values('testIndividu');  
insert into individuelecursussen(id, duurtijd)
  values((select id from cursussen where naam='testIndividu'),3);