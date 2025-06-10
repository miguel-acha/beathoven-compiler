@echo off
echo Compilando el proyecto Beathoven...
cd src
javac -d ../out beathoven/*.java
if %ERRORLEVEL% NEQ 0 (
    echo Error en la compilación. Verifique el código fuente.
    pause
    exit /b
)
cd ..

echo Ejecutando Beathoven...
java -cp out beathoven.Beathoven

echo.
echo Abriendo el PDF generado...
start "" "output\output.pdf"

echo.
echo Abriendo el MIDI generado...
start "" "output\output.mid"

pause
