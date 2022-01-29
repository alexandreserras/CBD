create KEYSPACE "videos" 
 WITH replication = {'class': 'SimpleStrategy','replication_factor' : 1};


use videos;

Create Table User(
    username text,
    nome text,
    email text,
    created_on TIMESTAMP,
    PRIMARY KEY(username)
);
Create Table Video(
    id int , 
    nome text,
    descricao text,
    tag set<text>,
    autor text,
    duracao float,
    created_on TIMESTAMP,
    PRIMARY KEY((id),created_on))
    with Clustering ORDER BY (created_on DESC);



Create Table Follower(
    video_id int,
    username set<text>,
    PRIMARY KEY((video_id))
);
Create Table Evento(
    tipo text,
    user_username text,
    video_id int,
    created_on TIMESTAMP,
    video_timestamp float,
    PRIMARY KEY((user_username,video_id),created_on ,video_timestamp)

)    with Clustering ORDER BY (created_on DESC);


CREATE TABLE ratings (
	video_id int,
    rating int, 
    
    PRIMARY KEY(video_id, rating)
);



Create Table Video_User(
    id int , 
    nome text,
    descricao text,
    tag set<text>,
    autor text,
    duracao float,
    created_on TIMESTAMP,
    PRIMARY KEY((autor),created_on)
);

Create Table Comentario_User(
    user_username text,
    video_id int,
    created_on TIMESTAMP,
    comment_text Text,
    PRIMARY Key(user_username,created_on )
)with Clustering ORDER BY (created_on DESC);


Create Table Comentario_Video(
    id int ,
    user_username text,
    video_id int,
    comment_text text,
    created_on TIMESTAMP,
    Primary Key (video_id,created_on  )
)with Clustering ORDER BY (created_on DESC);



insert into User (username, nome, email, created_on) values ('serras', 'Alexandre Serras', 'alexandreserras@ua.pt', toTimestamp(now()));
insert into User (username, nome, email, created_on) values ('reis', 'Jonhy Kings', 'reis@ua.pt', toTimestamp(now()));
insert into User (username, nome, email, created_on) values ('leal', 'Gonçalo Leal', 'gonçaloleal@ua.pt', toTimestamp(now()));
insert into User (username, nome, email, created_on) values ('bernas', 'João Farias', 'bernas@ua.pt', toTimestamp(now()));
insert into User (username, nome, email, created_on) values ('dx', 'Diogo Cruz', 'dx@ua.pt', toTimestamp(now()));
insert into User (username, nome, email, created_on) values ('ricardo', 'Ricardo Rodriguez', 'rodriguez@ua.pt', toTimestamp(now()));
insert into User (username, nome, email, created_on) values ('mariana', 'Mariana Rosa', 'rosa@ua.pt', toTimestamp(now()));
insert into User (username, nome, email, created_on) values ('pylance', 'Paulo Guilherme', 'paulo@ua.pt', toTimestamp(now()));
insert into User (username, nome, email, created_on) values ('fahla', 'Nuno Fahla', 'fahla@ua.pt', toTimestamp(now()));
insert into User (username, nome, email, created_on) values ('arturito', 'Artur Romão', 'artur@ua.pt', toTimestamp(now()));


insert into Video (id, nome, descricao, tag, autor, created_on,duracao) values (1, 'SIIIIIIIU', 'Festejo do ronaldo', {'cr7', 'futebol'}, 'dx',toTimestamp(now()),1.5);
insert into Video (id, nome, descricao, tag, autor, created_on,duracao) values (2, 'WBG ao vivo', 'Concerto dos wbg em lisboa', {'musica', 'concerto','wbg'}, 'serras',toTimestamp(now()),5);
insert into Video (id, nome, descricao, tag, autor, created_on,duracao) values (3, 'Golo ronaldo', 'Ronaldo marca de livre com o Chelsea', {'futebol', 'cr7'}, 'arturito',toTimestamp(now()),2);
insert into Video (id, nome, descricao, tag, autor, created_on,duracao) values (4, 'Seferovic falha de baliza aberta', 'Seferovic podia classificar o benfica na liga dos campeões e faz falhanço incrivel',  {'slb', 'champions'},'pylance', toTimestamp(now()),1);
insert into Video (id, nome, descricao, tag, autor, created_on,duracao) values (5, 'Como abrir uma porta', 'Video que explica como deve ser aberta uma porta', {'joke'}, 'mariana',toTimestamp(now()),20);
insert into Video (id, nome, descricao, tag, autor, created_on,duracao) values (6, 'Eu sou aquele ao vivo', 'A música mais conhecida de portugal ao vivo num grande concerto em AVEIRO',{'musica', 'funny'},'dx', toTimestamp(now()),7.3);
insert into Video (id, nome, descricao, tag, autor, created_on,duracao) values (7, 'Vlog por Aveiro', 'Caminhada por Aveiro desde a Estação até ao Deti',  {'joke', 'vlog'}, 'leal',toTimestamp(now()),60.2);
insert into Video (id, nome, descricao, tag, autor, created_on,duracao) values (8, 'Conheci o dx', 'A pessoa mais conhecida da 3 matricula ou melhor do DETI tornou-se meu amigo', {'joke', 'funny'}, 'ricardo',toTimestamp(now()),3);
insert into Video (id, nome, descricao, tag, autor, created_on,duracao) values (9, 'Mary Channel pedida como patroa',  'Pedi a mary channel como patroa!', {'faina', 'lei'}, 'bernas',toTimestamp(now()),15.2);
insert into Video (id, nome, descricao, tag, autor, created_on,duracao) values (10, 'Como dar surrender do curso',  'O curso anda uma seca vou explicar como o tornar melhor!', {'joke', 'funny'}, 'fahla',toTimestamp(now()),30.2);
insert into Video (id, nome, descricao, tag, autor, created_on,duracao) values (11, 'Last Dance',  'O retorno para a verdadeira despedida!', {'nba', 'heat'}, 'fahla',toTimestamp(now()),15.2);

insert into follower (video_id, username) values (1, {'serras', 'reis'});
insert into follower (video_id, username) values (2, {'dx', 'mariana', 'pylance'});
insert into follower (video_id, username) values (3, {'leal','fahla'});
insert into follower (video_id, username) values (4, {'dx', 'ricardo','arturito'});
insert into follower (video_id, username) values (5, {'bernas', 'ricardo'});
insert into follower (video_id, username) values (6, {'leal','dx','arturito'});
insert into follower (video_id, username) values (7, {'pylance', 'reis', 'fahla'});
insert into follower (video_id, username) values (8, {'serras', 'pylance'});
insert into follower (video_id, username) values (9, {'fahla', 'leal', 'bernas'});
insert into follower (video_id, username) values (10, {'pylance'});

insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (10, 'serras', 'play', toTimestamp(now()), 5.2);
insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (10, 'bernas', 'pause', toTimestamp(now()), 10.1);
insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (10, 'serras', 'stop', toTimestamp(now()), 22);
insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (2, 'leal', 'stop', toTimestamp(now()), 3.02);
insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (4, 'mariana', 'play', toTimestamp(now()), 0.15 );
insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (4, 'reis', 'play', toTimestamp(now()), 0.25);
insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (5, 'arturito', 'pause', toTimestamp(now()),0.55 );
insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (6, 'reis', 'play', toTimestamp(now()),1.2 );
insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (7, 'fahla', 'stop', toTimestamp(now()), 15.2);
insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (7, 'pylance', 'stop', toTimestamp(now()), 10.7);
insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (10, 'serras', 'stop', toTimestamp(now()), 8.2);
insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (10, 'serras', 'play', toTimestamp(now()), 15.2);
insert into Evento (video_id, user_username, tipo, created_on, video_timestamp) values (10, 'serras', 'pause', toTimestamp(now()), 2.2);


insert into ratings (video_id, rating) values (1, 4);
insert into ratings (video_id, rating) values (2, 3);
insert into ratings (video_id, rating) values (6, 1);
insert into ratings (video_id, rating) values (2, 5);
insert into ratings (video_id, rating) values (2, 3);
insert into ratings (video_id, rating) values (1, 1);
insert into ratings (video_id, rating) values (1, 2);
insert into ratings (video_id, rating) values (3, 3);
insert into ratings (video_id, rating) values (4, 4);
insert into ratings (video_id, rating) values (4, 2);
insert into ratings (video_id, rating) values (10, 4);
insert into ratings (video_id, rating) values (10, 2);
insert into ratings (video_id, rating) values (7, 5);
insert into ratings (video_id, rating) values (7, 4);



insert into Video_User (id, nome, descricao, tag, autor, created_on,duracao) values (1, 'SIIIIIIIU', 'Festejo do ronaldo', {'cr7', 'futebol'}, 'dx',toTimestamp(now()),1.5);
insert into Video_User (id, nome, descricao, tag, autor, created_on,duracao) values (2, 'WBG ao vivo', 'Concerto dos wbg em lisboa', {'musica', 'concerto','wbg'}, 'serras',toTimestamp(now()),5);
insert into Video_User (id, nome, descricao, tag, autor, created_on,duracao) values (3, 'Golo ronaldo', 'Ronaldo marca de livre com o Chelsea', {'futebol', 'cr7'}, 'arturito',toTimestamp(now()),2);
insert into Video_User (id, nome, descricao, tag, autor, created_on,duracao) values (4, 'Seferovic falha de baliza aberta', 'Seferovic podia classificar o benfica na liga dos campeões e faz falhanço incrivel',  {'slb', 'champions'},'pylance', toTimestamp(now()),1);
insert into Video_User (id, nome, descricao, tag, autor, created_on,duracao) values (5, 'Como abrir uma porta', 'Video que explica como deve ser aberta uma porta', {'joke'}, 'mariana',toTimestamp(now()),20);
insert into Video_User (id, nome, descricao, tag, autor, created_on,duracao) values (6, 'Eu sou aquele ao vivo', 'A música mais conhecida de portugal ao vivo num grande concerto em AVEIRO',{'musica', 'funny'},'dx', toTimestamp(now()),7.3);
insert into Video_User (id, nome, descricao, tag, autor, created_on,duracao) values (7, 'Vlog por Aveiro', 'Caminhada por Aveiro desde a Estação até ao Deti',  {'joke', 'vlog'}, 'leal',toTimestamp(now()),60.2);
insert into Video_User (id, nome, descricao, tag, autor, created_on,duracao) values (8, 'Conheci o dx', 'A pessoa mais conhecida da 3 matricula ou melhor do DETI tornou-se meu amigo', {'joke', 'funny'}, 'ricardo',toTimestamp(now()),3);
insert into Video_User (id, nome, descricao, tag, autor, created_on,duracao) values (9, 'Mary Channel pedida como patroa',  'Pedi a mary channel como patroa!', {'faina', 'lei'}, 'bernas',toTimestamp(now()),15.2);
insert into Video_User (id, nome, descricao, tag, autor, created_on,duracao) values (10, 'Como dar surrender do curso',  'O curso anda uma seca vou explicar como o tornar melhor!', {'joke', 'funny'}, 'fahla',toTimestamp(now()),30.2);





insert into Comentario_User (video_id, comment_text, created_on, user_username) values ( 1, 'GOLAÇO', toTimestamp(now()), 'serras');
insert into Comentario_User (video_id, comment_text, created_on, user_username) values ( 1, 'Jogas tanto', toTimestamp(now()), 'arturito');
insert into Comentario_User (video_id, comment_text, created_on, user_username) values ( 1, 'Fraco', toTimestamp(now()), 'fahla');
insert into Comentario_User (video_id, comment_text, created_on, user_username) values ( 2, 'SHOW', toTimestamp(now()), 'serras');
insert into Comentario_User (video_id, comment_text, created_on, user_username) values ( 2, 'vem vem vem vem ', toTimestamp(now()), 'fahla');
insert into Comentario_User (video_id, comment_text, created_on, user_username) values ( 4, 'Conteudo', toTimestamp(now()), 'leal');
insert into Comentario_User (video_id, comment_text, created_on, user_username) values ( 4, 'Faz mais videos', toTimestamp(now()), 'serras');
insert into Comentario_User (video_id, comment_text, created_on, user_username) values ( 3, 'OK', toTimestamp(now()), 'ricardo');
insert into Comentario_User (video_id, comment_text, created_on, user_username) values ( 9, 'ADOREIIIIIII', toTimestamp(now()), 'mariana');
insert into Comentario_User (video_id, comment_text, created_on, user_username) values ( 10, 'Quero parte 2', toTimestamp(now()), 'dx');


insert into Comentario_Video (id,video_id, comment_text, created_on, user_username) values (1, 1, 'GOLAÇO', toTimestamp(now()), 'serras');
insert into Comentario_Video (id,video_id, comment_text, created_on, user_username) values ( 2,1, 'Jogas tanto', toTimestamp(now()), 'arturito');
insert into Comentario_Video (id,video_id, comment_text, created_on, user_username) values ( 3,1, 'Fraco', toTimestamp(now()), 'fahla');
insert into Comentario_Video (id,video_id, comment_text, created_on, user_username) values ( 4,2, 'SHOW', toTimestamp(now()), 'serras');
insert into Comentario_Video (id,video_id, comment_text, created_on, user_username) values ( 5,2, 'vem vem vem vem ', toTimestamp(now()), 'fahla');
insert into Comentario_Video (id,video_id, comment_text, created_on, user_username) values ( 6,4, 'Conteudo', toTimestamp(now()), 'leal');
insert into Comentario_Video (id,video_id, comment_text, created_on, user_username) values ( 7,4, 'Faz mais videos', toTimestamp(now()), 'serras');
insert into Comentario_Video (id,video_id, comment_text, created_on, user_username) values ( 8,3, 'OK', toTimestamp(now()), 'ricardo');
insert into Comentario_Video (id,video_id, comment_text, created_on, user_username) values ( 9,9, 'ADOREIIIIIII', toTimestamp(now()), 'mariana');
insert into Comentario_Video (id,video_id, comment_text, created_on, user_username) values ( 10,10, 'Quero parte 2', toTimestamp(now()), 'dx');



select json * from User;
select json * from Video;
select json * from follower;
select json * from Evento;
select json * from ratings;
select json * from Video_User;
select json * from comentario_user ;
select json * from Comentario_Video ;



C)
    7:
            select * from video_user where autor = 'dx';

        autor | created_on                      | descricao                                                                 | duracao | id | nome                  | tag
        -------+---------------------------------+---------------------------------------------------------------------------+---------+----+-----------------------+---------------------
        dx | 2021-12-04 22:35:53.569000+0000 |                                                        Festejo do ronaldo |     1.5 |  1 |             SIIIIIIIU |  {'cr7', 'futebol'}
        dx | 2021-12-04 22:35:53.588000+0000 | A música mais conhecida de portugal ao vivo num grande concerto em AVEIRO |     7.3 |  6 | Eu sou aquele ao vivo | {'funny', 'musica'}

    8:
        select * from Comentario_User  where user_username = 'serras';

        user_username | created_on                      | comment_text    | video_id
        ---------------+---------------------------------+-----------------+----------
                serras | 2021-12-04 22:28:07.460000+0000 | Faz mais videos |        4
                serras | 2021-12-04 22:28:07.445000+0000 |            SHOW |        2
                serras | 2021-12-04 22:28:07.431000+0000 |          GOLAÇO |        1
    9:
        select * from Comentario_Video  where video_id = 1;

    video_id | created_on                      | comment_text | id | user_username
    ----------+---------------------------------+--------------+----+---------------
            1 | 2021-12-04 22:32:02.014000+0000 |        Fraco |  3 |         fahla
            1 | 2021-12-04 22:32:02.012000+0000 |  Jogas tanto |  2 |      arturito
            1 | 2021-12-04 22:32:02.009000+0000 |       GOLAÇO |  1 |        serras
    10:
        select avg(rating) as rating_medio,count(rating) as numero_vezes from ratings where video_id=1;

        rating_medio | numero_vezes
        --------------+--------------
                    2 |            3


D)
    1:
    select * from comentario_video where video_id = 1 limit 3 ;
    video_id | created_on                      | comment_text | id | user_username
    ----------+---------------------------------+--------------+----+---------------
            1 | 2021-12-04 22:32:02.014000+0000 |        Fraco |  3 |         fahla
            1 | 2021-12-04 22:32:02.012000+0000 |  Jogas tanto |  2 |      arturito
            1 | 2021-12-04 22:32:02.009000+0000 |       GOLAÇO |  1 |        serras

    2:
    select tag from video where id=1 ;
    tag
    --------------------
    {'cr7', 'futebol'}

    3:
    Nesta aqui para o conceito do cassandra só seria possivel com a introdução de um index,visto que 
    tag nem faz parte da chave primária e mesmo se fizesse a particion key é o id do video.
    Com um allow filtering tambem seria possivel, mas seria possivel , mas o resultado seria:
        cqlsh:videos> select * from video where tag contains 'cr7' allow filtering;

        id | created_on                      | autor    | descricao                            | duracao | nome         | tag
        ----+---------------------------------+----------+--------------------------------------+---------+--------------+--------------------
        1 | 2021-12-04 22:08:05.385000+0000 |       dx |                   Festejo do ronaldo |     1.5 |    SIIIIIIIU | {'cr7', 'futebol'}
        3 | 2021-12-04 22:08:05.392000+0000 | arturito | Ronaldo marca de livre com o Chelsea |       2 | Golo ronaldo | {'cr7', 'futebol'}
    Não utilizei aveiro porque não tinha nenhuma tag Aveiro
 
    4:
    select * from evento where user_username='serras' and video_id=10  limit 5;

    user_username | video_id | created_on                      | video_timestamp | tipo
    ---------------+----------+---------------------------------+-----------------+-------
            serras |       10 | 2021-12-05 11:33:28.650000+0000 |             2.2 | pause
            serras |       10 | 2021-12-05 11:33:28.648000+0000 |            15.2 |  play
            serras |       10 | 2021-12-05 11:33:28.645000+0000 |             8.2 |  stop
            serras |       10 | 2021-12-04 22:07:29.772000+0000 |              22 |  stop
            serras |       10 | 2021-12-04 22:07:29.765000+0000 |             5.2 |  play


    5:
    select * from Video_User where autor = 'serras' and created_on > '2021-11-03';

    autor  | created_on                      | descricao                  | duracao | id | nome        | tag
    --------+---------------------------------+----------------------------+---------+----+-------------+-------------------------------
    serras | 2021-12-04 22:35:53.572000+0000 | Concerto dos wbg em lisboa |       5 |  2 | WBG ao vivo | {'concerto', 'musica', 'wbg'}


    6:
    Não é   possive visto que cassandra não permite o global querying. 
    Caso todos os videos tivessem o mesmo id, aí sim já iria ser possivel.
    Porque a clustering key iria atuar,e colocava tudo ordenado por ordem inversa.


    7:
        select username from follower where video_id=1;
         username
        --------------------
        {'reis', 'serras'}

    8:
    Para fazer esta pesquisa preciso de fazer joins entre a tabela follower e a tabela coments o que não é
    possivel em cassandra.
    9:
    Impossivel visto que cassandra não permite Global Querying.

    10:
    select * from video where id in (1,3,2,4,6,5,8,9,10,7);

    id | created_on                      | autor    | descricao                                                                          | duracao | nome                             | tag
    ----+---------------------------------+----------+------------------------------------------------------------------------------------+---------+----------------------------------+-------------------------------
    1 | 2021-12-19 14:16:29.712000+0000 |       dx |                                                                 Festejo do ronaldo |     1.5 |                        SIIIIIIIU |            {'cr7', 'futebol'}
    2 | 2021-12-19 14:16:29.728000+0000 |   serras |                                                         Concerto dos wbg em lisboa |       5 |                      WBG ao vivo | {'concerto', 'musica', 'wbg'}
    3 | 2021-12-19 14:16:29.735000+0000 | arturito |                                               Ronaldo marca de livre com o Chelsea |       2 |                     Golo ronaldo |            {'cr7', 'futebol'}
    4 | 2021-12-19 14:16:29.739000+0000 |  pylance | Seferovic podia classificar o benfica na liga dos campeões e faz falhanço incrivel |       1 | Seferovic falha de baliza aberta |          {'champions', 'slb'}
    5 | 2021-12-19 14:16:29.743000+0000 |  mariana |                                   Video que explica como deve ser aberta uma porta |      20 |             Como abrir uma porta |                      {'joke'}
    6 | 2021-12-19 14:16:29.747000+0000 |       dx |          A música mais conhecida de portugal ao vivo num grande concerto em AVEIRO |     7.3 |            Eu sou aquele ao vivo |           {'funny', 'musica'}
    7 | 2021-12-19 14:16:29.751000+0000 |     leal |                                   Caminhada por Aveiro desde a Estação até ao Deti |    60.2 |                  Vlog por Aveiro |              {'joke', 'vlog'}
    8 | 2021-12-19 14:16:29.757000+0000 |  ricardo |       A pessoa mais conhecida da 3 matricula ou melhor do DETI tornou-se meu amigo |       3 |                     Conheci o dx |             {'funny', 'joke'}
    9 | 2021-12-19 14:16:29.761000+0000 |   bernas |                                                   Pedi a mary channel como patroa! |    15.2 |  Mary Channel pedida como patroa |              {'faina', 'lei'}
    10 | 2021-12-19 14:16:29.767000+0000 |    fahla |                           O curso anda uma seca vou explicar como o tornar melhor! |    30.2 |      Como dar surrender do curso |             {'funny', 'joke'}

    11:
    Apenas era exequivel se criasse outra tabela onde  a lista de tags era a chave primaria e os videos 
    era um dos atributos.


