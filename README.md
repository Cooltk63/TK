@echo off
setlocal EnableDelayedExpansion

REM 🔧 SET YOUR FOLDER PATH BELOW
set "folder=C:\Your\Target\Folder\Path"

set /a totalLines=0

echo Scanning folder and subfolders: %folder%
echo.

for /r "%folder%" %%f in (*.*) do (
    if exist "%%f" (
        for /f "tokens=1 delims=:" %%c in ('find /v /c "" "%%f"') do (
            echo File: %%f - Lines: %%c
            set /a totalLines+=%%c
        )
    )
)

echo.
echo ========================================
echo Total number of lines in ALL files: !totalLines!
echo ========================================
pause