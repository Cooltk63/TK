@echo off
setlocal EnableDelayedExpansion

REM 🔧 SET YOUR FOLDER PATH BELOW
set "folder=C:\Your\Target\Folder\Path"

set /a totalLines=0

echo Scanning folder and subfolders for .js and .jsx files: %folder%
echo.

for /r "%folder%" %%f in (*.js *.jsx) do (
    if exist "%%f" (
        for /f %%c in ('find /v /c "" ^< "%%f" 2^>nul') do (
            echo File: %%f - Lines: %%c
            set /a totalLines+=%%c
        )
    )
)

echo.
echo ========================================
echo Total number of lines in .js and .jsx files: !totalLines!
echo ========================================
pause