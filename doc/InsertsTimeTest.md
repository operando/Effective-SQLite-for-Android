# 50000件の適当なデータInsert選手権

リンク先がおかしくなってたらすいません！

この検証は、**[Xperia acro HD](http://www.sonymobile.co.jp/xperia/docomo/so-03d/)**で行ったものです。   

## [execSQL](https://github.com/operando/Effective-SQLite-for-Android/blob/master/app/src/main/java/com/operando/os/sqlitesample/activityes/SqliteInsertActivity.java#L35)

D/Shibuya Java(19034): 12295ms

D/Shibuya Java(19034): 11863ms

## [insert](https://github.com/operando/Effective-SQLite-for-Android/blob/master/app/src/main/java/com/operando/os/sqlitesample/activityes/SqliteInsertActivity.java#L54)

測定不能

毎回Commitしてるんだろうなー

## [beginTransaction](https://github.com/operando/Effective-SQLite-for-Android/blob/master/app/src/main/java/com/operando/os/sqlitesample/activityes/SqliteInsertActivity.java#L74)

D/Shibuya Java(18610): 8651ms

D/Shibuya Java(18610): 8598ms

## [beginTransaction + compileStatement](https://github.com/operando/Effective-SQLite-for-Android/blob/master/app/src/main/java/com/operando/os/sqlitesample/activityes/SqliteInsertActivity.java#L98)

D/Shibuya Java(18610): 5574ms

D/Shibuya Java(18610): 5617ms

### Good Time!!

## [beginTransaction + compileStatement(Nexus 5)](https://github.com/operando/Effective-SQLite-for-Android/blob/master/app/src/main/java/com/operando/os/sqlitesample/activityes/SqliteInsertActivity.java#L98)

D/Shibuya Java(14840): 2047ms

D/Shibuya Java(14840): 2017ms

### Very Good Time!!