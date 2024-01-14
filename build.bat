@echo off
call .\gradlew build
copy /Y "F:\Projects\Minecraft\MyGrablePluginMC\build\libs\MyGrablePluginMC-1.0-SNAPSHOT.jar" "C:\Users\Bolt\Desktop\test-server\plugins\MyGradlePlugin.jar"
