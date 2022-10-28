function setup_maven() {
  if [ -n "$HTTP_PROXY_HOST" -a -n "$HTTP_PROXY_PORT" ]; then
    xml="<proxy>\
         <id>genproxy</id>\
         <active>true</active>\
         <protocol>http</protocol>\
         <host>$HTTP_PROXY_HOST</host>\
         <port>$HTTP_PROXY_PORT</port>"
    if [ -n "$HTTP_PROXY_USERNAME" -a -n "$HTTP_PROXY_PASSWORD" ]; then
      xml="$xml\
         <username>$HTTP_PROXY_USERNAME</username>\
         <password>$HTTP_PROXY_PASSWORD</password>"
    fi
    if [ -n "$HTTP_PROXY_NONPROXYHOSTS" ]; then
      xml="$xml\
         <nonProxyHosts>$HTTP_PROXY_NONPROXYHOSTS</nonProxyHosts>"
    fi
  xml="$xml\
       </proxy>"
    sed -i "s|<!-- ### configured http proxy ### -->|$xml|" $HOME/.m2/settings.xml
  fi

  if [ -n "$MAVEN_MIRROR_URL" ]; then
    xml="    <mirror>\
      <id>mirror.default</id>\
      <url>$MAVEN_MIRROR_URL</url>\
      <mirrorOf>external:*</mirrorOf>\
    </mirror>"
    sed -i "s|<!-- ### configured mirrors ### -->|$xml|" $HOME/.m2/settings.xml
  fi

  if [ -f "${S2I_SOURCE_DIR}/configuration/settings.xml" ]; then
    maven_env_args="${maven_env_args} -s ${S2I_SOURCE_DIR}/configuration/settings.xml"
    echo "Using custom maven settings from ${S2I_SOURCE_DIR}/configuration/settings.xml"
  fi
}
function build_maven() {
    mvn clean package -DskipTests=true $maven_args
}
