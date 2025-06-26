@echo off
setlocal EnableDelayedExpansion

REM Set the folder path (default to current directory if not provided)
set "folder=%~1"
if "%folder%"=="" set "folder=%cd%"

set /a totalLines=0

echo Scanning folder: %folder%
echo.

for /r "%folder%" %%f in (*.*) do (
    for /f %%c in ('find /v /c "" ^< "%%f"') do (
        echo File: %%f - Lines: %%c
        set /a totalLines+=%%c
    )
)

echo.
echo ========================================
echo Total number of lines in all files: !totalLines!
echo ========================================
pause