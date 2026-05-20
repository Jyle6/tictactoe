@echo off
if "%~1"=="" goto target
if "%~1"=="target" goto target
if "%~1"=="test" goto test
if "%~1"=="fast" goto fast
goto end

:target
call gradlew.bat jar
goto end

:test
java -jar build\libs\tic-tac-toe.jar
goto end

:fast
call build.bat target
call build.bat test
goto end

:end