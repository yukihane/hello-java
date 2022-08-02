package com.github.yukihane.so.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private final Long id = null;

    @Column(nullable = false)
    @NonNull
    @ToString.Include
    private final String name;

    // 変更できる想定のフィールドなので final ではない
    @Column(nullable = false)
    @ToString.Include
    private int members;

    // 本来不変だが、双方向の参照を final にする手立ては無さそう
    // (リフレクションを利用しない限り)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "companyId")
    private Company company;
}
