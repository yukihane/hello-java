package com.github.yukihane.so.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
// デフォルト設定だと関連が循環するのでスタックオーバーフローする
@ToString(onlyExplicitlyIncluded = true)
// プログラムからは利用させないので private,
// ユーザの設定必須なものもno-argコンストラクタで初期化したいので force
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
// ユーザが使うコンストラクタ
@RequiredArgsConstructor
public class Company {

    // ユーザに設定させたくないので初期値 null 設定
    // (RequiredArgsConstructor の対象外にする)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private final Long id = null;

    // ユーザが設定する必要があるので初期値なしの final
    @Column(nullable = false)
    @NonNull
    @ToString.Include
    private final String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Branch> branches = new ArrayList<>();

    public void addBranch(@NonNull final Branch branch) {
        branches.add(branch);
        branch.setCompany(this);
    }
}
