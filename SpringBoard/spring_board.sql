create table spring_board(
	bno number(10,0),
	title varchar2(200) not null,
	content varchar2(2000) not null,
	writer varchar2(50) not null,
	regdate date default sysdate,
	updatedate date default sysdate
);

select * from spring_board;

alter table spring_board add constraint pk_spring_board primary key(bno);

create sequence seq_board;

--페이지 나누기 : rownum
select rownum, bno, title from spring_board where rownum <= 10;

-- 주의 할점 order by를 할 때

-- 가장 최신글 20개 가져오기
select rownum, bno, title
from(select bno,title from spring_board order by bno desc)
where rownum <= 10;

-- 1 페이지 요청 시 => 가장 최신글 10개 단, 2페이지 3페이지 요청 시 그 다음 다다음 최신글 10개
select *
from(select rownum rn, bno,title, writer, regdate, updatedate 
	from spring_board where rownum <= 20)
where rn>10;

--페이지 번호가 넘어오는 경우
-- 1 => 10(페이지 번호*현재 페이지에 보여줄 게시물 수),0((페이지 번호 -1) * 현재 페이지에 보여줄 게시물 수)
-- || 2=> 20,10 || 3=> 30,20 .....
select *
from(select rownum rn, bno,title, writer, regdate, updatedate 
	from spring_board where rownum <= ?)
where rn>?;

--더미 데이터 삽입
insert into spring_board(bno,title,content,writer)
(select seq_board.nextval,title,content,writer from spring_board);

select count(*) from spring_board;

-- 검색
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board) */ rownum rn, bno, title, writer, regdate, updatedate
	from spring_board
	where title like '%더미%' and rownum<=10)
where rn>0;

-- 제목 || 내용
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board) */ rownum rn, bno, title, writer, regdate, updatedate
	from spring_board
	where (title like '%더미%' or content like '%더미%') and rownum<=10)
where rn>0;

-- 제목 || 내용 || 작성자
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board) */ rownum rn, bno, title, writer, regdate, updatedate
	from spring_board
	where (title like '%더미%' or content like '%더미%' or writer like '%더미%') and rownum<=10)
where rn>0;

-- 댓글
create table spring_reply(
	rno number(10,0) constraint pk_reply primary key, --댓글 글번호
	bno number(10,0) not null, --원본글 글번호
	reply varchar2(1000) not null, --댓글
	replyer varchar2(50) not null, --댓글 작성자
	replyDate date default sysdate, --댓글작성일
	updateDate date default sysdate, --댓글 수젖일
	constraint fk_reply_board foreign key(bno) references spring_board(bno) --외래키 설정
);

create sequence seq_reply;
-- 인덱스 생성
create index idx_reply on spring_reply(bno desc,rno asc);

select * from SPRING_REPLY;