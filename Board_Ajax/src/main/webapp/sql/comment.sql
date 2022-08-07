create table comm(
num 			number		 primary key,
id				varchar2(30) references member(id),
content			varchar2(200),
reg_date		date,
comment_board_num number  references board(board_num) on delete cascade,
							            --comm테이블이 참조하는 보드 글 번호
comment_re_lev	number(1) check(comment_re_lev in (0,1,2)), --원문이면0 답글이면 1
comment_re_seq  number,	 --원문이면 0, 1레벨이면 1레벨 시퀀스 + 1
comment_re_ref  number	 --원문은 자신 글 번호, 답글이면 원문 글번호
);
--게시판 글이 삭제되면 참조하는 댓글도 삭제됩니다.

drop sequence com_seq;

--시퀀스를 생성합니다.
create sequence com_seq;


select * from COMM

