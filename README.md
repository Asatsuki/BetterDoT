# BetterDoT

炎、毒、ウィザーなどの継続ダメージによる無敵時間によって、他の攻撃が邪魔されなくなるようにするMODです。

onFire、witherなどの特定のDamageSourceによる無敵時間を、他のダメージによる無敵時間と別に扱い、記憶するようになります。
 
Fire Aspectなど、攻撃と同時に継続ダメージデバフを与えるような攻撃は、ダメージと同時に継続ダメージが入るようになります。
（物理ダメージの無敵時間と、継続ダメージの無敵時間が別になるため）

## ダウンロード

[Releases](https://github.com/Asatsuki/BetterDoT/releases/latest)

## 前提

Minecraft Forge 1.18.1 - 32.0.63+

※Forge Betaのため、32.0.63以外のバージョンではAPI変更により動作しない可能性があります。

## コンフィグ(betterdot-common.toml)

|キー|内容|
|----|----|
|dotDamageSource|本MODを適用し、無敵時間を他のダメージと別にするDamageSource。onFire、witherなど、翻訳ファイル上のキー名を設定してください。|
|affectToPoison|毒によって引き起こされた魔法ダメージに本MODを適用するかどうか。|
