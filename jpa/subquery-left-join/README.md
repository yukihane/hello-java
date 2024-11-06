次のようなことがしたかった:
```sql
select c from Company c
left join fetch (
    select d from Department d
    where d.name like '%1'
) dep on c = dep.company
```

- Company は全取得する
- その上で、joinするDepartmentは特定のものだけにしたい

上記のようなことはできなさそうだった。
代わりに、join fetchした上でwhere句で絞り込む。
そして null のものも含める、という対応になった。

```sql
select c from Company c
left join fetch c.departments d
where d.name like '%1' or d is null
```
