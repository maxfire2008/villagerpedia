echo "from rcon.source import Client
with Client('127.0.0.1', 25575, passwd='password', timeout=1) as client:
    resp = client.run('pluginmanager unload villagerpedia')" | py

install-module -name Recycle
Remove-ItemSafely .\plugins\Villagerpedia*.jar
copy ..\villagerpedia\target\*.jar .\plugins\

echo "from rcon.source import Client
with Client('127.0.0.1', 25575, passwd='password', timeout=1) as client:
    resp = client.run('pluginmanager load villagerpedia')" | py