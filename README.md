<settings>
    <proxies>
        <proxy>
            <id>proxy</id>
            <active>true</active>
            <protocol>http</protocol>
            <host>your.proxy.server.url</host>
            <port>your_proxy_port</port>
            <username>your_username</username>
            <password>your_password</password>
        </proxy>
        <proxy>
            <id>proxy-secure</id>
            <active>true</active>
            <protocol>https</protocol>
            <host>your.proxy.server.url</host>
            <port>your_proxy_port</port>
            <username>your_username</username>
            <password>your_password</password>
        </proxy>
    </proxies>
</settings>


{
    "http.proxy": "http://your.proxy.server.url:your_proxy_port",
    "http.proxyAuthorization": "Basic VjEwMTIyOTc6VHVzaEAyNDg1"
}