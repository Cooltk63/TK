@echo off
setlocal EnableDelayedExpansion

REM 🔧 Set your target folder path
set "folder=C:\Your\Target\Folder\Path"

set /a totalLines=0

echo Scanning folder and subfolders for .js, .jsx, .ts files
echo.

REM Loop through each extension separately
for %%x in (js jsx ts) do (
    for /r "%folder%" %%f in (*.%%x) do (
        if exist "%%f" (
            for /f %%c in ('find /v /c "" ^< "%%f" 2^>nul') do (
                REM Check that count is a number (skip if unreadable)
                echo File: %%f - Lines: %%c
                set /a totalLines+=%%c
            )
        )
    )
)

echo.
echo ========================================
echo Total number of lines in .js, .jsx, .ts files: !totalLines!
echo ========================================
pause