insert into parent(id, content) values (1001, 'parent content 1001'),(1002, 'parent content 1002');
insert into child(id, parent_id, content) values (1,1001,'child content 1'),(2,1001,'child content 2');
insert into grandsons(id,child_id,content) values (11,1,'gs1'),(12,1,'gs2'),(21,2,'gs3');