@echo off
setlocal EnableDelayedExpansion
set /a total=0
for /r %%f in (*.js *.jsx) do (
    for /f %%l in ('find /c /v "" "%%f"') do (
        set /a total+=%%l
    )
)
echo Total lines: !total!