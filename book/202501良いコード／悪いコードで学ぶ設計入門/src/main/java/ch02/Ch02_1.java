package ch02;

public class Ch02_1 {
    // 書籍のコード(p.15, リスト2.2)
    public int calcDamageBook(int playerArmPower, int playerWeaponPower, int enemyBodyDefence, int enemyArmorDefence) {
        int damageAmount = 0;
        damageAmount = playerArmPower + playerWeaponPower;
        damageAmount = damageAmount - ((enemyBodyDefence + enemyArmorDefence) / 2);
        if (damageAmount < 0) {
            damageAmount = 0;
        }
        return damageAmount;
    }

    // リファクター後
    public int calcDamageRefactor(final int playerArmPower, final int playerWeaponPower, final int enemyBodyDefence, final int enemyArmorDefence) {
        // 不要な初期化を行わない。immutableにしない。
        // つまり、変数には基本的にfinalを付ける
        final int damageAmount;
        damageAmount = (playerArmPower + playerWeaponPower) - ((enemyBodyDefence + enemyArmorDefence) / 2);
        return Math.max(damageAmount, 0);
    }

}
