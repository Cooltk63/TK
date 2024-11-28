dir /s /b src\com\tcs\*.java > sources.txt


{
    "label": "Compile Java",
    "type": "shell",
    "command": "javac",
    "args": [
        "-d",
        "out",
        "-classpath",
        "WebContent/WEB-INF/lib/*",
        "@sources.txt"
    ],
    "group": {
        "kind": "build",
        "isDefault": true
    },
    "problemMatcher": "$javac"
}



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
                "-sourcepath",
                "src",
                "src/com/tcs/**/*.java"
            ],
            "group": {
                "kind": "build",
                "isDefault": true
            },
            "problemMatcher": "$javac"
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




