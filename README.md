{
    "version": "2.0.0",
    "tasks": [
        {
            "label": "Compile Java",
            "type": "shell",
            "command": "javac",
            "args": [
                "-d",
                "out",
                "-classpath",
                "WebContent/WEB-INF/lib/*",
                "${fileDirname}/**/*.java"
            ],
            "windows": {
                "args": [
                    "-d",
                    "out",
                    "-classpath",
                    "WebContent\\WEB-INF\\lib\\*",
                    "@sources.txt"
                ]
            },
            "problemMatcher": "$javac",
            "group": {
                "kind": "build",
                "isDefault": true
            }
        },
        {
            "label": "Build WAR",
            "type": "shell",
            "command": "jar",
            "args": [
                "-cvf",
                "dist/Dashboard.war",
                "-C",
                "out",
                ".",
                "-C",
                "WebContent",
                "."
            ],
            "group": {
                "kind": "build",
                "isDefault": false
            },
            "dependsOn": "Compile Java"
        }
    ]
}