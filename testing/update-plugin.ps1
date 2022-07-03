install-module -name Recycle
Remove-ItemSafely .\plugins\Villagerpedia*.jar
copy ..\villagerpedia\target\*.jar .\plugins\
