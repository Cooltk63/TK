@echo off
setlocal EnableDelayedExpansion
set "targetFolder=C:\Path\To\Your\Folder"
set /a total=0

for /r "%targetFolder%" %%f in (*.js *.jsx) do (
    for /f "tokens=1 delims=:" %%a in ('find /c /v "" "%%f"') do (
        set /a total+=%%a
    )
)

echo Total lines in .js and .jsx files: !total!
pause