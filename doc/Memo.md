##SQLiteのTypeにDate Typeがない
Longで保存するとよいよ！

SQLiteのInteger＝Long

Androidからformatしたのを、String(TEXT)で格納するのはちょっと微妙。

DateのObjectにすることがある場合に、ちょっとコスト高そう。なので、Longで。   

## WAL(Write-Ahead Logging)

http://www.sqlite.org/wal.html

[SQLiteDatabase#enableWriteAheadLogging](http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#enableWriteAheadLogging())

[SQLiteDatabase#disableWriteAheadLogging](http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#disableWriteAheadLogging())

## もっと早くなれよ！松岡修造みたいなノリで。   

journal_mode変えれば早くなるかも！

synchronous(SyncMode)変えれば早くなるかも！

SDKのコード読めば変更方法わかります。今度やってみよー

## Drop Tableじゃなくて、Dabasesごと全部削除したいんだけど！

[SQLiteDatabase#deleteDatabaseメソッド](http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#deleteDatabase(java.io.File))あります！

API Level16(Android 4.1 JELLY_BEAN)から使用可能

えー16かよー。でも、大丈夫！Javaのコードしか書かれてないので、コピペ可能！

## vacuumしなくて大丈夫です！

auto_vacuumがdefaultONです

## Cursor？なんだこれ。コレクション返ってこないのか。

Cursorは行のコレクションを返却するわけではありません。

メモリに直接大量のデータを格納するのではなく、必要に応じてデータの取得と解放を行うことにより
Androidがリソースを効率的に使用出きるようになってます。

多分、テーブルの行数が多ければ多いほど効果が高まりす。